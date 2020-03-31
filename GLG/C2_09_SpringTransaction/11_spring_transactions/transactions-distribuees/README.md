# Démonstration de SpringBoot, JTA et des transactions

Cette application simule un système qui effectue des transactions banquaires entre deux comptes, 
situés dans deux banques différentes.

Ces deux banques sont simulées par deux serveurs Apache Derby (qui peut utiliser XTA).

On simule un problème au moment du dépôt : si le montant déposé est de 100 euros, le dépôt échoue.

- lancer l'appli `gradle bootRun`

Remarque : dans cet exemple, nous montrons en particulier comment :

- déclarer deux bases de données séparées
- comment partager un même type d'entité entre les deux bases (cas pas forcément très fréquent).
- comment utiliser le même TransactionManager (autre possibilité : en utiliser deux différents, mais ça ne permet pas d'avoir la transactionalité si les requêtes utilisent les deux bases).

# Créer deux bases séparées

Chaque base aura ses propres repositories. Un même type d'entité peut exister dans les deux bases, mais elles
auront quand même besoin de dupliquer leurs repositories.

On doit alors configurer soit-même :
- les datasources
- les entity managers (un par base)
- configurer explicitement les repositories dans l'annotation @EnableJpaRepositories.

  On a placé une telle annotation sur les classes Banque1Config et Banque2Config.

  Elles a la forme
~~~~
@EnableJpaRepositories(
    basePackageClasses = Banque1Repository.class, 
    entityManagerFactoryRef = "emf1", 
    transactionManagerRef = "transactionManager")
~~~~ 

Elle précise donc, que pour cette configuration, on veut créer les repositories situés dans le même package que `Banque1Repository` (soit en fait `Banque1Repository` lui-même), en utilisant le bean nommé `emf1` comme entityManagerFactory, et le transaction manager `transactionManager` comme transactionManager.

Notez que le transactionManager est le même pour les deux bases de données, puisque c'est un transactionManager partagé.

