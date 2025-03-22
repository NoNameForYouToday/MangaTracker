#pragma once
#include <crow.h>
#include "libs/sqlite3pp.h";
#include "util/Logger.h";
#include "MangaDex.h"

void initaliseSite(int argc,char* argv[]);
void initaliseDB(sqlite3pp::database*);