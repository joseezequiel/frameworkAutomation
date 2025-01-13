package pages;

// Importaciones necesarias
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.eo.Se;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
    /*
     * Declaración de una variable estática 'driver' de tipo WebDriver
     * Esta variable va a ser compartida por todas las instancias de BasePage y sus
     * subclases
     */
    protected static WebDriver driver;

    private static Actions action;

    /*
     * Declaración de una variable de instancia 'wait' de tipo WebDriverWait.
     * Se inicializa inmediatamente con una instancia dew WebDriverWait utilizando
     * el 'driver' estático
     * WebDriverWait se usa para poner esperas explícitas en los elementos web
     */
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    /*
     * Configura el WebDriver para Chrome usando WebDriverManager.
     * WebDriverManager va a estar descargando y configurando automáticamente el
     * driver del navegador
     */
    static {
        WebDriverManager.chromedriver().setup();
        // Inicializa la variable estática 'driver' con una instancia de ChromeDriver
        driver = new ChromeDriver();
    }

    /*
     * Este es el constructor de BasePage que acepta un objeto WebDriver como
     * argumento.
     * Esta clase constructor va a recibir el driver 1 única vez al principio de la
     * ejecución.
     */
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    // Método estático para navegar a una URL.
    public static void navigateTo(String url) {
        driver.get(url);
    }

    public void goToLinkText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public static void closeBrowser() {
        driver.quit();
    }

    // Encuentra y devuelve un WebElement en la página utilizando un locator XPath,
    // esperando a que esté presentente en el DOM
    // que devuelva un WebElement nos permite el acceso a todas las funciones de
    // selenium para interacturar con web elements(ejem: clear, click, etc)
    private WebElement Find(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    // En vez de usar el "click()" normal, usaremos el "clickElement" porque va a
    // estar usando el "Find" el cual va a estar esperando el tiempo que estipulé en
    // BasePage
    public void clickElement(String locator) {
        Find(locator).click();
    }

    public void submitElement(String locator) {
        Find(locator).click();
    }

    public void write(String locator, String textToWrite) {
        Find(locator).clear();
        Find(locator).sendKeys(textToWrite);
    }

    // DROPDOWN
    public int dropdownSize(String locator) {
        Select dropdown = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }

    public void selectFromDropdownByValue(String locator, String valueToSelect) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByValue(valueToSelect);
    }

    public void selectFromDropdownByIndex(String locator, int valueToSelect) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByIndex(valueToSelect);
    }

    public void selectFromDropdownByText(String locator, String valueToSelect) {
        Select dropdown = new Select(Find(locator));
        dropdown.selectByVisibleText(valueToSelect);
    }

    public void hoverOverElement(String locator) {
        action.moveToElement(Find(locator));
    }

    public void doubleClick(String locator) {
        action.doubleClick(Find(locator));
    }

    public void rightClick(String locator) {
        action.contextClick(Find(locator));
    }

    public String getValueFromTable(String locator, int row, int column) {
        String cellINeed = locator + "/table/tbody/tr[" + row + "]/td[" + column + "]";
        return Find(cellINeed).getText();
    }

    public void setValueOnTable(String locator, int row, int column, String stringToSend) {
        String cellToFill = locator + "/table/tbody/tr[" + row + "]+/td[" + column + "]";
        Find(cellToFill).sendKeys(stringToSend);
    }

    public void switchToFrame(int iFrameIndex) {
        driver.switchTo().frame(iFrameIndex);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void dismissAlert() {
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
    }

    public List<String> getDropdownValues(String locator) {
        Select dropdown = new Select(Find(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }

        return values;
    }

    public String textFromElement(String locator) {
        try {
            return Find(locator).getText();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el texto del elemento: " + locator, e);
        }
    }

    public boolean elementEnabled(String locator) {
        return Find(locator).isEnabled();
    }

    public boolean elementIsDisplayed(String locator) {
        return Find(locator).isDisplayed();
    }

    public boolean elementIsSelected(String locator) {
        return Find(locator).isSelected();
    }

    public List<WebElement> bringMeAllElements(String locator) {
        return driver.findElements(By.className(locator));
    }

    public void selectNthElement(String locator, int index) {
        List<WebElement> list = driver.findElements(By.xpath(locator));
        list.get(index).click();
    }

    public void dragAndDrop(String locator, String locator2) {
        WebElement element = Find(locator);
        WebElement element2 = Find(locator2);
        action.dragAndDrop(element, element2).build().perform();
    }

    public void selectCriteriaFromList(String locator, String criteria) {
        List<WebElement> list = driver.findElements(By.className(locator));
        for (WebElement element : list) {
            if (element.getText().equals(criteria)) {
                element.click();
                break;
            }
        }
    }
}