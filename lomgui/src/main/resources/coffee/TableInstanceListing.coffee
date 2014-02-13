class TableInstanceListing

	init: (conf) ->
		LOM.getJSON "rest/data/class/#{conf.classFullName}/attributes", (attributes) =>
			@drawTable(attributes, conf.classFullName)


	drawTable: (attributesJson, classFullName) ->
		@page = LOM.emptyPage()
		table = $("<table>")
		@page.append table
		@buildTableHead(attributesJson, table);
		LOM.getJSON "rest/data/class/#{classFullName}/instances", (instances) =>
			@buildTableBody(instances, attributesJson, table, classFullName)


	buildTableHead: (attributesJson, table) ->
		thead = $("<thead>");
		table.append thead
		trHead = $("<tr>");
		trHead.attr "id", "attributes"
		thead.append trHead
		attributesJson.forEach (attribute) ->
			thHead = $("<th>#{attribute.name}</th>")
			thHead.attr "id", "attribute_" + attribute.id
			trHead.append thHead


	buildTableBody: (instancesJson, attributesJson, table, classFullName) ->
		if(instancesJson.length > 0)
			tbody = $("<tbody>");
			tbody.attr "id", "instances"
			table.append tbody
			instancesJson.forEach (instance) =>
				trbody = $("<tr>")
				trbody.attr "id", "instance_" + instance.id
				tbody.append trbody
				attributesJson.forEach (attribute) =>
					td  = $("<td>#{instance[attribute.name]}</td>" );
					td.attr "id", "instance_" + instance.id + "_attribute_" + attribute.id
					trbody.append td
				trbody.click => 
					LOM.loadScript 'rest/widget/class/' + classFullName + '/instance/' + instance.id,
						classFullName: classFullName
						id: instance.id
		else
			table.append "Nenhuma instância!"


return new TableInstanceListing