package steps;

import configuration.ConfigReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DashboardPage;
import pages.LoginPage;


import static configuration.DriverFactory.driver;


public class LoginSteps {

    LoginPage loginPage = new LoginPage(driver);
    DashboardPage dashboardPage = new DashboardPage(driver);
    public ConfigReader settings = new ConfigReader();

    @Given("l'utilisateur est sur la page de connexion")
    public void openLogin() {
        String url = settings.getProperty("url");
        loginPage.open(url);
    }

    @When("il saisit le login {string} et le mot de passe {string}")
    public void login(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("il est redirigé vers la page d'accueil")
    public void checkDashboard() {
        Assert.assertTrue(dashboardPage.isDisplayed());
    }


}
