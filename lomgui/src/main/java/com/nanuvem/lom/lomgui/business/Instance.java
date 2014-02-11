package com.nanuvem.lom.lomgui.business;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public class Instance {

	private Long id;
	private Clazz clazz;
	private Map<Attribute, Object> attributeValues;

	public Instance() {
		super();
		this.attributeValues = new HashMap<Attribute, Object>();
	}
	
	public Instance(Long id, Clazz clazz) {
		this();
		this.id = id;
		this.clazz = clazz;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Clazz getClazz() {
		return clazz;
	}
	
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Object getValue(String attributeName) {
		return getValue(this.clazz.getAttribute(attributeName));
	}

	public Object getValue(Attribute attribute) {
		return attributeValues.get(attribute);
	}

	public void setValue(String attributeName, Object value) {
		Attribute attribute = clazz.getAttribute(attributeName);
		if (attribute != null) {
			setValue(attribute, value);
		}
	}

	public void setValue(Attribute attribute, Object value) {
		if (clazz.getAttributes().contains(attribute)) {
			attributeValues.put(attribute, value);
		}
	}
	
	public ObjectNode getJson() {
		ObjectNode instanceNode = JsonNodeFactory.instance.objectNode();
		instanceNode.put("id", getId());
		instanceNode.put("clazzID", clazz.getId());
		for(Attribute attribute : clazz.getAttributes()){
			Object value = getValue(attribute);
			if(value != null){
				instanceNode.put(attribute.getName(), value.toString());
			}else{
				instanceNode.put(attribute.getName(), "");
			}
		}
		return instanceNode;
	}
	
	public void setValuesFromJson(JsonNode instanceJSON){
		if (instanceJSON.has("id")) {
			this.setId(instanceJSON.get("id").asLong());
		}
		
		for(Attribute attribute : this.clazz.getAttributes()){
			if (instanceJSON.has(attribute.getName())) {
				this.setValue(attribute.getName(), instanceJSON.get(attribute.getName()).asText());
			}
		}
	}
	
	public static Instance instanceFromJson(JsonNode instanceJSON, Clazz clazz) {
		Instance instance = new Instance();
		instance.setClazz(clazz);
		instance.setValuesFromJson(instanceJSON);
		return instance;
	}
	
	
}
