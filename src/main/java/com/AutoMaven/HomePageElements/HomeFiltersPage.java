package com.AutoMaven.HomePageElements;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutoMaven.Base.BasePage;

public class HomeFiltersPage extends BasePage {

	@FindBy(id = "selectProductSort")
	private WebElement sortByDropdown;

	@FindBy(xpath = "//li[@id='grid']/a/i")
	private WebElement gridViewButton;

	@FindBy(xpath = "//li[@id='list']/a/i")
	private WebElement listViewButton;

	@FindBy(id = "layered_price_range")
	private WebElement priceRange;

	@FindBy(xpath = "//div[@id='layered_price_slider']/div")
	private WebElement priceRangeSlider;

	@FindBy(xpath = "//div[@id='layered_price_slider']/a[1]")
	private WebElement minPriceSlider;

	@FindBy(xpath = "//div[@id='layered_price_slider']/a[2]")
	private WebElement maxPriceSlider;

	private WebDriver driver;

	public HomeFiltersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectSortBy(String option) {
		selectByVisibleTextFromDropDownBox(driver, sortByDropdown, option);
	}

	public void gridView() {
		clickButton(driver, gridViewButton);
	}

	public void listView() {
		clickButton(driver, listViewButton);
	}

	private Double getMinPrice(String price) {
		String originalValue = priceRange.getText().trim();
		String[] splittedValue = originalValue.split("[-]");
		String minValue$ = splittedValue[0];
		String minValue = minValue$.replaceAll("[$]", "").trim();
		// System.out.println("min value is: " + minValue);
		Double returnMaxPriceFromSlider = Double.valueOf(minValue);
		return returnMaxPriceFromSlider;
	}

	private String getMaxPrice(String price) {
		String originalValue = priceRange.getText().trim();
		String[] splittedValue = originalValue.split("[-]");
		String maxValue$ = splittedValue[1];
		String maxValue = maxValue$.replaceAll("[$]", "").trim();
		// System.out.println("max value is: " + maxValue);
		// Double returnMinPriceFromSlider = Double.valueOf(maxValue);
		return maxValue;
	}

	/***
	 * set price range of Range Filter (price cannot be set exactly, for ex. min
	 * price is $30, it can be $30.x)
	 * 
	 * @param minPrice
	 *            : pass minimum price as String, for ex. "30"
	 * @param maxPrice
	 *            : pass maximum price as String, for ex. "40"
	 * @throws InterruptedException
	 */
	public void setPriceRange(String minPrice, String maxPrice) throws InterruptedException {
		// int x = 10;
		scrollToElement(driver, priceRange);

		// checkElementIsClickable(driver, minPriceSlider);
		for (int i = 1; i <= priceRangeSlider.getSize().getWidth(); i++) {
			int width = priceRangeSlider.getSize().getWidth();
			Actions move = new Actions(driver);
			move.dragAndDropBy(minPriceSlider, (width - (width - i)), 0).build().perform();
			String price = priceRange.getText().trim();
			// System.out.println("price range shown: " + price);
			Double minPriceDouble = Double.valueOf(minPrice);
			if (getMinPrice(price) >= minPriceDouble) {
				break;
			}
		}

		Thread.sleep(3000);
		// checkElementIsClickable(driver, maxPriceSlider);
		for (int j = 1; j <= priceRangeSlider.getSize().getWidth(); j++) {
			int widthOfSlider = priceRangeSlider.getSize().getWidth();
			// System.out.println("width of slider is: " + widthOfSlider);
			Actions move = new Actions(driver);
			move.moveToElement(maxPriceSlider, ((widthOfSlider * -j) / 100), 0).click();
			move.build().perform();
			String price = priceRange.getText().trim();
			if (getMaxPrice(price).contains(maxPrice)) {
				break;
			}
		}
	}

	/***
	 * it will scroll to the Label present in Filter Area of the Page (Label
	 * must be loaded before it scroll to it)
	 * 
	 * @param filterName
	 *            name of the filter (taken from the html code)
	 */
	private void scrollToLabelOfFilter(String filterName) {
		List<WebElement> labels = driver.findElements(By.xpath("//div/div[@class='layered_filter']/div/span"));
		int labelLength = labels.size();
		// for loop to take all the labels with similar xpath and then later
		// check with condition
		for (int i = 1; i <= labelLength; i++) {
			WebElement label = driver.findElement(By.xpath("//div/div[@class='layered_filter'][" + i + "]/div/span"));
			scrollToElement(driver, label);
			if (label.getText().trim().equalsIgnoreCase(filterName)) {
				// if label match with the parameter then it will scroll to it
				// and break the loop and exit
				scrollToElement(driver, label);
				break;
			}
		}
	}

	/***
	 * to click on Color Filter by passing "color" as parameter. parameterized
	 * color name should be available in color filter, otherwise it wont click
	 * 
	 * @param color
	 *            : name of color from html code
	 */
	public void selectColorFromFilters(String color) {
		scrollToLabelOfFilter("Color");
		List<WebElement> colors = driver.findElements(By.xpath("//ul[@id='ul_layered_id_attribute_group_3']/li"));
		int lengthOfColors = colors.size();
		for (int i = 1; i <= lengthOfColors; i++) {
			WebElement colorName = driver
					.findElement(By.xpath("//ul[@id='ul_layered_id_attribute_group_3']/li[" + i + "]/label/a"));
			String sColor = colorName.getText().trim();
			String[] colorSplit = sColor.split("[(]");
			String splittedColor = colorSplit[0].trim();
			// System.out.println(splittedColor);
			if (splittedColor.equalsIgnoreCase(color)) {
				// System.out.println(sColor);
				colorName.click();
				break;
			}
		}
	}

	/***
	 * to select available size from the Size filter panel size must be
	 * available and visible to select
	 * 
	 * @param size
	 */
	public void selectSizeFromFilters(String size) {
		scrollToLabelOfFilter("Size");
		List<WebElement> colors = driver.findElements(By.xpath("//ul[@id='ul_layered_id_attribute_group_1']/li"));
		int length = colors.size();
		for (int i = 1; i <= length; i++) {
			WebElement sizeElement = driver
					.findElement(By.xpath("//ul[@id='ul_layered_id_attribute_group_1']/li[" + i + "]/label/a"));
			String[] sizeElementSplit = sizeElement.getText().trim().split("[(]");
			String sizeStringValue = sizeElementSplit[0].trim();
			// System.out.println(sizeStringValue);
			if (sizeStringValue.equalsIgnoreCase(size)) {
				WebElement sizeCheckbox = driver.findElement(
						By.xpath("//ul[@id='ul_layered_id_attribute_group_1']/li[" + i + "]/div/span/input"));
				selectCheckBox(sizeCheckbox, "yes");
				break;
			}
		}
	}

}
