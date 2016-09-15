package com.lucas.servlets;

import javax.servlet.http.HttpServlet;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.GitHubTokenResponse;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.lucas.entities.Persona;
import com.lucas.service.PersonaService;

public class AcceptedServlet extends HttpServlet{
	PersonaService ps;
	Persona p;
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException ,java.io.IOException {
		String code = (String)req.getSession().getAttribute("code");
		System.out.println(code);
		p = new Persona();
		ps= new PersonaService();
		p.setDni(req.getParameter("dni"));
		p.setApellido(req.getParameter("apellido"));
		p.setNombre(req.getParameter("nombre"));
		
		
		try{
		OAuthClientRequest oRequest = OAuthClientRequest
									.tokenProvider(OAuthProviderType.FACEBOOK)
									.setGrantType(GrantType.AUTHORIZATION_CODE)
									.setClientId("1842578792639748")
									.setClientSecret("522848465fcaa082108eabd60c6a2a47")
									.setRedirectURI("http://localhost:8080/client-side/saraza.jsp")
									.setCode(code)
									.buildQueryMessage();
		
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		//Facebook is not fully compatible with OAuth 2.0 draft 10, access token response is
        //application/x-www-form-urlencoded
		GitHubTokenResponse oAuthResponse = oAuthClient.accessToken(oRequest, GitHubTokenResponse.class);
		 
        String accessToken = oAuthResponse.getAccessToken();
        Long expiresIn = oAuthResponse.getExpiresIn();
        
        System.out.println("accesToken: "+accessToken+" "+" expires: "+expiresIn);
        p.setToken(accessToken);
        ps.post(p);
        
		

		
		
		}catch(Exception e){
			throw new RuntimeException(e);
			
		}							
		
		
	}
	

}
