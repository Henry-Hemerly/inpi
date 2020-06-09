package application;
	
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.DBConnection;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			@SuppressWarnings("deprecation")
            URL url = new File ("BachelorThesis/src/view/MainFX.fxml").toURL();
            System.out.println(url);
            Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Banco de dados de marcas");
		} catch (IOException e) 
		{		

			System.err.println(e + " Error in the main.");
		}
	}
	
	public static void main(String[] args) throws IOException, SQLException {
		//uncomment to create another database
		DBConnection.getConnection();
		launch(args);
		//removeDuplicates rd = new removeDuplicates();
		//rd.stripDuplicatesFromFile("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/data.txt");
		
	}
	

}
