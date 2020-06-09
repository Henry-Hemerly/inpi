LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/data.txt'
REPLACE INTO TABLE marcasofficial
LINES
    TERMINATED BY '\n' (marcasofficialnames);