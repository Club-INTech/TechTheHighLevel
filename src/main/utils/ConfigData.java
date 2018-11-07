package utils;

import pfg.config.ConfigInfo;

/**
 * Enumération contenant la liste des valeurs configurable via un fichier (config/config.txt),
 * La valeur associée dans cette classe est celle attribuée par défaut, lorsque l'on fait une faute d'orthographe
 * dans le nom de la clé par exemple.
 *
 * @author pf
 */
public enum ConfigData implements ConfigInfo
{
    /** Constantes (rarement modifiées) */
    TABLE_X(3000),
    TABLE_Y(2000),
    TEMPS_MATCH(100),
    ETHERNET_DEFAULT_TIME(1),
    MASTER(true),

    /** Paramètres log */
    PRINT_LOG(true),
    SAVE_LOG(true),

    /** Paramètres match !*/
    COULEUR("violet"),


    /** Dimensions du main.robot */
    //TODO : adapter
    ROBOT_LENGTH(300),
    ROBOT_WIDTH(300),
    ROBOT_RAY(212),
    BUDDY_RAY(150),
    ENNEMY_RAY(400),

    /**
     * Ips et ports des raspis, lidar & teensy
     */
    MASTER_IP("192.168.0.3"),
    MASTER_PORT(14500),
    TEENSY_MASTER_IP("192.168.0.1"),
    TEENSY_MASTER_PORT(13500),
    TEENSY_SLAVE_IP("192.168.0.2"),
    TEENSY_SLAVE_PORT(13500),
    LIDAR_PORT(15500),

    LOCALHOST("localhost"),
    LOCALSERVER_PORT(13550),

    /** Vitesses du main.robot */
    ROBOT_LINEAR_SPEED(840),        // mm/s
    ROBOT_ANGULAR_SPEED(Math.PI),   // rad/s

    /**Longueur bras*/
    //TODO : adapter
    LONGUEUR_BRAS_AVANT(317),
    LONGUEUR_BRAS_ARRIERE(333),

    /** Paramètres obstacles */
    PEREMP_OBST(2000),

    /** Paramètres capteurs */
    ROBOT_EN_RADIUS(220),           //en mm
    MAX_SENSOR_RANGE(600),          //en mm
    MIN_SENSOR_RANGEAV(30),         //en mm
    MIN_SENSOR_RANGEAR(30),         //en mm
    MIN_SENSOR_RANGE(30),           //en mm
    SENSOR_ORIENTATION_FRONT(0),    //en radians
    SENSOR_ORIENTATION_BACK(0),     //en radians
    SENSOR_ANGLE_WIDENESS(1.04),    //en radians, CONE TOTAL (PAS DEMI CONE)
    UNCERTAINTY(1),
    // TODO à compléter

    /** Paramètres main.locomotion */
    BASIC_DETECTION(true),
    ADVANCED_DETECTION(false),
    BASIC_DETECTION_LOOP_DELAY(500),
    BASIC_DETECTION_DISTANCE(300),
    DETECTION_DISTANCE(300),
    DETECTION_RAY(300),
    FEEDBACK_LOOPDELAY(10),
    ENNEMY_TIMEOUT(10000),

    DISTANCE_TO_DISENGAGE(50),
    MAX_RETRIES_IF_BLOCKED(1),

    /** Paramètre simulation */
    SIMULATION(false),

    /** Paramètre d'attente du jumper */
    ATTENTE_JUMPER(true),


    /**Paramètres scripts*/
    //TODO : Compléter
    // Exemple : DISTANCE_INTERRUPTEUR(50),

    ;
    private Object defaultValue;

    /**
     * Constructor with some default value
     * @param defaultValue  valeur défaut du paramètre
     */
    private ConfigData(Object defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    /**
     * Just a getter
     */
    @Override
    public Object getDefaultValue()
    {
        return defaultValue;
    }

}

