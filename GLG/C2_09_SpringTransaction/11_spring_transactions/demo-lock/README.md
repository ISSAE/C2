
# Démonstrations de verrouillage pessimiste.

Note : le comportement est très différent selon les bases et les détails des paramétrage et les implémentations de jpa.

On touche ici les limites de la généralité des standards.


## Comprendre l'affichage

Si on regarde l'affichage, on voit une trace des traitements :

~~~~~~
1       2020-01-10 09:47:12.228 tâche lecture 1, avant récupération 
1       2020-01-10 09:47:12.247 tâche lecture 1, après récupération 
1       2020-01-10 09:47:12.249 tâche lecture 1 total des comptes 2000
2       2020-01-10 09:47:13.232 tâche lecture 2, avant récupération 
3       2020-01-10 09:47:13.232 tâche écriture 3, avant récupération
4       2020-01-10 09:47:13.233 tâche écriture 4, avant récupération
5       2020-01-10 09:47:18.232 tâche lecture 5, avant récupération 
9       2020-01-10 09:47:21.234 tâche lecture 9, avant récupération 
6       2020-01-10 09:47:25.237 tâche lecture 6, avant récupération 
1       2020-01-10 09:47:32.25  tâche lecture 1, fin
9       2020-01-10 09:47:32.265 tâche lecture 9, après récupération
~~~~~~

Qui correspondrait à un tableau de la forme : 

|numéro de tâche|timestamp              |commentaire                           |
|---------------|-----------------------|--------------------------------------|
|1              |2020-01-10 09:47:12.249|tâche lecture 1 total des comptes 2000|
|2              |2020-01-10 09:47:13.232|tâche lecture 2, avant récupération   |
|3              |2020-01-10 09:47:13.232|tâche écriture 3, avant récupération  |

Les lignes les plus intéressantes sont celle qui contiennent les textes suivants :


avant récupération
: la tâche est en attente d'un verrou pour se continuer

après récupération
: la tâche vient d'obtenir le verrou souhaité et peut se continuer

fin
: la tâche se termine (et va relâcher ses verrous)

## Analyse d'un affichage obtenu avec h2

Dans la suite :

- A1 : signifie que la tâche 1 est en attente
- L1 : signifie que la tâche 1 a obtenu le verrou qu'elle souhaitait
- F1 : signifie que la tâche 1 est terminée (et a relâché son verrou)

L'affichage obtenu avec h2 peut se résumer à :

a1 l1 a2 a3 a4 a5 a9 a6 f1 l9 f9 l4 f4 l6 f6 l5 f5 l2 f2 l3 f3 a7 l7 f7

On voit alors qu'entre le verrouillage effectué par une tâche et la fin de celle-ci, toutes les autres tâches sont bloquées. 

En rentrant plus dans le détail, les tâches de lecture voient les modifications apportées par la dernière tâche d'écriture exécutée.

- Par exemple, h2 traite les verrous READ comme les WRITE : ils sont bloquants.
- derby a des verrous READ plus souples. Non seulement ils sont compatibles entre eux, mais il n'empêchent pas un Write de commencer (l'inverse n'étant pas vrai).

En regardant le log sql, les select effectués pour h2 sont `select ... for update`. Ce qui 
correspond, pour tous les cas, à des lock pessimistes en write

Voir : https://blog.arnoldgalovics.com/pessimistic-locking-in-jpa-hibernate/ pour une discussion de ce problème.
En gros, c'est correct quant au résultat final (et aux spécifications), mais décevant pour l'efficacité espérée.

## Analyse d'un affichage obtenu avec derby

Derby est plus souple que h2 ici, mais son comportement est plus complexe.

