package com.cc32.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class HttpResponse implements HttpServletResponse {

	// the default buffer size
	private static final int BUFFER_SIZE = 1024;

	HttpRequest request;
	OutputStream output;
	PrintWriter writer;
	protected byte[] buffer = new byte[BUFFER_SIZE];
	protected int bufferCount = 0;

	/**
	 * Has this response been committed yet?
	 */
	protected boolean committed = false;

	/**
	 * The actual number of bytes written to this Response.
	 */
	protected int contentCount = 0;
	
	/**
	 * The content length associated with this Response.
	 */
	protected int contentLength = -1;
	
	/**
	 * The content type associated with this Response.
	 */
	protected String contentType = null;
	
	/**
	 * The character encoding associated with this Response.
	 */
	protected String encoding = null;

	/**
	 * The set of Cookies associated with this Response.
	 */
	protected ArrayList cookies = new ArrayList();
	
	/**
	 * The HTTP headers explicitly added via addHeader(), but not including
	 * those to be added with setContentLength(), setContentType(), and so on.
	 * This collection is keyed by the header name, and the elements are 
	 * ArrayLists containing the associated values that have been set.
	 */
	protected HashMap headers = new HashMap();
	
	/**
	 * The date format we will use for creatign date headers.
	 */
	protected final SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz",Locale.US);
	
	/**
	 * The error message set by <code>sendError()</code>.
	 */
	protected String message = getStatusMessage(HttpServletResponse.SC_OK);
	
	/**
	 * The HTTP status code associated with this Response.
	 */
	protected int status = HttpServletResponse.SC_OK;
	
	public HttpResponse(OutputStream output){
		this.output = output;
	}
	
	/**
	 * call this method to send headers and response to the output.
	 */
	public void finishResponse(){
		// sendHeaders();
		// Flush and close the appropriate output mechanism
		if (writer != null){
			writer.flush();
			writer.close();
		}
	}
	
	public int getContentLength(){
		return contentLength;
	}
	
	public String getContentType(){
		return contentType;
	}
	
	public String getProrocol(){
		return request.getProtocol();
	}
	
	/**
	 * Returns a default status message for the specified HTTP status code.
	 * @param status The status conde for which a message is desired.
	 */
	protected String getStatusMessage(int status){
		switch (status) {
		case SC_OK:
	        return ("OK");
	      case SC_ACCEPTED:
	        return ("Accepted");
	      case SC_BAD_GATEWAY:
	        return ("Bad Gateway");
	      case SC_BAD_REQUEST:
	        return ("Bad Request");
	      case SC_CONFLICT:
	        return ("Conflict");
	      case SC_CONTINUE:
	        return ("Continue");
	      case SC_CREATED:
	        return ("Created");
	      case SC_EXPECTATION_FAILED:
	        return ("Expectation Failed");
	      case SC_FORBIDDEN:
	        return ("Forbidden");
	      case SC_GATEWAY_TIMEOUT:
	        return ("Gateway Timeout");
	      case SC_GONE:
	        return ("Gone");
	      case SC_HTTP_VERSION_NOT_SUPPORTED:
	        return ("HTTP Version Not Supported");
	      case SC_INTERNAL_SERVER_ERROR:
	        return ("Internal Server Error");
	      case SC_LENGTH_REQUIRED:
	        return ("Length Required");
	      case SC_METHOD_NOT_ALLOWED:
	        return ("Method Not Allowed");
	      case SC_MOVED_PERMANENTLY:
	        return ("Moved Permanently");
	      case SC_MOVED_TEMPORARILY:
	        return ("Moved Temporarily");
	      case SC_MULTIPLE_CHOICES:
	        return ("Multiple Choices");
	      case SC_NO_CONTENT:
	        return ("No Content");
	      case SC_NON_AUTHORITATIVE_INFORMATION:
	        return ("Non-Authoritative Information");
	      case SC_NOT_ACCEPTABLE:
	        return ("Not Acceptable");
	      case SC_NOT_FOUND:
	        return ("Not Found");
	      case SC_NOT_IMPLEMENTED:
	        return ("Not Implemented");
	      case SC_NOT_MODIFIED:
	        return ("Not Modified");
	      case SC_PARTIAL_CONTENT:
	        return ("Partial Content");
	      case SC_PAYMENT_REQUIRED:
	        return ("Payment Required");
	      case SC_PRECONDITION_FAILED:
	        return ("Precondition Failed");
	      case SC_PROXY_AUTHENTICATION_REQUIRED:
	        return ("Proxy Authentication Required");
	      case SC_REQUEST_ENTITY_TOO_LARGE:
	        return ("Request Entity Too Large");
	      case SC_REQUEST_TIMEOUT:
	        return ("Request Timeout");
	      case SC_REQUEST_URI_TOO_LONG:
	        return ("Request URI Too Long");
	      case SC_REQUESTED_RANGE_NOT_SATISFIABLE:
	        return ("Requested Range Not Satisfiable");
	      case SC_RESET_CONTENT:
	        return ("Reset Content");
	      case SC_SEE_OTHER:
	        return ("See Other");
	      case SC_SERVICE_UNAVAILABLE:
	        return ("Service Unavailable");
	      case SC_SWITCHING_PROTOCOLS:
	        return ("Switching Protocols");
	      case SC_UNAUTHORIZED:
	        return ("Unauthorized");
	      case SC_UNSUPPORTED_MEDIA_TYPE:
	        return ("Unsupported Media Type");
	      case SC_USE_PROXY:
	        return ("Use Proxy");
	      case 207:       // WebDAV
	        return ("Multi-Status");
	      case 422:       // WebDAV
	        return ("Unprocessable Entity");
	      case 423:       // WebDAV
	        return ("Locked");
	      case 507:       // WebDAV
	        return ("Insufficient Storage");
	      default:
	        return ("HTTP Response Status " + status);
		}
	}
	
	public OutputStream getStream(){
		return this.output;
	}
	
	@Override
	public void flushBuffer() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBufferSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCharacterEncoding() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCommitted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetBuffer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBufferSize(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentLength(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setContentType(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLocale(Locale arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addCookie(Cookie arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsHeader(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String encodeRedirectURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeRedirectUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeURL(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encodeUrl(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendError(int arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendError(int arg0, String arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendRedirect(String arg0) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDateHeader(String arg0, long arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeader(String arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setIntHeader(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStatus(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

}
