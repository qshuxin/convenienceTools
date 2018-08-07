#!/usr/bin/env python
# -*- coding:UTF-8 -*-
from urllib import request

if __name__ == "__main__":
    file = open('a.txt', 'w', encoding='utf-8')
    download_url = 'http://l-corehr.ops.beta.cn0.qunar.com:10089/api/employees/?require=tree&setid=FINCE$QUNAR'
    download_req = request.Request(url=download_url)
    download_response = request.urlopen(download_req)
    download_html = download_response.read().decode('utf-8', 'ignore')
    file.write(download_html)
    file.write('\n')
    # print(child)
# print('----------------------------------------')
