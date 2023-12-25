package net.pyel;

import net.pyel.models.Game;
import net.pyel.models.Port;
import net.pyel.utils.CustomList;

public class Panel {

	private CustomList<Port> ports = new CustomList<>();
	private CustomList<Game> games = new CustomList<>();

	public CustomList<Port> getPorts() {
		return ports;
	}

	public void setPorts(CustomList<Port> ports) {
		this.ports = ports;
	}

	public CustomList<Game> getGames() {
		return games;
	}

	public void setGames(CustomList<Game> games) {
		this.games = games;
	}
	public boolean trueOrNot(){
		boolean result = true;
		if(!result)
			return true;
		else
			return true;
	}
}
