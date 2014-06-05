package com.nanuvem.lom.lomgui;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/widget")
public class WidgetService {

	@Context
	private HttpServletRequest servletRequest;

	@GET
	@Produces("text/plain; charset=utf-8")
	@Path("/root")
	public Response getRootWidget() {
		String widgetName = WidgetStoreFacade.getInstance().widgetsMapping.get("root");
		String result = FileSystemUtil.getWidgetScript(servletRequest, widgetName);
		return Response.ok(result).build();
	}
	
	@POST
	@Path("/root")
	public Response setRootWidget(String widgetName) {
		WidgetStoreFacade.getInstance().widgetsMapping.put("root", widgetName);
		return Response.ok().build();
	}

	@GET
	@Produces("text/plain; charset=utf-8")
	@Path("/class/{fullName}")
	public Response getClassWidget(@PathParam("fullName") String fullName) {
		String widgetName = WidgetStoreFacade.getInstance().widgetsMapping.get("class." + fullName);
		if(widgetName == null){
			widgetName = WidgetStoreFacade.getInstance().widgetsMapping.get("class");
		}
		String result = FileSystemUtil.getWidgetScript(servletRequest, widgetName);
		return Response.ok(result).build();
	}
	
	@POST
	@Path("/class")
	public Response setDefaultClassWidget(String widgetName) {
		String key = "class";
		WidgetStoreFacade.getInstance().widgetsMapping.put(key, widgetName);
		return Response.ok().build();
	}
	
	@POST
	@Path("/class/{fullName}")
	public Response setClassWidget(@PathParam("fullName") String fullName, String widgetName) {
		String key = "class." + fullName;
		WidgetStoreFacade.getInstance().widgetsMapping.put(key, widgetName);
		return Response.ok().build();
	}
	
	
	@GET
	@Produces("text/plain; charset=utf-8")
	@Path("/class/{fullName}/instance/{id}")
	public Response getInstanceWidget(@PathParam("fullName") String fullName, @PathParam("id") Integer id) {
		String widgetName = WidgetStoreFacade.getInstance().widgetsMapping.get("instance." + fullName);
		if(widgetName == null){
			widgetName = WidgetStoreFacade.getInstance().widgetsMapping.get("instance");
		}
		String result = FileSystemUtil.getWidgetScript(servletRequest, widgetName);
		return Response.ok(result).build();
	}
	
	@POST
	@Path("/class/{fullName}/instance")
	public Response setInstanceWidget(@PathParam("fullName") String fullName, String widgetName) {
		String key = "instance." + fullName;
		WidgetStoreFacade.getInstance().widgetsMapping.put(key, widgetName);
		return Response.ok().build();
	}
	
	@POST
	@Path("/instance")
	public Response setDefaultInstanceWidget(String widgetName) {
		String key = "instance";
		WidgetStoreFacade.getInstance().widgetsMapping.put(key, widgetName);
		return Response.ok().build();
	}
	
}
