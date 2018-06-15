package it.unige.req2graph;

/**
 * @author Alessandro Scotto
 *
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//import snl2fl.req.requirements.Requirement;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ReqVClient {
	private static String bearerToken;
	private static final MediaType JSON
		= MediaType.parse("application/json; charset=utf-8");
	private static final String URL_BASE ="https://reqv.vuotto.tech/api/";
	private static List<Project> projects;
	public static List<ObjRequirement> requirements;
	public static String inputJson;

	OkHttpClient client = new OkHttpClient();

	public String UserLogin (String username, String password) throws IOException{
		Map<String, String> params = new HashMap<String, String>();
	    params.put("username", username);
	    params.put("password", password);
	    JSONObject parameter = new JSONObject(params);

		RequestBody body = RequestBody.create(JSON, parameter.toString());
		Request request = new Request.Builder()
			.url(URL_BASE + "login")
			.post(body)
			.build();
		// perform the request
		try (Response response = client.newCall(request).execute()) {
			
			return response.header("Authorization");
		
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
	return null;
	}
  

	public List<Project> getProjects(String userToken)throws Exception {
		
		if (userToken==null){
			return null;
		}
		List<Project> listP=null;
		Request request = new Request.Builder()
			.url(URL_BASE+"projects")
		    .addHeader("Authorization", userToken)
		    .build();

		try {
		    Response response = client.newCall(request).execute();
		    inputJson=response.body().string();
		    System.out.println("t: "+ response.message().toString());
		    System.out.println("u: "+ inputJson);
		    ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			listP= mapper.readValue(inputJson, mapper.getTypeFactory().constructCollectionType(List.class, Project.class));
				
		    

		    } catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
		}
		return listP; 
	}
	public List<ObjRequirement> getRequirements(String userToken,String idProject) throws IOException{
		if (userToken==null){
			return null;
		}
		List<ObjRequirement> listR=null;	
		Request request = new Request.Builder()
	        .url(URL_BASE+"requirements?pId="+idProject)
	        .addHeader("Authorization", userToken)
	        .build();

	    try {
	    	Response response = client.newCall(request).execute();
	    	inputJson=response.body().string();
	    	System.out.println("q: "+ response.message().toString());
	    	System.out.println("r: "+ inputJson);
	    	ObjectMapper mapper = new ObjectMapper();
			listR= mapper.readValue(inputJson, mapper.getTypeFactory().constructCollectionType(List.class, ObjRequirement.class));
	      
	    }catch (IOException e) {
			e.printStackTrace();
		}
		
		return listR; 
			
	}

	
	
	
	
	
	
	public static void main(String[] args) throws Exception {

		ReqVClient example =new ReqVClient();  
		bearerToken=example.UserLogin("Alessandro", "Scotto2018");
		System.out.println(bearerToken);
		projects=example.getProjects(bearerToken);
		requirements=example.getRequirements(bearerToken, "813");		
		System.out.println("requisito: "+requirements.get(1).getText());

	 //	Req2Graph graph=new Req2Graph();
	// 	graph.CreateVarMap(requirements);
		
    
  }
}