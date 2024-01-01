package net.pyel;

import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.utils.CustomHashMap;
import net.pyel.utils.CustomList;

import java.util.Objects;

public class Panel {
	private boolean debugMode = false;

	private CustomList<Machine> machines = new CustomList<>();
	public CustomHashMap<String, Machine> machinesHash = new CustomHashMap<>();
	private CustomList<Game> games = new CustomList<>();

	public CustomList<Machine> getMachines() {
		return machines;
	}

	public void setMachines(CustomList<Machine> machines) {
		this.machines = machines;
	}

	public boolean addMachine(Machine m) {
		for (Machine machine : machines) {
			if (Objects.equals(machine.getName(), m.getName())) {
				return false;
			}
		}
		machinesHash.put(m.getName(), m);
		return machines.add(m);
	}

	public void updateMachine(Machine m, String name, String manufacturer, String description, String type, String media, int year, double rrp, String URL) {
		machinesHash.remove(m.getName());
		m.setName(name);
		m.setManufacturer(manufacturer);
		m.setDescription(description);
		m.setType(type);
		m.setMedia(media);
		m.setLaunchYear(year);
		m.setRRP(rrp);
		m.setImage(URL);
		machinesHash.put(m.getName(), m);
	}

	public boolean removeMachine(Machine m) {
		machinesHash.remove(m.getName());
		return machines.remove(m);
	}

	public boolean addGame(Game g) {
		return games.add(g);
	}

	public boolean removeGame(Game g) {
		return games.remove(g);
	}

	public CustomList<Game> getGames() {
		return games;
	}

	public void setGames(CustomList<Game> games) {
		this.games = games;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	public boolean trueOrNot() {
		boolean result = true;
		if (!result)
			return true;
		else
			return true;
	}
}
