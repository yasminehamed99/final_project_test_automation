package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AllProductsPage;
import pages.ResultProuctsPage;

import java.util.List;

import static filesManager.ReaderFromFiles.getJsonValueByKey;

public class SearchProductTests extends BaseTest{
    String searchProductTestData = "searchProductTestData.json";
    String productName;
    String exactProductName;
    SoftAssert softAssert=new SoftAssert();
    @BeforeClass
    public void loadTestData() {
        productName = getJsonValueByKey(searchProductTestData, "productName");
        exactProductName=getJsonValueByKey(searchProductTestData,"ExactProduct");
    }

    // TEST CASE #3 IN THE DOCUMENT
    @Test
    public void checkTheAllResultContainsTheKeyWord(){
        AllProductsPage allProductsPage=new AllProductsPage(driver);
        ResultProuctsPage resultProuctsPage=allProductsPage.searchForProduct_searchButton(productName);
      softAssert.assertTrue( resultProuctsPage.allSearchResultContainKeyword(productName),"there is wrong item not contains the key word");
      softAssert.assertAll();

    }
    // TEST CASE #1 IN THE DOCUMENT
    @Test
    public void checkAllRelatedSearchIsShown_clickSearchButton(){
        AllProductsPage allProductsPage=new AllProductsPage(driver);
        List<String>allProduct=allProductsPage.allProducts();
        ResultProuctsPage resultProuctsPage=allProductsPage.searchForProduct_searchButton(productName);
        softAssert.assertTrue(resultProuctsPage.allRelatedProductsShown(allProduct,resultProuctsPage.searchResultProductsName(),productName),"there are some related items not shown in the result");
        softAssert.assertAll();

    }
    // TEST CASE #5 IN THE DOCUMENT
    @Test
    public void verifyExactlyMatchedSearch(){
        AllProductsPage allProductsPage=new AllProductsPage(driver);
        List<String>allProduct=allProductsPage.allProducts();
        ResultProuctsPage resultProuctsPage=allProductsPage.searchForProduct_searchButton(exactProductName);

        softAssert.assertTrue(resultProuctsPage.exactlySearchProductFirstListedThenNext(allProduct,resultProuctsPage.searchResultProductsName(),exactProductName),"there are some related items not shown in the result");
        softAssert.assertAll();
    }
    // TEST CASE #2 IN THE DOCUMENT
    @Test
    public void verifySearching_pressEnter(){
        AllProductsPage allProductsPage=new AllProductsPage(driver);
        List<String>allProduct=allProductsPage.allProducts();
        ResultProuctsPage resultProuctsPage=allProductsPage.searchForProduct_pressEnter(productName);
        softAssert.assertFalse(resultProuctsPage.allRelatedProductsShown(allProduct,resultProuctsPage.searchResultProductsName(),productName),"related items didn't shown");
        softAssert.assertAll();
    }
}
