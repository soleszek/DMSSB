package com.oleszeksylwester.dmssb.DMSSB.domain;

public class Tag {

	public int id;
	public String tagName;
	public String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Tag(int id, String tagName, String username) {
		this.id = id;
		this.tagName = tagName;
		this.username = username;
	}

}