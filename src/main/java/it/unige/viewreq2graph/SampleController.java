package it.unige.viewreq2graph;



import java.net.URL;
import java.util.ResourceBundle;

import it.unige.req2graph.ReqVClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SampleController implements Initializable {
	
	private ReqVClient reqVClient;
	private String mytoken;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btnConnect;

    @FXML
    private TextField txtSite;

    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private Label lblMessage;
    
    @FXML
    private ComboBox<String> cbProject;

    @FXML
    void btnConnect_OnClick(ActionEvent event) throws Exception {
      
    mytoken = ReqVClient.getInstance().UserLogin(txtUsername.getText(), txtPassword.getText());
    ReqVClient.getInstance().setBearerToken(mytoken);
    
    if (mytoken==null) {
    	lblMessage.setText("Username or password incorrect..."); 
    } 
    else
    {
    	lblMessage.setText(mytoken); 
    	reqVClient.setBearerToken(mytoken);

		try {
			//cambia scena
			Parent newRoot = FXMLLoader.load(getClass().getResource("Sample1.fxml"));
	    	Scene newRootScene = new Scene(newRoot);
	    	Stage window= (Stage)((Node)event.getSource()).getScene().getWindow();
	    	window.setScene(newRootScene);
	    	window.setTitle("Requirements Analysis - Software Engineering Project");
	    	window.show(); 
	    	window.centerOnScreen();
		
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
    	//popola combobox
    	
   // 	List<Project> projects=reqVClient.getProjects(mytoken);
    	
    	
    //	ObservableList<String> list= FXCollections.observableArrayList("2", "3",  "4");
    	
    //	cbProject.setItems(list);


    	
    	
    	/*
    	Callback<ListView<Project>, ListCell<Project>> cellFactory = new Callback<ListView<Project>, ListCell<Project>>() {
    	    @Override
    	    public ListCell<Project> call(ListView<Project> projects) {
    	        return new ListCell<Project>() {
    	            @Override
    	            protected void updateItem(Project item, boolean empty) {
    	                super.updateItem(item, empty);
    	                if (item == null || empty) {
    	                    setGraphic(null);
    	                } else {
    	                    setText(item.getId()+"    "+item.getName());
    	                }
    	            }
    	        } ;
    	    }
    	};

    	// Just set the button cell here:
    	cbProject.setButtonCell(cellFactory.call(null));
    	cbProject.setCellFactory(cellFactory);
    	//cbProject.setItems((ObservableList<Project>) projects);
        
        cbProject.setItems(proj);

        StringConverter<Project> converter = new StringConverter<Project>() {
            @Override
            public String toString(Project project) {
                return project.getName();
            }

            @Override
            public Project fromString(String id) {
                return proj.stream()
                        .filter(proj -> proj.getId().equals(id))
                        .collect(Collectors.toList()).get(0);
            }
        };
        cbProject.setConverter(converter);
        // Print the name of the Bank that is selected
     
*/        
        
    	//cbProject.getItems().clear();
    	//cbProject.setConverter((StringConverter<?>) projects);
    	//cbProject.setItems((ObservableList<?>) projects);
    	//cbProject.getItems().addAll(projects) ;   
    
    }
    
    	
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReqVClient.getInstance();
    }
  
    
    

	public void setReqVClient(ReqVClient reqVClient) {
		this.reqVClient = reqVClient;
	}

	
}
