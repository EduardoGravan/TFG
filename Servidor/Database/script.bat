call del DB.db
call sqlite3 DB.db ".read createTables.sql"
call sqlite3 DB.db ".read populateTables.sql"
