# Démonstration de Spring Security

Un petit forum en ligne avec quelques règles de sécurité.

- tout le monde peut voir les messages
- seul un utilisateur connecté peut créer un message
- seul un administrateur peut créer des utilisateurs (URL commençant par /admin)
- un message ne peut être détruit que par son auteur ou un administrateur 
    (le code de Spring security serait le même ou presque pour éditer un message)


On essaie de montrer le plus possible de caractéristiques de Spring Security:

- mise en place d'un login par JPA ; protection du mot de passe.
- utilisation de la protection contre le csrf.
- protection par URL dans la configuration
- protection individuelle des méthodes
- gestion manuelle de la sécurité (pour la suppression de message), avec renvoi éventuel d'erreur 403.

Par ailleurs:

- le système utilise de manière avancée les templates de Thymeleaf. La ligne suivante :
~~~~~~html
<html lang="fr" xmlns:th="http://www.thymeleaf.org" 
    th:replace="~{layout :: layout(~{::title}, ~{::main}, ~{::#nav/li})}">
~~~~~~
Se trouve dans la plupart des vues.
Elle dit de remplacer le contenu de la page courante par la template "layout",
en injectant dans celle-ci les trois arguments qu'elle attend : le titre, le contenu, et la navigation.
Le système de désignation des éléments est inspiré de Xpath. Pour le troisième élément, je dis

> injecter les éléments li fils direct de l'élément d'id #nav.

Ce système permet d'avoir à la fois des menus globaux et locaux.