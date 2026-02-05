package steps;

import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static configuration.DriverFactory.driver;

public class SideBarSteps {

    public boolean ChoosenSideBarisDisplayed(String choosenSideBar) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(choosenSideBar));
    }

    @And("je clique sur l'onglet {string} avec verification de la redirection")
    public void jeCliqueSurLOnglet(String ongletchoisi) {
        WebElement ongletElt = driver.findElement(By.xpath("//span[normalize-space() = \""+ongletchoisi+"\"]"));
        ongletElt.click();
        String ongletinsidebar = ongletchoisi.toLowerCase();
        Assert.assertTrue(ChoosenSideBarisDisplayed(ongletinsidebar));
    }
}
