package data;

import pfg.config.ConfigInfo;

public enum ConfigData implements ConfigInfo
{
    /** Constantes (rarement modifiées) */
    TABLE_X(3000),
    TABLE_Y(2000),
    TEMPS_MATCH(100),
    ETHERNET_DEFAULT_DELAY(1),

    /** Paramètres log */
    PRINT_LOG(true),
    SAVE_LOG(true),

    /** Paramètres match !*/
    COULEUR("vert"),
    C_DES_FOUS_EN_FACE(false),
    TAS_BASE_PRIS(false),
    TAS_CHATEAU_PRIS(false),
    TAS_STATION_EPURATION_PRIS(false),
    TAS_BASE_ENNEMI_PRIS(false),
    TAS_CHATEAU_ENNEMI_PRIS(false),
    TAS_STATION_EPURATION_ENNEMI_PRIS(false),
    INDICE_PATTERN_SIMULATION(0),

    /** Dimensions du robot */
    ROBOT_LENGTH(300),
    ROBOT_WIDTH(300),
    ROBOT_RADIUS(212),
    ENNEMY_RADIUS(400),

    /** Vitesses du robot */
    ROBOT_LINEAR_SPEED(840),        // mm/s
    ROBOT_ANGULAR_SPEED(Math.PI),   // rad/s

    /**Les cubes*/
    LONGUEUR_CUBE(58),

    /**Longueur bras*/
    LONGUEUR_BRAS_AVANT(317),
    LONGUEUR_BRAS_ARRIERE(333),

    /**Dimension portes*/
    DIMENSION_PORTES(87),

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

    /** Paramètres Locomotion */
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

    /** Paramètres Pathfinding */
    COUT_FIXE(10),

    /** Paramètre simulation */
    SIMULATION(false),

    /** Paramètre d'attente du jumper */
    ATTENTE_JUMPER(true),

    /** Paramètre permettant de savoir quel matchscript on exception */
    MATCHSCRIPT_TO_EXECUTE(0),

    /** Paramètres reconnaissance de couleurs */
    LOCALIZATION_AUTOMATED(true),
    FIRST_COLOR("null"),
    SECOND_COLOR("null"),
    THIRD_COLOR("null"),
    IMAGE_WIDTH(1280),
    IMAGE_HEIGHT(720),

    /**Paramètres couleurs*/
    rorange(183),
    gorange(107),
    borange(71),
    rjaune(184),
    gjaune(177),
    bjaune(37),
    rbleu(50),
    gbleu(84),
    bbleu(112),
    rnoir(13),
    gnoir(24),
    bnoir(20),
    rvert(43),
    gvert(120),
    bvert(68),
    saturationPreModifier(1.8),
    brightnessPreModifier(1.3),
    saturationModifierLightingUp(1.3),
    brightnessModifierLightingUp(1.1),

    /**Paramètres scripts*/
    DISTANCE_INTERRUPTEUR(50),
    DISTANCE_PENETRATION_ZONE_DEPOSE_CUBES(70),

    ;
    private Object defaultValue;

    /**
     * Constructor with some default value
     * @param defaultValue
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

    /**
     * The toString() method is already adapted
     */
}

