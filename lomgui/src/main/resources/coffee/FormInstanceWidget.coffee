class FormInstanceWidget

	init: (conf) ->
		LOM.getJSON "rest/data/class/#{conf.classFullName}/instance/#{conf.id}", (instance) =>
			@drawInstance(instance, conf.classFullName)

	drawInstance: (instance, classFullName) ->
		@page = LOM.emptyPage()
		form = $("<form>")
		@page.append form
		LOM.getJSON "rest/data/class/#{classFullName}/attributes", (attributes) =>
			attributes.forEach (attribute) =>
				@drawAttribute(form, attribute, instance[attribute.name])


	drawAttribute: (form, attribute, value) ->
		form.append attribute.name + ": "
		form.append $("<input name=#{attribute.name} value=#{value}>")
		form.append "<br>"

return new FormInstanceWidget