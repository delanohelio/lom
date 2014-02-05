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
      this.buildTableHead(attributesJson, table, classFullName);
      return LOM.getJSON("rest/data/class/" + classFullName + "/instances", function(instances) {
        return _this.buildTableBody(instances, attributesJson, table, classFullName);
      });
    };

    TableInstanceListing.prototype.buildTableHead = function(attributesJson, table, classFullName) {
      var thead, trHead;
      thead = $("<thead>");
      table.append(thead);
      trHead = $("<tr>");
      trHead.attr("id", classFullName + "_attributes");
      thead.append(trHead);
      return $.each(attributesJson, function(i, attribute) {
        var thHead;
        thHead = $("<th>" + attribute.name + "</th>");
        return trHead.append(thHead);
      });
    };

    TableInstanceListing.prototype.buildTableBody = function(instancesJson, attributesJson, table, classFullName) {
      var tbody,
        _this = this;
      tbody = $("<tbody>");
      table.append(tbody);
      return instancesJson.forEach(function(instance) {
        var trbody;
        trbody = $("<tr>");
        trbody.attr("id", classFullName + "_" + instance.id);
        tbody.append(trbody);
        attributesJson.forEach(function(attribute) {
          var td;
          td = $("<td>" + instance[attribute.name] + "</td>");
          return trbody.append(td);
        });
        return trbody.click(function() {
          return LOM.loadScript('rest/widget/class/' + classFullName + '/instance/' + instance.id, {
            classFullName: classFullName,
            id: instance.id
          });
        });
      });
    };

    return TableInstanceListing;

  })();

  return new TableInstanceListing;

}).call(this);
