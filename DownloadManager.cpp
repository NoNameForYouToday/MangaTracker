#include "DownloadManager.h"


DownloadManager::DownloadManager(bool *lock) {
	this->locker = lock;
}

//checks if there is a latest Update to all the mangas and downloads it
void DownloadManager::childOne() {
	sqlite3pp::database db("manga.db");
	while (this->locker) {
		logger.log("checking");
		try {
			sqlite3pp::query qry(db, "SELECT * FROM mangas");
			logger.log("parsing rows");
			for (auto row : qry) {
				int id;
				std::string mangaID, mangaTitle, desiredLanuage, outDir, state;
				float h_Volume, h_Chapter;
				row.getter() >> id >> mangaID >> mangaTitle >> desiredLanuage >> h_Volume >> h_Chapter >> outDir >> state;
				//parser ignores first argument so a filler value is added

				char mangaID_buf[256];
				char outDir_buf[256];

				strncpy(mangaID_buf, mangaID.c_str(), sizeof(mangaID_buf));
				strncpy(outDir_buf, outDir.c_str(), sizeof(outDir_buf));

				char* args[] = {
					(char*)"filler",
					(char*)"-i", mangaID_buf,
					(char*)"-o", outDir_buf,
					(char*)"-m", (char*)"volume"
				};

				logger.log(mangaID);
				int size = sizeof(args) / sizeof(args[0]);

				//Cmdparser initalisation
				CmdParser parser(false);
				MangaDex dex(&parser,&logger,size,args);


			

				//checkLatest
				std::vector<float> vals = dex.getHighestChapterAndVolume();
				
				//checks if there is any new updates
				if (vals.at(0) > h_Volume || vals.at(1) > h_Chapter) {
					logger.log("Found a new release! installing");
					sqlite3pp::command cmd(db, "UPDATE mangas SET highest_Volume = ?, highest_Chapter = ? WHERE mangaID = ?");
					cmd.binder() << vals.at(0) << vals.at(1) << mangaID;
					cmd.execute();
					dex.writeMangaToDisk();
				}
			}
		}
		catch (...) {
			logger.log("Encountered a error skipping check");
		}

		std::this_thread::sleep_for(std::chrono::seconds(this->timeCheckInterval));
	}
}
void DownloadManager::runThread() {
	logger.log("Starting childOne");
	std::thread ch1([this] {this->childOne(); });
	ch1.detach();
	logger.log("All threads relating to download manager have been started");
}