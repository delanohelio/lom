package com.nanuvem.lom.web;

import com.nanuvem.lom.dao.typesquare.Instance;

import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebJson(jsonObject = Instance.class)
@Controller
@RequestMapping("/instances")
public class InstanceController {
}
