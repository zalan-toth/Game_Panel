package net.pyel.models;

import net.pyel.utils.CustomList;

public class Machine {
	private String name;
	private String manufacturer;
	private String description;
	private String type;
	private String media;
	private int launchYear;
	private double RRP;
	private String image;
	private CustomList<Game> linkedGames = new CustomList<>();

	public Machine(String name, String manufacturer, String description, String type, String media, int launchYear, double RRP, String image) {
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

	public double getRRP() {
		return RRP;
	}

	public void setRRP(double RRP) {
		this.RRP = RRP;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Machine)) return false;

		Machine machine = (Machine) o;

		if (getLaunchYear() != machine.getLaunchYear()) return false;
		if (Double.compare(machine.getRRP(), getRRP()) != 0) return false;
		if (getName() != null ? !getName().equals(machine.getName()) : machine.getName() != null) return false;
		if (getManufacturer() != null ? !getManufacturer().equals(machine.getManufacturer()) : machine.getManufacturer() != null)
			return false;
		if (getDescription() != null ? !getDescription().equals(machine.getDescription()) : machine.getDescription() != null)
			return false;
		if (getType() != null ? !getType().equals(machine.getType()) : machine.getType() != null) return false;
		if (getMedia() != null ? !getMedia().equals(machine.getMedia()) : machine.getMedia() != null) return false;
		return getImage() != null ? getImage().equals(machine.getImage()) : machine.getImage() == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getManufacturer() != null ? getManufacturer().hashCode() : 0);
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		result = 31 * result + (getType() != null ? getType().hashCode() : 0);
		result = 31 * result + (getMedia() != null ? getMedia().hashCode() : 0);
		result = 31 * result + getLaunchYear();
		temp = Double.doubleToLongBits(getRRP());
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return name;
	}
}
