# Giganotosaurus (Parc de dinosaures)

Notre firme a été sollicité pour développer Giganotosaurus qui consiste à mettre en place un API pour un jeux video de parc de dinosaures

Voici les fonctionnalités actuellement supportées par notre API :
* Exécuter un tour ([TURN](https://projet2021.qualitelogicielle.ca/stories/01_turn))
* Gérer les ressources ([RES](https://projet2021.qualitelogicielle.ca/stories/02_res))
* Ajouter des dinosaures ([DINO](https://projet2021.qualitelogicielle.ca/stories/03_dino)) 
* Ajouter des bébés ([BEBE](https://projet2021.qualitelogicielle.ca/stories/04_bebe))
* Ajouter des dinosaures omnivores ([OMNI](https://projet2021.qualitelogicielle.ca/stories/05_omni))
* Les dinosaures herbivores plus faibles sont favorisés lors de la consommation de ressources ([EMP](https://projet2021.qualitelogicielle.ca/stories/surprise_emp/))
* Ajouter des combats de sumo-dinosaures ([SUMO](https://projet2021.qualitelogicielle.ca/stories/06_sumo))
* Les bébés grandissent ([GROW](https://projet2021.qualitelogicielle.ca/stories/07_grow))

## Pour débuter

Ces instructions vous permettrons de faire fonctionner une copie de ce projet sur votre poste de travail pour des fins de développement et de tests.

## Structure des modules

Il existe trois [modules maven](https://maven.apache.org/guides/mini/guide-multiple-modules.html) dans le projet : 

* `external-service-api` : ce projet offre un API codé avec Spring Boot. Son utilité sera révélé au cours des _user stories_ de la session.
* `game-api` : le projet contenant Giganotosaurus.
* `application` : permet de démarrer les 2 API simultanément.
 
## Compilation

Pour compiler l'application, exécuter:
```
mvn clean install
```

## Exécuter l'application

Pour exécuter l'application, exécuter:
```
mvn exec:java -pl application
```
L'application sera alors accessible au lien: http://localhost:8181.

## Exécuter les tests

Pour exécuter les tests, exécuter:
```
mvn test
```

## Exécuter Docker

Pour démarrer le conteneur Docker, exécuter:
```bash
docker build -t application-glo4002 .
docker run -p 8080:8080 -p 8181:8181 application-glo4002
```

## Application construite avec

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jetty](https://www.eclipse.org/jetty/) - Servlet Container
* [Jersey](https://jersey.github.io/) - Servlet API REST
* [Jackson](https://www.baeldung.com/jackson) - Serializer JSON
