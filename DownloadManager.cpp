#include "DownloadManager.h"
DownloadManager::DownloadManager(bool *lock) {
	this->locker = lock;
}
void DownloadManager::runThread(){
	logger.log("Starting childOne");
	std::thread ch1(childOne);
	logger.log("All threads relating to download manager have been started");
}
//checks if there is a latest Update to all the mangas and downloads it
void DownloadManager::childOne() {
	while (this->locker) {



		std::this_thread::sleep_for(std::chrono::seconds(this->timeCheckInterval));
	}
}
