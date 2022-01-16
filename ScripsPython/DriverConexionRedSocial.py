import time

from selenium import webdriver

f = open("Usr.sgf", "r")
Contenido_GUI = f.read()
lineas=Contenido_GUI.split('\n')
x=0
usuario=str(Contenido_GUI[Contenido_GUI.find('usuario:')+len('usuario:'):Contenido_GUI.find('.com')+4]).strip()
passwd=str(Contenido_GUI[Contenido_GUI.find('password:')+len('password:'):]).strip()
redSocial = str(Contenido_GUI[Contenido_GUI.find('redSocial:')+len('redSocial:'):]).strip()

print(redSocial)

driver = webdriver.Chrome('C:\chromedriver')  #ejecucion del path

if redSocial=="Facebook":

    driver.get('https://www.facebook.com/');

    time.sleep(2) # un sleep para encontrar errores de conexion

    txtUsuario= driver.find_element_by_id('email')
    txtUsuario.send_keys(usuario)

    txtContrasenia = driver.find_element_by_id('pass')
    txtContrasenia.send_keys(passwd)
elif redSocial=="Instagram":
    driver.get('https://www.instagram.com/');

    time.sleep(2) # un sleep para encontrar errores de conexion

    txtUsuario= driver.find_element_by_name('username')
    txtUsuario.send_keys(usuario)

    txtContrasenia = driver.find_element_by_name('password')
    txtContrasenia.send_keys(passwd)

#txtContrasenia.submit()
