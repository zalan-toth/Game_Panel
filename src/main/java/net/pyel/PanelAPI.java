package net.pyel;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.models.Port;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class PanelAPI {
	Panel panel = new Panel(); //Every potential data is stored in the panel.
	public String currentStaff = "";

	public PanelAPI(String staff) {
		currentStaff = staff;
	}


	//██████╗░███████╗██████╗░░██████╗██╗░██████╗████████╗███████╗███╗░░██╗░█████╗░███████╗
	//██╔══██╗██╔════╝██╔══██╗██╔════╝██║██╔════╝╚══██╔══╝██╔════╝████╗░██║██╔══██╗██╔════╝
	//██████╔╝█████╗░░██████╔╝╚█████╗░██║╚█████╗░░░░██║░░░█████╗░░██╔██╗██║██║░░╚═╝█████╗░░
	//██╔═══╝░██╔══╝░░██╔══██╗░╚═══██╗██║░╚═══██╗░░░██║░░░██╔══╝░░██║╚████║██║░░██╗██╔══╝░░
	//██║░░░░░███████╗██║░░██║██████╔╝██║██████╔╝░░░██║░░░███████╗██║░╚███║╚█████╔╝███████╗
	//╚═╝░░░░░╚══════╝╚═╝░░╚═╝╚═════╝░╚═╝╚═════╝░░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░╚════╝░╚══════╝


	/**
	 * The save method uses the XStream component to write all the objects in the ArrayList
	 * to the xml file stored on the hard disk.
	 *
	 * @throws Exception An exception is thrown if an error occurred during the save e.g. drive is full.
	 */
	public void save() throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("panel.xml"));
		out.writeObject(panel);
		out.close();
	}

	/**
	 * The load method uses the XStream component to read all the objects from the xml
	 * file stored on the hard disk.
	 *
	 * @throws Exception An exception is thrown if an error occurred during the load e.g. a missing file.
	 */
	public void load() throws Exception {
		//list of classes that you wish to include in the serialisation, separated by a comma
		Class<?>[] classes = new Class[]{Panel.class, Port.class, Machine.class, Game.class};

		//setting up the xstream object with default security and the above classes
		XStream xstream = new XStream(new DomDriver());
		//XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(classes);

		//doing the actual serialisation to an XML file
		ObjectInputStream in = xstream.createObjectInputStream(new FileReader("panel.xml"));
		panel = (Panel) in.readObject();
		in.close();
	}

	//███████╗░██████╗░██╗░░░██╗░█████╗░██╗░░░░░░██████╗
	//██╔════╝██╔═══██╗██║░░░██║██╔══██╗██║░░░░░██╔════╝
	//█████╗░░██║██╗██║██║░░░██║███████║██║░░░░░╚█████╗░
	//██╔══╝░░╚██████╔╝██║░░░██║██╔══██║██║░░░░░░╚═══██╗
	//███████╗░╚═██╔═╝░╚██████╔╝██║░░██║███████╗██████╔╝
	//╚══════╝░░░╚═╝░░░░╚═════╝░╚═╝░░╚═╝╚══════╝╚═════╝░


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PanelAPI)) return false;

		PanelAPI panelAPI = (PanelAPI) o;

		if (!Objects.equals(panel, panelAPI.panel)) return false;
		return Objects.equals(currentStaff, panelAPI.currentStaff);
	}

	@Override
	public int hashCode() {
		int result = panel != null ? panel.hashCode() : 0;
		result = 31 * result + (currentStaff != null ? currentStaff.hashCode() : 0);
		return result;
	}
}
