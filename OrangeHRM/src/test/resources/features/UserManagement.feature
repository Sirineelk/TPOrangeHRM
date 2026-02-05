Feature: Gestion des utilisateurs

  Background: Authentication et navigation page Admin
    Given l'utilisateur est sur la page de connexion
    When il saisit le login "Admin" et le mot de passe "admin123"
    Then il est redirigé vers la page d'accueil
    And je clique sur l'onglet "Admin" avec verification de la redirection

  @Ajoutuser
  Scenario: Ajout d'un utilisateur
    Given je clique sur le bouton add "Add"
    When je renseigne les champs pour la création d'un utilisateur avec les informations suivantes
      | Username         |azertyuiio|
      | Password         | Test@1234 |
      | Confirm Password | Test@1234 |

    And je selectionne les valeurs suivantes dans les listes déroulantes
      | User Role | ESS     |
      | Status    | Enabled |
    And je selectionne le nom de l'employé "Peter Mac Anderson"
    And je clique sur le bouton save "Save"
    Then je verifie l'existance de l'utilisateur "azertyuiio"

  @ModificationUser
  Scenario: Modification d'un utilisateur existant
    Given je verifie l'existance de l'utilisateur "FMLName"
    When je clique sur le bouton de modification
    Then je suis redirigé vers la page de modification
    And je saisi le username "azerty1996"
    And je selectionne les valeurs suivantes dans les listes déroulantes
          | User Role | Admin     |
          | Status    | Disabled |
    And je selectionne le nom de l'employé "Peter Mac Anderson"
    And je clique sur le bouton save "Save"

    @supressionUser
    Scenario: Suppression utilisateur
      Given je verifie l'existance de l'utilisateur "harikoti"
      When je clique sur le bouton de suppression de "harikoti"
      And je confirme la suppression
      Then je verifie que l'utilisateur "harikoti" n'existe plus

