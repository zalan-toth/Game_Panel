package net.pyel;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.models.Port;
import net.pyel.utils.CustomList;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Base Controller - Manages all windows with fxml
 *
 * @author Zalán Tóth & Marcin Budzinski
 */
public class BaseController implements Initializable {


	//██████╗░███████╗░█████╗░██╗░░░░░░█████╗░██████╗░███████╗
	//██╔══██╗██╔════╝██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔════╝
	//██║░░██║█████╗░░██║░░╚═╝██║░░░░░███████║██████╔╝█████╗░░
	//██║░░██║██╔══╝░░██║░░██╗██║░░░░░██╔══██║██╔══██╗██╔══╝░░
	//██████╔╝███████╗╚█████╔╝███████╗██║░░██║██║░░██║███████╗
	//╚═════╝░╚══════╝░╚════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝
	private static PanelAPI panelAPI = new PanelAPI(null);
	private CustomList<Machine> machines;
	private CustomList<Game> games = new CustomList<>();
	Stage popupstage = new Stage();
	Parent popuproot;
	Scene popupScene;
	Stage terminalStage = new Stage();
	Parent terminalRoot;
	Scene terminalScene;
	boolean setRun = true;

	//███████╗██╗░░██╗███╗░░░███╗██╗░░░░░░░░░░░░░██████╗░███████╗░█████╗░██╗░░░░░░█████╗░██████╗░███████╗
	//██╔════╝╚██╗██╔╝████╗░████║██║░░░░░░░░░░░░░██╔══██╗██╔════╝██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔════╝
	//█████╗░░░╚███╔╝░██╔████╔██║██║░░░░░░░░░░░░░██║░░██║█████╗░░██║░░╚═╝██║░░░░░███████║██████╔╝█████╗░░
	//██╔══╝░░░██╔██╗░██║╚██╔╝██║██║░░░░░░░░░░░░░██║░░██║██╔══╝░░██║░░██╗██║░░░░░██╔══██║██╔══██╗██╔══╝░░
	//██║░░░░░██╔╝╚██╗██║░╚═╝░██║███████╗░░░░░░░░██████╔╝███████╗╚█████╔╝███████╗██║░░██║██║░░██║███████╗
	//╚═╝░░░░░╚═╝░░╚═╝╚═╝░░░░░╚═╝╚══════╝░░░░░░░░╚═════╝░╚══════╝░╚════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝

	@FXML
	private ListView<String> viewFacility = new ListView<>();
	@FXML
	private ListView<Port> portListView = new ListView<>();
	@FXML
	private ListView<Machine> machineListView = new ListView<>();
	@FXML
	private ListView<Game> gameListView = new ListView<>();

	//MACHINE FIELDS
	@FXML
	private Text machineNameText = new Text();
	@FXML
	private TextField machineNameBox = new TextField();
	@FXML
	private TextField machineRRPBox = new TextField();
	@FXML
	private TextField machineManufacturerBox = new TextField();
	@FXML
	private TextField machineTypeBox = new TextField();
	@FXML
	private TextField machineMediaBox = new TextField();
	@FXML
	private TextField machineYearBox = new TextField();
	@FXML
	private TextField machineURLBox = new TextField();
	@FXML
	private TextArea machineDescriptionBox = new TextArea();

	//GAME FIELDS
	@FXML
	private Text gameNameText = new Text();
	@FXML
	private TextField gameNameBox = new TextField();
	@FXML
	private TextField gameSelectedMachineBox = new TextField();
	@FXML
	private TextField gameCurrentMachineBox = new TextField();
	@FXML
	private TextField gamePublisherBox = new TextField();
	@FXML
	private TextField gameDeveloperBox = new TextField();
	@FXML
	private TextField gameURLBox = new TextField();
	@FXML
	private TextField gameYearBox = new TextField();
	@FXML
	private TextArea gameDescriptionBox = new TextArea();

	//PORT FIELDS
	@FXML
	private Text portNameText = new Text();
	@FXML
	private TextField portDeveloperBox = new TextField();
	@FXML
	private TextField portYearBox = new TextField();
	@FXML
	private TextField portSelectedMachineBox = new TextField();
	@FXML
	private TextField portCurrentMachineBox = new TextField();
	@FXML
	private TextField portURLBox = new TextField();

	//Selected items in listviews are syncronised with the following objects, so basically use that for referencing the selected item from the listview
	private Machine selectedMachine;
	private Game selectedGame;
	private Port selectedPort;

