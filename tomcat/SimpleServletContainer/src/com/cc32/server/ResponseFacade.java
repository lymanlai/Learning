package com.cc32.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;

public class ResponseFacade implements ServletResponse{

	private ServletResponse response;
	public ResponseFacade(Response response){
		this.response = response;
	}
	
	@Override
	public void flushBuffer() throws IOException {
		response.flushBuffer();
	}

	@Override
	public int getBufferSize() {
		return response.getBufferSize();
	}

	@Override
	public String getCharacterEncoding() {
		return response.getCharacterEncoding();
	}

	@Override
	public Locale getLocale() {
		return response.getLocale();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return response.getOutputStream();
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return response.getWriter();
	}

	@Override
	public boolean isCommitted() {
		return response.isCommitted();
	}

	@Override
	public void reset() {
		response.reset();
	}

	@Override
	public void resetBuffer() {
		response.resetBuffer();
	}

	@Override
	public void setBufferSize(int bufferSize) {
		response.setBufferSize(bufferSize);
	}

	@Override
	public void setContentLength(int contentLength) {
		response.setContentLength(contentLength);
	}

	@Override
	public void setContentType(String contentType) {
		response.setContentType(contentType);
	}

	@Override
	public void setLocale(Locale locale) {
		response.setLocale(locale);
	}

}
