package com.sca.model;

import java.lang.reflect.Method;

public class MethodProperty<T> implements Property {

	private Object value;
	private Class<?> type;
	private boolean readOnly;
	
	public MethodProperty(Class<?> propertyType, Class<?> bean,
			Method readMethod, Method writeMethod) {
		
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Object newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReadOnly() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReadOnly(boolean newStatus) {
		// TODO Auto-generated method stub

	}

}
