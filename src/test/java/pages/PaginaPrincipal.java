package pages;
 
public class PaginaPrincipal extends BasePage {
    private String searchButton = "//*[@id=\"page_section_48252437\"]/div/section/div[2]";
 
    public PaginaPrincipal() {
        super(driver);
    }
 
    // Método para navegar a www.freerangetesters.com
    public void navigateToFreeRangeTesters() {
        navigateTo("https://www.freerangetesters.com");
        
    }
 
}
