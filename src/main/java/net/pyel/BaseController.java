package net.pyel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.models.Port;
import net.pyel.utils.CustomList;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Base Controller - Manages all windows with fxml
 *
 * @author Zalán Tóth
 */
public class BaseController implements Initializable {
	private static PanelAPI panelAPI = new PanelAPI(null);
	private CustomList<Machine> machines;
	private CustomList<Game> games = new CustomList<>();
	Stage popupstage = new Stage();
	Parent popuproot;
	Scene popupScene;
	Stage terminalStage = new Stage();
	Parent terminalRoot;
	Scene terminalScene;
	Stage facilityStage = new Stage();
	Parent facilityRoot;
	Scene facilityScene;
	boolean setRun = true;

	public BaseController() {
		panelAPI = BackgroundController.getPanelAPI();
		machines = panelAPI.panel.getMachines();
		games = panelAPI.panel.getGames();
	}


	@FXML
	private void logTerminal() throws IOException {
		terminalRoot = FXMLLoader.load(getClass().getResource("log.fxml"));
		terminalScene = new Scene(terminalRoot);
		terminalStage.setScene(terminalScene);
		terminalStage.setResizable(true);
		terminalStage.setTitle("Game Panel | Terminal 2.0");
		terminalStage.show();
	}

	@FXML
	TextField logInput = new TextField();

	@FXML
	ListView<String> log = new ListView<>();
	@FXML
	TextField staff = new TextField();
	String previousCommand = "";


