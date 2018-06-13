package it.unige.se.ReqAnModel;


import java.util.List;


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
	 	graph.returnVarGraph2();
	 	
		System.out.println("requisito tt: "+requirements.get(1).getText());
		System.out.println("Fine elaborazione");
		
		
		
    

	}

}
