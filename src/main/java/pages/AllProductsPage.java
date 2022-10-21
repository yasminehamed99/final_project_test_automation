package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class AllProductsPage extends BasePage{
    private By searchProductField = By.id("search_product");
    private By searchButton = By.id("submit_search");

    private By allProductsItems=By.xpath("//div[@class=\"productinfo text-center\"]//p");
    private By AddToCartButtons = By.xpath("//div[@class=\"productinfo text-center\"]//a[@class=\"btn btn-default add-to-cart\"]");
    private By viewCartButton = By.xpath("//a[@href=\"/view_cart\"]//u");

    public AllProductsPage(WebDriver driver) {
        super(driver);
    }
    public ResultProuctsPage searchForProduct_searchButton(String productName){
        typeOnFields(searchProductField,productName);
        elementClick(searchButton);
        return new ResultProuctsPage(driver);
    }
    public ResultProuctsPage searchForProduct_pressEnter(String productName){
        typeOnFields(searchProductField,productName+ Keys.ENTER);

        return new ResultProuctsPage(driver);
    }
    public List<String> allProducts() {
        List<String> allProducts = new ArrayList<>();
        for (int i = 0; i < get_list_of_elements(allProductsItems).size(); i++) {
            allProducts.add(get_list_of_elements(allProductsItems).get(i).getText());
        }
        return allProducts;
    }
    public void addToCart(int position) {
        performMouseHover(get_list_of_elements(allProductsItems).get(position));
        clickUsingJavaScript(get_list_of_elements(AddToCartButtons).get(position));
    }
    public CartPage viewCart() {

        clickUsingJavaScript(waitElementToBeLocated(viewCartButton));
        return new CartPage(driver);
    }
}
