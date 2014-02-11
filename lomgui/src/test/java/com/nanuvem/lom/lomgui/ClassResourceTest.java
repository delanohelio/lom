package com.nanuvem.lom.lomgui;

import com.nanuvem.lom.lomgui.business.Clazz;
import com.nanuvem.restest.TypedResource;
import com.nanuvem.restest.TypedResourceTest;

public class ClassResourceTest extends TypedResourceTest<Clazz> {

	@Override
	protected Clazz createObject() {
		Clazz clazz = new Clazz((long) 0);
		clazz.setName("Teste");
		return clazz;
	}

	@Override
	protected TypedResource<Clazz> createTypedResource() {
		return new ClassResource();
	}

	@Override
	protected Clazz editObject(Clazz c) {
		c.setName("Test");
		return c;
	}

	@Override
	protected Object getId(Clazz c) {
		return c.getFullName();
	}

}
