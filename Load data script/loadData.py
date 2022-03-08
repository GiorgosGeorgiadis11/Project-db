from globals import *

try:
    connection = mysql.connector.connect(host="localhost" , user="root",passwd="134679158Gew",allow_local_infile=True)
    print("Connected to localhost db")
    cursor = connection.cursor()
except Exception as e:
    print(e)
    sys.exit(1)

def createDatabase():
    cursor.execute("CREATE DATABASE IF NOT EXISTS "+dbName)
    cursor.execute("USE "+dbName+";")
    print("Database",dbName,"was succesfully created")

def createCountyTable():
    countries = pd.read_csv(folderName+"\Countries.csv",skiprows=0)
    columnName = countries.columns
    idQuery = "id INT AUTO_INCREMENT PRIMARY KEY"
    query = ""
    for q in columnName:
        q = q.replace(" ","_")
        query = query +q + " VARCHAR(40),"
    query = query + idQuery
    cursor.execute("CREATE TABLE IF NOT EXISTS Countries("+query+")")
    print("Table Countries was succesfully created")


def loadInTableCountries():
    cursor.execute("SET GLOBAL local_infile=1;")
    cursor.execute("LOAD DATA LOCAL INFILE '"+folderName+"""/Countries.csv' INTO TABLE Countries FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;""")
    connection.commit()
    print("Data Countries succesfully loaded")


def main():
    createDatabase()
    createCountyTable()
    loadInTableCountries()
main()