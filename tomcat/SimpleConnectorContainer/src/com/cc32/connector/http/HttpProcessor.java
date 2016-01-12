package com.cc32.connector.http;

import java.net.Socket;

/* this class used to be called HttpServer */
public class HttpProcessor {

	public HttpProcessor(HttpConnector connector){
		this.connector = connector;
	}
	
	/**
	 * The HttpConnector with which this processor is associated.
	 */
	private HttpConnector connector = null;
	private HttpRequest request;
	private HttpRequestLine requestLine = new HttpRequestLine();
	private HttpResonse response;
	
	protected String method = null;
	protected String queryString = null;
	
	/**
	 * The string manager for this package.
	 */
	protected StringMangaer sm = StringManager.getManagner("com.cc32.connector.http");
	
	public void process(Socket socket){
		SocketInputStream input = null;
		OutputStream output = null;
		try{
			input = new SocketInputStream(socket,getInputStream(), 2048);
			output = socket.getOutputStream();
			
			// create HttpRequest object and parse
			request = new HttpRequest(input);
			
			// create HttpResponse object
			response = new HttpResponse(output);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
