# Maven et GitHub: "Forker" un dépôt GitHub et l'hébergement de ses artefacts Maven

Nous allons utilisez GitHub pour héberger nos dépôts Maven pour C2. Un référentiel Maven est, en son cœur, juste un ensemble structuré de fichiers et de répertoires accessibles au public via http, et GitHub vous permet de le faire facilement avec son support de téléchargement brut. La même technique est utilisée par GitHub lui-même pour servir les sites Web de GitHub Pages.

## La solution de base comprend trois étapes:

* Créez une branche appelée mvn-repo pour héberger vos artefacts Maven.
* Utilisez le site Github site-maven-plugin pour envoyer vos artefacts vers Github.
* Configurez Maven pour utiliser le référentiel mvn distant en tant que référentiel Maven.

### Cette approche présente plusieurs avantages:

* Il s'intègre naturellement avec la cible de déploiement de sorte qu'il n'y ait aucune nouvelle commande Maven à apprendre. Utilisez simplement mvn deploy comme vous le feriez normalement.
* Les artefacts Maven sont conservés séparément de votre source dans une branche appelée mvn-repo, un peu comme les pages github sont conservées dans une branche distincte appelée gh-pages (si vous utilisez des pages github).
* Il n’y a aucune surcharge d’héberger un serveur distinct de stockage Maven Nexus ou Cloud, et vos artefacts Maven étant conservés à proximité de votre dépôt github, il est facile pour les utilisateurs de trouver l’un s’ils savent où se trouve l’autre.

La manière habituelle de déployer des artefacts sur un dépôt Maven distant consiste à utiliser mvn deploy, nous allons donc adapter ce mécanisme pour la faire fonctionner avec notre dépôt github.

# Comment faire?

## Etape1: Indiquez à maven de déployer des artefacts dans un emplacement temporaire dans votre répertoire cible. Ajoutez ceci à votre pom.xml: 

```xml
<distributionManagement>
    <repository>
        <id>internal.repo</id>
        <name>Dépôt temporaire de stockage intermédiaire (Staging)</name>
        <url>file://${project.build.directory}/mvn-repo</url>
    </repository>
</distributionManagement>
 
<plugins>
    <plugin>
        <artifactId>maven-deploy-plugin</artifactId>
        <version>2.8.1</version>
        <configuration>
            <altDeploymentRepository>internal.repo::default::file://${project.build.directory}/mvn-repo</altDeploymentRepository>
        </configuration>
    </plugin>
</plugins>
```

