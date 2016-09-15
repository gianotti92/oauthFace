package com.lucas.service;





import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ClientResource;


import com.lucas.entities.Persona;

public class PersonaService{
	//String uri = "http://bluemixdb.mybluemix.net/webapi/personal/";
	
	public void post(Persona p){
		ClientResource resource = new ClientResource("http://bluemixdb.mybluemix.net/webapi/personal/tedoy");
		resource.getReference().addQueryParameter("format", "json");
		resource.getReference().addQueryParameter("token", p.getToken());
		JSONObject JSONPersona = new JSONObject();
		

		try {
			JSONPersona.put("nombre", p.getNombre());
			JSONPersona.put("apellido", p.getApellido());
			JSONPersona.put("_rev",p.get_rev());
			JSONPersona.put("_id", p.getId());
			JSONPersona.put("dni", p.getDni());
			

		} catch (JSONException e) {

			e.printStackTrace();
		}
		StringRepresentation stringRep = new StringRepresentation(JSONPersona.toString());
        stringRep.setMediaType(MediaType.APPLICATION_JSON);
		try{
			resource.post(stringRep).write(System.out);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

}
