
import time
import os
from selenium import webdriver
import pyautogui

class Publicacion:
    #constructor para atributos principales
    def __init__(self,controlador):
        self.usuario=""
        self.password=""
        self.contenidoApublicar=""
        self.redSocial=""
        self.multimedia=[]
        self.Contenido_File_Objeto=""
        self.driver=controlador
        #creacion del archivo de estatus
        self.f_err=open("Usr."+"err","w")

    #metodo para la apertura del archivo generado por java
    def obtenFileObjeto(self,ubicacion):
        if ubicacion is None:
            return False
        f = open(ubicacion, "r")
        self.Contenido_File_Objeto = f.read()
        return True

    #metdodo que nos permite recurperar del archivo generado por java su contenido y separarlos para 
    #trabajar de manera mas sencilla
    def debanadoFileObjeto(self):
        self.usuario=str(self.Contenido_File_Objeto[self.Contenido_File_Objeto.find('usuario:')+len('usuario:'):self.Contenido_File_Objeto.find('password:')]).strip()
        self.password=str(self.Contenido_File_Objeto[self.Contenido_File_Objeto.find('password:')+len('password:'):self.Contenido_File_Objeto.find('contenido:')]).strip()
        self.redSocial = str(self.Contenido_File_Objeto[self.Contenido_File_Objeto.find('redSocial:')+len('redSocial:'):self.Contenido_File_Objeto.find('multimedia:')]).strip()
        self.contenidoApublicar=str(self.Contenido_File_Objeto[self.Contenido_File_Objeto.find('contenido:')+len('contenido:'):self.Contenido_File_Objeto.find('redSocial:')]).strip()
        multimedia=str(self.Contenido_File_Objeto[self.Contenido_File_Objeto.find('multimedia:')+len('multimedia:'):]).strip()
        files=""
        print("debanado "+multimedia)
        for x in range(len(multimedia)):
            if multimedia[x]=="(":
                continue
            elif multimedia[x]==")":
                break
            elif multimedia[x]==",":
                self.multimedia.append(files)
                files=""
            elif multimedia[x]=="\\":
                files+="/"
            else:
                files+=multimedia[x]

    #metodo que nos permite saber en que plataforma se publicara 
    def prePublica(self):
        if self.redSocial=="Facebook":
            self.publicaFacebook()
        elif self.redSocial=="Instagram":
            self.publicaInstagram()
        else:
            return False

    #metodo para publicar con facebook
    def publicaFacebook(self):
        self.driver.get('https://www.facebook.com/');
        self.driver.maximize_window()
        time.sleep(2) # un sleep para encontrar errores de conexion

        txtUsuario= self.driver.find_element_by_id('email')
        txtUsuario.send_keys(self.usuario)

        txtContrasenia = self.driver.find_element_by_id('pass')
        txtContrasenia.send_keys(self.password)
        txtContrasenia.submit()
        ##comprobacion de usuario y contraseña de red social facebook
        time.sleep(2)
        
        titulo=""+self.driver.title
        if titulo=="Iniciar sesión en Facebook" or titulo=="Facebook - Inicia sesión o regístrate":
            return False
        time.sleep(2)
        
        self.driver.get('https://www.facebook.com/')
        time.sleep(3)
        formularioPublicacion= self.driver.find_element_by_xpath('/html/body/div[1]/div[1]/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div[3]/div/div[2]/div/div/div/div[1]/div')
        formularioPublicacion.click()

        #campoTexto = driver.find_element_by_xpath("//*[@name='xhpc_message_text']")
        #campoTexto.send_keys(txtPost)
        #pyautogui.typewrite(txtPost)

        time.sleep(3)
        pyautogui.write(self.contenidoApublicar) 

        if len(self.multimedia)!=0:
            time.sleep(3) 
            btnImagenVideo= self.driver.find_element_by_xpath('/html/body/div[1]/div[1]/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[3]/div[1]/div[2]/div/div[1]').click()
            accionSubirFoto=self.driver.find_element_by_xpath('/html/body/div[1]/div[1]/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[2]/div[1]/div[2]/div/div[1]/div/div[1]').click()
            time.sleep(3)
            
            for y in range(len(self.multimedia)):
                pyautogui.write('"'+self.multimedia[y].replace('/','\\')+'"')
                time.sleep(1)
        
        pyautogui.press('enter')
        time.sleep(2)
        btnPublicar = self.driver.find_element_by_xpath('/html/body/div[1]/div[1]/div[1]/div/div[4]/div/div/div[1]/div/div[2]/div/div/div/form/div/div[1]/div/div/div/div[3]/div[2]/div/div').click()
        time.sleep(1)
        pyautogui.press('enter')
        time.sleep(2)
        self.driver.get('https://www.facebook.com/profile.php?')
        time.sleep(15)
        return True
    
    #metodod para publicar con instagram
    def publicaInstagram(self):
        self.driver.get('https://www.instagram.com/');
        self.driver.maximize_window()
        time.sleep(2)
        
        txtUsuario= self.driver.find_element_by_name('username')
        txtUsuario.send_keys(self.usuario)
        time.sleep(2) # un sleep para encontrar errores de conexion
        txtContrasenia = self.driver.find_element_by_name('password')
        txtContrasenia.send_keys(self.password)

        txtContrasenia.submit()

        time.sleep(2)
        #driver.get('https://www.instagram.com/')
        #time.sleep(1)
        pyautogui.press('enter') 
        #driver.refresh()
        time.sleep(5)
        formularioPublicacion= self.driver.find_element_by_xpath('/html/body/div[1]/section/nav/div[2]/div/div/div[3]/div/div[3]/div/button').click()

        #campoTexto = driver.find_element_by_xpath("//*[@name='xhpc_message_text']")
        #campoTexto.send_keys(txtPost)
        #pyautogui.typewrite(txtPost)

        time.sleep(2)
        btnImagenVideo= self.driver.find_element_by_xpath('/html/body/div[8]/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/button').click()


        time.sleep(2)
        for y in range(len(self.multimedia)):
            pyautogui.write('"'+self.multimedia[y].replace('/','\\')+'"')
            time.sleep(1)
        pyautogui.press('enter') 
        time.sleep(2)
        accionSubirFoto=self.driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button').click()
        time.sleep(1)
        accionSaltafiltro=self.driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button').click()
        time.sleep(3) 
        textArea= self.driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[2]/div[1]/textarea')
        textArea.send_keys(self.contenidoApublicar)
        time.sleep(2)

        btnPublicar = self.driver.find_element_by_xpath('/html/body/div[6]/div[2]/div/div/div/div[1]/div/div/div[2]/div/button').click()

        time.sleep(15) # Let the user actually see something!

        #driver.quit()
    
    
    
    #metodo para generar un reporte de error en la publicacion 
    #debido a que el usuario o contrasena de la cuenta de red social
    #ha sido cambiada y/o son incorrectos los datos
    def creaDocumentoEstatus(self,estatus):
        self.f_err.write(estatus)
        pass
    #metodo de ejecucion de metodos de la clase
    def ejecucion(self,fileObj):
        self.obtenFileObjeto(fileObj)
        self.debanadoFileObjeto()
        #print(self.Contenido_File_Objeto)
        print("Variables: \n usuario: {}\n, password: {}\n, redsocial: {}\n, contenido: {}\n, Multimedia: {} \n".format(self.usuario,self.password,self.redSocial,self.contenidoApublicar,self.multimedia))
        
        """for y in range(len(self.multimedia)):
            print(self.multimedia[y].replace('/','\\'))"""
        
        if self.prePublica():
            print("Publicado\n")
        else:
            print("credenciales invalidas o redsocial invalida\n")

def main():
    controlador=webdriver.Chrome('C:\chromedriver\chromedriver') 
    obj_algoritmo=Publicacion(controlador)
    obj_algoritmo.ejecucion('Usr.sgf')


main()