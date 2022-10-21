package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AllProductsPage;
import pages.CartPage;

public class ViewCartTests extends BaseTest{
    CartPage cartPage;
    SoftAssert softAssert=new SoftAssert();
@BeforeMethod
    public void addProductToCart(){
    AllProductsPage allProductsPage=new AllProductsPage(driver);
   allProductsPage.addToCart(1);
    cartPage=allProductsPage.viewCart();

}
@Test
public void checkCartTableInfo(){
softAssert.assertTrue(cartPage.checkCartTableInfo(),"the cart table info is not correct ");
softAssert.assertAll();

}
@Test
    public void verifyX_signDeleteProduct(){
    cartPage.deleteProduct();
    softAssert.assertTrue(cartPage.productIsDeletedMsg(),"the product isn't deleted");
    softAssert.assertAll();

}

}
