package com.sca.model;

public interface GWTPropertyDescriptor {

	public String getName();

	public Class<?> getPropertyType();

	public Property createProperty(Class<?> bean);

}
