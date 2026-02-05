package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Map;


public class AdminUMPage extends BasePage {

    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addButton;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    public WebElement usernameInput;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    public WebElement passwordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    public WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit' and normalize-space()='Save']")
    public WebElement saveButton;

    @FindBy(xpath = "//input[contains(@placeholder,'Type for hints...')]")
    public WebElement employeeInput;

    // 🔹 Champ Username recherche
    @FindBy(xpath = "//label[text()='Username']/parent::div/following-sibling::div//input")
    private WebElement SearchusernameInput;

    // 🔹 Bouton Search
    @FindBy(xpath = "//button[@type='submit' and .//text()[contains(.,'Search')]]")
    private WebElement searchButton;

    //bouton modification
    @FindBy(xpath = "//button [@type=\"button\"]//i[@class=\"oxd-icon bi-pencil-fill\"]")
    private WebElement modifButton;


    //bouton confimer supprimer
    @FindBy(xpath = "//button[contains(normalize-space(), 'Yes, Delete')]")
    private WebElement ConfirmsuppButon;

    public void supprimerUtilisateur(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // attendre que le tableau soit visible
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'oxd-table-row')]")));

        // chercher le bouton dans la ligne correspondante
        WebElement suppButton = driver.findElement(By.xpath(
                "//div[contains(@class,'oxd-table-row') and .//div[normalize-space(text())='" + username + "']]//button[i[contains(@class,'bi-trash')]]"
        ));

        wait.until(ExpectedConditions.elementToBeClickable(suppButton)).click();
    }



    public void confirmerSuppressionUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ConfirmsuppButon)).click();
    }

    public void modificationUser(){
            modifButton.click();
        ModifDisDisplayed();
    }

    public boolean isUserPresentAfterSearch(String usernameToCheck) {
        // Vider le champ et rechercher
        SearchusernameInput.click();
        SearchusernameInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        SearchusernameInput.sendKeys(Keys.DELETE);
        SearchusernameInput.sendKeys(usernameToCheck);
        searchButton.click();

        By locator = By.xpath("//div[@role='row']//div[normalize-space(.)='" + usernameToCheck + "']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Attend que l'élément devienne invisible ou absent
            return !wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            // Si timeout, l'élément est toujours visible => l'utilisateur existe
            return true;
        }
    }


    public void selectEmployee(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        employeeInput.click();
        employeeInput.clear();
        // Forcer le nettoyage du champ
        employeeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        employeeInput.sendKeys(Keys.DELETE);

        employeeInput.sendKeys(employeeName);
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-autocomplete-option') and normalize-space()='" + employeeName + "']")
        ));
        option.click();
    }


    public AdminUMPage(WebDriver driver) {
        super(driver);
    }

    public void selectDropdownValue(String fieldName, String value) {

        WebElement label = driver.findElement(
                By.xpath("//label[normalize-space()='" + fieldName + "']")
        );

        // 2️⃣ Trouver le dropdown associé
        WebElement dropdown = label.findElement(
                By.xpath("./ancestor::div[contains(@class,'oxd-input-group')]//div[contains(@class,'oxd-select-text')]")
        );
        // 3️⃣ Cliquer sur le dropdown
        dropdown.click();
        // 4️⃣ Attendre et cliquer sur la valeur
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option']//span[normalize-space()='" + value + "']")
        ));
        option.click();
    }

    // Méthode pour gérer plusieurs dropdowns depuis un DataTable
    public void selectDropdownValues(Map<String, String> data) throws InterruptedException {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            selectDropdownValue(entry.getKey(), entry.getValue());
        }
    }

    public void clickAdd() {
        addButton.click();
    }


    public void clickSave() {
        saveButton.click();
    }
    public void fillUserFields(String username, String password, String confirmPassword) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);
    }
    public void saisirusername(String username){
        usernameInput.sendKeys(username);
    }

    public boolean isDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("admin/viewSystemUsers"));
    }

    public boolean AddisDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("admin/saveSystemUser"));
    }

    public boolean ModifDisDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains("admin/saveSystemUser"));
    }

}
