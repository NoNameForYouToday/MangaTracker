#define _CRT_SECURE_NO_WARNINGS

#include  <iostream>
#include "Website.h"
#include "DownloadManager.h";
int main(int argc,char* argv[]) {


	bool *isRunning = new bool(true);
	DownloadManager dmanager(isRunning);
	
	dmanager.runThread();

	initaliseSite(argc, argv);

	//When the site stops running so does the program
	isRunning = new bool(false);

	

}