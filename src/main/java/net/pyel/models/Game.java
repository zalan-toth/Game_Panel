package net.pyel.models;

import net.pyel.utils.CustomList;

public class Game {

	private Machine machine;
	private String name;
	private String publisher;
	private String description;
	private String developer;
	private int releaseYear;
	private String cover;
	private CustomList<Port> ports = new CustomList<>();
}
