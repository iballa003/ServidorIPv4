/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package servidoripv4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alumno
 */
public class ServidorIPv4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int port = 12341;
        boolean exit = false;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor para cálculo de direcciones IPv4 iniciado en el puerto "+port+".");
            while(true){
                //Aceptar una conexión
                Socket client = server.accept();
                System.out.println("Cliente conectado: "+client.getInetAddress());
                //Leer y responde al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(),true);
                while(exit == false){
                    output.println("Mándame la primera dirección Ip");
                    String firstIp = input.readLine();
                    System.out.println("Se ha recibido como primera dirección IP "+firstIp);
                    if(firstIp.equals("0.0.0.0/0")){
                        exit = true;
                        break;
                    }else{
                        output.println("Mándame la segunda dirección Ip");
                        String secondIp = input.readLine();
                        System.out.println("Se ha recibido como segunda dirección IP "+secondIp);
                        IPv4Address dir1 = new IPv4Address(firstIp.split("/")[0], firstIp.split("/")[1]);
                        IPv4Address dir2 = new IPv4Address(secondIp.split("/")[0], secondIp.split("/")[1]);

                        if(dir1.getDecimalNetwork().equals(dir2.getDecimalNetwork())){
                            System.out.println("Las dos direcciones pertenecen a la misma red");
                            output.println("Las dos direcciones pertenecen a la misma red");
                        }else{
                            System.out.println("Las dos direcciones NO pertenecen a la misma red");
                            output.println("Las dos direcciones NO pertenecen a la misma red");
                        }
                    }
                    
                    
                }

                client.close();
                System.out.println("La conexión se cerró");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
