package com.nanuvem.lom.lomgui.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public class Clazz {

	private Long id;
	private Integer version;
	private String name;
	private String namespace;
	private Map<Long, Attribute> attributes;
	private Map<Long, Instance> instances;

	private long attributesCounter;
	private long instancesCounter;

	public Clazz() {
		super();
		this.attributes = new HashMap<Long, Attribute>();
		this.instances = new HashMap<Long, Instance>();
		this.attributesCounter = 0;
		this.instancesCounter = 0;
	}

	public Clazz(Long id) {
		this();
		this.id = id;

	}

	public Clazz(Long id, String name) {
		this(id);
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		if (namespace != null) {
			return getNamespace() + "." + getName();
		}
		return getName();
	}

	public Collection<Attribute> getAttributes() {
		return attributes.values();
	}

	public Collection<Instance> getInstances() {
		return instances.values();
	}

	public Attribute getAttribute(Long attributeID) {
		return this.attributes.get(attributeID);
	}

	public Attribute getAttribute(String attributeName) {
		for (Attribute attribute : this.attributes.values()) {
			if (attribute.getName().equalsIgnoreCase(attributeName)) {
				return attribute;
			}
		}
		return null;
	}

	public Instance getInstance(Long instanceID) {
		return this.instances.get(instanceID);
	}

	public void setAttribute(Attribute attribute) {
		attribute.setClazz(this);
		this.attributes.put(attribute.getId(), attribute);
	}

	public void setInstance(Instance instance) {
		instance.setClazz(this);
		this.instances.put(instance.getId(), instance);
	}

	public boolean containsAttribute(String attributeName) {
		return getAttribute(attributeName) != null;
	}

	public void addAttribute(Attribute attribute) {
		attribute.setId(attributesCounter++);
		this.attributes.put(attribute.getId(), attribute);
	}

	public void addAttribute(String name, String type) {
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setType(type);
		attribute.setClazz(this);
		addAttribute(attribute);
	}

	public void addInstance(Instance instance) {
		instance.setId(instancesCounter++);
		this.instances.put(instance.getId(), instance);
	}

	public Instance createInstance() {
		Instance instance = new Instance(instancesCounter++, this);
		instances.put(instance.getId(), instance);
		return instance;
	}

	public void removeAttribute(Long id) {
		this.attributes.remove(id);
	}

	public void removeInstance(Long id) {
		this.instances.remove(id);
	}

	public void removeAllAttributes() {
		this.attributes.clear();
	}

	public void removeAllInstances() {
		this.instances.clear();
	}

	public ArrayNode getJsonAttributes() {
		ArrayNode attributesNode = JsonNodeFactory.instance.arrayNode();
		for (Attribute attribute : this.attributes.values()) {
			attributesNode.add(attribute.getJson());
		}
		return attributesNode;
	}

	public ArrayNode getJsonInstances() {
		ArrayNode instancesNode = JsonNodeFactory.instance.arrayNode();
		for (Instance instance : this.instances.values()) {
			instancesNode.add(instance.getJson());
		}
		return instancesNode;
	}

	public ObjectNode getJson() {
		ObjectNode classNode = JsonNodeFactory.instance.objectNode();
		classNode.put("id", getId());
		classNode.put("name", getName());
		classNode.put("namespace", "");
		classNode.put("fullName", getFullName());
		return classNode;
	}

	public void setValuesFromJson(JsonNode clazzJSON) {
		if (clazzJSON.has("id")) {
			this.setId(clazzJSON.get("id").asLong());
		}
		if (clazzJSON.has("version")) {
			this.setVersion(clazzJSON.get("version").asInt());
		}
		if (clazzJSON.has("name")) {
			this.setName(clazzJSON.get("name").asText());
		}
		if (clazzJSON.has("namespace")) {
			this.setNamespace(clazzJSON.get("namespace").asText());
		}
	}

	public static Clazz clazzFromJson(JsonNode clazzJSON) {
		Clazz clazz = new Clazz();
		clazz.setValuesFromJson(clazzJSON);
		return clazz;
	}

}
