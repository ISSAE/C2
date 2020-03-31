# Démonstration minimaliste de Spring Security et du web (ne fait presque rien)

Nous lançons une application Spring boot web, avec Spring security intégré sous forme de 
`org.springframework.boot:spring-boot-starter-security` dans les dépendances `gradle`.

Automatiquement, un gestionnaire de login est mis en place. 
Par défaut, l'intégralité des pages sont protégées par ce gestionnaire.

Le login est "user", et le mot de passe est affiché au lancement du logiciel : 
~~~~
Using generated security password: f1d594ba-a2db-43e5-98c0-cc05fa5448dc
~~~~

On peut aussi les fixer dans `application.properties` :
~~~~~
spring.security.user.name=user
spring.security.user.password=monMotDePasse
~~~~~

Ça peut permettre de mettre en place simplement une application web single-user.

