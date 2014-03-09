package com.sca.model;

import java.io.Serializable;

public interface Property extends Serializable { 
	

	
	public Object getValue() ;

	public void setValue(Object newValue);

	@Override
	public String toString();
	public Class<?> getType();

	public boolean isReadOnly() ;

	public void setReadOnly(boolean newStatus);

	 
}
