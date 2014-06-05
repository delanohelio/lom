(function() {
  var DlRootWidget;

  DlRootWidget = (function() {

    function DlRootWidget() {}

    DlRootWidget.prototype.init = function(conf) {
      var _this = this;
      this.page = LOM.emptyPage();
      return LOM.getJSON('rest/data/class', function(jsonObj) {
        return _this.drawList(jsonObj);
      });
    };

    DlRootWidget.prototype.drawList = function(jsonObj) {
      var dl,
        _this = this;
      dl = $("<dl>");
      dl.attr("id", "classes");
      this.page.append(dl);
      return $.each(jsonObj, function(i, clazz) {
        return _this.drawLine(dl, clazz);
      });
    };

    DlRootWidget.prototype.drawLine = function(dl, clazz) {
      var dt,
        _this = this;
      dt = $("<dt>" + clazz.name + "</dt>");
      dt.attr("id", "class_" + clazz.fullName);
      dl.append(dt);
      this.drawDescription(dl, clazz);
      return dt.click(function() {
        return LOM.loadScript('rest/widget/class/' + clazz.fullName, {
          classFullName: clazz.fullName
        });
      });
    };

    DlRootWidget.prototype.drawDescription = function(dl, clazz) {
      var dd;
      dd = $("<dd>");
      dd.attr("id", "class_" + clazz.fullName + "id");
      dd.append("id: " + clazz.id);
      dl.append(dd);
      dd = $("<dd>");
      dd.attr("id", "class_" + clazz.fullName + "attributes");
      dd.append("attributes: " + clazz.attributes);
      dl.append(dd);
      dd = $("<dd>");
      dd.attr("id", "class_" + clazz.fullName + "instances");
      dd.append("instances: " + clazz.instances);
      return dl.append(dd);
    };

    return DlRootWidget;

  })();

  return new DlRootWidget;

}).call(this);
