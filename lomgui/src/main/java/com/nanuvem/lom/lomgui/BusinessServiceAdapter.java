package com.nanuvem.lom.lomgui;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

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
		
		for(Clazz clazz : LomBusinessFacade.getInstance().getAllClazz()){
			noClasses.add(clazz.getJson());
		}

		return noClasses.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class/{fullName}/instances")
	public String getInstances(@PathParam("fullName") String fullName) {
		ArrayNode instancesNode = JsonNodeFactory.instance.arrayNode();
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if(clazz != null){
			instancesNode = clazz.getJsonInstances();
		}
		return instancesNode.toString();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class/{fullName}/attributes")
	public String getAttributes(@PathParam("fullName") String fullName) {
		ArrayNode attributesNode = JsonNodeFactory.instance.arrayNode();
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if(clazz != null){
			attributesNode = clazz.getJsonAttributes();
		}
		return attributesNode.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/class/{fullName}/instance/{id}")
	public String getInstance(@PathParam("fullName") String fullName,
			@PathParam("id") Long id) {
		ObjectNode instanceNode = JsonNodeFactory.instance.objectNode();
		Clazz clazz = LomBusinessFacade.getInstance().getClazz(fullName);
		if(clazz != null){
			Instance instance = clazz.getInstance(id);
			if(instance != null){
				instanceNode = instance.getJson();
			}
		}
		return instanceNode.toString();
	}
	
}

