from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
import pandas
#import psycopg2
import sqlite3 as db
from sqlite3 import Error

con = db.connect("nonprofits.db")
c = con.cursor()
c.execute("DROP TABLE organizations")
c.execute("CREATE TABLE organizations (ein, name, return_type, city, state, tRev, tGiven, tAsset)")




data = pandas.read_csv('IRS nonprofit.csv')
org_dict = data.to_dict('records')

result_list = []




driver = webdriver.Chrome()

for org in org_dict:
 form = org.get('return_type')
 if form == '990T':
    continue
 else:
  driver.get('https://beta.candid.org/search?keyword=843468403')
  sleep(1)
  try:
   location = driver.find_element(By.XPATH, '/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[1]/p').text
   city = location[:-4]
   state = location[-3:]
   state = state.replace(' ', '')
   total_rev = driver.find_element(By.XPATH, '/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div[1]/div[1]/div/div[2]/p').text
   total_give = driver.find_element(By.XPATH, '/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div[1]/div[2]/div/div[2]/p').text
   total_asset = driver.find_element(By.XPATH, '/html/body/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div[1]/div[3]/div/div[2]/p').text
  except:
     pass
  result_list.append((str(org.get('EIN')), str(org.get('TAXPAYER_NAME')), str(org.get('return_type')), str(city), str(state), str(total_rev), str(total_give), str(total_asset)))
  ein = org.get('EIN')
  name = org.get('TAXPAYER_NAME')
  form = org.get('return_type')
  #c.execute(f"INSERT INTO organizations VALUE ('{ein}', '{name}', '{form}', '{city}', '{state}', '{total_rev}', '{total_give}', '{total_asset}')")

c.executemany("insert into organizations(ein, name, return_type, city, state, tRev, tGiven, tAsset) values (?, ?, ?, ?, ?, ?, ?, ?)", result_list)
con.commit()

con.close()


 
    
    





