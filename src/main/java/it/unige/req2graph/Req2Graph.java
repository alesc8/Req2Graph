package it.unige.req2graph;


/**
 * @author Alessandro Scotto
 *
 * Questa classe si occupa della costruzione del grafo a partire dai requisiti,
 * di esportare il grafo in formato .dot e di fare l'analisi del grafo
 * I tipi di grafo disponibili sono due, multigrafo e pseudografo
 * ReqNotCompliant è una lista di oggetto requisito definbiti "Not Compliant"
 * 
 */ 

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.io.DOTExporter;
import org.paukov.combinatorics3.Generator;

import snl2fl.Snl2FlParser;
import snl2fl.req.parser.RequirementsBuilder;

public class Req2Graph {

	private static Pseudograph<ObjRequirement, LabelEdge> pseudograph = new Pseudograph<ObjRequirement, LabelEdge>(LabelEdge.class) ;
	private static Multigraph<ObjRequirement, LabelEdge> multigraph = new Multigraph<ObjRequirement, LabelEdge>(LabelEdge.class) ;
	private static List<ObjRequirement > reqNotCompliant = new LinkedList<ObjRequirement>();
	private static Map<String, List<ObjRequirement>> mapVar = new HashMap<String, List<ObjRequirement>>();


	/**
	 * Questo metodo si occupa di aggiungere un nodo al grafo
	 * 	 * @param r1 nodo da aggiungere
	 */
	public static void addNode(ObjRequirement r1){
		multigraph.addVertex(r1);
		pseudograph.addVertex(r1);
	}
	
	/**
	 * Questo metodo si occupa di aggiungere un arco al grafo. Tale arco congiunge due nodi
	 * e l'etichetta è pari al valore della variabile
	 * @param r1 Nodo r1
	 * @param r2 Nodo r2
	 * @param variableName etichetta dell'arco
	 */
	public static void addArc(ObjRequirement r1, ObjRequirement r2, String variableName) {
	    LabelEdge e = new LabelEdge(variableName);
	    multigraph.addEdge(r1, r2, e);
	    pseudograph.addEdge(r1,r2, e);
	}
	
	/**
	 * Questo metodo si occupa di aggiungere un arco al grafo nel caso i due nodi coincidano.
	 * L'etichetta è pari al valore della variabile
	 * @param r1 Nodo r1
	 * @param variableName etichettta dell1arco
	 */
	public static void addArc(ObjRequirement r1,  String variableName) {
	    LabelEdge e = new LabelEdge(variableName);
	    pseudograph.addEdge(r1,r1, e);
		//System.out.println("Aggiunto arco: "+e+ " tra - "+ r1.getId()+" e - "+r1.getId());
	}

	/**
	 * Metodo che crea una map in cui per ogni variabile estrapolata da tutti i requisiti,
	 * si associano i requisiti corrispondenti, ovvero i nodi
	 * @param input flusso di dati in json
	 */
	public static void CreateVarMap(List<ObjRequirement> input) {

		try{
		 ListIterator<ObjRequirement> litr = input.listIterator();
			//System.out.println( "litr next: "+litr.nextIndex());
			//System.out.println( "litr previous: "+litr.previousIndex());

		    while(litr.hasNext())   //In forward direction
		    {
		    	ObjRequirement current= litr.next();
		    	String reqName= current.getId().toString();
				//System.out.println( "reqname: "+reqName);
		    	String reqState=current.getState();
				//System.out.println( "state: "+reqState);
		    	String reqtoParse=current.getText();
		    	//System.out.println(reqName +" - " +reqtoParse);
				//Prima di tutto costruisco l'oggetto parser:
				Snl2FlParser parser = new Snl2FlParser();
				//poi, gli passo lo stream di input, requisito
				 parser.parseString(reqtoParse);
				//ottengo l'oggetto RequirementsBuilder che è utilizzato internamente per fare la traduzione:
				RequirementsBuilder builder=parser.getBuilder();

				//System.out.println( "builder: "+builder.getRequirementList().size());

				if (reqState.equals("COMPLIANT") ){
					
					//System.out.println( "compliant"); 

		        	Req2Graph.addNode(current);

					for (String newVar: builder.getSymbolTable().keySet()) {
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

	/**
	 * Metodo checrea il grafo 
	 */
	public void returnVarGraph(){

		// get entrySet() into Set
        Set<Map.Entry<String, List<ObjRequirement>>> entrySet = mapVar.entrySet();

        // Collection Iterator
        Iterator<Entry<String, List<ObjRequirement>>> iterator = entrySet.iterator();
        //System.out.println("\nNumero di variabili totali: "+entrySet.size());

        while(iterator.hasNext()) {

            Entry<String, List<ObjRequirement>> entry = iterator.next();
            //Calcolo il numero n di requisiti per quella variabile
            int n=entry.getValue().size();

            //System.out.println("\nDimensione array: "+entry.getValue().size());

            //System.out.println("\nVariabile: "  + entry.getKey()
            //        + "\nListarequisiti: " + entry.getKey() + ":");


            //Qui inserisco gli archi nel grafo
        	 Generator.combination(entry.getValue())
            	.simple(2)
            	.stream()
            	.forEach( (reqs) -> { addArc( reqs.get(0),  reqs.get(1), entry.getKey());}  );
        	 
        	 //Inserisco gli archi pseudograph
             if (n==1){
             	Req2Graph.addArc(entry.getValue().get(0), entry.getKey());
             }

        }
	}
	
	

	/**
	 * Metodo che esporta il multigrafo in un file con estensione dot
	 */
	static public void exportMultiGraph(){
		
	    DOTExporter<ObjRequirement, LabelEdge> export = new DOTExporter<ObjRequirement, LabelEdge>(new VertexNameProvider(), null, new EdgeNameProvider());
	    
	    try {
	        export.exportGraph(  multigraph, new FileWriter("multigraph.dot"));
	    }catch (IOException e){}
	    
	} 


	/**
	 * Metodo che esporta lo pseudografo in un file con estensione dot
	 */
	static public void exportPseudoGraph(){
		
	    DOTExporter<ObjRequirement, LabelEdge> export = new DOTExporter<ObjRequirement, LabelEdge>(new VertexNameProvider(), null, new EdgeNameProvider());
	    
	    try {
	        export.exportGraph(  pseudograph, new FileWriter("pseudograph.dot"));
	    }catch (IOException e){}
	    
	} 
	
	/**
	 * Metodo che usa un connectivityInspector per calcolare se il grafo è connesso oppure no
	 * @return vero o falso
	 */
	public static boolean isConnected() {

		org.jgrapht.alg.connectivity.ConnectivityInspector<ObjRequirement, LabelEdge> inspector = new org.jgrapht.alg.connectivity.ConnectivityInspector<ObjRequirement, LabelEdge>(multigraph);
					
			return inspector.isConnected();
				
	}

	/**
	 * Metodo che usa un connectivityInspector per calcolare se il grafo è connesso oppure no
	 * @return lista dei Set dei vertici massimamente connessi
	 */
	public static List<Set<ObjRequirement>> getConnectedSet() {
		org.jgrapht.alg.connectivity.ConnectivityInspector<ObjRequirement, LabelEdge> inspector = new org.jgrapht.alg.connectivity.ConnectivityInspector<ObjRequirement, LabelEdge>(multigraph);
					
			return inspector.connectedSets();
	}



}
