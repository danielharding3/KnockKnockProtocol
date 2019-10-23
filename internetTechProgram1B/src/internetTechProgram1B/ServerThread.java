package internetTechProgram1B;

import java.io.*;
import java.net.*;
import java.util.*;

class ServerThread extends Thread {

	Socket sock;
	PrintWriter writeSock;
	PrintWriter logWriter;
	BufferedReader readSock;
	
	public ServerThread(Socket clientSock, PrintWriter logfile) {
		try {
			sock = clientSock;
			//logWriter = logfile;
			
			writeSock = new PrintWriter(sock.getOutputStream(), true);
			readSock = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			logWriter = new PrintWriter(logfile, true);
		} catch (Exception e) {
			//System.out.println("Error: " + e);
			logWriter.println(e);
		}
	}
	
	public void run() {
		try {
			
			boolean quitTime = false;
			Date date = new Date();
			logWriter.println("Date: " + date.toString());
			logWriter.println("IP Address: " + sock.getInetAddress());
			logWriter.println("Port Number: " + sock.getPort());
			
			while (!quitTime) {
					
					String inLine = readSock.readLine();
					//if (inLine.equals("null")) inLine = "quit";
					logWriter.println("Client: " + inLine);

					
					if (inLine.equals("quit")) {
						quitTime = true;
						String outLine = "Good Bye!";
						writeSock.println(outLine);
						logWriter.println("Server: " + outLine);
						logWriter.println("Connection on port " + sock.getPort() + " has been closed.\n");
						System.out.println("Connection closed.");
						sock.close();
						return;
					}

					PolyAlphabet poly = new PolyAlphabet();
					//String outLine = inLine + "HaHa!";
					String outLine = poly.encrypt(inLine);
					writeSock.println(outLine);
					logWriter.println("Server: " + outLine);
				}
			
//			logWriter.println("Connection on port " + sock.getPort() + " has been closed.");
//			System.out.println("Conenction on port " + sock.getPort() + " has been closed.");
//			sock.close();
//			return;
			
		} catch (IOException e) {
			//System.out.println("Error: " + e);
			e.printStackTrace();
			logWriter.println(e);
		}
	}
}
