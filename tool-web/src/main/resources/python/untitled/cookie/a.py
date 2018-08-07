# # -*- coding:UTF-8 -*-
# from selenium import webdriver
#
# if __name__ == '__main__':
#     url = 'http://pythonscraping.com'
#     driver = webdriver.PhantomJS(executable_path='/home/shuxinqin/PycharmProjects/phantomjs-2.1.1-linux-x86_64/bin/phantomjs')
#     driver.get(url)
#     driver.implicitly_wait(1)
#     print(driver.get_cookies())

from selenium import webdriver
from selenium.webdriver.chrome.options import Options

chrome_options = Options()
chrome_options.add_argument('--headless')
chrome_options.add_argument('--disable-gpu')
driver = webdriver.Chrome(chrome_options=chrome_options)
driver.get("https://cnblogs.com/")
driver.implicitly_wait(1)
print(driver.get_cookies())

