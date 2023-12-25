package net.pyel;

import net.pyel.models.Port;
import net.pyel.utils.CustomList;

/**
 * Manages saves and loads in the background mainly
 *
 * @author Zalán Tóth
 */
public class BackgroundController {
	private static PanelAPI panelAPI = new PanelAPI("");
	private CustomList<Port> machines = panelAPI.panel.getMachines();

	public static PanelAPI getPanelAPI() {
		return panelAPI;


	}

	public static Panel getPanel() {
		return panelAPI.panel;


	}


	public static void setPanel(Panel panel) {
		panelAPI.panel = panel;


	}

	public static void setPanelAPI(PanelAPI newPanelAPI) {
		panelAPI = newPanelAPI;


	}

	public static void loadData() {
		load();
	}

	public static void saveData() {
		save();
	}

	private static void save() {

		try {
			System.out.println("Data save attempted.");
			panelAPI.save();
		} catch (Exception e) {
			System.err.println("Error writing to file: " + e);
		}
	}

	private static void load() {
		try {
			System.out.println("Data load attempted.");
			panelAPI.load();
		} catch (Exception e) {
			System.err.println("Error reading from file: " + e);
		}

	}
}