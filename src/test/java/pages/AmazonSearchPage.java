package pages;

public class AmazonSearchPage extends BasePage {

    // xpath relativos
    private String searchBox = "//input[@id='twotabsearchtextbox']";
    private String searchButton = "//input[@id='nav-search-submit-button']";
    private String addToCartButton = "//input[@id='add-to-cart-button']";
    private String addedMessageText = "//*[contains(text(), 'Agregado al carrito')]";

    // XPath común para todos los resultados de búsqueda
    private static final String searchResultsXPathTemplate = "//div[@role='listitem'][%s]//a";

    public AmazonSearchPage() {
        super(driver);
    }

    public void navigateToAmazon() {
        navigateTo("http://www.amazon.com");
    }

    public void enterSearchCriteria(String criteria) {
        write(searchBox, criteria);
    }

    public void clickSearch() {
        clickElement(searchButton);
    }

    public void goToPage(String pageNumber) {
        goToLinkText(pageNumber);
    }

    // hace click en el elemento
    public void pickItem(String section) {
        String xpathSection = String.format(searchResultsXPathTemplate, section);
        clickElement(xpathSection);
    }

    public void addToCart() {
        clickElement(addToCartButton);
    }

    public String addedToCartMessage() {
        return textFromElement(addedMessageText);
    }
}