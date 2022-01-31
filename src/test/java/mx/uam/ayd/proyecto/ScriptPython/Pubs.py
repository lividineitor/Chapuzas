from selenium import webdriver
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By
from selenium.common.exceptions import TimeoutException

browser = webdriver.Chrome('C:\chromedriver\chromedriver')  # Optional argument, if not specified will search path.
browser.get('https://www.facebook.com/')
browser.maximize_window()

timeout = 3

try:
    myElem = WebDriverWait(browser, timeout).until(EC.presence_of_element_located((By.NAME, 'login')))
    elem = browser.find_element_by_xpath('/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button')
    elem.click()
except TimeoutException:
    print("Se vencio el timeout")