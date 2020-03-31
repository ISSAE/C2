# Démonstration de service Web - côté serveur

Inspiré de https://spring.io/guides/gs/producing-web-service/

Selon les outils, on peut créer la description d'un service, puis l'implémenter, ou le contraire.

La logique proposée par `org.springframework.boot:spring-boot-starter-web-services` privilégie le sens specification → implémentation, dans une certaine mesure.

Il est capable de créer les "bonnes" classes java à partir d'une spécification. Celle-ci
concerne au minimum les données (fichier xsd). Le fichier wsdl peut être fourni ou créé par l'outil.

## Le service proposé :

Pour être un peu réaliste au niveau de l'interface, nous 
proposons un service qui travaille sur des classes et non sur 
des types primitifs.

On va travailler sur des "Publications", qui ont un titre et 
un contenu, tous les deux textuels.

Le système va gérer une liste de publications.

## La configuration

Comme souvent quand ça n'est pas prévu depuis le départ, c'est pénible.



## Problèmes rencontrés

1. Bon, des heures passées sur une erreur de namespace. J'avais utilisé http://glg203.cnam.fr/publications dans mon
fichier xsd, et http://glg203.cnam/monService dans la classe PublicationFacade. 
2. La logique de la bibliothèque pour Spring est de coller au webservice (en J2EE, c'est plutôt le contraire, on écrit des classes java "normales", et le web service s'y adapte).
	- les valeurs passées et renvoyées par les méthodes java sont les request et les responses définies au niveau du web service (en particulier, les arguments sont regroupés en un objet)
	- bien penser à créer un élément par objet (la requête, la réponse et leurs parties)
	- en cas d'utilisations multiples d'un même type, aider le système à comprendre comment désambiguïser en évitant d'attacher directement un type à un élément complexe (voir listePublicationsResponse dans le xsd)

## Essai

Par exemple :

`requete.xml`

~~~xml
<?xml version="1.0" ?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
	<S:Body>
		<listePublicationsRequest xmlns="http://glg203.cnam.fr/publications"/>
	</S:Body>
</S:Envelope>
~~~

~~~bash
	curl --header "content-type: text/xml" -d @requete.xml http://localhost:8080/ws
~~~

Notez le `@` : il signifie que -d ne donne pas directement le contenu de la requête, mais désigne un fichier.