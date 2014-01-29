class FormInstanceWidget

	init: (conf) ->
		LOM.getJSON "rest/data/class/#{conf.classFullName}/instance/#{conf.id}", (jsonObj) =>
			@drawInstance(jsonObj)

	drawInstance: (jsonObj) ->
		@page = LOM.emptyPage()
		form = $("<form>")
		@page.append form
		$.each jsonObj.attributes, (i, attribute) =>
			@drawAttribute(form, attribute, jsonObj.instance[attribute.name])


	drawAttribute: (form, attribute, value) ->
		form.append attribute.name + ": "
		form.append $("<input name=#{attribute.name} value=#{value}>")
		form.append "<br>"

return new FormInstanceWidget