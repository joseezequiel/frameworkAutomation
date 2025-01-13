package steps;

import org.testng.Assert;

import io.cucumber.java.en.*;
import pages.AmazonSearchPage;

public class AmazonSearchSteps {
    AmazonSearchPage amazon = new AmazonSearchPage();

    @Given("^the user navigates to www.amazon.com$")
    public void navigateToAmazon() {
        amazon.navigateToAmazon();
    }

    @And("^searches for (.+)$")
    public void enterSearchCriteria(String criteria) {
        amazon.enterSearchCriteria(criteria);
        amazon.clickSearch();
    }

    @When("^navigates to the page number (.+)$")
    public void navigateToSecondPage(String pageNumber) {
        amazon.goToPage(pageNumber);
    }

    @And("^selects the (.+) item$")
    public void selectsItem(String itemPos) {
        amazon.pickItem(itemPos);
    }

    @Then("^the user is able to add it to the cart$")
    public void itemCanBeAddedToTheCart() {
        amazon.addToCart();
        Assert.assertEquals("Agregado al carrito", amazon.addedToCartMessage());
    }
}
