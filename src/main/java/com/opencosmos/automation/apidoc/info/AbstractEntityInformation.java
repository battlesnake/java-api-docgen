package com.opencosmos.automation.apidoc.info;

public abstract class AbstractEntityInformation {

	public String getFriendlyName() {
		return friendlyName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDescription() {
		return description;
	}

	private final String friendlyName;
	private final String fullName;
	private final String description;

	public AbstractEntityInformation(String friendlyName, String fullName, String description) {
		this.friendlyName = friendlyName;
		this.fullName = fullName;
		this.description = description;
	}

}
