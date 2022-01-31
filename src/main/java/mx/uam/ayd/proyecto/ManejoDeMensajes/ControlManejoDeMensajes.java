package mx.uam.ayd.proyecto.ManejoDeMensajes;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

@Component
public class ControlManejoDeMensajes {
	
	public void MuestraMensajeExito(String mensaje){
    	JOptionPane.showMessageDialog(null , mensaje, "Exito!", JOptionPane.DEFAULT_OPTION);
    }
    
	public void MuestraMensajeInformativo(String mensaje)
	{
		JOptionPane.showMessageDialog(null, mensaje, "Info" , JOptionPane.INFORMATION_MESSAGE);
	}
	public void MuestraMensajeErrorBorrar(String mensaje) {
    	JOptionPane.showMessageDialog(null , mensaje, "Error 1", JOptionPane.ERROR_MESSAGE);
    }
	
    public void MuestraMensajeErrorVacio(String mensaje) {
    	JOptionPane.showMessageDialog(null , mensaje, "Error 2", JOptionPane.ERROR_MESSAGE);
    }
    
    public void MuestraMensajeErrorCampo(String mensaje) {
    	JOptionPane.showMessageDialog(null , mensaje, "Error 3", JOptionPane.ERROR_MESSAGE);
    }
    
    public void MuestraMensajeErrorRepetido(String mensaje) {
    	JOptionPane.showMessageDialog(null , mensaje, "Error 4", JOptionPane.ERROR_MESSAGE);
    }
    
    public void MuestraMensajeErrorInfraccion(String mensaje) {
    	JOptionPane.showMessageDialog(null , mensaje, "Error 5", JOptionPane.ERROR_MESSAGE);
    }
    
    public void MuestraMensajeErrorInexistencia(String mensaje)
    {
    	JOptionPane.showMessageDialog(null , mensaje, "Error 6", JOptionPane.WARNING_MESSAGE);
    }
}
