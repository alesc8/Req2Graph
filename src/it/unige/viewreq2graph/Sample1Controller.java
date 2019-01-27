package it.unige.viewreq2graph;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.*;

import it.unige.req2graph.ObjRequirement;
import it.unige.req2graph.Project;
import it.unige.req2graph.Req2Graph;
import it.unige.req2graph.ReqVClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

public class Sample1Controller implements Initializable{

	private String mytoken;
	private ReqVClient reqVClient;
	private String idProject;
	private Req2Graph graph;
	private static List<ObjRequirement> requirements ;
	private static final String HIGHLIGHTED_LISTVIEW = "derive(palegreen, 50%)";

	 

    @FXML
    private Pane pane;
    
    @FXML
    private Label lbl1Message;
    
    @FXML
    private Button btnCreateGraph;

    @FXML
    private TextArea txtAreaGraph;

    @FXML
    private ComboBox<Project> cbProject;
    
    @FXML
    private ListView<ObjRequirement> lwGraph;
        
    @FXML
    private ListView<ObjRequirement> lwRequirements;
    
    @FXML
    private Button btnGetRequirements;
    
    @FXML
    void cbProject_OnChange(ActionEvent event) {
    	btnGetRequirements.setDisable(false);
    	btnCreateGraph.setDisable(true);
    	lbl1Message.setText("Selected project is: " + cbProject.getValue().getName()); 
    }
    
    @FXML
    void btnCreateGraph_OnClick(ActionEvent event) throws Exception {
    	lbl1Message.setText("Creating graph... Id: "+idProject + " - " + cbProject.getValue().getName()); 
        try{
        	//createJgrapht(requirements);
        	btnCreateGraph.setDisable(true);
        	requirements=reqVClient.getRequirementsList();
            Req2Graph graph=new Req2Graph();
            Req2Graph.CreateVarMap(requirements);    	 	
    	 	graph.returnVarGraph();
    	 	graph.exportMultiGraph();
    	 	graph.exportPseudoGraph();
    	 //	System.out.print(graph.getReport());
    	 	txtAreaGraph.setText("Inizio Analisi del progetto Id: "+idProject + " - "+ cbProject.getValue().getName()+"\r\n");
    	 	graph.AnalyzeGraph();
    	 	txtAreaGraph.appendText(graph.getReport());
    	 	txtAreaGraph.appendText("\r\n"+"\r\n"+"Fine Analisi");
        	lbl1Message.setText("Graph created with success Id: "+idProject + " - " + cbProject.getValue().getName()); 
        }
        catch(Exception e) {
			e.printStackTrace();
			e.getStackTrace().toString();
			e.getMessage();
		}

    }

    @FXML
    void btnGetRequirements_OnClick(ActionEvent event)  throws Exception {
    	lwRequirements.getItems().clear();;
		btnCreateGraph.setDisable(false);
		btnGetRequirements.setDisable(true);
    	if (cbProject.getValue() != null){
    	idProject=cbProject.getValue().getId().toString();
        	lbl1Message.setText("Your choise is Project Id "+idProject + " - "+ cbProject.getValue().getName()); 
        	try{
        		List<ObjRequirement> requirements =reqVClient.getRequirements(mytoken, idProject);
        		ReqVClient.setRequirementsList(requirements);
        		lwRequirements.getItems().addAll(requirements);
        	}
            catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	else{
    		lbl1Message.setText("Select any project...");
    	}    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reqVClient=ReqVClient.getInstance();
        mytoken=ReqVClient.getBearerToken();
        //__________________________

        

        //____________/
        try{
            List<Project> projects=reqVClient.getProjects(mytoken);        	
        	cbProject.setConverter(new ProjectConverter());
        	cbProject.getItems().addAll(projects);
        	
        }
        catch(Exception e) {
			e.printStackTrace();
		}
    }
    public void createJgrapht(final List<ObjRequirement> requirements) {
    	this.requirements=requirements;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Req2Graph graph=new Req2Graph();
        	 	graph.CreateVarMap(requirements);    	 	
        	 	graph.returnVarGraph();
        	 	graph.exportMultiGraph();
        	 	graph.exportPseudoGraph();
            }
        });
    }
    public class ProjectConverter extends StringConverter<Project> {

        /** Cache of Project */
        private Map<String, Project> projectMap = new HashMap<String, Project>();

        @Override
        public String toString(Project project) {
            projectMap.put(project.getName(), project);
            return project.getName();
        }

        @Override
        public Project fromString(String name) {
            return projectMap.get(name);
        }

    }
    public class ObjRequirementConverter extends StringConverter<ObjRequirement> {

        /** Cache of ObjRequirement */
        private Map<String, ObjRequirement> objRequirementMap = new HashMap<String, ObjRequirement>();

        @Override
        public String toString(ObjRequirement objRequirement) {
        	objRequirementMap.put(objRequirement.getId().toString(), objRequirement);
            return objRequirement.getId().toString();
        }

        @Override
        public ObjRequirement fromString(String name) {
            return objRequirementMap.get(name);
        }

    }
    
  

}
