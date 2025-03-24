#pragma once
#define CROW_STATIC_DIRECTORY "website/"
#define CROW_STATIC_ENDPOINT "/static/<path>"
#include <crow.h>
#include "sqlite3pp.h"
#include "Logger.h"
#include "MangaDex.h"


void initaliseSite(int argc,char* argv[]);
void initaliseDB(sqlite3pp::database*);