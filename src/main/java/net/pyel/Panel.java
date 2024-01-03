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

	public CustomHashMap<String, Game> getGamesHash() {
		return gamesHash;
	}

	public void setGamesHash(CustomHashMap<String, Game> gamesHash) {
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
						returnValue.add(new TerminalElement(m.getName() + " [" + m.getLaunchYear() + "]", m));
					}

				} else if (value != null) {
					for (Machine m : machines) {
						if (m.getName().toLowerCase().contains(value.toLowerCase())) {
							returnValue.add(new TerminalElement(m.getName() + " [" + m.getLaunchYear() + "]", m));
						}
					}
				}

			} else if (data.equals("d")) {

				if (value.equals("%")) {
					for (Machine m : machines) {
						if (m.getDescription() != null) {
							returnValue.add(new TerminalElement(m.getName() + " [" + m.getLaunchYear() + "]", m));
						}
					}

				} else if (value != null) {
					for (Machine m : machines) {
						if (m.getDescription().toLowerCase().contains(value.toLowerCase())) {
							returnValue.add(new TerminalElement(m.getName() + " [" + m.getLaunchYear() + "]", m));
						}
					}
				}

			}
			if (sort.equals("0")) {
				return returnValue;
			} else if (sort.equals("1")) {
				/*CustomList<Machine> m = new CustomList<>();
				for (TerminalElement te : returnValue) {
					m.add(te.getInspectionElemenet());
				}
				CustomList<Machine> sortedM = sortByYearAscending(m);
				CustomList<TerminalElement> sortedMte = new CustomList<>();
				for (Machine ma : sortedM) {
					sortedMte.add(new TerminalElement(ma.getName() + " [" + ma.getLaunchYear() + "]", ma));
				}*/
				return sortByYear(returnValue);
			} else if (sort.equals("2")) {
				return sortByYearDescending(returnValue);
			} else if (sort.equals("3")) {
				return sortByName(returnValue);
			} else if (sort.equals("4")) {
				return sortByNameReverse(returnValue);
			}

		} else if (type.equals("g")) {
			if (data.equals("n")) {

				if (value.equals("%")) {
					for (Game g : games) {
						returnValue.add(new TerminalElement(g.getName() + " [" + g.getReleaseYear() + "]", g));
					}

				} else if (value != null) {
					for (Game g : games) {
						if (g.getName().toLowerCase().contains(value.toLowerCase())) {
							returnValue.add(new TerminalElement(g.getName() + " [" + g.getReleaseYear() + "]", g));
						}
					}
				}

			} else if (data.equals("d")) {

				if (value.equals("%")) {
					for (Game g : games) {
						if (g.getDescription() != null) {
							returnValue.add(new TerminalElement(g.getName() + " [" + g.getReleaseYear() + "]", g));
						}
					}

				} else if (value != null) {
					for (Game g : games) {
						if (g.getDescription().toLowerCase().contains(value.toLowerCase())) {
							returnValue.add(new TerminalElement(g.getName() + " [" + g.getReleaseYear() + "]", g));
						}
					}
				}

			}
			if (sort.equals("0")) {
				return returnValue;
			} else if (sort.equals("1")) {
				return sortByYear(returnValue);
			} else if (sort.equals("2")) {
				return sortByYearDescending(returnValue);
			} else if (sort.equals("3")) {
				return sortByName(returnValue);
			} else if (sort.equals("4")) {
				return sortByNameReverse(returnValue);
			}
		}
		return returnValue;
	}

	public CustomList<TerminalElement> sortByYear(CustomList<TerminalElement> takeInList) {
		CustomList<TerminalElement> returnList = new CustomList<>();
		for (int i = 0; i < takeInList.size(); i++) {
			returnList.add(takeInList.get(i));
		}

		for (int i = 1; i < returnList.size(); i++) {
			TerminalElement current = returnList.get(i);
			if (current != null) {
				int j = i - 1;
				System.out.println("Current terminal element: " + current);
				if (current.getInspectionElemenet() instanceof Machine) {
					while (j >= 0 && ((Machine) returnList.get(j).getInspectionElemenet()).getLaunchYear() > ((Machine) current.getInspectionElemenet()).getLaunchYear()) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				} else if (current.getInspectionElemenet() instanceof Game) {
					while (j >= 0 && ((Game) returnList.get(j).getInspectionElemenet()).getReleaseYear() > ((Game) current.getInspectionElemenet()).getReleaseYear()) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				}

			}
		}

		return returnList;
	}

	public CustomList<TerminalElement> sortByYearDescending(CustomList<TerminalElement> takeInList) {
		CustomList<TerminalElement> returnList = new CustomList<>();
		for (int i = 0; i < takeInList.size(); i++) {
			returnList.add(takeInList.get(i));
		}

		for (int i = 1; i < returnList.size(); i++) {
			TerminalElement current = returnList.get(i);
			if (current != null) {
				int j = i - 1;
				System.out.println("Current terminal element: " + current);
				if (current.getInspectionElemenet() instanceof Machine) {
					while (j >= 0 && ((Machine) returnList.get(j).getInspectionElemenet()).getLaunchYear() < ((Machine) current.getInspectionElemenet()).getLaunchYear()) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				} else if (current.getInspectionElemenet() instanceof Game) {
					while (j >= 0 && ((Game) returnList.get(j).getInspectionElemenet()).getReleaseYear() < ((Game) current.getInspectionElemenet()).getReleaseYear()) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				}

			}
		}

		return returnList;
	}

	public CustomList<TerminalElement> sortByName(CustomList<TerminalElement> takeInList) {
		CustomList<TerminalElement> returnList = new CustomList<>();
		for (int i = 0; i < takeInList.size(); i++) {
			returnList.add(takeInList.get(i));
		}

		for (int i = 1; i < returnList.size(); i++) {
			TerminalElement current = returnList.get(i);
			if (current != null) {
				int j = i - 1;
				System.out.println("Current terminal element: " + current);
				if (current.getInspectionElemenet() instanceof Machine) {
					while (j >= 0 && ((Machine) returnList.get(j).getInspectionElemenet()).getName().compareTo(((Machine) current.getInspectionElemenet()).getName()) > 0) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				} else if (current.getInspectionElemenet() instanceof Game) {
					while (j >= 0 && ((Game) returnList.get(j).getInspectionElemenet()).getName().compareTo(((Game) current.getInspectionElemenet()).getName()) > 0) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				}
			}
		}

		return returnList;
	}

	public CustomList<TerminalElement> sortByNameReverse(CustomList<TerminalElement> takeInList) {
		CustomList<TerminalElement> returnList = new CustomList<>();
		for (int i = 0; i < takeInList.size(); i++) {
			returnList.add(takeInList.get(i));
		}

		for (int i = 1; i < returnList.size(); i++) {
			TerminalElement current = returnList.get(i);
			if (current != null) {
				int j = i - 1;
				System.out.println("Current terminal element: " + current);
				if (current.getInspectionElemenet() instanceof Machine) {
					while (j >= 0 && ((Machine) returnList.get(j).getInspectionElemenet()).getName().compareTo(((Machine) current.getInspectionElemenet()).getName()) < 0) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				} else if (current.getInspectionElemenet() instanceof Game) {
					while (j >= 0 && ((Game) returnList.get(j).getInspectionElemenet()).getName().compareTo(((Game) current.getInspectionElemenet()).getName()) < 0) {
						returnList.set(j + 1, returnList.get(j));
						j--;
					}
					returnList.set(j + 1, current);
				}
			}
		}

		return returnList;
	}

	/*
	Insertion

	public CustomList<Machine> sortByYearAscending(CustomList<Machine> takeInList) {
		CustomList<Machine> returnList = new CustomList<>();
		for (int i = 0; i < takeInList.size(); i++) {
			returnList.add(takeInList.get(i));
		}

		for (int i = 1; i < returnList.size(); i++) {
			Object o = returnList.get(i);
			if (o != null) {
				int j = i - 1;
				Machine current = returnList.get(i);
				System.out.println("Current terminal element: " + current);
				while (j >= 0 && returnList.get(j).getLaunchYear() > current.getLaunchYear()) {
					returnList.set(j + 1, returnList.get(j));
					j--;
				}
				returnList.set(j + 1, current);
			}
		}

		return returnList;
	}
	*/
}
