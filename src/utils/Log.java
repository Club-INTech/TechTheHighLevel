package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public enum Log
{
    COMMUNICATION(true),
    DATA_HANDLER(true),
    LOCOMOTION(true),
    STRATEGY(true)
    ;

    /**
     * Préfixes de couleurs pour l'affichage (Debug, Warning & Critical)
     */
    private static final String DEBUG       = "\u001B[32m";
    private static final String WARNING     = "\u001B[33m";
    private static final String CRITICAL    = "\u001B[31m";
    private static final String LOG_INFO    = "\u001B[36m";

    /**
     * Instance permettant d'avoir la date et l'heure
     */
    private static GregorianCalendar calendar;

    /**
     * Buffer d'écriture dans un fichier de log
     */
    private static BufferedWriter writer;

    /**
     * True pour sauvegarder les logs
     */
    private static boolean saveLogs = true;

    /**
     * Nom du fichier de sauvegarde des logs
     */
    private static String finalSaveFile;

    /**
     * Spécifie si les logs du canal doivent être activés ou non
     */
    private boolean active;

    /**
     * Pour chaque canaux, on peut spécifier une couleur d'affichage
     * @param defaultActive     true si par défault affiché
     */
    Log(boolean defaultActive)
    {
        active = defaultActive;
    }

    /**
     * Méthode standard de log
     *
     * @param message   message à logger
     */
    public void debug(Object message)
    {
        writeToLog(DEBUG, message.toString());
    }

    /**
     * Méthode warning de log
     *
     * @param message   message à logger
     */
    public void warning(Object message)
    {
        writeToLog(WARNING, message.toString());
    }

    /**
     * Méthode warning de log
     *
     * @param message   message à logger
     */
    public void critical(Object message)
    {
        writeToLog(CRITICAL, message.toString());
    }


    /**
     * Log du message
     *
     * @param color     le préfixe pour la couleur en sortie standart
     * @param message   message à affiché
     */
    private void writeToLog(String color, String message)
    {
        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "h" +
                calendar.get(Calendar.MINUTE) + ":" +
                calendar.get(Calendar.SECOND) + "," +
                calendar.get(Calendar.MILLISECOND);

        if(this.active)
        {
            StackTraceElement elem = Thread.currentThread().getStackTrace()[3];
            System.out.println(color + hour + " " + this.name() + " " +
                    elem.getClassName() + "." + elem.getMethodName() + ":" + elem.getLineNumber() + " > " + message);
        }

        if(saveLogs)
        {
            writeToFile(hour + " " + this.name() + " > " + message);
        }
    }

    /**
     * Ecrit le message spécifié dans le fichier de log
     *
     * @param message le message a logguer
     */
    private void writeToFile(String message)
    {
        // chaque message sur sa propre ligne
        message += "\n";
        try
        {
            writer.write(message);
            writer.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Initialise les flux d'entrée/sortie
     */
    public static void init()
    {
        try {
            calendar = new GregorianCalendar();
            String hour = calendar.get(Calendar.HOUR) + ":" +
                    calendar.get(Calendar.MINUTE) + ":" +
                    calendar.get(Calendar.SECOND);
            File testFinalRepertoire = new File("./logs");
            finalSaveFile = "./logs/LOG-" + hour + ".txt";
            if (!testFinalRepertoire.exists())
                testFinalRepertoire.mkdir();
            writer = new BufferedWriter(new FileWriter(finalSaveFile, true));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Ferme le log et sauvegarde dans un fichier si besoin
     */
    public static void close()
    {
        System.out.println(LOG_INFO + "FERMETURE DU LOG");
        if(saveLogs)
            try {
                System.out.println(LOG_INFO + "SAUVEGARDE DES FICHIERS DE LOG");
                if(writer != null)
                    writer.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }

    /**
     * Active tous les channels
     */
    public static void activeAllChannels()
    {
        for (Log log : values())
        {
            log.setActive(true);
        }
    }

    /**
     * Désactive tout les channels
     */
    public static void disableAllChannels()
    {
        for (Log log : values())
        {
            log.setActive(false);
        }
    }

    /**
     * Getters & Setters
     */
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
