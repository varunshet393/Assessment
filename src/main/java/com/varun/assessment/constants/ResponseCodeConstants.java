package com.varun.assessment.constants;


public enum ResponseCodeConstants {
	INTERNAL_SERVER_ERROR(500, "internal server error"), DATA_UPDATION_FAIL(437, "Data updation failure"),
	INVALID_ARGUMENT(400, "Bad Request"), RESOURCE_NOT_FOUND(441, "Resource not found"),
	RESOURCE_ALREADY_EXISTS(442, "Resource Already Exists"),
	FORBIDDEN(403, "UnAuthorized Access to the resource is Forbidden");

	private final int value;
	private final String description;

	ResponseCodeConstants(int value, String description) {
		this.value = value;
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return value + " " + description;
	}

	public static ResponseCodeConstants getByValue(int value) {
		for (ResponseCodeConstants status : values()) {
			if (status.value == value)
				return status;
		}
		throw new IllegalArgumentException("Invalid status code: " + value);
	}
}
