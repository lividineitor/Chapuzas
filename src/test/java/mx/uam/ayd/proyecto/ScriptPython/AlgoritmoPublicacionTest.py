import unittest
import AlgorimoPublicacion
from selenium import webdriver
class TestPublicar(unittest.TestCase):

    def setUp(self):
        self.driver=webdriver.Chrome('C:\chromedriver\chromedriver') 
        self.instancia=AlgorimoPublicacion.Publicacion(self.driver)
    def test_obtenFileObjeto(self):
        #caso 1: se envia un archivo nullo y se regresa un false de carga
        self.assertFalse(self.instancia.obtenFileObjeto(None))
        #caso 2: se envia la ubicacion de un archivo no null y se regresa un true de carga
        self.assertTrue(self.instancia.obtenFileObjeto('Usr.sgf'))

    def test_debanadoArchivoUsr(self):
        #considereando el contenido del archivo Usr.sgf como:
        print(self.instancia.Contenido_File_Objeto)
        self.assertTrue(self.instancia.obtenFileObjeto('Usr.sgf'))
        #usuario:basnbna
        #password:fhjdkfkj
        #contenido: mi primer publicacion desde selenium
        #redSocial:abc 
        #multimedia:(C:\Users\leonh\OneDrive\Escritorio\salida.png,) 


        #probaremos que estos datos estan en los atributos mediante el llamado al metodo de debanado del archivo objeto Usr.sgf
        self.assertEquals(self.instancia.usuario,"basnbna")
        self.assertEquals(self.instancia.password,"fhjdkfkj")
        self.assertEquals(self.instancia.contenidoApublicar.strip(),"mi primer publicacion desde selenium")
        self.assertEquals(self.instancia.redSocial,"abc")
        self.assertEquals(self.instancia.multimedia,r"C:\Users\leonh\OneDrive\Escritorio\salida.png")

    def test_prePublica(self):
        #caso1 no coincide redsocial no facecbook ni instagram
        self.assertFalse(self.instancia.prePublica())

    def test_publicaFacebook(self):
        self.instancia.obtenFileObjeto('Usr.sgf')
        self.instancia.debanadoFileObjeto()
        self.instancia.redSocial="Facebook"
        #caso 1 usuario y/0 contraseña invalida
        self.assertFalse(self.instancia.publicaFacebook())
    
    def test_publicaInstagram(self):
        self.instancia.obtenFileObjeto('Usr.sgf')
        self.instancia.debanadoFileObjeto()
        self.instancia.redSocial="Instagram"
        #caso 1 usuario y/0 contraseña invalida
        #self.assertFalse(self.instancia.publicaInstagram())

if __name__ == '__main__':
    unittest.main()