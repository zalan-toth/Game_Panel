package net.pyel.models;

/**
 * Link model - stores URL in String format
 *
 * @author Zalán Tóth
 */
public class Link {
	String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Link(String link) {
		this.link = link;
	}
}
