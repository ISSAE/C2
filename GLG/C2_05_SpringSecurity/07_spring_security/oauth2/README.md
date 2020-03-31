# Démonstration d'une connexion avec oauth2 et google

Pour utiliser cette démonstration : 

# Allez sur google:

Documentation sur : https://developers.google.com/identity/protocols/OAuth2WebServer?hl=fr

Connectez-vous sur https://console.developers.google.com/ avec un de vos comptes.

1. Créez un projet (nombre max limité à 12 ; on peut en détruire)
2. aller dans la section "identifiants",  choisir "Créer des identifiants/ID client OAuth" ;
2. un bouton permet d'aller dans la section « écran d'autorisation OAuth »
    - remplir le nom de l'appli : demo-oauth
    - valider (ne pas remplir les autres champs)
3. choisir dans "Créer un ID Client OAuth", le radio button "Application Web".
    - remplir le nom "demo oauth"
    - remplir l'URI de redirection autorisée : http://localhost:8080/login/oauth2/code/google
4. Cliquez "créer". Une fenêtre s'affiche avec votre ID client et votre code secret client.


Copiez le fichier `application.properties.orig` à la racine du projet en le renommant `application.properties`

remplissez-le :
~~~
spring.security.oauth2.client.registration.google.client-id=VOTRE CLIENT ID
spring.security.oauth2.client.registration.google.client-secret=CODE SECRET CLIENT
~~~

# Bibliographie

Attention, l'ancienne bibliothèque spécialisée pour OAuth 2 est obsolète en Spring 5.

Voir https://github.com/spring-projects/spring-security/tree/5.2.0.RELEASE/samples/boot/oauth2login#google-login pour une démo du nouveau système.

