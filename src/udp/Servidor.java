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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.io.*;
import java.util.InputMismatchException;
/**
 *
 * @author legion
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int PUERTO = 5000;
        
        byte[] buffer = new byte[1024];
        try{
           System.out.println("servidor conectado");
           InetAddress direccionCliente = InetAddress.getByName("Direccion del cliente.");
           DatagramSocket socketUDP = new DatagramSocket(PUERTO);
           //Ciclo para que siempre este recibiendo peticiones.
           //while(true){
               System.out.println("Servicio infinito");
               int time = 10;
                  DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
               socketUDP.receive(peticion);
                 System.out.println("RECIBIENDO LA INFORMACION DESDE EL CLIENTE");
                 String mensaje = new String(peticion.getData());
                                    
                 System.out.println(mensaje);
                int puertoCliente = peticion.getPort();
                InetAddress direccion = peticion.getAddress();
                //mensaje = "¡Hola CLIENTE, soy el servidor!";
                buffer = mensaje.getBytes();
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
                 System.out.println("La informacion se le ha enviado al cliente");
                socketUDP.send(respuesta);
           //}
     
        } catch (SocketException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
