from selenium import webdriver

options = webdriver.ChromeOptions()
options.add_argument('user-agent="Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19"')
driver = webdriver.Chrome(chrome_options = options)
driver.get('https://wenku.baidu.com/view/aa31a84bcf84b9d528ea7a2c.html')
morepage = driver.find_elements_by_xpath("//div[@class='pagerwg-button']")
driver.execute_script('arguments[0].scrollIntoView();', morepage[-1]) #拖动到可见的元素去
morepage.click()
page = driver.find_elements_by_xpath("//div[@class='page']")
driver.execute_script('arguments[0].scrollIntoView();', page[-1]) #拖动到可见的元素去
nextpage = driver.find_element_by_xpath("//a[@data-fun='next']")
nextpage.click()