package com.cc32.servlet;

import com.cc32.server.Request;
import com.cc32.server.Response;

public class StaticResourceProcessor {

	public void process(Request request, Response response){
		try {
			response.sendStaticResource();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
