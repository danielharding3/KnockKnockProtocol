package ITPA1;

import java.io.*;
import java.net.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class KnockKnockProtocol extends JFrame {

	private JPanel contentPane;
	private JTextField ipAddressText;
	private JTextField portNumberText;
	private JTextField messageText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KnockKnockProtocol frame = new KnockKnockProtocol();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Socket sock = null;
	PrintWriter writeSock;
	BufferedReader readSock;

	/**
	 * Create the frame.
	 */
	public KnockKnockProtocol() {
		setResizable(false);
		
		setTitle("Program 1a Knock Knock Protocol");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblIpAddress = new JLabel("IP Address");
		
		ipAddressText = new JTextField();
		//ipAddressText.setText("constance.cs.rutgers.edu");
		ipAddressText.setText("127.0.0.1");
		ipAddressText.setColumns(10);
		
		JLabel lblPortNumber = new JLabel("Port Number");
		
		portNumberText = new JTextField();
		portNumberText.setText("5520");
		portNumberText.setColumns(10);
/////////////////////////////////////////////////////////////////////
		

		
//		try {
//			int portNum = Integer.parseInt(portNumberText.getText());
//			String hostAddress = ipAddressText.getText();
//		} catch (Exception ex) {
//			System.out.println("Error: " + ex);
//			sock = null;
//		}
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		JScrollPane scrollPane = new JScrollPane();
		JTextArea textArea = new JTextArea();
		
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		contentPane.setLayout(gl_contentPane);

		
		//connect button event handler
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (btnConnect.getText().equals("Connect")) {

					try {
						int portNum = Integer.parseInt(portNumberText.getText());
						String hostAddress = ipAddressText.getText();
						sock = new Socket(hostAddress, portNum);
						writeSock = new PrintWriter(sock.getOutputStream(), true);
						readSock = new BufferedReader(new InputStreamReader(sock.getInputStream()));
						btnConnect.setText("Disconnect");
						textArea.append("Connected to Server\n");
					} catch (Exception ex) {
						System.out.println("Error: " + ex);
						textArea.append("Error: " + ex + "\n");
						sock = null;
					}
					
			
				} else {
					
					try {
						readSock.close();
						writeSock.close();
						sock.close();
						sock = null;
						btnConnect.setText("Connect");
						textArea.append("Disconnected!\n");
					} catch (Exception ex) {
						System.out.println("Error: " + ex);
						textArea.append("Error: " + ex + "\n");
						sock = null;
					}
				}
				
			}
		});

		
		
		//btnConnect.addActionListener(new Action);
		
		JLabel lblMessageToServer = new JLabel("Message to Server");
		
		messageText = new JTextField();
		messageText.setColumns(10);
		
		JLabel lblClientserverCommunication = new JLabel("Client/Server Communication");
		

		
		
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if (sock != null) {
						String someTextToWrite = messageText.getText();
						writeSock.println(someTextToWrite);
						textArea.append("Client: " + someTextToWrite + "\n");
						
						String dataRead = readSock.readLine();
						System.out.println("Got from Socket: " + dataRead);
						textArea.append("Server: " + dataRead + "\n");
						
						if (dataRead.equals("Good Bye!")) {
							readSock.close();
							writeSock.close();
							sock.close();
							sock = null;
							btnConnect.setText("Connect");
							textArea.append("Disconnected!\n");
						}
					} else {
						textArea.append("No server connected! \n");
					}
				} catch (IOException ex) {
					System.out.println("Error: " + ex);
					textArea.append("Error: " + ex + "\n");
				}
					
			}
		});
		
		
		
		
		
		
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnSend)
					.addContainerGap(365, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblIpAddress)
									.addGap(25)
									.addComponent(ipAddressText, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPortNumber)
									.addGap(12)
									.addComponent(portNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(12)
									.addComponent(btnConnect)))
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblClientserverCommunication))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMessageToServer))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(messageText, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)))
					.addGap(99))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(9, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblIpAddress))
						.addComponent(ipAddressText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(5)
							.addComponent(lblPortNumber))
						.addComponent(portNumberText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConnect))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMessageToServer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(messageText, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSend)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblClientserverCommunication)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		

		

		
		
		
	}
}
