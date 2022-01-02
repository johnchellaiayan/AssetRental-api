package com.assetmgmt.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.assetmgmt.util.Constants;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

	@Override 
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {
		
		String isApiKeyInvalid = (String) req.getAttribute("isApiKeyInvalid");
		if (isApiKeyInvalid != null && req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null) {
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_FORBIDDEN, "Your API is invalid");			
		}		

		String expired = (String) req.getAttribute("expired"); 
		if (expired != null && req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null) {
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT is Expired.please renew the token");			
		}
		
		String jwtInvalid = (String) req.getAttribute("jwt_invalid"); 
		if (jwtInvalid != null && req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null) {
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Format."+" - "+req.getAttribute("msg"));			
		}
		
		
		
		if(req.getAttribute("general_error")!=null)
		{
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_FORBIDDEN,	"General Error " + e.getMessage()+" - "+req.getAttribute("msg"));
		}
       
		if(req.getAttribute(Constants.BooleanValue.IS_ERROR_HANDLED)==null)
		{
			req.setAttribute(Constants.BooleanValue.IS_ERROR_HANDLED,"true");
			res.sendError(HttpServletResponse.SC_FORBIDDEN,	"You're not authorized to access this resource.." + e.getMessage()+" - "+req.getAttribute("msg"));
		}
	}
}
