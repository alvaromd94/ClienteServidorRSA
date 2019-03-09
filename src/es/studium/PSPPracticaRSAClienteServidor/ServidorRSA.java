package es.studium.PSPPracticaRSAClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorRSA  {
	static final String HOST = "localhost";
	static final int PUERTO = 6000;
	public static void main(String[] arg) {
		try {
			ServerSocket servidor = new ServerSocket(PUERTO);
			Socket cliente=null;
			System.out.println("Esperando al cliente...");
			cliente = servidor.accept(); // Crea objeto
			//Flujo de entrada del cliente
			InputStream entrada = cliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(entrada);
			//			OutputStream salida = cliente.getOutputStream(); 

			RSA rsa = new RSA();
//			rsa.genKeyPair(512);
//			rsa.saveToDiskPrivateKey("rsa.pri");
//			rsa.saveToDiskPublicKey("rsa.pub");

			rsa.openFromDiskPrivateKey("rsa.pri");	
			rsa.openFromDiskPublicKey("rsa.pub");

			//			OutputStream salida = cliente.getOutputStream();
			//			DataOutputStream flujoSalida = new DataOutputStream(salida);

			//El cliente me envia un mensaje
			String encriptado = flujoEntrada.readUTF();
			String desencriptado= rsa.Decrypt(encriptado);
			System.out.println("Texto encriptado: \n \t"+ encriptado);
			System.out.println("Recibiendo del CLIENTE: \n \t"+ desencriptado);
			//Flujo de salida
			//cerramos streams y sockets
			entrada.close();
			flujoEntrada.close();
			//			salida.close(); 
			cliente.close();
			servidor.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}