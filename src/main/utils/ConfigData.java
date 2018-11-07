package utils;

import pfg.config.ConfigInfo;

/**
 * Enumération contenant la liste des valeurs configurable via un fichier (config/config.txt),
 * La valeur associée dans cette classe est celle attribuée par défaut, lorsque l'on fait une faute d'orthographe
 * dans le nom de la clé par exemple.
 *
 * @author rem
 */
public enum ConfigData implements ConfigInfo
{
    /**
     * Constantes (rarement modifiées)
     */
    TABLE_X(3000),
    TABLE_Y(2000),
    TEMPS_MATCH(100),
    ETHERNET_DEFAULT_TIME(1),

    /**
     * Couleur
     */
    COULEUR("violet"),

    /**
     * Informations relatives au status du robot (Maître ou esclave ?)
     */
    MASTER(true),

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

    /**
     * Paramètres du log
     */
    PRINT_LOG(true),
    SAVE_LOG(true),

    /**
     * Dimensions du robot
     */
    ROBOT_RAY(220),
    BUDDY_RAY(150),
    ENNEMY_RAY(220),

    /**
     * Threshold de comparaison de deux positions
     */
    VECTOR_COMPARISON_THRESHOLD(60),

    /**
     * Paramètres du Graphe
     */
    NBR_NOEUDS_X(30),
    NBR_NOEUDS_Y(20),
    NBR_NOEUDS_CIRCLE(12),
    ESPACEMENT_CIRCLE(1.2)
    ;

    /**
     * Valeur par défaut de la config (en dure)
     */
    private Object defaultValue;

    /**
     * Constructor with some default value
     *
     * @param defaultValue  valeur par défaut du paramètre
     */
    ConfigData(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    /**
     * Just a getter
     */
    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

}
