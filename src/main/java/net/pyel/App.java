package net.pyel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;
	private static Stage stageInfo;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("base"));
		stage.setScene(scene);
		stage.setTitle("Game Panel | Welcome");
		stage.show();
		stageInfo = stage;
	}

	public static Stage getStageInfo() {
		return stageInfo;
	}

	static void setRoot(String fxml) throws IOException {
		Parent root = loadFXML(fxml);
		// Check if the new root is for "main.fxml" and adjust the scene size
		if ("main".equals(fxml)) {
			scene = new Scene(root, 1413, 882);
			stageInfo.setScene(scene);
		} else {
			scene.setRoot(root);
		}
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