	//██╗░░░░░░█████╗░░██████╗░██╗███╗░░██╗
	//██║░░░░░██╔══██╗██╔════╝░██║████╗░██║
	//██║░░░░░██║░░██║██║░░██╗░██║██╔██╗██║
	//██║░░░░░██║░░██║██║░░╚██╗██║██║╚████║
	//███████╗╚█████╔╝╚██████╔╝██║██║░╚███║
	//╚══════╝░╚════╝░░╚═════╝░╚═╝╚═╝░░╚══╝
	//░█████╗░░█████╗░███╗░░██╗████████╗██████╗░░█████╗░██╗░░░░░██╗░░░░░███████╗██████╗░
	//██╔══██╗██╔══██╗████╗░██║╚══██╔══╝██╔══██╗██╔══██╗██║░░░░░██║░░░░░██╔════╝██╔══██╗
	//██║░░╚═╝██║░░██║██╔██╗██║░░░██║░░░██████╔╝██║░░██║██║░░░░░██║░░░░░█████╗░░██████╔╝
	//██║░░██╗██║░░██║██║╚████║░░░██║░░░██╔══██╗██║░░██║██║░░░░░██║░░░░░██╔══╝░░██╔══██╗
	//╚█████╔╝╚█████╔╝██║░╚███║░░░██║░░░██║░░██║╚█████╔╝███████╗███████╗███████╗██║░░██║
	//░╚════╝░░╚════╝░╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝░╚════╝░╚══════╝╚══════╝╚══════╝╚═╝░░╚═╝

	public BaseController() {
		panelAPI = BackgroundController.getPanelAPI();
		machines = panelAPI.panel.getMachines();
		games = panelAPI.panel.getGames();
	}


	//████████╗███████╗██████╗░███╗░░░███╗██╗███╗░░██╗░█████╗░██╗░░░░░
	//╚══██╔══╝██╔════╝██╔══██╗████╗░████║██║████╗░██║██╔══██╗██║░░░░░
	//░░░██║░░░█████╗░░██████╔╝██╔████╔██║██║██╔██╗██║███████║██║░░░░░
	//░░░██║░░░██╔══╝░░██╔══██╗██║╚██╔╝██║██║██║╚████║██╔══██║██║░░░░░
	//░░░██║░░░███████╗██║░░██║██║░╚═╝░██║██║██║░╚███║██║░░██║███████╗
	//░░░╚═╝░░░╚══════╝╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═╝╚═╝░░╚══╝╚═╝░░╚═╝╚══════╝
	//░█████╗░░█████╗░███╗░░██╗████████╗██████╗░░█████╗░██╗░░░░░██╗░░░░░███████╗██████╗░
	//██╔══██╗██╔══██╗████╗░██║╚══██╔══╝██╔══██╗██╔══██╗██║░░░░░██║░░░░░██╔════╝██╔══██╗
	//██║░░╚═╝██║░░██║██╔██╗██║░░░██║░░░██████╔╝██║░░██║██║░░░░░██║░░░░░█████╗░░██████╔╝
	//██║░░██╗██║░░██║██║╚████║░░░██║░░░██╔══██╗██║░░██║██║░░░░░██║░░░░░██╔══╝░░██╔══██╗
	//╚█████╔╝╚█████╔╝██║░╚███║░░░██║░░░██║░░██║╚█████╔╝███████╗███████╗███████╗██║░░██║
	//░╚════╝░░╚════╝░╚═╝░░╚══╝░░░╚═╝░░░╚═╝░░╚═╝░╚════╝░╚══════╝╚══════╝╚══════╝╚═╝░░╚═╝
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
	private void clearOutputTerminal() {
		log.getItems().clear();


	}
	//██████╗░███████╗██████╗░░██████╗██╗░██████╗████████╗███████╗███╗░░██╗░█████╗░███████╗
	//██╔══██╗██╔════╝██╔══██╗██╔════╝██║██╔════╝╚══██╔══╝██╔════╝████╗░██║██╔══██╗██╔════╝
	//██████╔╝█████╗░░██████╔╝╚█████╗░██║╚█████╗░░░░██║░░░█████╗░░██╔██╗██║██║░░╚═╝█████╗░░
	//██╔═══╝░██╔══╝░░██╔══██╗░╚═══██╗██║░╚═══██╗░░░██║░░░██╔══╝░░██║╚████║██║░░██╗██╔══╝░░
	//██║░░░░░███████╗██║░░██║██████╔╝██║██████╔╝░░░██║░░░███████╗██║░╚███║╚█████╔╝███████╗

