#pragma once
#include <chrono>
#include <thread>
#include "libs/sqlite3pp.h";
#include "libs/cmdParser.h"
#include "util/Logger.h";
#include "MangaDex.h"

struct manga {
	std::string id,title,lang;
	float highestVolume,highestChapter;
};

class DownloadManager{
public:	
	DownloadManager(bool* lock);
	void runThread();
	void childOne();
	
	

private:
	const int timeCheckInterval = 5; //5 min
	bool* locker;
	Logger logger;
};

