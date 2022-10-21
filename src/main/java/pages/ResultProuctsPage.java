package pages;

import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class ResultProuctsPage extends BasePage {
    private By searchResultItems = By.xpath("//div[@class=\"productinfo text-center\"]//p");

    public ResultProuctsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> searchResultProductsName() {
        List<String> allProducts = new ArrayList<>();
        for (int i = 0; i < get_list_of_elements(searchResultItems).size(); i++) {
            allProducts.add(get_list_of_elements(searchResultItems).get(i).getText());
        }
        return allProducts;
    }

    public boolean allSearchResultContainKeyword(String keyword) {
        boolean contain = false;
        for (int i = 0; i < searchResultProductsName().size(); i++) {

            contain = StringUtils.containsAnyIgnoreCase(searchResultProductsName().get(i), keyword);

        }
        return contain;
    }

    public boolean allRelatedProductsShown(List<String> allProducts, List<String> resultProducts, String searchedProduct) {
        ArrayList<String> searchWord = new ArrayList<String>();
        for (String word : searchedProduct.split(" ")) {
            searchWord.add(word);
        }

        boolean allRelatedShown = false;
        for (int i = 0; i < allProducts.size(); i++) {

            for (int j = 0; j < searchWord.size(); j++) {

                if (StringUtils.containsAnyIgnoreCase(allProducts.get(i), searchWord.get(j)) == true)
                    if (resultProducts.contains(allProducts.get(i)) == true)
                        allRelatedShown = true;
                    else if (resultProducts.contains(allProducts.get(i)) == false) allRelatedShown = false;


            }
        }
        return allRelatedShown;
    }
    public boolean exactlySearchProductFirstListedThenNext(List<String> allProducts, List<String> resultProducts, String searchedProduct){
    boolean firstListed = false;
    boolean exactlyPFirstThenRelatedPNext=false;
        if (StringUtils.equalsIgnoreCase(resultProducts.get(0),searchedProduct)==true)
    firstListed = true;
                else
    firstListed = false;
        System.out.println(resultProducts.get(0));
        System.out.println(searchedProduct);
        System.out.println(firstListed);
        if(firstListed==true&&allRelatedProductsShown(allProducts,resultProducts,searchedProduct)==true)
    exactlyPFirstThenRelatedPNext=true;



        return exactlyPFirstThenRelatedPNext;}

}