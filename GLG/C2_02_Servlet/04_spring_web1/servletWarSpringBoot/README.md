# Application Springboot avec "vraies" servlets, utilisables dans un fichier .war

A priori, Springboot ne permet d'utiliser facilement des servlets que quand il l'application
Springboot embarque le serveur applicatif. Dans le cas contraire,
pour une application Springboot installée comme war dans un serveur existant,
les servlets ne sont pas gérées par Springboot. Donc pas d'injection des dépendances Spring... à moins 
de procéder comme dans ce projet.


