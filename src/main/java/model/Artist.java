package model;

public class Artist {

	private long id;
	private String name;
	
	public Artist(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Artist(String artistName) {
		this.name = artistName;
	}

	public String getName() {
		return this.name;
	}
	
	public Long getId() {
		return this.id;
	}
}
