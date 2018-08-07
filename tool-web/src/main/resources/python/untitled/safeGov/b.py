# -*- coding:UTF-8 -*-
from urllib import request

import sys

import time
from bs4 import BeautifulSoup

if __name__ == "__main__":
    #file = open('人民币汇率.txt', 'w', encoding='utf-8')
    download_url = 'http://www.safe.gov.cn/AppStructured/view/project_exportRMBExcel.action'
    head = {}
    head[
        'User-Agent'] = 'Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166  Safari/535.19'
    download_req = request.Request(url=download_url, headers=head)
    download_response = request.urlopen(download_req)
    download_html = download_response.read().decode('utf-8', 'ignore')
    soup_texts = BeautifulSoup(download_html, 'lxml')
    print(soup_texts)
    # texts = soup_texts.find_all(id='InfoTable')
    # lis = BeautifulSoup(str(texts), 'lxml')
    # # print(lis)
    # flag = 0
    #
    # for child in lis.table.children:
    #     flag = flag + 1
    #     if (flag % 2 == 1 or flag == 2):
    #         continue
    #     home1 = child.text.replace('\xa0', '').replace('\t', '').replace('\r\n', '').replace('\n',' ')
    #     print(home1)
    #     rate = str(home1).split(' ')
    #     print(rate)
    #     print('-----------------------------')
        # print(home5)
        #file.write(home5)
        #file.write('\n')
        # print(child)
        # print('----------------------------------------')
