# Exemple de client REST

Ce client se connecte au web service créé par le projet `restserveur`.
Il faut donc démarrer ce web service pour utiliser le présent logiciel.

Pour faire un client destiné à `restData`, c'est un peu plus complexe, eut égard à la pagination.

Voir https://stackoverflow.com/questions/38468839/spring-data-rest-consume-list-of-entities-java-hateoas-client

Noter qu'en observant les données renvoyées, on peut toujours s'en sortir.

Le logiciel se connecte à `restserveur`, puis

- affiche les données du contact 0
- affiche les données de la liste de contacts.
