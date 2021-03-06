package com.assetmgmt.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ServletRequestWrapper extends HttpServletRequestWrapper {

	private Map headerMap;

	public void addHeader(String name, String value) {
		headerMap.put(name, value);
	}

	public ServletRequestWrapper(HttpServletRequest request) {
		super(request);
		headerMap = new HashMap();
	}

	public Enumeration getHeaderNames() {
		HttpServletRequest request = (HttpServletRequest) getRequest();
		List list = new ArrayList();
		for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();)
			list.add(e.nextElement().toString());
		for (Iterator i = headerMap.keySet().iterator(); i.hasNext();) {
			list.add(i.next());
		}
		return Collections.enumeration(list);
	}

	public String getHeader(String name) {
		Object value;
		if ((value = headerMap.get("" + name)) != null)
			return value.toString();
		else
			return ((HttpServletRequest) getRequest()).getHeader(name);
	}
}
