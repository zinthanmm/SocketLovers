package server;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class ChatServer extends Application {

	public static Label logs = new Label("[Server Logs]");
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Thread startServer = new Thread(new StartServer());
		startServer.start();

		// server window layout settings
		ScrollPane layout = new ScrollPane();
		layout.setPrefSize(500, 600);
		layout.setContent(logs);
		
		primaryStage.setTitle("Sever Window");
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

}