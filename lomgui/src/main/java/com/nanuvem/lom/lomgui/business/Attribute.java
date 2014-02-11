package com.nanuvem.lom.lomgui.business;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;


public class Attribute {
	
	private Long id;
	private String name;
	private String type;
	private Clazz clazz;
	
	public Attribute() {
		super();
	}
	
	public Attribute(Long id) {
		this();
		this.id = id;
	}
	
	public Attribute(Long id, String name, Clazz clazz, String type) {
		this(id);
		this.clazz = clazz;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public Clazz getClazz() {
		return clazz;
	}
	
	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ObjectNode getJson() {
		ObjectNode attributeNode = JsonNodeFactory.instance.objectNode();
		attributeNode.put("id", getId());
		attributeNode.put("clazzID", clazz.getId());
		attributeNode.put("name", name);
		attributeNode.put("type", type);
		return attributeNode;
	}
	
	public void setValuesFromJson(JsonNode attributeJSON) {
		if (attributeJSON.has("id")) {
			this.setId(attributeJSON.get("id").asLong());
		}		
		if (attributeJSON.has("name")) {
			this.setName(attributeJSON.get("name").asText());
		}
		if (attributeJSON.has("type")) {
			this.setType(attributeJSON.get("type").asText());
		}
	}
	
	public static Attribute attributeFromJson(JsonNode attributeJSON) {
		Attribute attribute = new Attribute();
		attribute.setValuesFromJson(attributeJSON);
		return attribute;
	}

}
