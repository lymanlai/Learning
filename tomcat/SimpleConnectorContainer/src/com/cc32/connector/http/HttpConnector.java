package com.cc32.connector.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpConnector implements Runnable{
	
	boolean stopped;
	private final String scheme = "http";
	private final String host = "127.0.0.1";
	
	public String getScheme(){
		return scheme;
	}
	
	public void run(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try{
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName(host));
		}
		catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		while(!stopped){
			// Accept the next incoming connection from the server socket
			Socket socket = null;
			try{
				socket = serverSocket.accept();
			}
			catch (Exception e){
				continue;
			}
			// Hand this socket off to an HttpProcessor
			HttpProcessor processor = new HttpProcessor(this);
			processor.process(socket);
		}
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
}