	/**
	 * Loads data from panel.xml
	 */
	@FXML
	private void loadData() {
		BackgroundController.loadData();
		BackgroundController.setPanelAPI(panelAPI);
		//deselectMachine();
		//deselectGame();
		machines = panelAPI.panel.getMachines();
		games = panelAPI.panel.getGames();
		initialize(null, null);
	}


	/**
	 * Saves data to panel.xml
	 */
	@FXML
	private void saveData() {
		BackgroundController.saveData();
	}

	/**
	 * Will load the panel with new data
	 */
	@FXML
	private void newPanel() throws IOException {
		BackgroundController.setPanelAPI(new PanelAPI(staff.getText()));
		App.setRoot("main");
		App.getStageInfo().setTitle("Game Panel | Ports");
		App.getStageInfo().setHeight(900);
		App.getStageInfo().setWidth(1400);
		App.getStageInfo().setResizable(false);


	}

	/**
	 * Setup of basepanel
	 */
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

	/**
	 * Call this method if you wanna close the application
	 */
	@FXML
	private void quit() {
		javafx.application.Platform.exit();
	}


	/**
	 * Will load the panel with the saved data panel.xml
	 */
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

	//██╗░░██╗███████╗██╗░░░░░██████╗░░░░░░██╗░░░░░░░██╗██╗███╗░░██╗██████╗░░█████╗░░██╗░░░░░░░██╗
	//██║░░██║██╔════╝██║░░░░░██╔══██╗░░░░░██║░░██╗░░██║██║████╗░██║██╔══██╗██╔══██╗░██║░░██╗░░██║
	//███████║█████╗░░██║░░░░░██████╔╝░░░░░╚██╗████╗██╔╝██║██╔██╗██║██║░░██║██║░░██║░╚██╗████╗██╔╝
	//██╔══██║██╔══╝░░██║░░░░░██╔═══╝░░░░░░░████╔═████║░██║██║╚████║██║░░██║██║░░██║░░████╔═████║░
	//██║░░██║███████╗███████╗██║░░░░░░░░░░░╚██╔╝░╚██╔╝░██║██║░╚███║██████╔╝╚█████╔╝░░╚██╔╝░╚██╔╝░
	//╚═╝░░╚═╝╚══════╝╚══════╝╚═╝░░░░░░░░░░░░╚═╝░░░╚═╝░░╚═╝╚═╝░░╚══╝╚═════╝░░╚════╝░░░░╚═╝░░░╚═╝░░
	@FXML
	private void openHelpMenu() throws IOException {
		popuproot = FXMLLoader.load(getClass().getResource("help.fxml"));
		popupstage.setResizable(false);
		popupstage.setTitle("Game Panel | About & Help Centre");
		popupScene = new Scene(popuproot);
		popupstage.setScene(popupScene);
		popupstage.show();
	}


	//██████╗░███████╗░██████╗███████╗██╗░░░░░███████╗░█████╗░████████╗░██████╗
	//██╔══██╗██╔════╝██╔════╝██╔════╝██║░░░░░██╔════╝██╔══██╗╚══██╔══╝██╔════╝
	//██║░░██║█████╗░░╚█████╗░█████╗░░██║░░░░░█████╗░░██║░░╚═╝░░░██║░░░╚█████╗░
	//██║░░██║██╔══╝░░░╚═══██╗██╔══╝░░██║░░░░░██╔══╝░░██║░░██╗░░░██║░░░░╚═══██╗
	//██████╔╝███████╗██████╔╝███████╗███████╗███████╗╚█████╔╝░░░██║░░░██████╔╝
	//╚═════╝░╚══════╝╚═════╝░╚══════╝╚══════╝╚══════╝░╚════╝░░░░╚═╝░░░╚═════╝░
	//TODO Test if deselects work correctly in every case/scenario
	@FXML
	private void deselectMachines() {
		machineNameText.setText("-");
		machineNameBox.setText("");
		gameSelectedMachineBox.setText("[No selection]");
		portSelectedMachineBox.setText("[No selection]");
		machineManufacturerBox.setText("");
		machineRRPBox.setText("");
		machineMediaBox.setText("");
		machineTypeBox.setText("");
		machineYearBox.setText("");
		machineURLBox.setText("");
		machineDescriptionBox.setText("");
		selectedMachine = null;
		machineListView.getSelectionModel().clearSelection();
	}

