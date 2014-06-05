package com.nanuvem.lom.lomgui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.nanuvem.lom.lomgui.business.Clazz;

public class RootWidgetTest {

	private static final int DEFAULT_TIMEOUT = 10;
	private WebDriver driver;
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() {
		driver.close();
	}

	@Test
	public void scenarioRootWidget() {
		Clazz clazz = new Clazz((long) 0, "Cliente");
		ClassResource clazzResource = new ClassResource();
		clazzResource.post(clazz);
		
		driver.get("http://localhost:8080/lomgui/");
		
		WebElement clientLi = ElementHelper.waitAndFindElementById(driver, "class_Cliente", DEFAULT_TIMEOUT);

		assertNotNull("Client Class not found", clientLi);
		assertEquals("Cliente", clientLi.getText());
	}
	
//	@Test
	public void mockScenarioClassListingWidget() {
		driver.get("http://localhost:8080/lomgui/");

		WebElement clientLi = ElementHelper.waitAndFindElementById(driver, "class_Cliente", DEFAULT_TIMEOUT);
		clientLi.click();
		WebElement instanceTr = ElementHelper.waitAndFindElementById(driver, "attributes", DEFAULT_TIMEOUT);

		assertNotNull("Client Instance not found", instanceTr);
		assertEquals("Jose", instanceTr.getText());
	}

}
