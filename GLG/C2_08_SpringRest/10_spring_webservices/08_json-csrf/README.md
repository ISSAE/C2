# JSON/CSRF

Une démonstration d'une application Ajax - qui pourrait être 
une application REST - sécurisée par session, et *transmettant
un X-CSRF-TOKEN* lors de ses requêtes POST.

L'application :

- permet de lire la valeur d'un message (pour tout le monde)
- permet à l'utilisateur connecté de modifier ce message.
- l'utilisateur a les logins et password "user" et "user".

## Bibliographie
Nous suivons les conseils donnés dans [la documentation de Spring security](https://docs.spring.io/spring-security/site/docs/5.2.1.RELEASE/reference/html5/#servlet-csrf-include)

## Première approche possible

On peut inclure le token CSRF dans l'en-tête du HTML (exemple thymeleaf)
~~~html
<meta name="_csrf" content="" th:content="${_csrf.token}"/>
<meta name="_csrf_header" th:content="${_csrf.parameterName}" content=""/> 
~~~

Ce qui donnera au final quelque chose comme :
~~~html
<meta name="_csrf" content="567DS-DSFGD-344DZ"/>
<meta name="_csrf_header" content="X-CSRF-TOKEN"/> 
~~~

Le javascript peut l'y récupérer avec une simple requête DOM, et l'inclure dans
ses requêtes à protéger.

~~~javascript
function preventCsrf() {
    // En JQuery:
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}
~~~

Pour l'utiliser, on pourra par exemple écrire :
~~~~javascript
function supprimerEntree(id) {
    preventCsrf();
    $.post("${jsonBase}/" + l.id + "/reject").success(
        function () {...// mise à jour de l'affichage ?}
    )
}
~~~~

**Inconvénient** : la génération de HTML doit alors être dynamique (jsp, thymeleaf, etc...).
Empêche de séparer complètement le travail sur le front-end et le travail sur le backend.

## Récupération du token par REST

Spring security permet de créer un contrôleur qui retournera le token CSRF :

~~~java
@RestController
public class CsrfController {

    @GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken token) {
        return token;
    }
}
~~~

Dans ce code, le token CSRF sera injecté quand on le demandera. L'application Javascript, 
au moment de faire un POST, devra donc

- demander le token (requête AJAX GET sur '/csrf')
- quand il sera renvoyé, l'inclure dans la requête POST suivante.

Un bénéfice secondaire de ce procédé est que le token CSRF peut 
changer fréquemment (dans l'autre option, si la valeur est modifiée en cours de session,
les requêtes issues d'une page chargée depuis quelques temps seront invalides).

**Attention** le token CSRF ne doit pas pouvoir être lu par un attaquant. Ce qui signifie 
qu'il doit être impossible de faire un GET sur cette adresse depuis une page extérieure au site.
Si certaines ressources sont néanmoins accessibles (CORS), il faudra filtrer le token.

Cela posé, c'est vrai aussi pour le premier cas.

# Sécurité

Par commodité, pour cette application, nous avons décidé de réaliser l'intégralité de la configuration de
sécurité dans la classe `SecurityConfig`.

Il serait cependant peut-être plus pratique de le faire par annotation sur les méthodes.
