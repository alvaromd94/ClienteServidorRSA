package es.studium.PSPPracticaRSAClienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class ClienteRSA  {
	static final String HOST = "localhost";
	static final int PUERTO = 6000;
	public static void main(String[] arg) {
		try {
			System.out.println("Iniciando programa cliente..");
			Socket cliente = new Socket(HOST, PUERTO);
			//creo el flujo de salida al servidor
			String str = "Este es el texto a cifrar";
			DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

			//Envío un saludo al servidor
			//Generamos un par de claves
			//Admite claves de 512, 1024, 2048 y 4096 bits
			RSA rsa = new RSA();

			rsa.openFromDiskPrivateKey("rsa.pri");	
			rsa.openFromDiskPublicKey("rsa.pub");


//			InputStream entrada = cliente.getInputStream();
//			String file_private = "rsa.pri";
//			String file_public = "rsa.pri";

			//Las guardamos asi podemos usarlas despues
			//a lo largo del tiempo
//			rsa.saveToDiskPrivateKey("rsa.pri");
//			rsa.saveToDiskPublicKey("rsa.pri");

			String secure = rsa.Encrypt(str);
			System.out.println("Mensaje encriptado: " + secure);
			System.out.println("Mensaje desencriptado: " + str);
			flujoSalida.writeUTF(secure);
			
//			DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
			//cerramos streams y sockets
			flujoSalida.close();
//			entrada.close(); 
//			flujoEntrada.close(); 
			cliente.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}