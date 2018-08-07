# -*- coding: UTF-8 -*-
from urllib import request

if __name__ == "__main__":
    a = 32*31*30*29*28*27*32
    print(a)
    response = request.urlopen("http://fanyi.baidu.com")
    html = response.read()
    print(html)