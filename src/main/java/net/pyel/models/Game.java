package net.pyel.models;

import net.pyel.utils.CustomList;

public class Game {

	public Game(Machine machine, String name, String publisher, String description, String developer, int releaseYear, String cover, CustomList<Port> ports) {
		this.machine = machine;
		this.name = name;
		this.publisher = publisher;
		this.description = description;
		this.developer = developer;
		this.releaseYear = releaseYear;
		this.cover = cover;
		this.ports = ports;
	}

	private Machine machine; //Original machine
	private String name;
	private String publisher;
	private String description;
	private String developer;
	private int releaseYear;
	private String cover;
	private CustomList<Port> ports = new CustomList<>();

	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public CustomList<Port> getPorts() {
		return ports;
	}

	public void setPorts(CustomList<Port> ports) {
		this.ports = ports;
	}
}
