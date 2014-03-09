package com.sca.adaptor;

import java.util.ArrayList;
import java.util.List;

import com.sca.model.Property;

  
 
public class BuildableObjectProperty implements Property {

	private static final long serialVersionUID = 1L;
	private Property property;
	private String displayName;
	private int width = 200;
	private boolean radio = false;
	private boolean richText = false;

	private List<Object> options = new ArrayList<Object>();
	private boolean required;

	
	public BuildableObjectProperty(Property property) {
 		this.property = property;
	}

	@Override
	public String toString() {
		return property.toString();
	}

	@Override
	public Class<?> getType() {
		return property.getType();
	}

	@Override
	public Object getValue() {
		return property.getValue();
	}

	@Override
	public boolean isReadOnly() {
		return property.isReadOnly();
	}

	@Override
	public void setReadOnly(boolean newStatus) {
		property.setReadOnly(newStatus);
	}

	@Override
	public void setValue(Object newValue) {
		property.setValue(newValue);
	}

	public BuildableObjectProperty setWidth(int width) {
		this.width = width;
		return this;
	}

	public int getWidth() {
		return width;
	}

	public List<Object> getOptions() {
		return options;
	}

	public BuildableObjectProperty setOptions(List<Object> options) {
		this.options = options;
		return this;
	}

	public String getDisplayName() {
		return displayName;
	}

	public BuildableObjectProperty setDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public boolean isRadio() {
		return radio;
	}

	public BuildableObjectProperty setRadio(boolean radio) {
		this.radio = radio;
		return this;
	}

	public boolean isRichText() {
		return richText;
	}

	public BuildableObjectProperty setRichText(boolean richText) {
		this.richText = richText;
		return this;
	}
	
	public BuildableObjectProperty setRequired(boolean required) {
		this.required = required;
		return this;
	}

	public boolean isRequired() {
		return required;
	}

}
