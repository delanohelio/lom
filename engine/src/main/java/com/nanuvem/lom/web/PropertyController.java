package com.nanuvem.lom.web;

import com.nanuvem.lom.dao.typesquare.Property;

import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Property.class)
@Controller
@RequestMapping("/properties")
public class PropertyController {
}
