package com.assetmgmt.util;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class URLUtil {

	@Autowired
	private Environment environment;

	@Autowired
	private ServletContext context;

	@Value("${api.baseurl.protocol}")
	private String protocol;

	@Value("${api.base-url}")
	private String baseUrl;

	public String getBaseUrl(HttpServletRequest request) throws MalformedURLException {
		URL requestURL = new URL(request.getRequestURL().toString());
		String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
		return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
	}

	public String getApiBaseUrl(HttpServletRequest request) throws MalformedURLException {
		String apiBaseUrl = null;
		String contextPath = context.getContextPath();
		apiBaseUrl = this.getBaseUrl(request) + "/" + contextPath;
		return apiBaseUrl;
	}

	public String getOauthUrl(HttpServletRequest request) throws MalformedURLException {
		String oauthBaseUrl = null;
		if (protocol != null && protocol.equals("http")) {
			String contextPath = context.getContextPath();
			oauthBaseUrl = this.getBaseUrl(request) + contextPath + "/oauth/token";
		}
	
		return oauthBaseUrl;
	}

}
