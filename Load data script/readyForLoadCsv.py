from globals import *

def createFolder():
    isExist = os.path.exists(folderName)
    if not isExist: #check if folder exists
        os.makedirs(folderName) #if not then create a new folder


def createCountryCsv():
    Countries = glob("CountriesData\*")
    for i in range(0,len(Countries)):#take all the countries that exist in the folder
        CountryProfil = glob(Countries[i]+"\*.csv")[1] 
        data = pd.read_csv(CountryProfil, skiprows=0)#read metadata_country csv
        data = data.iloc[:,:-1] #drop this last column
        data = data.drop(columns=['SpecialNotes']) #drop SpecialNotes column
        if i == 0 :
            allCountries = data
        else:
            allCountries = allCountries.append(data, ignore_index=True)
    allCountries.to_csv(folderName+"\Countries.csv",index=False)


def main():
    createFolder()
    createCountryCsv()
main()