~~~~~
1       2020-01-10 10:12:54.032 tâche lecture 1, avant récupération 
1       2020-01-10 10:12:54.058 tâche lecture 1, après récupération 
1       2020-01-10 10:12:54.06  tâche lecture 1 total des comptes 2000
3       2020-01-10 10:12:55.028 tâche écriture 3, avant récupération
4       2020-01-10 10:12:55.028 tâche écriture 4, avant récupération
2       2020-01-10 10:12:55.028 tâche lecture 2, avant récupération 
2       2020-01-10 10:12:55.032 tâche lecture 2, après récupération 
2       2020-01-10 10:12:55.033 tâche lecture 2 total des comptes 2000
4       2020-01-10 10:12:55.037 tâche écriture 4, après récupération
4       2020-01-10 10:12:55.141 tâche écriture 4, modifications faites 
2       2020-01-10 10:12:56.035 tâche lecture 2, fin
5       2020-01-10 10:13:00.032 tâche lecture 5, avant récupération 
9       2020-01-10 10:13:03.032 tâche lecture 9, avant récupération 
6       2020-01-10 10:13:07.035 tâche lecture 6, avant récupération 
1       2020-01-10 10:13:14.063 tâche lecture 1, fin
4       2020-01-10 10:13:15.146 tâche écriture 4, fin 
3       2020-01-10 10:13:15.161 tâche écriture 3, après récupération
9       2020-01-10 10:13:15.161 tâche lecture 9, après récupération 
5       2020-01-10 10:13:15.161 tâche lecture 5, après récupération 
6       2020-01-10 10:13:15.161 tâche lecture 6, après récupération 
9       2020-01-10 10:13:15.162 tâche lecture 9 total des comptes 2
5       2020-01-10 10:13:15.162 tâche lecture 5 total des comptes 2
6       2020-01-10 10:13:15.162 tâche lecture 6 total des comptes 2
3       2020-01-10 10:13:15.267 tâche écriture 3, modifications faites 
6       2020-01-10 10:13:16.165 tâche lecture 6, fin
9       2020-01-10 10:13:23.167 tâche lecture 9, fin
5       2020-01-10 10:13:23.167 tâche lecture 5, fin
3       2020-01-10 10:13:35.269 tâche écriture 3, fin 
7       2020-01-10 10:13:35.276 tâche lecture 7, avant récupération 
7       2020-01-10 10:13:35.278 tâche lecture 7, après récupération 
7       2020-01-10 10:13:35.279 tâche lecture 7, fin
7       2020-01-10 10:13:35.279 tâche lecture 7 total des comptes 0
~~~~~

a1 l1 a3 a4 a2 l2 a4 l4 f2 a5 a9 a6 f1 f4 l3 l9 l5 l6 f6 f9 f5 f3 a7 l7 f7

- tâche 1 voit un total des comptes de 2000
- tâche 2 voit un total des comptes de 2000
- tâche 4 fixe les comptes à 2 (en total)
- tâche 9 voit un total des comptes de 2
- tâche 5 voit un total des comptes de 2
- tâche 6 voit un total des comptes de 2
- tâche 3 fixe les comptes à 0 (en total)
- tâche 7 voit un total des comptes de 0

On constate que les verrous en écriture sont bien exclusifs : les tâches 3 et 4, 
qui prennent un verrou en écriture s'exécutent séquentiellement (alors qu'elles demandent le verrou à peu près au même moment). 3 démarre après la fin de 4.

Les verrous en lecture peuvent coexister avec ceux en écriture. Celles qui demandent le verrou pendant qu'une tâche d'écriture l'a doivent attendre la fin de celle-ci pour l'obtenir. En revanche, si le verrou a été demandé avant qu'il ne soit obtenu par une tâche d'écriture, il peut être obtenu avant la fin de cette dernière.

Côté SQL, contrairement au driver h2, on voit des requêtes différente :

~~~~
Hibernate: select compte0_.id as id1_0_, compte0_.solde as solde2_0_ from compte compte0_ for read only with rs
Hibernate: select compte0_.id as id1_0_, compte0_.solde as solde2_0_ from compte compte0_ for update with rs
~~~~

"`with rs`"
    : with *read stability*, proche de *read commited*
