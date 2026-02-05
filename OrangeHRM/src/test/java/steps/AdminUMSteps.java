package steps;

import configuration.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AdminUMPage;
import pages.BasePage;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static configuration.DriverFactory.driver;

public class AdminUMSteps  {

    private AdminUMPage adminUMPage;

    public AdminUMSteps() {
        adminUMPage = new AdminUMPage(DriverFactory.getDriver());
    }


    // Cliquer sur Add
    @Given("je clique sur le bouton add {string}")
    public void jeCliqueSurLeBoutonAdd(String button) {
        adminUMPage.clickAdd();
        adminUMPage.AddisDisplayed();
    }

    @When("je renseigne les champs pour la création d'un utilisateur avec les informations suivantes")
    public void jeRenseigneLesChampsPourLaCreationDUnUtilisateur(DataTable dataTable) {
        // Récupérer les données du DataTable
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        // Remplir uniquement les champs texte
        String username = data.get("Username");
        String password = data.get("Password");
        String confirmPassword = data.get("Confirm Password");
        adminUMPage.fillUserFields(username, password, confirmPassword);
    }
    @And("je selectionne les valeurs suivantes dans les listes déroulantes")
    public void jeSelectionneLesValeursDansLesListesDeroulantes(DataTable dataTable) throws InterruptedException {
        Map<String, String> dropdownValues = dataTable.asMap(String.class, String.class);
        adminUMPage.selectDropdownValues(dropdownValues);
    }

    // Sélectionner le nom de l'employé
    @And("je selectionne le nom de l'employé {string}")
    public void jeSelectionneLeNomDeLEmploye(String employeeName) {
        adminUMPage.selectEmployee(employeeName);
    }

    @And("je clique sur le bouton save {string}")
    public void jeCliqueSurLeBoutonsave(String button) {
        if (button.equalsIgnoreCase("Save")) {
            adminUMPage.clickSave();
        }
        Assert.assertTrue(adminUMPage.isDisplayed());
    }


    @Then("je verifie l'existance de l'utilisateur {string}")
    public void jeVerifieLExistenceDeLUtilisateur(String usernamesearch) {
        Assert.assertTrue(
                "Utilisateur non trouvé : " + usernamesearch,
                adminUMPage.isUserPresentAfterSearch(usernamesearch)
        );
    }


    @When("je clique sur le bouton de modification")
    public void jeCliqueSurLeBoutonDeModification() {
        adminUMPage.modificationUser();
    }

    @Then("je suis redirigé vers la page de modification")
    public void jeSuisRedirigeVersLaPageDeModification() {
        adminUMPage.ModifDisDisplayed();
    }

    @And("je saisi le username {string}")
    public void jesaisiusername(String username){

        adminUMPage.saisirusername(username);
    }

    @When ("je clique sur le bouton de suppression de {string}")
    public void supprimeruser(String username){
        adminUMPage.supprimerUtilisateur(username);
    }

    @And("je confirme la suppression")
    public void confirmsuppression(){
        adminUMPage.confirmerSuppressionUser();
    }

    @Then("je verifie que l'utilisateur {string} n'existe plus")
    public void verifierUtilisateurAbsent(String username) {
        Assert.assertFalse( adminUMPage.isUserPresentAfterSearch(username));
    }
}
