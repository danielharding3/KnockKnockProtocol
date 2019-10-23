package internetTechProgram1B;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	public void run() {
		

		
		try {
			
			//File file = new File("/Users/DanHarding/eclipse-workspace/internetTechProgram1B/src/internetTechProgram1B/prog1b.log");
			File file = new File("src/internetTechProgram1B/prog1b.log");
			boolean exists = file.exists();
			
			if (!exists) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			int portNum = 5520;
			ServerSocket servSock = new ServerSocket(portNum);
			//File file = new File("prog1b.log");
			//File file = new File("testFile.log");
			PrintWriter pw = new PrintWriter(new FileOutputStream(file), true);
			
			while (true) {
				Socket sock = servSock.accept();
				ServerThread servThread = new ServerThread(sock, pw);
				servThread.start();
			}

		} catch (IOException e) {
//			System.out.println("Error: " + e);
			e.printStackTrace();
		}
		
	}
	

	
	
	
	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.run();
		} catch (Exception e) {
			System.out.println("Error: " + e);
			e.printStackTrace();
		}
	}
}
