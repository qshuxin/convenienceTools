# -*- coding:UTF-8 -*-
from urllib import request

import sys
from bs4 import BeautifulSoup

if __name__ == "__main__":
    download_url = 'http://www.ziroom.com/z/nl/z2.html?qwd=&p=2'
    head = {}
    head['User-Agent'] = 'Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19'
    download_req = request.Request(url = download_url, headers = head)
    download_response = request.urlopen(download_req)
    download_html = download_response.read().decode('utf-8','ignore')
    soup_texts = BeautifulSoup(download_html, 'lxml')
    texts = soup_texts.find_all( class_ = 'txt')
    prices = soup_texts.find_all( class_ = 'priceDetail')
    #print(texts)
    flag = 1
    for text in texts:
        if(flag == 1):
            flag = flag + 1
            continue

        soup_text = BeautifulSoup(str(text), 'lxml')
        line = soup_text.div.text.replace('\xa0', '')
        line_text = BeautifulSoup(str(line), 'lxml')
        soup_price = BeautifulSoup(str(prices[flag - 2]), 'lxml')
        money = soup_price.div.text.replace('\xa0', '')
        money_test = BeautifulSoup(str(money), 'lxml')
        print(line_text.text.replace(' ', '') + money_test.text.replace(' ', ''))
        #print(text)
        print('---------------------------------------')
        flag = flag + 1
    #soup_text = BeautifulSoup(str(texts), 'lxml')
    #print(soup_text)
    #print(soup_text.div.text.replace('\xa0','\n'))
    #将\xa0无法解码的字符删除
    #print(soup_text.div.text.replace('\xa0',''))
