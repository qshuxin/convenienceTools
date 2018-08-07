# -*- coding:UTF-8 -*-
from urllib import request

import sys

import time
from bs4 import BeautifulSoup

if __name__ == "__main__":
    file = open('自如房源.txt', 'w', encoding='utf-8')
    for i in range(1,50):
        time.sleep(10)
        download_url = 'http://www.ziroom.com/z/nl/z2.html?qwd=&p='+str(i)
        head = {}
        head['User-Agent'] = 'Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19'
        download_req = request.Request(url = download_url, headers = head)
        download_response = request.urlopen(download_req)
        download_html = download_response.read().decode('utf-8','ignore')
        soup_texts = BeautifulSoup(download_html, 'lxml')
        texts = soup_texts.find_all(id = 'houseList')
        lis = BeautifulSoup(str(texts), 'lxml')
        #print(lis)
        flag = 0

        for child in lis.ul.children:
            flag = flag + 1
            if (flag % 2 == 1):
                continue
            home1 = child.text.replace('\xa0', '')
            home2 = BeautifulSoup(str(home1), 'lxml')
            home3 = home2.text.replace(' ', '')
            home4 = BeautifulSoup(str(home3), 'lxml')
            home5 = home4.text.replace('\n', ' ')
            print(home5)
            file.write(home5)
            file.write('\n')
            #print(child)
            #print('----------------------------------------')