	@FXML
	private void deselectGames() {
		gameNameText.setText("-");
		gameNameBox.setText("");
		gameDeveloperBox.setText("");
		gamePublisherBox.setText("");
		gameURLBox.setText("");
		gameYearBox.setText("");
		gameDescriptionBox.setText("");
		gameCurrentMachineBox.setText("");
		deselectPorts();
		portListView.setItems(null);
		selectedGame = null;
		gameListView.getSelectionModel().clearSelection();
	}

	@FXML
	private void deselectPorts() {
		portNameText.setText("");
		portDeveloperBox.setText("");
		portYearBox.setText("");
		portURLBox.setText("");
		portCurrentMachineBox.setText("");
		selectedPort = null;
		portListView.getSelectionModel().clearSelection();
	}

	//░█████╗░██████╗░██████╗░░██████╗
	//██╔══██╗██╔══██╗██╔══██╗██╔════╝
	//███████║██║░░██║██║░░██║╚█████╗░
	//██╔══██║██║░░██║██║░░██║░╚═══██╗
	//██║░░██║██████╔╝██████╔╝██████╔╝
	//╚═╝░░╚═╝╚═════╝░╚═════╝░╚═════╝░
	//TODO Test adds
	//TODO Add validation
	@FXML
	private void addMachine() {
		try {
			if (!machineNameBox.getText().isEmpty() && !machineManufacturerBox.getText().isEmpty() && !machineDescriptionBox.getText().isEmpty() && !machineTypeBox.getText().isEmpty() && !machineMediaBox.getText().isEmpty() && !machineYearBox.getText().isEmpty() && !machineRRPBox.getText().isEmpty() && !machineURLBox.getText().isEmpty()){
				int year = Integer.parseInt(machineYearBox.getText());
				double rrp = Double.parseDouble(machineRRPBox.getText());
				if (year >= 1920 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
					Machine m = new Machine(machineNameBox.getText(), machineManufacturerBox.getText(), machineDescriptionBox.getText(), machineTypeBox.getText(), machineMediaBox.getText(), year, rrp, machineURLBox.getText());
					machines.add(m);
					terminalOutError("Machine added successfully");
					deselectMachines();
				} else {
					terminalOutError("That is not a valid year.");
				}
			} else {
				terminalOutError("All fields are required!");
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year and RRP must be valid numbers.");
		}
		refresh();
	}


	@FXML
	private void addGame() {
		try {
			if (selectedMachine != null && !gameNameBox.getText().isEmpty() && !gamePublisherBox.getText().isEmpty() && !gameDescriptionBox.getText().isEmpty() && !gameDeveloperBox.getText().isEmpty() && !gameYearBox.getText().isEmpty() && !gameURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(gameYearBox.getText());
				if (year >= 1920 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
					Game g = new Game(selectedMachine, gameNameBox.getText(), gamePublisherBox.getText(), gameDescriptionBox.getText(), gameDeveloperBox.getText(), year, gameURLBox.getText(), new CustomList<>());
					games.add(g);
					terminalOutError("Game added successfully.");
					deselectGames();
				} else {
					terminalOutError("That is not a valid year.");
				}
			} else {
				terminalOutError("All fields are required!");
			}
		} catch (NumberFormatException e) {
			terminalOut("Year must be a number.");
		}
		refresh();
	}


	@FXML
	private void addPort() {
		try {
			if (selectedMachine != null && !portDeveloperBox.getText().isEmpty() && !portYearBox.getText().isEmpty() && !portURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(portYearBox.getText());
				if (year >= 1920 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
					Port p = new Port(selectedMachine, portDeveloperBox.getText(), year, portURLBox.getText());
					selectedGame.getPorts().add(p);
					terminalOutError("Game port Successfully added.");
					deselectPorts();
				} else {
					terminalOutError("That is not a valid year.");
				}
			} else {
				terminalOutError("All fields are required.");
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year must be a valid number.");
		}
		updateData();
	}


	//██████╗░███████╗███╗░░░███╗░█████╗░██╗░░░██╗███████╗░██████╗
	//██╔══██╗██╔════╝████╗░████║██╔══██╗██║░░░██║██╔════╝██╔════╝
	//██████╔╝█████╗░░██╔████╔██║██║░░██║╚██╗░██╔╝█████╗░░╚█████╗░
	//██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░██║░╚████╔╝░██╔══╝░░░╚═══██╗
	//██║░░██║███████╗██║░╚═╝░██║╚█████╔╝░░╚██╔╝░░███████╗██████╔╝
	//╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░╚════╝░░░░╚═╝░░░╚══════╝╚═════╝░
	//TODO Test removes
	@FXML
	private void removeMachine() {
		if (selectedMachine != null) {
			machines.remove(selectedMachine);
			deselectMachines();
		}
		refresh();

	}

	@FXML
	private void removeGame() {
		if (selectedGame != null) {
			games.remove(selectedGame);
		}
		refresh();

	}

	@FXML
	private void removePort() {
		if (selectedPort != null && selectedGame != null) {
			selectedGame.getPorts().remove(selectedPort);
		}
		refresh();

	}

	//██╗░░░██╗██████╗░██████╗░░█████╗░████████╗███████╗░██████╗
	//██║░░░██║██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██╔════╝
	//██║░░░██║██████╔╝██║░░██║███████║░░░██║░░░█████╗░░╚█████╗░
	//██║░░░██║██╔═══╝░██║░░██║██╔══██║░░░██║░░░██╔══╝░░░╚═══██╗
	//╚██████╔╝██║░░░░░██████╔╝██║░░██║░░░██║░░░███████╗██████╔╝
	//░╚═════╝░╚═╝░░░░░╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═════╝░

	//TODO Write updates


	//TODO Update Machine

	//TODO Update Game
	@FXML
	private void updateMachine() {
		try {
			if (selectedMachine != null && !machineNameBox.getText().isEmpty() && !machineManufacturerBox.getText().isEmpty() && !machineDescriptionBox.getText().isEmpty() && !machineTypeBox.getText().isEmpty() && !machineMediaBox.getText().isEmpty() && !machineYearBox.getText().isEmpty() && !machineRRPBox.getText().isEmpty() && !machineURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(machineYearBox.getText());
				double rrp = Double.parseDouble(machineRRPBox.getText());
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);

				if (year >= 1920 && year <= currentYear) {
					selectedMachine.setName(machineNameBox.getText());
					selectedMachine.setManufacturer(machineManufacturerBox.getText());
					selectedMachine.setDescription(machineDescriptionBox.getText());
					selectedMachine.setType(machineTypeBox.getText());
					selectedMachine.setMedia(machineMediaBox.getText());
					selectedMachine.setLaunchYear(year);
					selectedMachine.setRRP(rrp);
					selectedMachine.setImage(machineURLBox.getText());

					terminalOutError("Machine updated successfully.");
					deselectMachines();
				} else {
					terminalOutError("That is not a valid year.");
				}
			} else {
				terminalOutError("All fields are required!");
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year and RRP must be valid numbers.");
		}
		refresh();
	}

	@FXML
	private void updateGame() {
		try {
			if (selectedGame != null && !gameNameBox.getText().isEmpty() && !gamePublisherBox.getText().isEmpty() && !gameDescriptionBox.getText().isEmpty() && !gameDeveloperBox.getText().isEmpty() && !gameYearBox.getText().isEmpty() && !gameURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(gameYearBox.getText());
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);

				if (year >= 1920 && year <= currentYear) {
					selectedGame.setMachine(selectedMachine);
					selectedGame.setName(gameNameBox.getText());
					selectedGame.setPublisher(gamePublisherBox.getText());
					selectedGame.setDescription(gameDescriptionBox.getText());
					selectedGame.setDeveloper(gameDeveloperBox.getText());
					selectedGame.setReleaseYear(year);
					selectedGame.setCover(gameURLBox.getText());

					terminalOutError("Game updated successfully.");
					deselectGames();
				} else {
					terminalOutError("That is not a valid year.");
				}
			} else {
				terminalOutError("All fields are required!");
			}
		} catch (NumberFormatException e) {
			terminalOut("Year must be a number.");
		}
		refresh();
	}

	//TODO Update Port
	@FXML
	public void updatePort() {
		try {
			if (selectedPort != null && !portDeveloperBox.getText().isEmpty() && !portYearBox.getText().isEmpty() && !portURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(portYearBox.getText());
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);

				if (year >= 1920 && year <= currentYear) {
					selectedPort.setMachine(selectedMachine);
					selectedPort.setDeveloper(portDeveloperBox.getText());
					selectedPort.setReleaseYear(year);
					selectedPort.setCover(portURLBox.getText());
					terminalOutError("Game port successfully updated.");
					deselectPorts();
				} else {
					terminalOutError("That is not a valid year.");
				}
			} else {
				terminalOutError("All fields are required.");
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year must be a valid number.");
		}
		updateData();
	}


	//░██████╗██╗░░░██╗███╗░░██╗░█████╗░
	//██╔════╝╚██╗░██╔╝████╗░██║██╔══██╗
	//╚█████╗░░╚████╔╝░██╔██╗██║██║░░╚═╝
	//░╚═══██╗░░╚██╔╝░░██║╚████║██║░░██╗
	//██████╔╝░░░██║░░░██║░╚███║╚█████╔╝
	//╚═════╝░░░░╚═╝░░░╚═╝░░╚══╝░╚════╝░
	@FXML
	private void refresh() {
		initialize(null, null);
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

		gameListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// This will be called whenever the user selects a different item in the list
			if (newValue != null) {
				// Call initialize or any specific update method
				//refresh();
				updateData();
			}
		});

		machineListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// This will be called whenever the user selects a different item in the list
			if (newValue != null) {
				// Call initialize or any specific update method
				//refresh();
				updateOnlyMachines();
			}
		});
		portListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			// This will be called whenever the user selects a different item in the list
			if (newValue != null) {
				// Call initialize or any specific update method
				//refresh();
				updateOnlyPortsData();
			}
		});
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

	/**
	 * Updates values in machine related boxes and in listview
	 */
	private void updateOnlyMachines() {
		selectedMachine = machineListView.getSelectionModel().getSelectedItem();
		if (selectedMachine != null) {
			machineNameText.setText(selectedMachine.getName() + " by " + selectedMachine.getManufacturer());
			machineNameBox.setText(selectedMachine.getName());
			gameSelectedMachineBox.setText(selectedMachine.toString());
			portSelectedMachineBox.setText(selectedMachine.toString());
			machineManufacturerBox.setText(selectedMachine.getManufacturer());
			machineRRPBox.setText(String.valueOf(selectedMachine.getRRP()));
			machineMediaBox.setText(selectedMachine.getMedia());
			machineTypeBox.setText(selectedMachine.getType());
			machineYearBox.setText(String.valueOf(selectedMachine.getLaunchYear()));
			machineURLBox.setText(selectedMachine.getImage());
			machineDescriptionBox.setText(selectedMachine.getDescription());
		}
	}

	/**
	 * Updates values in game related boxes and in listview
	 * It also forces an update on the port listview as we have a list of ports for each game.
	 */
	private void updateData() {
		selectedGame = gameListView.getSelectionModel().getSelectedItem();
		if (selectedGame != null) {
			deselectPorts();
			if (selectedGame.getPorts() == null) {
				portListView.setItems(null);
			} else {
				portListView.setItems(FXCollections.observableList(selectedGame.getPorts()));
				portListView.refresh();
				updateOnlyPortsData();
			}
			gameNameText.setText(selectedGame.getName());
			gameNameBox.setText(selectedGame.getName());
			gameDeveloperBox.setText(selectedGame.getDeveloper());
			gamePublisherBox.setText(selectedGame.getPublisher());
			gameURLBox.setText(selectedGame.getCover());
			gameYearBox.setText(String.valueOf(selectedGame.getReleaseYear()));
			gameDescriptionBox.setText(selectedGame.getDescription());
			gameCurrentMachineBox.setText(selectedGame.getMachine().toString());
			//selectedMachine = gameListView.getSelectionModel().getSelectedItem().getMachine();
			//selectedMachine = selectedGame.getMachine();
		}

	}

	/**
	 * Updates values in port related boxes and in listview
	 */
	private void updateOnlyPortsData() {
		selectedPort = portListView.getSelectionModel().getSelectedItem();
		if (selectedPort != null) {
			portNameText.setText(selectedPort.getMachine().toString() + " by " + selectedPort.getDeveloper());
			portDeveloperBox.setText(selectedPort.getDeveloper());
			portYearBox.setText(String.valueOf(selectedPort.getReleaseYear()));
			portURLBox.setText(selectedPort.getCover());
			portCurrentMachineBox.setText(selectedPort.getMachine().toString());
			//selectedMachine = portListView.getSelectionModel().getSelectedItem().getMachine();
		}
	}
}