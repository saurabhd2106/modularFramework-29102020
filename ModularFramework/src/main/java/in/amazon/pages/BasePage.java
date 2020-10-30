package in.amazon.pages;

import org.openqa.selenium.WebDriver;

import commonLibs.implementation.CommonElement;
import commonLibs.implementation.DropdownControl;
import commonLibs.implementation.MouseAction;

public class BasePage {
	
	public CommonElement elementControl;

	public DropdownControl dropdownControl;
	
	public MouseAction mouseAction;
	
	public BasePage(WebDriver driver) {
		elementControl = new CommonElement();
		
		dropdownControl = new DropdownControl();
		
		mouseAction = new MouseAction(driver);
	}

}
