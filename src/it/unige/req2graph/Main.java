

package it.unige.req2graph;


import java.util.List; 


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ReqVClient example =new ReqVClient();  
		String bearerToken=example.UserLogin("Alessandro", "Scotto2018");
		System.out.println(bearerToken);
		List<Project> listProjects=example.getProjects(bearerToken);
		System.out.println("Nel sistema sono presenti i seguenti Id Project: " );

		// visualizza gli Id progetto
		for(Project list : listProjects) {
			System.out.println(list.getId().toString()+";");
		}
		
		//List<ObjRequirement> requirements =example.getRequirements(bearerToken, "813");		
		List<ObjRequirement> requirements =example.getRequirements(bearerToken, "5");		
		//System.out.println("requisito tt: "+requirements.get(1).getText());
		System.out.println("inizio graph");
		
	 	Req2Graph graph=new Req2Graph();
	 	Req2Graph.CreateVarMap(requirements);
	 	
	 	graph.returnVarGraph();

	 	Req2Graph.exportMultiGraph();
	 	Req2Graph.exportPseudoGraph();
	 	
	 
		graph.AnalyzeGraph();
		System.out.println(Req2Graph.getReport());
		
		
    

	}

}
