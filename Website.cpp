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
	db.execute("CREATE TABLE IF NOT EXISTS mangas (id INTEGER PRIMARY KEY, mangaID TEXT, title TEXT,lang TEXT, highest_Volume REAL,highest_Chapter REAL , outDir TEXT,state TEXT);");

	CROW_ROUTE(app, "/getMangas")([&db](const crow::request& req) {
		sqlite3pp::query qry(db, "SELECT * FROM mangas");
		int index{ 0 };
		crow::json::wvalue toReturn;

		for (auto row : qry) {
			int id;

			char* args;

			crow::json::wvalue item;
			std::string mangaID, mangaTitle,desiredLanuage , outDir, state;
			float h_Volume, h_Chapter;


			row.getter() >> id >> mangaID >> mangaTitle >> desiredLanuage >> h_Volume >> h_Chapter >> outDir>>state;


			item["id"] = id;
			item["mangaID"] = mangaID;
			item["title"] = mangaTitle;
			item["h_Volume"] = h_Volume;
			item["h_Chapter"] = h_Chapter;
			item["outDir"] = outDir;
			item["lang"] = desiredLanuage;
			item["state"] = state;
			toReturn[index++] = std::move(item);
			
		}
		return toReturn;
	});
	//adds the manga To The Database
	CROW_ROUTE(app, "/addManga").methods(crow::HTTPMethod::POST)([&db](const crow::request& req) {
		
		float highestVolume = 0.0f, highestChapter = 0.0f;

		std::string mangaID = "";
		std::string outDir = "";
		crow::multipart::message msg(req);

		std::vector<crow::multipart::part> parts = msg.parts;
		
		mangaID = parts.at(0).body;
		outDir = parts.at(1).body;


		if (mangaID.empty() || outDir.empty()) {
			CROW_LOG_ERROR << "Missing required fields";
			return crow::response(400, "Missing required fields");
		}

		sqlite3pp::command cmd(db, "INSERT INTO mangas (mangaID,lang,outDir,highest_Volume, highest_Chapter,state) VALUES (?, ?, ?, ?, ?,?)");
		 cmd.binder() << mangaID << "en" << outDir << highestVolume << highestChapter << "new";
		 cmd.execute();

		crow::response res;
		res.code = 302;
		res.set_header("Location", "/");

		return res;

	});
	CROW_ROUTE(app, "/delete")([&db](const crow::request& req) {
		std::string id = req.url_params.get("id");
		std::string query = "DELETE FROM mangas WHERE mangaID = '" + id + "';";
		db.execute(query.c_str());

		crow::response res;
		res.code = 302;
		res.set_header("Location", "/");
		return res;
	});
	app.port(port).multithreaded().run();

}
