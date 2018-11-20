## TechTheHighLevel
Ce dépôt a pour but de reconstruire le code Haut Niveau (IA) d'Intech !

### Installation - IntelliJ
#### Prérequis
1. Installer Java (Par défaut sur beacoup de distribution)
2. Installer Maven (gestionnaire de dépendance, compilation, ...)

##### Si un projet est ouvert 
File -\> New... -\> Project from Existing Sources... -\> Import Project from external models (Maven)

Laissez tout par defaut
##### Si sur la fenêtre de démarrage 
Import Project -\> Choisir le dossier du dépôt cloné -\> Import Project from external models (Maven)

Laissez tout par defaut

#### Installer les dépendances
Alt+F12 pour ouvrir le terminal : 

    mvn clean install -DskipTests

Vous êtes parré pour naviguer dans le HL !

### General Architecture
Le HL est composé de trois modules Maven :
    
* **TTHL-common** : Socle commun aux codes des deux robots, il regroupe la gestion des données des capteurs, la gestion
    des mouvements du robot et la gestion des ordres à envoyer au LL
    
* **TTHL-master** : IA du robot principale, il concentre toute les décisions stratégiques

* **TTHL-slave** : IA du robot secondaire

#### TTHL-common 
    
L'architecture de ce projet est orientée micro-services : l'IA se base sur plusieurs modules qui ont tous une tâche bien
définie, et qui communiquent entre eux si besoin. Elle est construite de manière à ce qu'elle soit maintenable, 
performante (dans la limite du language utilisé), et tolérante aux fautes (voir norme ISO/IEC 25010).

Dans le dossier des sources, plusieurs packages regroupent les classes par fonctionnalités :
* Utils : il contient tous les utilitaires du code, tels que le service de Log, la Config, ou encore le Container, 
  qui permettent de faciliter la programmation du HL et de l'IA. La particularité des classes qui sont rangées dans ce
  package est qu'elles peuvent être utilisées pour d'autres projets puisqu'elles sont globalement indépendantes du HL, 
  à quelques exceptions près.
  
* Connection : ce petit package contient simplement les interfaces de communication que l'on souhaite créer avec 
  l'extérieur, ainsi qu'un service de gestion de ces dernières. Actuellement, elle sont au nombre de cinq : la Teensy 
  pour communiquer avec le Bas Niveau, la Raspi pour communiquer avec le Haut Niveau Maître/Esclave, le processus
  s'occupant du Lidar et deux LocalHost servant à effectuer des tests d'intégrité du HL.
  
* Orders : un peu plus étoffé que le précédent, ce package regroupe toutes les classes permettant de discuter avec le Bas
  Niveau sans avoir à s'occuper du côté de la table sur lequel navigue notre robot, et surtout sans avoir
  d'incompréhension lorsque l'on parle à ce cher Bas Niveau. Il offre aux autres modules, via la classe principale
  OrderWrapper les méthodes permettant d'envoyer tout les ordres que le Bas Niveau gère.
  
* Data : le plus gros package, et sans doute le plus complexe du HL, il regroupe toutes les données provenant de 
  l'exterieur, à savoir celles du Lidar ou des capteurs infrarouges Sick par exemple, ainsi que les contrôleurs qui
  récupèrent ces données et les traitent afin qu'elles soient utilisables par tous les autres modules du HL. Par exemple, 
  le Lidar Controler s'occupe de positionner les obstacles sur la Table et de mettre à jour le Graphe. Ces deux dernières
  classes, également dans le package data, définissent également comment est-ce que l'on représente la table afin que
  l'on puisse aisément s'y déplacer !
  
* Locomotion : dernier package à ce jour, ce module un peu plus haut niveau regroupe les fonctions permettant au robot
  de se déplacer en évitant si possible l'adversaire et les collisions avec les bords de la table et les obstacles
  
L'orchestration de tout ces packages et modules se fait de la manière suivante :

**TODO** : UML de l'architecture générale

#### TTHL-master
#### TTHL-slave

### Detailed Architecture
#### Utils 
##### Log
##### Config
##### Container
#### Orders
##### Order
##### Hook
#### Data
##### XYO
##### Table
##### Graphe
##### SensorState
##### Controlers
#### Locomotion
##### PathFollower
##### PathFinder
    
### Tests
Les tests, indispensables pour la maintenabilité du HL, et permettant d'être efficace pour trouver l'origine de vos bugs
lorsque vous développez de nouvelles fonctionnalités, sont découpés ici en trois packages :

* Unitaires : ce sont les tests de fonctionnalités qui ne dépendent d'aucune autre fonctionnalités ! Typiquement tout
  ce qu'il y a dans le package utils. Ils servent tout simplement à vérifier que les fonctionnalités de base n'ont pas
  été altérées par quelques curieux : si ces tests ne passent pas, rien ne fonctionnera correctement

* Validation : ces tests sont plus nombreux et plus complexes. Il servent à vérifier l'intégrité des 
  fonctionnalités testables sans le hardware, principalement celles du package data dans notre cas. Par exemple, on 
  vérifie que la méthode de mise à jour du graphe à partir de points données par le Lidar donne des résultats sans 
  fautes, c'est-à-dire que les obstacles sont correctement placés et que les arrêtes parcourables du Graphe ne traverse 
  pas ces obstacles.
  
* Réels : ces tests sont ceux réalisés en conditions réels, c'est-à-dire avec un vrai robot ! Ce sont ceux que vous 
  devrez sans doute beaucoup toucher puisqu'ils doivent assurer toute la fonctionnalité du Robot : **GAGNER**
  
Ces tests sont destinés à être executer quotidiennement par un bot Jenkins (excépté pour les réels), vous permettant de 
vite voir si un bug a été introduit par une feature et d'indentifier plus rapidement son origine.