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

	CROW_ROUTE(app, "/manga/getMangas").methods("POST"_method)([](const crow::request& req) {

		});


	app.port(port).multithreaded().run();

	

}

void initaliseDB(sqlite3pp::database* db) {
	
}