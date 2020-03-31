Une application servlet très simple.

Pour lancer :

    gradle appRun

Lance jetty (en utilisant le plugin gretty).

Se connecter à la bonne page : 
http://localhost:8080/01_web_simple/hello?nom=toto

Pour construire un "war" qui pourra être déployé sur un serveur applicatif quelconque:

    gradle war

Pour disposer d'une version complète (serveur plus application), on peut faire 

    gradle buildProduct

Le dossier `/build/output/01_web_simple` contient alors une installation de jetty, plus du war de notre application, ainsi que des scripts de démarrage pour unix/mac et Windows.

