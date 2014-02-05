(function() {
  var FormInstanceWidget;

  FormInstanceWidget = (function() {

    function FormInstanceWidget() {}

    FormInstanceWidget.prototype.init = function(conf) {
      var _this = this;
      return LOM.getJSON("rest/data/class/" + conf.classFullName + "/instance/" + conf.id, function(instance) {
        return _this.drawInstance(instance, conf.classFullName);
      });
    };

    FormInstanceWidget.prototype.drawInstance = function(instance, classFullName) {
      var form,
        _this = this;
      this.page = LOM.emptyPage();
      form = $("<form>");
      this.page.append(form);
      return LOM.getJSON("rest/data/class/" + classFullName + "/attributes", function(attributes) {
        return attributes.forEach(function(attribute) {
          return _this.drawAttribute(form, attribute, instance[attribute.name]);
        });
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
