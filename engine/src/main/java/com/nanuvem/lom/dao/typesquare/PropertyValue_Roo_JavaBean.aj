// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.nanuvem.lom.dao.typesquare;

import com.nanuvem.lom.dao.typesquare.Instance;
import com.nanuvem.lom.dao.typesquare.Property;
import com.nanuvem.lom.dao.typesquare.PropertyValue;

privileged aspect PropertyValue_Roo_JavaBean {
    
    public String PropertyValue.get_value() {
        return this._value;
    }
    
    public void PropertyValue.set_value(String _value) {
        this._value = _value;
    }
    
    public Instance PropertyValue.getInstance() {
        return this.instance;
    }
    
    public void PropertyValue.setInstance(Instance instance) {
        this.instance = instance;
    }
    
    public Property PropertyValue.getProperty() {
        return this.property;
    }
    
    public void PropertyValue.setProperty(Property property) {
        this.property = property;
    }
    
}