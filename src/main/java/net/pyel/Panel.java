package net.pyel;

import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.models.TerminalElement;
import net.pyel.utils.CustomHashMap;
import net.pyel.utils.CustomList;

import java.util.Objects;

public class Panel {
	private boolean debugMode = false;

	private CustomList<Machine> machines = new CustomList<>();
	private CustomHashMap<String, Machine> machinesHash = new CustomHashMap<>();
	private CustomList<Game> games = new CustomList<>();
	private CustomHashMap<String, Game> gamesHash = new CustomHashMap<>();

	public Panel(boolean debugMode, CustomList<Machine> machines, CustomHashMap<String, Machine> machinesHash, CustomList<Game> games, CustomHashMap<String, Game> gamesHash) {
		this.debugMode = debugMode;
		this.machines = machines;
		this.machinesHash = machinesHash;
		this.games = games;
		this.gamesHash = gamesHash;
	}

	public CustomHashMap<String, Machine> getMachinesHash() {
		return machinesHash;
	}

	public void setMachinesHash(CustomHashMap<String, Machine> machinesHash) {
		this.machinesHash = machinesHash;
	}

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
		machinesHash.put((String) m.getName(), (Machine) m);
		machines.add(m);
		return true;
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


	public void removeMachine(Machine m) {
		machinesHash.remove(m.getName());
		machines.remove(m);
	}

	public boolean addGame(Game g) {
		for (Game game : games) {
			if (Objects.equals(game.getName(), g.getName())) {
				return false;
			}
		}
		gamesHash.put((String) g.getName(), (Game) g);
		games.add(g);
		return true;
	}

	public void updateGame(Game g, Machine m, String name, String publisher, String description, String developer, int year, String URL) {
		gamesHash.remove(g.getName());
		g.setName(name);
		if (m != null) {
			g.setMachine(m);
		}
		g.setPublisher(publisher);
		g.setDescription(description);
		g.setDeveloper(developer);
		g.setReleaseYear(year);
		g.setCover(URL);

		gamesHash.put(g.getName(), g);
	}

	public void removeGame(Game g) {
		gamesHash.remove(g.getName());
		games.remove(g);
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

	public TerminalElement search(String type, String value, Object additinalValue) {
		TerminalElement returnValue;
		returnValue = new TerminalElement(null, null);
		if (Objects.equals(type, "m")) {
			Machine m = machinesHash.get(value);
			if (m == null) {
				returnValue = new TerminalElement("No machine found.", null);
			} else {
				returnValue = new TerminalElement("Found machine: " + m.getName(), m);
			}
		} else if (Objects.equals(type, "g")) {
			Game g = gamesHash.get(value);
			if (g == null) {
				returnValue = new TerminalElement("No game found.", null);
			} else {
				returnValue = new TerminalElement("Found game: " + g.getName(), g);
			}
		}
		return returnValue;
	}

	public CustomList<TerminalElement> find(String type, String sort, String data, String value) {
		CustomList<TerminalElement> returnValue = new CustomList<>();
		if (type.equals("m")) {
			if (data.equals("n")) {

				if (value.equals("%")) {
					for (Machine m : machines) {
						returnValue.add(new TerminalElement(m.getName(), m));
					}
					if (sort.equals("0")) {
						returnValue.add(new TerminalElement("---Machines listed---", null));
						return returnValue;
					} else if (sort.equals("1")) {
						returnValue.add(new TerminalElement("---Machines sorted by release year---", null));
						return sortByYearAscending(returnValue);
					}

				} else if (value != null) {
					for (Machine m : machines) {
						if (m.getName().toLowerCase().contains(value.toLowerCase())) {
							returnValue.add(new TerminalElement(m.getName(), m));
						}
					}
				}
			}

		}
		return returnValue;
	}

	/*
	Insertion
	 */
	public CustomList<TerminalElement> sortByYearAscending(CustomList<TerminalElement> takeInList) {
		CustomList<TerminalElement> returnList = new CustomList<>();
		for (int i = 0; i < takeInList.size(); i++) {
			returnList.add(takeInList.get(i));
		}
		for (int i = 1; i < returnList.getSize(); i++) {
			Object o = returnList.get(i).getInspectionElemenet();
			if (o instanceof Machine) {
				int j = i - 1;

				while ((j >= 0) && (((Machine) returnList.get(j).getInspectionElemenet()).getLaunchYear() > ((Machine) o).getLaunchYear())) {
					returnList.set(j + 1, null);//(Machine) returnList.get(j).getInspectionElemenet());
					j = j - 1;
				}
				returnList.set(j + 1, new TerminalElement(((Machine) o).getName(), o));
			}
		}
		return returnList;
	}
}
