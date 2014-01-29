class TableInstanceListing

	init: (conf) ->
		LOM.getJSON "rest/data/class/#{conf.classFullName}/instances", (jsonObj) =>
			@drawTable(jsonObj, conf.classFullName)

	drawTable: (jsonObj, classFullName) ->
		@page = LOM.emptyPage()
		table = $("<table>")
		@page.append table
		@buildTableHead(jsonObj, table, classFullName)
		@buildTableBody(jsonObj, table, classFullName)

	buildTableHead: (jsonObj, table, classFullName) ->
		thead = $("<thead>");
		table.append thead
		trHead = $("<tr>");
		trHead.attr "id", classFullName + "_attributes"
		thead.append trHead
		$.each jsonObj.attributes, (i, attribute) ->
			thHead = $("<th>#{attribute.name}</th>")
			trHead.append thHead

	buildTableBody: (jsonObj, table, classFullName) ->
		tbody = $("<tbody>");
		table.append tbody
		$.each jsonObj.instances, (i, instance) =>
			trbody = $("<tr>")
			trbody.attr "id", classFullName + "_" + instance.id
			tbody.append trbody
			$.each jsonObj.attributes, (i, attribute) =>
				td  = $("<td>#{instance[attribute.name]}</td>" );
				trbody.append td
			trbody.click => 
				LOM.loadScript 'rest/widget/class/'+ classFullName + '/instance/' + i,
					classFullName: classFullName
					id: i

return new TableInstanceListing