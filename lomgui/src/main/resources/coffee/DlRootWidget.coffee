class DlRootWidget

	init: (conf) ->
		@page = LOM.emptyPage()
		LOM.getJSON 'rest/data/class', (jsonObj) =>
			@drawList(jsonObj)

	drawList: (jsonObj) ->
		dl = $("<dl>");
		@page.append dl
		$.each jsonObj, (i, clazz) =>
			@drawLine(dl, clazz)

	drawLine: (dl, clazz) ->
		dt = $("<dt>#{clazz.name}</dt>")
		dt.attr "id", "class_" + clazz.fullName
		dl.append dt
		@drawDescription(dl, clazz)
		dt.click => 
			LOM.loadScript 'rest/widget/class/'+ clazz.fullName,
			classFullName: clazz.fullName
	
	drawDescription: (dl, clazz) ->
		dd = $("<dd>")
		dd.append "id: " + clazz.id
		dl.append dd

return new DlRootWidget