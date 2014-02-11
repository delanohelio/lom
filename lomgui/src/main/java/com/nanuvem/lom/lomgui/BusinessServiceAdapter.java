package com.nanuvem.lom.lomgui;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import com.nanuvem.lom.lomgui.business.Attribute;
import com.nanuvem.lom.lomgui.business.Clazz;
import com.nanuvem.lom.lomgui.business.Instance;
import com.nanuvem.lom.lomgui.business.LomBusinessFacade;

@Path("/data")
public class BusinessServiceAdapter {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class")
	public String getClasses() {
		ArrayNode noClasses = JsonNodeFactory.instance.arrayNode();

		for (Clazz clazz : LomBusinessFacade.getInstance().getAllClazz()) {
			noClasses.add(clazz.getJson());
		}

		return noClasses.toString();
	}

	@POST
	@Path("/class")
	public Response addClass(String json) {
		try {
			ObjectNode clazzJson = (ObjectNode) jsonNodeFromString(json);
			LomBusinessFacade.getInstance().addClazz(clazzJson);
			return Response.created(null).build();
		} catch (Exception e) {
			return Response.notAcceptable(null).build();
		}
	}

	@DELETE
	@Path("/class")
	public Response deleteAllClasses() {
		LomBusinessFacade.getInstance().removeAllClazz();
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class/{fullName}/attributes")
	public String getAttributes(@PathParam("fullName") String fullName) {
		ArrayNode attributesNode = JsonNodeFactory.instance.arrayNode();
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			attributesNode = clazz.getJsonAttributes();
		}
		return attributesNode.toString();
	}

	@POST
	@Path("/class/{fullName}/attributes")
	public Response addAttribute(@PathParam("fullName") String fullName,
			String json) {

		boolean error = false;

		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			try {
				ObjectNode attributeNode = (ObjectNode) jsonNodeFromString(json);
				clazz.addAttribute(attributeNode.get("name").asText(),
						attributeNode.get("type").asText());
			} catch (Exception e) {
				error = true;
			}
		} else {
			error = true;
		}

		if (error) {
			return Response.notAcceptable(null).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/class/{fullName}/attributes")
	public Response deleteAllAttributes(@PathParam("fullName") String fullName,
			String json) {
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			clazz.removeAllAttributes();
			return Response.ok().build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class/{fullName}/instances")
	public String getInstances(@PathParam("fullName") String fullName) {
		ArrayNode instancesNode = JsonNodeFactory.instance.arrayNode();
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			instancesNode = clazz.getJsonInstances();
		}
		return instancesNode.toString();
	}

	@POST
	@Path("/class/{fullName}/instances")
	public Response addInstance(@PathParam("fullName") String fullName,
			String json) {
		boolean error = false;

		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			try {
				ObjectNode instanceNode = (ObjectNode) jsonNodeFromString(json);
				Instance instance = Instance.instanceFromJson(instanceNode,
						clazz);
				clazz.addInstance(instance);
			} catch (Exception e) {
				error = true;
			}
		} else {
			error = true;
		}

		if (error) {
			return Response.notAcceptable(null).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/class/{fullName}/instances")
	public Response deleteAllInstances(@PathParam("fullName") String fullName,
			String json) {
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			clazz.removeAllInstances();
			return Response.ok().build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class/{fullName}/instance/{id}")
	public String getInstance(@PathParam("fullName") String fullName,
			@PathParam("id") Long id) {
		ObjectNode instanceNode = JsonNodeFactory.instance.objectNode();
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			Instance instance = clazz.getInstance(id);
			if (instance != null) {
				instanceNode = instance.getJson();
			}
		}
		return instanceNode.toString();
	}

	@PUT
	@Path("/class/{fullName}/instance/{id}")
	public Response editInstance(@PathParam("fullName") String fullName,
			@PathParam("id") Long id, String json) {
		boolean error = false;

		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		Instance instance = clazz.getInstance(id);
		if (clazz != null && instance != null) {
			try {
				ObjectNode instanceNode = (ObjectNode) jsonNodeFromString(json);
				instance.setValuesFromJson(instanceNode);
			} catch (Exception e) {
				error = true;
			}
		} else {
			error = true;
		}

		if (error) {
			return Response.notAcceptable(null).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/class/{fullName}/instance/{id}")
	public Response deleteInstance(@PathParam("fullName") String fullName,
			@PathParam("id") Long id) {
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			clazz.removeInstance(id);
			return Response.ok().build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	@PUT
	@Path("/class/{fullName}/attribute/{id}")
	public Response editAttribute(@PathParam("fullName") String fullName,
			@PathParam("id") Long id, String json) {
		boolean error = false;

		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		Attribute attribute = clazz.getAttribute(id);
		if (clazz != null && attribute != null) {
			try {
				ObjectNode attributeNode = (ObjectNode) jsonNodeFromString(json);
				attribute.setValuesFromJson(attributeNode);
			} catch (Exception e) {
				error = true;
			}
		} else {
			error = true;
		}

		if (error) {
			return Response.notAcceptable(null).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Path("/class/{fullName}/attribute/{id}")
	public Response deleteAttribute(@PathParam("fullName") String fullName,
			@PathParam("id") Long id) {
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if (clazz != null) {
			clazz.removeAttribute(id);
			return Response.ok().build();
		} else {
			return Response.notAcceptable(null).build();
		}
	}

	private JsonNode jsonNodeFromString(String json) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonFactory factory = objectMapper.getJsonFactory();
		JsonNode jsonNode = (JsonNode) objectMapper.readTree(factory
				.createJsonParser(json));
		return jsonNode;
	}

}
