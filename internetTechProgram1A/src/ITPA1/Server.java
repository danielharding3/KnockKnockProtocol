package ITPA1;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	public void run() {
		try {
			int portNum = 5520;
			ServerSocket sSock = new ServerSocket(portNum);
			
			
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
	}
	

	
	
	
	public static void main(String[] args) {
			Server server = new Server();
			server.run();
	}
}
