package com.nanuvem.lom.lomgui;

import java.util.HashMap;
import java.util.Map;

public class WidgetStoreFacade {
	
	private static WidgetStoreFacade singleton;
	
	public static WidgetStoreFacade getInstance(){
		if(WidgetStoreFacade.singleton == null){
			WidgetStoreFacade.singleton = new WidgetStoreFacade();
		}
		return WidgetStoreFacade.singleton;
	}
	
	public Map<String, String> widgetsMapping;
	
	private WidgetStoreFacade() {
		super();
		widgetsMapping = new HashMap<String, String>();
		widgetsMapping.put("root", "UlRootWidget");
		widgetsMapping.put("class", "TableInstanceListing");
		widgetsMapping.put("instance", "FormInstanceWidget");
	}
	
}
