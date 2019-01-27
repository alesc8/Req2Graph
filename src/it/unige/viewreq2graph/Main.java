
package it.unige.viewreq2graph;

import it.unige.req2graph.ReqVClient;
import it.unige.viewreq2graph.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage; 


public class Main extends Application {
	
	public String aaa;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
			Parent root = (Parent) loader.load();
			
			ReqVClient reqVClient = new ReqVClient();
			((SampleController)loader.getController()).setReqVClient(reqVClient);
			
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.setTitle("Requirements Analysis - Login");
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}

