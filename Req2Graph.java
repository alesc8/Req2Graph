package it.unige.se.ReqAnModel;


import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.Pseudograph;
import org.paukov.combinatorics3.Generator;

import snl2fl.Snl2FlParser;
import snl2fl.req.parser.RequirementsBuilder;

public class Req2Graph {

	private static Pseudograph<Node, DefaultEdge> pseudograph = new Pseudograph<Node, DefaultEdge>(LabelEdge.class) ;
//
	private static Multigraph<Node, DefaultEdge> graph = new Multigraph<Node, DefaultEdge>(LabelEdge.class) ;
//	private static Stream a =new Stream();
	private static List<ObjRequirement > reqNotCompliant = new LinkedList<ObjRequirement>();
//	private static List<List<String>> listE = new List<List<String>>();
	private static Map<String, List<ObjRequirement>> mapVar = new HashMap<String, List<ObjRequirement>>();
//	private static Map<String,List<String[]>> mapEdge = new HashMap<String,List<String[]>>();
	private static String newVar;
//	private static boolean itsok;


	public static void addNode(ObjRequirement r1){
		Node a=new Node(r1);
		graph.addVertex(a);
		pseudograph.addVertex(a);
	}
	public static void addArc(ObjRequirement r1, ObjRequirement r2, String variableName) {
	    LabelEdge e = new LabelEdge(variableName);
	    Node v1= new Node(r1);
	    Node v2= new Node(r2);
	    graph.addEdge(v1, v2, e);
	    pseudograph.addEdge(v1,v2, e);
	}

	public static void CreateVarMap(List<ObjRequirement> input) {

		try{

		 ListIterator<ObjRequirement> litr = input.listIterator();
			System.out.println( "litr next: "+litr.nextIndex());
			System.out.println( "litr previous: "+litr.previousIndex());

		    while(litr.hasNext())   //In forward direction
		    {
		    	ObjRequirement current= litr.next();
		    	String reqName= current.getId().toString();
				System.out.println( "reqname: "+reqName);
		    	String reqState=current.getState();
				System.out.println( "state: "+reqState);
		    	String reqtoParse=current.getText();
		    	System.out.println(reqName +" - " +reqtoParse);
				//Prima di tutto costruisco l'oggetto parser:
				Snl2FlParser parser = new Snl2FlParser();
				//poi, gli passo lo stream di input, requisito
				 parser.parseString(reqtoParse);
				//ottengo l'oggetto RequirementsBuilder che è utilizzato internamente per fare la traduzione:
				RequirementsBuilder builder=parser.getBuilder();

				System.out.println( "builder: "+builder.getRequirementList().size());

				if (reqState.equals("COMPLIANT") ){
					System.out.println( "compliant");


		        	Req2Graph.addNode(current);

					for (Iterator<String> h=builder.getSymbolTable().keySet().iterator(); h.hasNext();) {
						newVar=h.next();
						System.out.println("VAR: "+newVar+" - req:" +reqName);
						mapVar.computeIfAbsent(newVar, (x -> new ArrayList<>())).add(current);
					}
				}else {
					reqNotCompliant.add(current);
					System.out.println("not compliant: "+ reqName);
				}

		    }

		} catch (Exception e) {
		    System.err.println("Exception: " + e.getStackTrace());
		}

	}

	public void returnVarGraph(){

		// get entrySet() into Set
        Set<Map.Entry<String, List<ObjRequirement>>> entrySet = mapVar.entrySet();

        // Collection Iterator
        Iterator<Entry<String, List<ObjRequirement>>> iterator = entrySet.iterator();
        System.out.println("\nNumero di variabili totali: "+entrySet.size());

        while(iterator.hasNext()) {

        	/*
        	 * Qui inserisco i nodi nel grafo
        	 */

            Entry<String, List<ObjRequirement>> entry = iterator.next();
            //Calcolo il numero n di requisiti per quella variabile
            int n=entry.getValue().size();

            System.out.println("\nDimensione array: "+entry.getValue().size());

            System.out.println("\nVariabile: "  + entry.getKey()
                    + "\nListarequisiti: " + entry.getKey() + ":");

            for(ObjRequirement value : entry.getValue()) {
                System.out.println("\t\t\t\t" + value);

            }
            Generator.combination(entry.getValue()).simple(2).stream().forEach(System.out::println);

            /*
        	 * Qui inserisco gli archi nel grafo
             */
        	 Generator.combination(entry.getValue())
            	.simple(2)
            	.stream()
            	.forEach( (r1,  r2) -> { addArc( r1,  r2, entry.getKey());}  );


        }
	}
	public void returnVarGraph2(){
		for(Map.Entry<String, List<ObjRequirement>> entry : mapVar.entrySet()) {
			String key = entry.getKey();
			System.out.println("\nVariabile: "  + key
			                    + "\nListarequisiti" + key + ":");
			for (ObjRequirement value : entry.getValue()) {
				System.out.println("\t\t\t\t" + value);
			}
		}
	}



	public static void main(String[] args){

		//System.out.println(input.get(1).getText().toString());
		String[] list = { "a", "b", "c", "d", "e", "f" };
		for(int i = 0; i < list.length; ++i) {
			for(int j = i + 1; j < list.length; ++j) {
				// get requirements i and j } }
				System.out.println("sequenza " + list[ i] + list[j]);
			}

		}
	}
}
