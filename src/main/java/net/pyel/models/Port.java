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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Port)) return false;

		Port port = (Port) o;

		if (getReleaseYear() != port.getReleaseYear()) return false;
		if (getMachine() != null ? !getMachine().equals(port.getMachine()) : port.getMachine() != null) return false;
		if (getDeveloper() != null ? !getDeveloper().equals(port.getDeveloper()) : port.getDeveloper() != null)
			return false;
		return getCover() != null ? getCover().equals(port.getCover()) : port.getCover() == null;
	}

	@Override
	public int hashCode() {
		int result = getMachine() != null ? getMachine().hashCode() : 0;
		result = 31 * result + (getDeveloper() != null ? getDeveloper().hashCode() : 0);
		result = 31 * result + getReleaseYear();
		result = 31 * result + (getCover() != null ? getCover().hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return machine + " by " + developer + " [" + releaseYear + "]";
	}
}
