package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartPage extends BasePage {
    private By tableContents=By.xpath("//tr[@class=\"cart_menu\"]//td");
    private By cartDeletedMsg=By.id("empty_cart");
    private By xSign=By.className("cart_quantity_delete");
    List<String> cartTableInformation = Arrays.asList(new String[]{"Item","Description", "Price","Quantity","Total","Remove"});
    private By items = By.xpath("//tbody//tr");
    private By itemsQuantity = By.xpath("//tbody//button");
    private By itemsPrice = By.xpath("//tbody//td[@class=\"cart_price\"]//p");
    private By itemsTotalPrice = By.xpath("//tbody//td[@class=\"cart_total\"]//p");

    public CartPage(WebDriver driver) {
        super(driver);
    }
    public List<String> cartTableInfo() {
        List<String> tableInfo = new ArrayList<>();
        for (int i = 0; i < get_list_of_elements(tableContents).size(); i++) {
            tableInfo.add(get_list_of_elements(tableContents).get(i).getText());
        }
        return tableInfo;
    }

    public boolean checkCartTableInfo(){
        boolean correctInfo=true;
        for(int i=0;i<cartTableInfo().size();i++){
            if(cartTableInfo().get(i)==cartTableInformation.get(i)){
                correctInfo=true;
            }
            else{
                correctInfo=false;
            }
        }
        return correctInfo;

    }
    public void deleteProduct(){
        elementClick(xSign);
    }
    public boolean productIsDeletedMsg(){
       return msgWebElement(cartDeletedMsg).isDisplayed();
    }
}
