import pandas as pd
import os
import mysql.connector
import sys
from glob import glob
from venv import create
import time


folderName = "ReadyForLoad" #ready csv folder name
dbName = "Project" #database name