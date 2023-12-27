package net.pyel.models;

import net.pyel.utils.CustomList;

public class Machine {
	private String name;
	private String manufacturer;
	private String description;
	private String type;
	private String media;
	private int launchYear;
	private int RRP;
	private String image;
	private CustomList<Game> linkedGames = new CustomList<>();

	public Machine(String name, String manufacturer, String description, String type, String media, int launchYear, int RRP, String image) {
		setName(name);
		setManufacturer(manufacturer);
		setDescription(description);
		setType(type);
		setMedia(media);
		setLaunchYear(launchYear);
		setRRP(RRP);
		setImage(image);
	}


	//SETTERS & GETTERS


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public int getLaunchYear() {
		return launchYear;
	}

	public void setLaunchYear(int launchYear) {
		this.launchYear = launchYear;
	}

	public int getRRP() {
		return RRP;
	}

	public void setRRP(int RRP) {
		this.RRP = RRP;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return name;
	}
}
