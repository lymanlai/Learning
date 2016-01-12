package com.cc32.server;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	/** WEB_ROOT is the directory where out HTML and other files reside.
	 *  For this package, WEB_ROOT is the "webroot" directory under the 
	 *  working directory.
	 *  The working directory is the location in the file system.
	 * 	from where the java command was invoked.
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
	
	// shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	// the shutdown command received
	private boolean shutdown = false;
	
	private static final String WEB_ROOT_IP = "127.0.0.1";
	
	public static void main(String[] args){
		System.out.println("WEB_ROOT:" + WEB_ROOT);
		HttpServer server = new HttpServer();
		
		server.await();
	}
	
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName(WEB_ROOT_IP));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// Loop waiting for a request
		while (!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				// create Request object and parse
				Request request = new Request(input);
				request.parse();
				
				// create Response object
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				
				// close the socket
				socket.close();
				
				// checkt if the previous URL is a shutdown command
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
