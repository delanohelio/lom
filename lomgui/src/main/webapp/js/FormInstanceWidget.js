(function() {
  var FormInstanceWidget;

  FormInstanceWidget = (function() {

    function FormInstanceWidget() {}

    FormInstanceWidget.prototype.init = function(conf) {
      var _this = this;
      return LOM.getJSON("rest/data/class/" + conf.classFullName + "/instance/" + conf.id, function(jsonObj) {
        return _this.drawInstance(jsonObj);
      });
    };

    FormInstanceWidget.prototype.drawInstance = function(jsonObj) {
      var form,
        _this = this;
      this.page = LOM.emptyPage();
      form = $("<form>");
      this.page.append(form);
      return $.each(jsonObj.attributes, function(i, attribute) {
        return _this.drawAttribute(form, attribute, jsonObj.instance[attribute.name]);
      });
    };

    FormInstanceWidget.prototype.drawAttribute = function(form, attribute, value) {
      form.append(attribute.name + ": ");
      form.append($("<input name=" + attribute.name + " value=" + value + ">"));
      return form.append("<br>");
    };

    return FormInstanceWidget;

  })();

  return new FormInstanceWidget;

}).call(this);
