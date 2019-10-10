package io.github.dndanoff.doormonitor.config;

public enum ApplicationRoles {

    ADMIN("ADMIN"),
    SENSOR_CLIENT("SENSOR_CLIENT");

	private final String roleName;

	private ApplicationRoles(String roleName){
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
}