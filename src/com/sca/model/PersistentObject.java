package com.sca.model;

public abstract class PersistentObject {
	protected Long id;

	public PersistentObject() {
 	}

	public PersistentObject(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
