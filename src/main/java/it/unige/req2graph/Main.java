package it.unige.req2graph;

import java.util.List;
import java.util.Set;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ReqVClient example =new ReqVClient();  
		String bearerToken=example.UserLogin("Alessandro", "Scotto2018");
		System.out.println(bearerToken);
		List<Project> projects=example.getProjects(bearerToken);
		//List<ObjRequirement> requirements =example.getRequirements(bearerToken, "813");		
		List<ObjRequirement> requirements =example.getRequirements(bearerToken, "649");		
		//System.out.println("requisito tt: "+requirements.get(1).getText());
		System.out.println("inizio graph");
		
	 	Req2Graph graph=new Req2Graph();
	 	graph.CreateVarMap(requirements);
	 	
	 	graph.returnVarGraph();

	 	graph.exportMultiGraph();
	 	graph.exportPseudoGraph();
	 	
	 // stampa true o false se è connesso
	 	System.out.println("Is connected?: " + graph.isConnected());

	 	// ottiene la lista dei set
	 	List<Set<ObjRequirement>> connectedSet = graph.getConnectedSet();

	 	// per ogni set i-esimo della lista
	 	for(int i=0; i<connectedSet.size(); i++)
	 	{

	 		System.out.println("set " + i);
	 		 
	 	        // stampa l'id di ogni nodo all'interno del set
	 		for(ObjRequirement v : connectedSet.get(i))
	 		 {
	 			System.out.print(v.getId() + "; ");
	 		}
	 	}

	 	
		System.out.println("Fine elaborazione");
		
		
		
    

	}

}
