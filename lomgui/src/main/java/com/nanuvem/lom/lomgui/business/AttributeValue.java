package com.nanuvem.lom.lomgui.business;

public class AttributeValue {

	private Attribute attribute;
	private Object value;

	public AttributeValue(Attribute attribute, Object value) {
		super();
		this.attribute = attribute;
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Attribute getAttribute() {
		return attribute;
	}

}
