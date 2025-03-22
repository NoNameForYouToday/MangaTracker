#include "Website.h"

void initaliseSite(int argc,char* argv[]) {
	Logger logger;
	CmdParser parser(false);

	const std::string portIdentifier{ "-p" };
	parser.addOption(portIdentifier, "The port the webgui binds to", false, true);
	parser.passArguments(argc, argv);


	int port = 7802;

	if (parser.doesArgExist(portIdentifier)) {
		port = std::stoi(parser.getArgument(portIdentifier));
	}

	crow::SimpleApp app;
	crow::mustache::set_global_base("website");

	CROW_ROUTE(app, "/")([]() {
		crow::response res;
		res.set_static_file_info("website/index.html");
		return res;
		});
	CROW_ROUTE(app, "/addManga")([]() {
		crow::response res;
		res.set_static_file_info("website/pages/addManga.html");
		return res;
	});
	
	sqlite3pp::database db("manga.db");
	db.execute("CREATE TABLE IF NOT EXISTS mangas (id INTEGER PRIMARY KEY, mangaID TEXT, title TEXT, Highest_Volume REAL,Highest_Chapter REAL);");

	CROW_ROUTE(app, "/api/getMangas")([&db](const crow::request& req) {
		sqlite3pp::query qry(db, "SELECT * FROM mangas");
		int index{ 0 };
		crow::json::wvalue toReturn;

		for (auto row : qry) {
			int id;


			crow::json::wvalue item;
			std::string mangaID, mangaTitle;
			float h_Volume, h_Chapter;

			row.getter() >> id >> mangaID >> mangaTitle >> h_Volume >> h_Chapter;


			item["id"] = id;
			item["mangaID"] = mangaID;
			item["title"] = mangaTitle;
			item["h_Volume"] = h_Volume;
			item["h_Chapter"] = h_Chapter;

			toReturn[index++] = std::move(item);
			
		}
		return toReturn;
	});


	app.port(port).multithreaded().run();

	

}

void initaliseDB(sqlite3pp::database* db) {
	
}