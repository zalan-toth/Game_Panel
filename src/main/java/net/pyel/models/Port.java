package net.pyel.models;

/**
 * Port class
 *
 * @author Zalán Tóth
 */
public class Port {

	private Machine machine;
	private String developer;
	private int releaseYear;
	private String cover;

	public Port(Machine machine, String developer, int releaseYear, String cover) {
		this.machine = machine;
		this.developer = developer;
		this.releaseYear = releaseYear;
		this.cover = cover;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
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

	@Override
	public String toString() {
		return machine + " by " + developer + " [" + releaseYear + "]";
	}
}
