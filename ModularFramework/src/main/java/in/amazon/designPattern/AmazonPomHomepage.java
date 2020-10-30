package in.amazon.designPattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commonLibs.implementation.CommonElement;
import commonLibs.implementation.DropdownControl;

public class AmazonPomHomepage {
	
	private WebElement searchBox;
	
	private WebElement searchButton;
	
	private WebElement searchDropdown;
	
	private WebElement results;
	
	private CommonElement elementControl;
	
	private DropdownControl dropdownControl;
	
	public AmazonPomHomepage(WebDriver driver) {
		
		searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		
		searchButton = driver.findElement(By.xpath("//input[@value='Go']"));
		
		searchDropdown = driver.findElement(By.id("searchDropdownBox"));
		
		results = driver.findElement(By.xpath("//span[@data-component-type='s-result-info-bar']"));
		
		elementControl = new CommonElement();
		
		dropdownControl = new DropdownControl();
		
		
	}
	
	public void searchProduct(String product, String category) throws Exception{
		
		elementControl.setText(searchBox, product);
		
		dropdownControl.selectViaVisibleText(searchDropdown, category);
		
		elementControl.clickElement(searchButton);
		
	}

}
