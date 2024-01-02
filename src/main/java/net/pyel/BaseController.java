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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.pyel.models.*;
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
	ListView<TerminalElement> log = new ListView<>();
	@FXML
	TextField staff = new TextField();
	String previousCommand = "";
	TerminalElement selectedTerminalElement = new TerminalElement("", null);


	@FXML
	private void logPrompt() {
		String input = logInput.getText();
		logInput.clear();
		if (input.length() == 3) {
			if (input.toLowerCase().substring(0, 3).equals("log")) {
				terminalOutError("Usage: log [message]", null);
				return;
			}
		}
		if (input.length() < 4) {
			terminalOutError("Invalid command in terminal prompt. Type \"help\" for help.", null);
			return;
		}
		if (input.length() == 4) {
			if (input.toLowerCase().substring(0, 4).equals("help")) {

				terminalOutHelp("---------------------------------------------------------------------------------------------", null);
				terminalOutHelp("Example: find m 2 n %", null);
				terminalOutHelp("Example: find m 2 n PlayStation", null);
				terminalOutHelp("    [value]  (value to look for in that datatype or type in only \"%\" to list all)", null);
				terminalOutHelp("    [data] > n (name), d (description)", null);
				terminalOutHelp("           > 3 (by name - alphabetical order from bottom), 4 (by name - alphabetical order from top)", null);
				terminalOutHelp("    [sort] > 0 (default), 1 (by year - biggest from top), 2 (by year - lowest from top)", null);
				terminalOutHelp("    [type] > m (for machine), g (for game)", null);
				terminalOutHelp("find [type] [sort] [data] [value] - find all elements with the given value", null);
				terminalOutHelp("---------------------------------------------FIND--------------------------------------------", null);
				terminalOutHelp("Example: search m PlayStation 5", null);
				terminalOutHelp("    [value]  (value of the name we're looking for)", null);
				terminalOutHelp("    [type] > m (for machine), g (for game)", null);
				terminalOutHelp("search [type] [value] - find a specific element by its name", null);
				terminalOutHelp("--------------------------------------------SEARCH-------------------------------------------", null);
				terminalOutHelp("reset - Resets the program, erases loaded data", null);
				terminalOutHelp("debug - Switch debug mode (current: " + panelAPI.panel.isDebugMode() + ")", null);
				terminalOutHelp("------------------------------------------FACILITIES-----------------------------------------", null);
				terminalOutHelp("clear - Clear terminal", null);
				terminalOutHelp("bogosort - Bogo", null);
				terminalOutHelp("staff [name] - Change staff", null);
				terminalOutHelp("log [message] - Log a message", null);
				terminalOutHelp("help - Help page", null);
				terminalOutHelp("-------------------------------------------HELP MENU-----------------------------------------", null);
				terminalOut("HELP executed", null);
				return;
			}
		}

		if (input.length() == 4 || input.length() == 5) {
			if (input.toLowerCase().substring(0, 4).equals("find")) {
				terminalOutError("Example: find m 2 n %", null);
				terminalOutError("Example: find m 2 n PlayStation", null);
				terminalOutError("    [value]  (value to look for in that datatype or type in only \"%\" to list all)", null);
				terminalOutError("    [data] > n (name), d (description)", null);
				terminalOutError("           > 3 (by name - alphabetical order from bottom), 4 (by name - alphabetical order from top)", null);
				terminalOutError("    [sort] > 0 (default), 1 (by year - biggest from top), 2 (by year - lowest from top)", null);
				terminalOutError("    [type] > m (for machine), g (for game)", null);
				terminalOutError("Usage: find [type] [sort] [data] [value] - find all elements with the given value", null);
				return;
			}
		}
		if (input.length() == 5) {
			if (input.toLowerCase().substring(0, 5).equals("clear")) {
				terminalOut("CLEAR executed", null);
				log.getItems().clear();
				return;
			} else if (input.toLowerCase().substring(0, 5).equals("debug")) {
				switchDebugMode();
				return;
			} else if (input.toLowerCase().substring(0, 5).equals("reset")) {
				log.getItems().clear();
				terminalOut("RESET executed", null);
				try {
					newPanel();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				return;
			}
		}
		if (input.length() == 6 || input.length() == 7) {
			if (input.toLowerCase().substring(0, 6).equals("search")) {
				terminalOutError("       Example: search m PlayStation 5", null);
				terminalOutError("       [value]  (value of the name we're looking for)", null);
				terminalOutError("       [type] > m (for machine), g (for game)", null);
				terminalOutError("Usage: search [type] [value] - find a specific element by its name", null);
				return;
			}
		}
		if (input.length() > 5) {
			if (input.toLowerCase().substring(0, 5).equals("find ")) {
				String type = input.substring(5, 6);
				String sort = input.substring(7, 8);
				String data = input.substring(9, 10);
				String value = input.substring(11);
				terminalOut("FIND EXECUTED AS: " + type + " " + sort + " " + data + " " + value, null);
				if (type.equals("m") || type.equals("g")) {
					if (data.equals("n") || data.equals("d")) {
						if (sort.equals("0") || sort.equals("1") || sort.equals("2") || sort.equals("3") || sort.equals("4")) {
							CustomList<TerminalElement> lte = panelAPI.panel.find(type, sort, data, value);
							for (TerminalElement te : lte) {
								terminalOutHelp(te);
							}
							terminalOutHelp("----FIND_OUTPUT----", null);
						}
					}
				} else {
					terminalOut("\"" + type + "\" is invalid, possible options: m, g", null);
				}

				return;
			}
		}
		if (input.length() > 7) {
			if (input.toLowerCase().substring(0, 7).equals("search ")) {
				String type = input.substring(7, 8);
				String value = input.substring(9);
				if (type.equals("m") || type.equals("g")) {
					TerminalElement te = panelAPI.panel.search(type, value, null);
					terminalOut(te);
				} else {
					terminalOut("\"" + type + "\" is invalid, possible options: m, g", null);
				}

				return;
			}
		}
		if (input.length() > 5) {
			if (input.toLowerCase().substring(0, 6).equals("staff ")) {
				String name = input.substring(6);
				panelAPI.currentStaff = name;
				terminalOut("Look at me! I'm Mr. Meeseeks!", null);
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
					terminalOutHelp("" + variable, null);
					log.getItems().clear();
					count++;
				}
				terminalOut("Your public static void main String args[" + numero + "] quantum bogo is found in " + count + " attempt(s).", null);

				return;
			}
		}
		if (input.toLowerCase().substring(0, 4).equals("log ")) {

			if (input.length() > 4) {
				String message = input.substring(4);
				terminalOut(message, null);
			} else {
				terminalOutError("Usage: log [message]", null);
			}
		} else {
			terminalOutError("Invalid command in log prompt. Type \"help\" for help.", null);
		}
	}

	public void switchDebugMode() {
		boolean current = panelAPI.panel.isDebugMode();
		if (current) {
			panelAPI.panel.setDebugMode(false);
		} else {
			panelAPI.panel.setDebugMode(true);
		}
		terminalOut("Set DEBUG mode to " + panelAPI.panel.isDebugMode(), null);
	}

	@FXML
	public void inspectElement() {
		refresh();
		Object ie = selectedTerminalElement.getInspectionElemenet();
		if (ie == null) {
			terminalOutError("No element to inspect", null);
		} else if (ie instanceof String) {
			terminalOutHelp("---------------------------------------------------------------------------------------------", null);
			terminalOutHelp((String) ie, ie);
			terminalOutHelp("Inspected element (String): ", ie);
			terminalOutHelp("---------------------------------------------------------------------------------------------", null);
		} else if (ie instanceof Link) {
			terminalOutHelp("Opening link", ie);
			openWebpage(((Link) ie).getLink());
		} else if (ie instanceof Machine) {
			terminalOut("---------------------------------------------------------------------------------------------", null);
			terminalOutHelp("    Description > inspect", ((Machine) ie).getDescription());
			terminalOutHelp("    Image URL: " + ((Machine) ie).getImage(), new Link(((Machine) ie).getImage()));
			terminalOutHelp("    RRP: " + ((Machine) ie).getRRP(), null);
			terminalOutHelp("    Launch Year: " + ((Machine) ie).getLaunchYear(), null);
			terminalOutHelp("    Media: " + ((Machine) ie).getMedia(), null);
			terminalOutHelp("    Type: " + ((Machine) ie).getType(), null);
			terminalOutHelp("    Manufacturer: " + ((Machine) ie).getManufacturer(), null);
			terminalOutHelp("    Name: " + ((Machine) ie).getName(), ie);
			terminalOutHelp("Inspected element (Machine): " + ((Machine) ie).getName(), ie);
			terminalOutHelp("---------------------------------------------------------------------------------------------", null);
		} else if (ie instanceof Game) {
			terminalOut("---------------------------------------------------------------------------------------------", null);
			for (Port p : ((Game) ie).getPorts()) {
				terminalOutHelp("           " + p.getMachine() + " by " + p.getDeveloper() + "[" + p.getReleaseYear() + "]", p);
			}
			terminalOutHelp("    Ports ˇˇ", null);
			terminalOutHelp("    Description > inspect", ((Game) ie).getDescription());
			terminalOutHelp("    Cover URL: " + ((Game) ie).getCover(), new Link(((Game) ie).getCover()));
			terminalOutHelp("    Release Year: " + ((Game) ie).getReleaseYear(), null);
			terminalOutHelp("    Developer: " + ((Game) ie).getDeveloper(), null);
			terminalOutHelp("    Publisher: " + ((Game) ie).getPublisher(), null);
			terminalOutHelp("    Original Machine: " + ((Game) ie).getMachine().getName(), ((Game) ie).getMachine());
			terminalOutHelp("    Name: " + ((Game) ie).getName(), ie);
			terminalOutHelp("Inspected element (Game): " + ((Game) ie).getName(), ie);
			terminalOutHelp("---------------------------------------------------------------------------------------------", null);
		} else if (ie instanceof Port) {
			terminalOut("---------------------------------------------------------------------------------------------", null);
			terminalOutHelp("    Cover: " + ((Port) ie).getCover(), new Link(((Port) ie).getCover()));
			terminalOutHelp("    Release Year: " + ((Port) ie).getReleaseYear(), null);
			terminalOutHelp("    Developer: " + ((Port) ie).getDeveloper(), null);
			terminalOutHelp("    Machine: " + ((Port) ie).getMachine().getName(), ((Port) ie).getMachine());
			terminalOutHelp("Inspected element (Port): " + ((Port) ie).getMachine(), ie);
			terminalOutHelp("---------------------------------------------------------------------------------------------", null);
		}
	}

	public static void openWebpage(String url) {
		try {
			new ProcessBuilder("x-www-browser", url).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void terminalReverse(String outPutView, Object inspectionElement) {
		TerminalElement nte = new TerminalElement(outPutView, inspectionElement);
		System.out.println(nte.toString());
		log.getItems().add(nte);
	}

	public void terminalOut(String outPutView, Object inspectionElement) {
		TerminalElement nte = new TerminalElement("[" + panelAPI.currentStaff + "]" + " > " + outPutView, inspectionElement);
		if (panelAPI.currentStaff.isEmpty()) {
			nte = new TerminalElement(" > " + outPutView, inspectionElement);
		}
		System.out.println(nte);
		log.getItems().add(0, nte);
	}

	public void terminalOut(TerminalElement te) {
		TerminalElement nte = new TerminalElement("[" + panelAPI.currentStaff + "]" + " > " + te.getOutPutView(), te.getInspectionElemenet());
		if (panelAPI.currentStaff.isEmpty()) {
			nte = new TerminalElement(" > " + te.getOutPutView(), te.getInspectionElemenet());
		}
		System.out.println(nte);
		log.getItems().add(0, nte);
	}

	public void terminalOutError(String outPutView, Object inspectionElement) {
		TerminalElement nte = new TerminalElement("(!) " + outPutView, inspectionElement);
		System.out.println(nte.toString());
		log.getItems().add(0, nte);
	}

	public void terminalOutHelp(TerminalElement te) {
		TerminalElement nte = new TerminalElement("| " + te.getOutPutView(), te.getInspectionElemenet());
		System.out.println(nte.toString());
		log.getItems().add(0, nte);
	}

	public void terminalOutHelp(String outPutView, Object inspectionElement) {
		TerminalElement nte = new TerminalElement("| " + outPutView, inspectionElement);
		System.out.println(nte.toString());
		log.getItems().add(0, nte);
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
		App.getStageInfo().setHeight(900);//920
		App.getStageInfo().setWidth(1400);//1434
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
		imageOfMachine.setImage(null);
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
		imageOfGame.setImage(null);
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
		imageOfPort.setImage(null);
		portListView.getSelectionModel().clearSelection();
	}

	//░█████╗░██████╗░██████╗░░██████╗
	//██╔══██╗██╔══██╗██╔══██╗██╔════╝
	//███████║██║░░██║██║░░██║╚█████╗░
	//██╔══██║██║░░██║██║░░██║░╚═══██╗
	//██║░░██║██████╔╝██████╔╝██████╔╝
	//╚═╝░░╚═╝╚═════╝░╚═════╝░╚═════╝░

	@FXML
	private void addSampleData() {/*
		panelAPI.panel.addMachine(new Machine("PlayStation (PS)", "Sony", "The original PlayStation, a game-changing console in the 90s.", "Home console", "CD-ROM", 1994, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PS One", "Sony", "A smaller, redesigned version of the original PlayStation.", "Home console", "CD-ROM", 2000, 99, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PlayStation 2 (PS2)", "Sony", "Best-selling console ever, known for its vast game library.", "Home console", "DVD-ROM", 2000, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PlayStation 2 Slimline", "Sony", "A slimmer, more compact version of the PS2.", "Home console", "DVD-ROM", 2004, 149, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PlayStation 3 (PS3)", "Sony", "Introduced Blu-ray and online gaming to PlayStation.", "Home console", "Blu-ray Disc", 2006, 499, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PlayStation 3 Slim", "Sony", "A slimmer and lighter version of the original PS3.", "Home console", "Blu-ray Disc", 2009, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PlayStation 3 Super Slim", "Sony", "The most compact version of the PS3.", "Home console", "Blu-ray Disc", 2012, 269, "imageURL"));
		panelAPI.panel.addMachine(new Machine("PlayStation 4 Slim", "Sony", "A smaller version of the original PS4.", "Home console", "Blu-ray Disc", 2016, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Nintendo Entertainment System (NES)", "Nintendo", "The console that revived the home video game market.", "Home console", "Cartridge", 1985, 179.99, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Super Nintendo Entertainment System (SNES)", "Nintendo", "Introduced advanced graphics and sound capabilities.", "Home console", "Cartridge", 1991, 199, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Nintendo 64 (N64)", "Nintendo", "First Nintendo console with 3D graphics.", "Home console", "Cartridge", 1996, 199.99, "imageURL"));
		panelAPI.panel.addMachine(new Machine("GameCube", "Nintendo", "Featured a unique cube shape and miniDVD discs.", "Home console", "MiniDVD", 2001, 199, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Wii", "Nintendo", "Introduced motion controls to gaming.", "Home console", "Wii Optical Disc", 2006, 249.99, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Wii U", "Nintendo", "Featured a tablet-like controller and HD graphics.", "Home console", "Wii U Optical Disc", 2012, 299.99, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Nintendo Switch", "Nintendo", "Hybrid console, can be used as handheld or connected to a TV.", "Hybrid console", "Game card, Digital", 2017, 299.99, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox", "Microsoft", "Microsoft's first foray into the gaming console market.", "Home console", "DVD-ROM", 2001, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox 360", "Microsoft", "Known for its online gaming service Xbox Live.", "Home console", "DVD-ROM", 2005, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox 360 S", "Microsoft", "A slimmer version of the original Xbox 360.", "Home console", "DVD-ROM", 2010, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox 360 E", "Microsoft", "A further redesigned version of the Xbox 360.", "Home console", "DVD-ROM", 2013, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox One", "Microsoft", "Integrated with live TV, streaming, and Blu-ray.", "Home console", "Blu-ray Disc", 2013, 499, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox One S", "Microsoft", "Slimmer version of Xbox One, supports HDR and 4K video.", "Home console", "Blu-ray Disc", 2016, 299, "imageURL"));
		panelAPI.panel.addMachine(new Machine("Xbox One X", "Microsoft", "Most powerful Xbox, supports 4K gaming.", "Home console", "Blu-ray Disc", 2017, 499, "imageURL"));
*//*
		panelAPI.panel.addGame(new Game(selectedMachine, "Halo Infinite", "Xbox Game Studios", "Next chapter in the legendary Halo series, featuring the Master Chief.", "343 Industries", 2021, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Forza Horizon 5", "Xbox Game Studios", "An open-world racing game set in a fictional representation of Mexico.", "Playground Games", 2021, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Gears 5", "Xbox Game Studios", "A third-person shooter, the sixth installment in the Gears of War series.", "The Coalition", 2019, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "FIFA 21", "Electronic Arts", "The latest installment in the popular FIFA football simulation series.", "EA Vancouver, EA Romania", 2020, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Cyberpunk 2077", "CD Projekt", "An open-world, action-adventure story set in the megalopolis of Night City.", "CD Projekt Red", 2020, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Call of Duty: Black Ops Cold War", "Activision", "A first-person shooter set during the early 1980s of the Cold War.", "Treyarch, Raven Software", 2020, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Hitman 3", "IO Interactive", "A stealth game, the eighth main installment in the Hitman series.", "IO Interactive", 2021, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Spider-Man: Miles Morales", "Sony Interactive Entertainment", "An action-adventure game, a follow-up to Marvel's Spider-Man.", "Insomniac Games", 2020, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Demon's Souls", "Sony Interactive Entertainment", "A remake of the original Demon's Souls, an action RPG known for its difficulty.", "Bluepoint Games, Japan Studio", 2020, "coverURL", new CustomList<>()));
		panelAPI.panel.addGame(new Game(selectedMachine, "Ratchet & Clank: Rift Apart", "Sony Interactive Entertainment", "A third-person shooter platform game in the Ratchet & Clank series.", "Insomniac Games", 2021, "coverURL", new CustomList<>()));
*/
	}

	@FXML
	private void addMachine() {
		//addSampleData();
		try {
			if (!machineNameBox.getText().isEmpty() && !machineManufacturerBox.getText().isEmpty() && !machineDescriptionBox.getText().isEmpty() && !machineTypeBox.getText().isEmpty() && !machineMediaBox.getText().isEmpty() && !machineYearBox.getText().isEmpty() && !machineRRPBox.getText().isEmpty() && !machineURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(machineYearBox.getText());
				double rrp = Double.parseDouble(machineRRPBox.getText());
				if (year >= 1920 && year <= Calendar.getInstance().get(Calendar.YEAR)) {
					Machine m = new Machine(machineNameBox.getText(), machineManufacturerBox.getText(), machineDescriptionBox.getText(), machineTypeBox.getText(), machineMediaBox.getText(), year, rrp, machineURLBox.getText());
					terminalOut("Machine add: " + panelAPI.panel.addMachine(m), null);
					terminalOutError("Machine added successfully", m);
					deselectMachines();
				} else {
					terminalOutError("That is not a valid year.", null);
				}
			} else {
				terminalOutError("All fields are required!", null);
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year and RRP must be valid numbers.", null);
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
					terminalOut("Machine add: " + panelAPI.panel.addGame(g), null);
					terminalOutError("Game added successfully.", null);
					deselectGames();
				} else {
					terminalOutError("That is not a valid year.", null);
				}
			} else {
				terminalOutError("All fields are required!", null);
			}
		} catch (NumberFormatException e) {
			terminalOut("Year must be a number.", null);
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
					selectedGame.addPort(p);
					terminalOutError("Game port Successfully added.", null);
					deselectPorts();
				} else {
					terminalOutError("That is not a valid year.", null);
				}
			} else {
				terminalOutError("All fields are required.", null);
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year must be a valid number.", null);
		}
		updateData();
	}


	//██████╗░███████╗███╗░░░███╗░█████╗░██╗░░░██╗███████╗░██████╗
	//██╔══██╗██╔════╝████╗░████║██╔══██╗██║░░░██║██╔════╝██╔════╝
	//██████╔╝█████╗░░██╔████╔██║██║░░██║╚██╗░██╔╝█████╗░░╚█████╗░
	//██╔══██╗██╔══╝░░██║╚██╔╝██║██║░░██║░╚████╔╝░██╔══╝░░░╚═══██╗
	//██║░░██║███████╗██║░╚═╝░██║╚█████╔╝░░╚██╔╝░░███████╗██████╔╝
	//╚═╝░░╚═╝╚══════╝╚═╝░░░░░╚═╝░╚════╝░░░░╚═╝░░░╚══════╝╚═════╝░
	@FXML
	private void removeMachine() {
		if (selectedMachine != null) {
			panelAPI.panel.removeMachine(selectedMachine);
			deselectMachines();
		}
		refresh();

	}

	@FXML
	private void removeGame() {
		if (selectedGame != null) {
			panelAPI.panel.removeGame(selectedGame);
		}
		refresh();

	}

	@FXML
	private void removePort() {
		if (selectedPort != null && selectedGame != null) {
			selectedGame.removePort(selectedPort);
		}
		refresh();

	}

	//██╗░░░██╗██████╗░██████╗░░█████╗░████████╗███████╗░██████╗
	//██║░░░██║██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔════╝██╔════╝
	//██║░░░██║██████╔╝██║░░██║███████║░░░██║░░░█████╗░░╚█████╗░
	//██║░░░██║██╔═══╝░██║░░██║██╔══██║░░░██║░░░██╔══╝░░░╚═══██╗
	//╚██████╔╝██║░░░░░██████╔╝██║░░██║░░░██║░░░███████╗██████╔╝
	//░╚═════╝░╚═╝░░░░░╚═════╝░╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═════╝░

	@FXML
	private void updateMachine() {
		try {
			if (selectedMachine != null && !machineNameBox.getText().isEmpty() && !machineManufacturerBox.getText().isEmpty() && !machineDescriptionBox.getText().isEmpty() && !machineTypeBox.getText().isEmpty() && !machineMediaBox.getText().isEmpty() && !machineYearBox.getText().isEmpty() && !machineRRPBox.getText().isEmpty() && !machineURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(machineYearBox.getText());
				double rrp = Double.parseDouble(machineRRPBox.getText());
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);

				if (year >= 1920 && year <= currentYear) {
					panelAPI.panel.updateMachine(selectedMachine, machineNameBox.getText(), machineManufacturerBox.getText(), machineDescriptionBox.getText(), machineTypeBox.getText(), machineMediaBox.getText(), year, rrp, machineURLBox.getText());
					//selectedMachine.setName(machineNameBox.getText());
					//selectedMachine.setManufacturer(machineManufacturerBox.getText());
					//selectedMachine.setDescription(machineDescriptionBox.getText());
					//selectedMachine.setType(machineTypeBox.getText());
					//selectedMachine.setMedia(machineMediaBox.getText());
					//selectedMachine.setLaunchYear(year);
					//selectedMachine.setRRP(rrp);
					//selectedMachine.setImage(machineURLBox.getText());
//
					terminalOutError("Machine updated successfully.", null);
					deselectMachines();
				} else {
					terminalOutError("That is not a valid year.", null);
				}
			} else {
				terminalOutError("All fields are required!", null);
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year and RRP must be valid numbers.", null);
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
					Machine machineToSet = selectedMachine;
					panelAPI.panel.updateGame(selectedGame, machineToSet, gameNameBox.getText(), gamePublisherBox.getText(), gameDescriptionBox.getText(), gameDeveloperBox.getText(), year, gameURLBox.getText());

					//selectedGame.setName(gameNameBox.getText());
					//selectedGame.setPublisher(gamePublisherBox.getText());
					//selectedGame.setDescription(gameDescriptionBox.getText());
					//selectedGame.setDeveloper(gameDeveloperBox.getText());
					//selectedGame.setReleaseYear(year);
					//selectedGame.setCover(gameURLBox.getText());

					terminalOutError("Game updated successfully.", null);
					deselectGames();
				} else {
					terminalOutError("That is not a valid year.", null);
				}
			} else {
				terminalOutError("All fields are required!", null);
			}
		} catch (NumberFormatException e) {
			terminalOut("Year must be a number.", null);
		}
		refresh();
	}

	@FXML
	public void updatePort() {
		try {
			if (selectedPort != null && !portDeveloperBox.getText().isEmpty() && !portYearBox.getText().isEmpty() && !portURLBox.getText().isEmpty()) {
				int year = Integer.parseInt(portYearBox.getText());
				int currentYear = Calendar.getInstance().get(Calendar.YEAR);

				if (year >= 1920 && year <= currentYear) {
					if (selectedMachine != null) {
						selectedPort.setMachine(selectedMachine);
					}
					selectedPort.setDeveloper(portDeveloperBox.getText());
					selectedPort.setReleaseYear(year);
					selectedPort.setCover(portURLBox.getText());
					terminalOutError("Game port successfully updated.", null);
					deselectPorts();
				} else {
					terminalOutError("That is not a valid year.", null);
				}
			} else {
				terminalOutError("All fields are required.", null);
			}
		} catch (NumberFormatException e) {
			terminalOutError("Year must be a valid number.", null);
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
		selectedTerminalElement = log.getSelectionModel().getSelectedItem();
		updateData();


	}
	ImageView imageView = new ImageView();
	public String image;
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
			machineImage = new Image(selectedMachine.getImage());
			imageOfMachine.setImage(machineImage);
			machineDescriptionBox.setText(selectedMachine.getDescription());
		}
	}

	/**
	 * Updates values in game related boxes and in listview
	 * It also forces an update on the port listview as we have a list of ports for each game.
	 */
	public ImageView imageOfGame = new ImageView();
	public ImageView imageOfMachine = new ImageView();
	public ImageView imageOfPort = new ImageView();
	public Image gameImage;
	public Image portImage;
	public Image machineImage;
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
			gameImage = new Image(selectedGame.getCover());
			imageOfGame.setImage(gameImage);
			System.out.println(selectedGame.getCover());
			System.out.println(image);
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
			portImage = new Image(selectedPort.getCover());
			imageOfPort.setImage(portImage);
			portCurrentMachineBox.setText(selectedPort.getMachine().toString());
			//selectedMachine = portListView.getSelectionModel().getSelectedItem().getMachine();
		}
	}
}