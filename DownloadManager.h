#pragma once
#include <chrono>
#include <thread>
#include "libs/sqlite3pp.h";
#include "util/Logger.h";
#include "MangaDex.h"

class DownloadManager
{
;
	DownloadManager(bool* lock);
	void runThread();
	void childOne();
	const int timeCheckInterval = 60*5; //5 min
	bool* locker;
	Logger logger;
};

