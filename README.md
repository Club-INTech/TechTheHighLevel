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
Prenez une grande inspiration, on rentre ici dans le détail de l'architecture !
#### TTHL-common
##### Utils 
* **Log**

Le service de log est une enum : chaque instance représente un cannal de journalisation que l'on peut activer et
désactiver au besoin, le but étant d'éviter de surcharger le terminal d'informations lorsque l'on se concentre sur une
fonctionnalité du robot. Il y a trois niveau de log : debug, warning, et critical. Ce dernier niveau de log s'affiche
toujours, que le cannal spécifié soit activé ou non. Attention à bien initialiser log si le container n'est pas 
instancié !
Utilisation :
    
    Log.CANNAL.setActive(true);
    Log.CANNAL.debug("Debut de la methode A");  // Ca s'affiche !
    Log.CANNAL.setActive(false);
    Log.CANNAL.warning("Fin de la methode A");  // Ca ne s'affiche pas...
    Log.CANNAL.critical("AH GROS BUG");         // Ca s'affiche !
    


* **Config**

La config est une librairie externe utilisé pour changer des paramètres dans le Haut Niveau sans avoir à recompiler. Il
a été developpé par PF Gimenez, un vieux d'Intech aujourd'hui docteur ! Bref, ce service est lui aussi gérer par le 
container et les paramètres que l'on veut manipuler/retirer/ajouter sont rassemblé dans l'enum ConfigData, qui contient
les valeurs des paramètres par défaut. Les valeurs chargées par le container à l'instanciation des services sont 
présentes dans le fichier config.txt. Attention à utiliser les mêmes clés et types entre le fichier texte et l'enum !
* **Container**

Le container fait office à la fois de factory .ie il instancie les services, et de gestion des dépendances : lorsque 
l'on demande un service via la methode _getService(Class class)_, le container va instancier tout les paramètres du 
constructeur en tant que service s'ils n'ont pas déjà instanciés. Utilisation :
    
    Container container = Container.getInstance("Master");
    MonService service = container.getService(MonService.class);
    
"Tu nous parles de service depuis tout à l'heure mais c'est quoi au juste un service ???"

Et bien c'est un **singleton** offrant des **fonctionnalités** bien définies ! Dans notre cas c'est une interface qui 
doit surcharger la méthode _public void updateConfig(Config config)_, qui permet justement de récupérer des valeurs de 
la config ! On entend par singleton une classe qui n'a qu'un seule instance. Exemple :

ConfigData.java :

    import pfg.config.ConfigInfo;
    
    public enum ConfigData implements ConfigInfo {
        PARAM_MONSERVICE(18)
        ;
    }
config/config.txt :

    ...
    PARAM_MONSERVICE =              24
    ...
MonService.java :

    import utils.container.Service
    
    public class MonService implements Service {
        private int param;
        public MonService(MonAutreService ah) {...}
        @Override
        public void updateConfig(Config config) {
            this.param = config.getInt(ConfigData.PARAM_MONSERVICE);
        }
    }

##### Orders
* **OrderWrapper**
* **HookFactory**
##### Data
* **Table**
* **Graphe**
* **SensorState**
* **XYO**
* **Controlers & Listener**
##### Locomotion
* **PathFollower**
* **PathFinder**

#### TTHL-master
#### TTHL-slave
    
### Tests
Les tests, indispensables pour la maintenabilité du HL, et permettant d'être efficace pour trouver l'origine de vos bugs
lorsque vous développez de nouvelles fonctionnalités, sont découpés ici en trois packages :

* **Unitaires** : ce sont les tests de fonctionnalités qui ne dépendent d'aucune autre fonctionnalités ! Typiquement tout
  ce qu'il y a dans le package utils. Ils servent tout simplement à vérifier que les fonctionnalités de base n'ont pas
  été altérées par quelques curieux : si ces tests ne passent pas, rien ne fonctionnera correctement

* **Validation** : ces tests sont plus nombreux et plus complexes. Il servent à vérifier l'intégrité des 
  fonctionnalités testables sans le hardware, principalement celles du package data dans notre cas. Par exemple, on 
  vérifie que la méthode de mise à jour du graphe à partir de points données par le Lidar donne des résultats sans 
  fautes, c'est-à-dire que les obstacles sont correctement placés et que les arrêtes parcourables du Graphe ne traverse 
  pas ces obstacles.
  
* **Embedded** : ces tests sont ceux réalisés en conditions réels, c'est-à-dire avec un vrai robot ! Ce sont ceux que vous 
  devrez sans doute beaucoup toucher puisqu'ils doivent assurer toute la fonctionnalité du Robot : **GAGNER**
  
Ces tests sont destinés à être executer quotidiennement par un bot Jenkins (excépté pour les réels), vous permettant de 
vite voir si un bug a été introduit par une feature et d'indentifier plus rapidement son origine.