	@FXML
	private void logPrompt() {
		String input = logInput.getText();
		logInput.clear();
		if (input.length() == 3) {
			if (input.toLowerCase().substring(0, 3).equals("log")) {
				terminalOutError("Usage: log [message]");
				return;
			}
		}
		if (input.length() < 4) {
			terminalOutError("Invalid command in log prompt. Type \"help\" for help.");
			return;
		}
		if (input.length() == 4) {
			if (input.toLowerCase().substring(0, 4).equals("help")) {

				terminalOutHelp("-------------------------------------");
				terminalOutHelp("reset - Resets the program, erases loaded data");
				terminalOutHelp("debug - Switch debug mode (current: " + panelAPI.panel.isDebugMode() + ")");
				terminalOutHelp("--------------FACILITIES-------------");
				terminalOutHelp("clear - Clear terminal");
				terminalOutHelp("bogosort - Bogo");
				terminalOutHelp("staff [name] - Change staff");
				terminalOutHelp("log [message] - Log a message");
				terminalOutHelp("help - Help page");
				terminalOutHelp("---------------HELP MENU-------------");
				terminalOut("HELP executed");
				return;
			}
		}
		if (input.length() == 5) {
			if (input.toLowerCase().substring(0, 5).equals("clear")) {
				terminalOut("CLEAR executed");
				log.getItems().clear();
				return;
			} else if (input.toLowerCase().substring(0, 5).equals("debug")) {
				switchDebugMode();
				return;
			} else if (input.toLowerCase().substring(0, 5).equals("reset")) {
				log.getItems().clear();
				terminalOut("RESET executed");
				try {
					newPanel();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				return;
			}
		}

		if (input.length() == 8) {
			if (input.toLowerCase().substring(0, 8).equals("bogosort")) {

				Random random = new Random();
				int variable = random.nextInt(30001);
				int numero = 69;
				int count = 0;
				while (variable != numero) {
					variable = random.nextInt(30001);
					terminalOutHelp("" + variable);
					log.getItems().clear();
					count++;
				}
				terminalOut("Your public static void main String args[" + numero + "] quantum bogo is found in " + count + " attempt(s).");

				return;
			}
		}
		if (input.toLowerCase().substring(0, 4).equals("log ")) {

			if (input.length() > 4) {
				String message = input.substring(4);
				terminalOut(message);
			} else {
				terminalOutError("Usage: log [message]");
			}
		} else {
			terminalOutError("Invalid command in log prompt. Type \"help\" for help.");
		}
	}

	public void switchDebugMode() {
		boolean current = panelAPI.panel.isDebugMode();
		if (current) {
			panelAPI.panel.setDebugMode(false);
		} else {
			panelAPI.panel.setDebugMode(true);
		}
		terminalOut("Set DEBUG mode to " + panelAPI.panel.isDebugMode());
	}


	public void terminalReverse(String out) {
		String message = out;
		System.out.println(message);
		log.getItems().add(message);
	}

	public void terminalOut(String out) {
		String message = "[" + panelAPI.currentStaff + "]" + " > " + out;
		if (panelAPI.currentStaff.isEmpty()) {
			message = " > " + out;
		}
		System.out.println(message);
		log.getItems().add(0, message);
	}

	public void terminalOutError(String out) {
		String message = "(!) " + out;
		System.out.println(message);
		log.getItems().add(0, message);
	}

	public void terminalOutHelp(String out) {
		String message = "| " + out;
		//System.out.println(message);
		log.getItems().add(0, message);
	}

	@FXML
	private void newPanel() throws IOException {
		BackgroundController.setPanelAPI(new PanelAPI(staff.getText()));
		App.setRoot("main");
		App.getStageInfo().setTitle("Game Panel | Ports");
		App.getStageInfo().setHeight(900);
		App.getStageInfo().setWidth(1400);
		App.getStageInfo().setResizable(false);


	}

	@FXML
	private void basePanel() throws IOException {
		BackgroundController.setPanelAPI(new PanelAPI(staff.getText()));
		App.getStageInfo().setFullScreen(false);
		App.getStageInfo().setHeight(647);
		App.getStageInfo().setWidth(1024);
		App.getStageInfo().setResizable(false);
		App.getStageInfo().setTitle("Game Panel | Welcome");
		App.setRoot("base");


	}

	@FXML
	private void refresh() {
		initialize(null, null);
	}

	@FXML
	private void loadPanel() throws IOException {
		BackgroundController.setPanelAPI(new PanelAPI(staff.getText()));
		panelAPI = BackgroundController.getPanelAPI();
		loadData();
		machines = panelAPI.panel.getMachines();
		games = panelAPI.panel.getGames();
		/*if (shipsOnSea == null) {
			shipsOnSea = new CustomList<>();
		}*/
		initialize(null, null);
		App.setRoot("main");
		App.getStageInfo().setHeight(900);
		App.getStageInfo().setWidth(1400);
		App.getStageInfo().setResizable(false);


	}

	@FXML
	private void openHelpMenu() throws IOException {
		popuproot = FXMLLoader.load(getClass().getResource("help.fxml"));
		popupstage.setResizable(false);
		popupstage.setTitle("Game Panel | About & Help Centre");
		popupScene = new Scene(popuproot);
		popupstage.setScene(popupScene);
		popupstage.show();
	}

	@FXML
	private void loadData() {
		BackgroundController.loadData();
		BackgroundController.setPanelAPI(panelAPI);
		deselectMachine();
		deselectGame();
		machines = panelAPI.panel.getMachines();
		games = panelAPI.panel.getGames();
		initialize(null, null);
	}


	@FXML
	private void saveData() {
		BackgroundController.saveData();
	}

	@FXML
	private ListView<String> viewFacility = new ListView<>();
	@FXML
	private ListView<Port> portListView = new ListView<>();

	private Machine selectedMachine;
	private Game selectedGame;
	private Port selectedPort;


	@FXML
	private void clearOutputTerminal() {
		log.getItems().clear();


	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (setRun) {
			setupPortListViewListener();
			setRun = false;
		}
		//portListView.getItems().addAll(ports);
		machineListView.setItems(FXCollections.observableList(machines)); //Yay!
		gameListView.setItems(FXCollections.observableList(games)); //Yay!
		updateData();


	}

	private void setupPortListViewListener() {
		log.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// This will be called whenever the user selects a different item in the list
			if (newValue != null) {
				updateData();
			}
		});
		viewFacility.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// This will be called whenever the user selects a different item in the list
			if (newValue != null) {
				refresh();
			}
		});
		logInput.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				logPrompt();
				previousCommand = logInput.getText();
			}

		});
	}


	private void updateData() {
	}
}