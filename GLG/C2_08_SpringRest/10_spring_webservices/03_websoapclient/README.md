# Démonstration de service Web - côté client

Inspiré de https://spring.io/guides/gs/consuming-web-service/

Le fichier wsdl pourrait être obtenu à travers son URL, mais pour simplifier 
la compilation nous l'avons recopié. 

Il est créé par le projet  `webservice`, à l'url http://localhost:8080/ws/publications.wsdl.

**IMPORTANT** : en cas de modification du service, il *faut* mettre à jour manuellement le fichier
`src/resources/publications.wsdl`. Sinon, les deux ne seront pas synchronisés !
            