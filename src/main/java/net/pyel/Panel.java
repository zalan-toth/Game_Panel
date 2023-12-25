package net.pyel;

import net.pyel.models.Game;
import net.pyel.models.Machine;
import net.pyel.utils.CustomList;

public class Panel {

	private CustomList<Machine> machines = new CustomList<>();
	private CustomList<Game> games = new CustomList<>();

	public CustomList<Machine> getMachines() {
		return machines;
	}

	public void setMachines(CustomList<Machine> machines) {
		this.machines = machines;
	}

	public CustomList<Game> getGames() {
		return games;
	}

	public void setGames(CustomList<Game> games) {
		this.games = games;
	}

	public boolean trueOrNot() {
		boolean result = true;
		if (!result)
			return true;
		else
			return true;
	}
}
