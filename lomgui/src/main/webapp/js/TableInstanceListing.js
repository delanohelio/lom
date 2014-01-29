(function() {
  var TableInstanceListing;

  TableInstanceListing = (function() {

    function TableInstanceListing() {}

    TableInstanceListing.prototype.init = function(conf) {
      var _this = this;
      return LOM.getJSON("rest/data/class/" + conf.classFullName + "/instances", function(jsonObj) {
        return _this.drawTable(jsonObj, conf.classFullName);
      });
    };

    TableInstanceListing.prototype.drawTable = function(jsonObj, classFullName) {
      var table;
      this.page = LOM.emptyPage();
      table = $("<table>");
      this.page.append(table);
      this.buildTableHead(jsonObj, table, classFullName);
      return this.buildTableBody(jsonObj, table, classFullName);
    };

    TableInstanceListing.prototype.buildTableHead = function(jsonObj, table, classFullName) {
      var thead, trHead;
      thead = $("<thead>");
      table.append(thead);
      trHead = $("<tr>");
      trHead.attr("id", classFullName + "_attributes");
      thead.append(trHead);
      return $.each(jsonObj.attributes, function(i, attribute) {
        var thHead;
        thHead = $("<th>" + attribute.name + "</th>");
        return trHead.append(thHead);
      });
    };

    TableInstanceListing.prototype.buildTableBody = function(jsonObj, table, classFullName) {
      var tbody,
        _this = this;
      tbody = $("<tbody>");
      table.append(tbody);
      return $.each(jsonObj.instances, function(i, instance) {
        var trbody;
        trbody = $("<tr>");
        trbody.attr("id", classFullName + "_" + instance.id);
        tbody.append(trbody);
        $.each(jsonObj.attributes, function(i, attribute) {
          var td;
          td = $("<td>" + instance[attribute.name] + "</td>");
          return trbody.append(td);
        });
        return trbody.click(function() {
          return LOM.loadScript('rest/widget/class/' + classFullName + '/instance/' + i, {
            classFullName: classFullName,
            id: i
          });
        });
      });
    };

    return TableInstanceListing;

  })();

  return new TableInstanceListing;

}).call(this);
