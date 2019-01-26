package it.unige.req2graph;

/**
 * @author Alessandro Scotto
 *
 * Questa classe si occupa di accreditarsi su un sito web
 * e di ottenere una lista di oggetti requisito in formato json
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

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ReqVClient {

    private final static ReqVClient instance = new ReqVClient();
	private static String bearerToken;
	private static final MediaType JSON
		= MediaType.parse("application/json; charset=utf-8");
	private static final String URL_BASE ="https://reqv.sagelab.it/api/";
//	private static List<Project> projects;
	public static List<ObjRequirement> requirements;
	private static List<ObjRequirement> requirementsList;
	public static String inputJson;
	
	/**
	 * Getter
	 * @return bearerToken
	 */
	public static String getBearerToken() {
		return bearerToken;
	}
	
	/**
	 * Setter
	 * @param bearerToken String Authorization
	 */
	public static void setBearerToken(String bearerToken) {
		ReqVClient.bearerToken = bearerToken;
	}
	
	/**
	 * Getter
	 * @return requirementsList
	 */
	public static List<ObjRequirement> getRequirementsList() {
		return requirementsList;
	}
	
	/**
	 * Setter
	 * @param requirementsList lista dei requisiti 
	 */
	public static void setRequirementsList(List<ObjRequirement> requirementsList) {
		ReqVClient.requirementsList = requirementsList;
	}
	
	/**
	 * Getter
	 * @return instance of ReqVClient
	 */
	public static ReqVClient getInstance() {
        return instance;
	}
	
	OkHttpClient client = new OkHttpClient();

	/**
	 * UserLogin è una funzione che ritorna un Bearer token se 
	 * l'autenticazione è andata a buon fine
	 * @param username username
	 * @param password password
	 * @return Authorization sottoforma di token
	 * @throws IOException in presenza di un errore I/O
	 */
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
  
	/**
	 * Procedura che ritorna la lista dei progetti in uso 
	 * allo specifico utente
	 * 
	 * @param userToken di autorizzazione
	 * @return lista di progetti 
	 * @throws Exception in presenza di errore generico
	 */
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
		    //System.out.println("t: "+ response.message().toString());
		    //System.out.println("u: "+ inputJson);
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
	
	/**
	 * Procedura che ritorna tutti gli oggetti requisito legati allo specifico progetto
	 * @param userToken autorizzazione
	 * @param idProject id del progetto
	 * @return lista degli oggetti requisito
	 * @throws IOException in presenza di un errore I/O
	 */
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
	    	//System.out.println("q: "+ response.message().toString());
	    	//System.out.println("r: "+ inputJson);
	    	ObjectMapper mapper = new ObjectMapper();
			listR= mapper.readValue(inputJson, mapper.getTypeFactory().constructCollectionType(List.class, ObjRequirement.class));
			setRequirementsList(requirements);
	      
	    }catch (IOException e) {
			e.printStackTrace();
		}
		
		return listR; 
			
	}
		
	
}