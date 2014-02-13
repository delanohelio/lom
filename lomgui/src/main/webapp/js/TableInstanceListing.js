(function() {
  var TableInstanceListing;

  TableInstanceListing = (function() {

    function TableInstanceListing() {}

    TableInstanceListing.prototype.init = function(conf) {
      var _this = this;
      return LOM.getJSON("rest/data/class/" + conf.classFullName + "/attributes", function(attributes) {
        return _this.drawTable(attributes, conf.classFullName);
      });
    };

    TableInstanceListing.prototype.drawTable = function(attributesJson, classFullName) {
      var table,
        _this = this;
      this.page = LOM.emptyPage();
      table = $("<table>");
      this.page.append(table);
      this.buildTableHead(attributesJson, table);
      return LOM.getJSON("rest/data/class/" + classFullName + "/instances", function(instances) {
        return _this.buildTableBody(instances, attributesJson, table, classFullName);
      });
    };

    TableInstanceListing.prototype.buildTableHead = function(attributesJson, table) {
      var thead, trHead;
      thead = $("<thead>");
      table.append(thead);
      trHead = $("<tr>");
      trHead.attr("id", "attributes");
      thead.append(trHead);
      return attributesJson.forEach(function(attribute) {
        var thHead;
        thHead = $("<th>" + attribute.name + "</th>");
        thHead.attr("id", "attribute_" + attribute.id);
        return trHead.append(thHead);
      });
    };

    TableInstanceListing.prototype.buildTableBody = function(instancesJson, attributesJson, table, classFullName) {
      var tbody,
        _this = this;
      if (instancesJson.length > 0) {
        tbody = $("<tbody>");
        tbody.attr("id", "instances");
        table.append(tbody);
        return instancesJson.forEach(function(instance) {
          var trbody;
          trbody = $("<tr>");
          trbody.attr("id", "instance_" + instance.id);
          tbody.append(trbody);
          attributesJson.forEach(function(attribute) {
            var td;
            td = $("<td>" + instance[attribute.name] + "</td>");
            td.attr("id", "instance_" + instance.id + "_attribute_" + attribute.id);
            return trbody.append(td);
          });
          return trbody.click(function() {
            return LOM.loadScript('rest/widget/class/' + classFullName + '/instance/' + instance.id, {
              classFullName: classFullName,
              id: instance.id
            });
          });
        });
      } else {
        return table.append("Nenhuma instância!");
      }
    };

    return TableInstanceListing;

  })();

  return new TableInstanceListing;

}).call(this);
