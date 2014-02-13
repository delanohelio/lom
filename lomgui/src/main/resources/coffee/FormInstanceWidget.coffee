class FormInstanceWidget

	init: (conf) ->
		LOM.getJSON "rest/data/class/#{conf.classFullName}/instance/#{conf.id}", (instance) =>
			@drawInstance(instance, conf.classFullName)

	drawInstance: (instance, classFullName) ->
		@page = LOM.emptyPage()
		form = $("<form>")
		form.attr "id", "instance_" + instance.id
		@page.append form
		LOM.getJSON "rest/data/class/#{classFullName}/attributes", (attributes) =>
			attributes.forEach (attribute) =>
				@drawAttribute(form, attribute, instance[attribute.name])


	drawAttribute: (form, attribute, value, classFullName) ->
		form.append attribute.name + ": "
		input = $("<input>")
		input.attr "id", "attribute_" + attribute.id
		input.attr "name", attribute.name
		input.attr "value", value
		form.append input
		form.append "<br>"

return new FormInstanceWidget