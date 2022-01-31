import time
import os
from selenium import webdriver
import pyautogui

"""
Preubas de publcacion a instagram
"""

usuario =""#str(input('Usuario de facebok--> '))
passwd = "" #input('Ingresa el password de tu facebook--> ')
txtPost="hola instagram desde selenium"
img=r'ubicacionImagen'
driver = webdriver.Chrome('ubicacion del webdriver')  # Optional argument, if not specified will search path.
driver.get('https://www.instagram.com/')
driver.maximize_window()

time.sleep(5) # Let the user actually see something!

txtUsuario= driver.find_element_by_name('username')
txtUsuario.send_keys(usuario)

txtContrasenia = driver.find_element_by_name('password')
txtContrasenia.send_keys(passwd)

txtContrasenia.submit()

time.sleep(2)
pyautogui.press('enter') 
#driver.refresh()
time.sleep(5)
formularioPublicacion= driver.find_element_by_xpath('/html/body/div[1]/section/nav/div[2]/div/div/div[3]/div/div[3]/div/button').click()


time.sleep(2)
btnImagenVideo= driver.find_element_by_xpath('/html/body/div[8]/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/button').click()

time.sleep(2)
pyautogui.write(img) 
time.sleep(2)
pyautogui.press('enter') 
time.sleep(2)
accionSubirFoto=driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button').click()
time.sleep(1)
accionSaltafiltro=driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button').click()
time.sleep(3) 
textArea= driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/textarea')
textArea.send_keys(txtPost)
time.sleep(2)

btnPublicar = driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button').click()

time.sleep(15) # Let the user actually see something!

driver.quit()
