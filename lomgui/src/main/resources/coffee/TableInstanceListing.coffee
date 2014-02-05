class TableInstanceListing

	init: (conf) ->
		LOM.getJSON "rest/data/class/#{conf.classFullName}/attributes", (attributes) =>
			@drawTable(attributes, conf.classFullName)


	drawTable: (attributesJson, classFullName) ->
		@page = LOM.emptyPage()
		table = $("<table>")
		@page.append table
		@buildTableHead(attributesJson, table, classFullName);
		LOM.getJSON "rest/data/class/#{classFullName}/instances", (instances) =>
			@buildTableBody(instances, attributesJson, table, classFullName)


	buildTableHead: (attributesJson, table, classFullName) ->
		thead = $("<thead>");
		table.append thead
		trHead = $("<tr>");
		trHead.attr "id", classFullName + "_attributes"
		thead.append trHead
		$.each attributesJson, (i, attribute) ->
			thHead = $("<th>#{attribute.name}</th>")
			trHead.append thHead


	buildTableBody: (instancesJson, attributesJson, table, classFullName) ->
		tbody = $("<tbody>");
		table.append tbody
		instancesJson.forEach (instance) =>
			trbody = $("<tr>")
			trbody.attr "id", classFullName + "_" + instance.id
			tbody.append trbody
			attributesJson.forEach (attribute) =>
				td  = $("<td>#{instance[attribute.name]}</td>" );
				trbody.append td
			trbody.click => 
				LOM.loadScript 'rest/widget/class/' + classFullName + '/instance/' + instance.id,
					classFullName: classFullName
					id: instance.id


return new TableInstanceListing