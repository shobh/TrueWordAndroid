# -*- coding: utf-8 -*-
"""
Created on Sun Dec 23 12:02:47 2018

@author: ASUS
"""
import json
import requests
from bs4 import BeautifulSoup

name = ""
website = ""

page = requests.get(website + '/' + name + '/')

#file = open(name+ ".json","w") 

soup = BeautifulSoup(page.text, 'html.parser')

text1 = soup.findAll('blockquote')

data = []

count = 0
for idx,t2 in enumerate(text1):
    t4 = t2.p
    if(t4):
        item = {}
        t3 = t4.text.replace("Click to tweet", "").strip()
        print(t3)
        item["id"] = count
        item["author"] = "marcus aurelius"
        item["quote"] = t3
        count = count + 1
        data.append(item)
        if (count == 496):
            break

datar = {}
datar["array"] = data
datar["count"] = count     

with open(name+ ".json", "w") as writeJSON:
    json.dump(datar, writeJSON, ensure_ascii=False)









