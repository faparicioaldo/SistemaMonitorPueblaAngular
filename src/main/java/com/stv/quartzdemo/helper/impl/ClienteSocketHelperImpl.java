package com.stv.quartzdemo.helper.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.stv.quartzdemo.config.GlobanSession;
import com.stv.quartzdemo.helper.ClienteSocketHelper;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class ClienteSocketHelperImpl implements ClienteSocketHelper{

	@Autowired
	private GlobanSession session;

	@Value("${socket.server.gobierno.ip}")
	private String ipSocket;

	@Value("${socket.server.gobierno.port}")
	private Integer portSocket;

	@Value("${socket.server.dummy.start}")
	private boolean test;

	@Value("${socket.server.dummy.start.ip}")
	private String ipSocketTest;

	@Value("${socket.server.dummy.start.port}")
	private Integer portSocketTest;

	@Override
	public String socket1(String cadenaPeticion) {

		Socket sc = null;
		String resultado = "";
		int intentosLeerSocket = 5;

		InputStream in =null;
		PrintStream out = null;		

		try {
			log.info("#######################################################");
			log.info("InputStream and PrintStream");
			if (test) {
				sc = new Socket(ipSocketTest, portSocketTest);
				log.info("#######################################################");
				log.info("(1) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocketTest + " : " + portSocketTest);
				log.info("#######################################################");
			} else {
				sc = new Socket(ipSocket, portSocket);
				log.info("#######################################################");
				log.info("(1) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocket + " : " + portSocket);
				log.info("#######################################################");
			}
		} catch (Exception e) {
			log.error("Error socket de gobierno no disponible ", e);
		}
		int numBytes=0;
		String cadenaEntrada = "";
		try {
			sc.setSoTimeout(10000);
			in = sc.getInputStream();
//			out = new PrintStream(sc.getOutputStream(), true);
			out = new PrintStream(sc.getOutputStream());
//			out.flush();

//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//			numBytes = in.available();

//			if(numBytes > 0) {
//				byte[] bytes = new byte[numBytes];
//				in.read(bytes);
//				cadenaEntrada = new String(bytes);
//				resultado  = resultado + cadenaEntrada;
//				System.err.println("Entrada inesperada, se ignorara para poder enviar datos alarma: " + cadenaEntrada);
//			}
			
			out.print(cadenaPeticion+"\r\n");
//			out.println(cadenaPeticion);
			out.flush();
						
			log.info("Esperando respuesta...");
			do {
				log.info("...");

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					log.warn("Error el thread sleep: " + e);
				}
				numBytes = in.available();

				// Generacion de respuesta
				if(numBytes > 0) {
					log.info("hay respuesta!!!");
					byte[] bytes = new byte[numBytes];
					in.read(bytes);
					cadenaEntrada = new String(bytes);
					log.info("cadenaEntrada: " + cadenaEntrada);
					resultado  = resultado + cadenaEntrada;
				}
				intentosLeerSocket--;
			}while(intentosLeerSocket > 0);

			log.info("CADENA RESPUESTA: " + resultado);

			if (resultado.trim().equals(""))
				session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			else
				session.setCadenaRespuesta(resultado);

			in.close();
			out.close();
			sc.close();
		} catch(SocketTimeoutException  e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Se supero el tiempo limite de respuesta", e);
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e1) {
				log.warn("Error el cerrar socket cliente ", e1);
			}

		} catch (IOException e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");

			log.error("Error IOException al enviar datos de alarma a gobierno: ", e);
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e1) {
				log.warn("Error el cerrar socket cliente ", e1);
			}
		}catch(Exception e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");

			log.error("Error no controlado al enviar datos de alarma a gobierno: ", e);
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e1) {
				log.warn("Error el cerrar socket cliente ", e1);
			}
		}
	
		return resultado;
	}

	@Override
	public String socket2(String cadenaPeticion) {
		Socket sc = null;
		String resultado = "";
		BufferedReader in = null;
		PrintStream out = null;

		try {
			log.info("#######################################################");
			log.info("BufferedReader and PrintStream");
			if (test) {
				sc = new Socket(ipSocketTest, portSocketTest);
				log.info("#######################################################");
				log.info("(2) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocketTest + " : " + portSocketTest);
				log.info("#######################################################");
			} else {
				sc = new Socket(ipSocket, portSocket);
				log.info("#######################################################");
				log.info("(2) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocket + " : " + portSocket);
				log.info("#######################################################");
			}
		} catch (Exception e) {
			log.error("Error socket de gonierno no disponible ", e);
		}

		try {
			sc.setSoTimeout(5000);
			in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			out = new PrintStream(sc.getOutputStream());
//			out.flush();

//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			
			out.println(cadenaPeticion);
			out.flush();
						
			log.info("Esperando respuesta...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.warn("Error el thread sleep: " + e);
			}
			resultado = in.readLine();
			
			log.info("CADENA RESPUESTA: " + resultado);

			if (resultado.trim().equals(""))
				session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			else
				session.setCadenaRespuesta(resultado);

		} catch(SocketTimeoutException  e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Se supero el tiempo limite de respuesta", e);
		} catch (IOException e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Error no controlado al enviar datos de alarma a gobierno: ", e);
		}finally {
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e) {
				log.warn("Error el cerrar socket cliente ", e);
			}
		}
		
		return resultado;
	}

	@Override
	public String socket3(String cadenaPeticion) {
		Socket sc = null;
		String resultado = "";
		DataInputStream in = null;
		DataOutputStream out = null;

		try {
			log.info("#######################################################");
			log.info("DataInputStream and DataOuputStream");
			if (test) {
				sc = new Socket(ipSocketTest, portSocketTest);
				log.info("#######################################################");
				log.info("(3) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocketTest + " : " + portSocketTest);
				log.info("#######################################################");
			} else {
				sc = new Socket(ipSocket, portSocket);
				log.info("#######################################################");
				log.info("(3) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocket + " : " + portSocket);
				log.info("#######################################################");
			}
		} catch (Exception e) {
			log.error("Error socket de gonierno no disponible ", e);
		}

		try {
			sc.setSoTimeout(5000);
			in = new DataInputStream(sc.getInputStream());
			out = new DataOutputStream(sc.getOutputStream());
//			out.flush();

//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			
//			out.writeUTF(cadenaPeticion+"\r\n");
			out.writeBytes(cadenaPeticion+"\r\n");
			out.flush();
						
			log.info("Esperando respuesta...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.warn("Error el thread sleep: " + e);
			}
			resultado = in.readLine();
			
			log.info("CADENA RESPUESTA: " + resultado);

			if (resultado.trim().equals(""))
				session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			else
				session.setCadenaRespuesta(resultado);

		} catch(SocketTimeoutException  e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Se supero el tiempo limite de respuesta", e);
		} catch (IOException e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Error no controlado al enviar datos de alarma a gobierno: ", e);
		}finally {
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e) {
				log.warn("Error el cerrar socket cliente ", e);
			}
		}
		
		return resultado;
	}

	@Override
	public String socket4(String cadenaPeticion) {

		Socket sc = null;
		String resultado = "";
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			log.info("#######################################################");
			log.info("BUFFERREADREADER AND PRINTWRITER");
			if (test) {
				sc = new Socket(ipSocketTest, portSocketTest);
				log.info("#######################################################");
				log.info("(4) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocketTest + " : " + portSocketTest);
				log.info("#######################################################");
			} else {
				sc = new Socket(ipSocket, portSocket);
				log.info("#######################################################");
				log.info("(4) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocket + " : " + portSocket);
				log.info("#######################################################");
			}
		} catch (Exception e) {
			log.error("Error socket de gonierno no disponible ", e);
		}

		try {
			sc.setSoTimeout(5000);
			in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			out = new PrintWriter(sc.getOutputStream());
//			out.flush();

//			try {
//				Thread.sleep(3000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
			
			out.println(cadenaPeticion);
			out.flush();
						
			log.info("Esperando respuesta...");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.warn("Error el thread sleep: " + e);
			}
			resultado = in.readLine();
			
			log.info("CADENA RESPUESTA: " + resultado);

			if (resultado.trim().equals(""))
				session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			else
				session.setCadenaRespuesta(resultado);

		} catch(SocketTimeoutException  e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Se supero el tiempo limite de respuesta", e);
		} catch (IOException e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Error no controlado al enviar datos de alarma a gobierno: ", e);
		}finally {
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e) {
				log.warn("Error el cerrar socket cliente ", e);
			}
		}
		
		return resultado;
	}

	
	public String socket5(String cadenaPeticion) {

		Socket sc = null;
		String resultado = "";
		int intentosLeerSocket = 5;

		DataInputStream in =null;
		PrintStream out = null;		

		try {
			log.info("#######################################################");
			log.info("DataInputStream and PrintStream");
			if (test) {
				sc = new Socket(ipSocketTest, portSocketTest);
				log.info("#######################################################");
				log.info("(1) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocketTest + " : " + portSocketTest);
				log.info("#######################################################");
			} else {
				sc = new Socket(ipSocket, portSocket);
				log.info("#######################################################");
				log.info("(1) INTENTANDO CONECTAR IP PUERTO SOCKET " + ipSocket + " : " + portSocket);
				log.info("#######################################################");
			}
		} catch (Exception e) {
			log.error("Error socket de gobierno no disponible ", e);
		}
		int numBytes=0;
		String cadenaEntrada = "";
		try {
			sc.setSoTimeout(10000);
			in = new DataInputStream(sc.getInputStream());
			out = new PrintStream(sc.getOutputStream());
//			out.flush();

//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
//			numBytes = in.available();

//			if(numBytes > 0) {
//				byte[] bytes = new byte[numBytes];
//				in.read(bytes);
//				cadenaEntrada = new String(bytes);
//				resultado  = resultado + cadenaEntrada;
//				System.err.println("Entrada inesperada, se ignorara para poder enviar datos alarma: " + cadenaEntrada);
//			}
			
			out.println(cadenaPeticion);
			out.flush();
						
			log.info("Esperando respuesta...");
			do {
				log.info("...");

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					log.warn("Error el thread sleep: " + e);
				}
				numBytes = in.available();

				// Generacion de respuesta
				if(numBytes > 0) {
					log.info("hay respuesta!!!");
					byte[] bytes = new byte[numBytes];
					in.read(bytes);
					cadenaEntrada = new String(bytes);
					log.info("cadenaEntrada: " + cadenaEntrada);
					resultado  = resultado + cadenaEntrada;
				}
				intentosLeerSocket--;
			}while(intentosLeerSocket > 0);

			log.info("CADENA RESPUESTA: " + resultado);

			if (resultado.trim().equals(""))
				session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			else
				session.setCadenaRespuesta(resultado);

			in.close();
			out.close();
			sc.close();
		} catch(SocketTimeoutException  e) {
			log.error("Se supero el tiempo limite de respuesta", e);
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e1) {
				log.warn("Error el cerrar socket cliente ", e1);
			}

		} catch (IOException e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Error IOException al enviar datos de alarma a gobierno: ", e);
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e1) {
				log.warn("Error el cerrar socket cliente ", e1);
			}
		}catch(Exception e) {
			session.setCadenaRespuesta("No se obtuvo respuesta de gobierno.");
			log.error("Error no controlado al enviar datos de alarma a gobierno: ", e);
			try {
				in.close();
				out.close();
				sc.close();
			} catch (IOException e1) {
				log.warn("Error el cerrar socket cliente ", e1);
			}
		}
	
		return resultado;
	}
}
