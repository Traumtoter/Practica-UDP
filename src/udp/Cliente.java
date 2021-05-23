/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Scanner;
/**
 *
 * @author legion
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        final int PUERTO_SERVIDOR = 5000;
        byte[] buffer = new byte[1024];
        try{
             InetAddress direccionServidor = InetAddress.getByName("localhost");
             DatagramSocket socketUDP = new DatagramSocket();
             Scanner teclado = new Scanner(System.in);
             String mensaje;
             System.out.println("INTRODUZCA UN MSJ PARA EL SERVIDOR (EXIT PARA SALIR)");
             mensaje = teclado.nextLine();
             buffer = mensaje.getBytes();
             if(mensaje.isEmpty() || mensaje == null){
                 System.out.println("Proceso terminado, MENSAJE VACIO.\n");
                 socketUDP.close();
                 //break;
             }
             DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
             System.out.println("El mensaje se esta enviando al servidor");
             socketUDP.send(pregunta);
             DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
             socketUDP.receive(peticion);
           System.out.println("Mensaje recibido de SERVIDOR");
            mensaje = new String(peticion.getData());
            System.out.println(mensaje);
            socketUDP.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
