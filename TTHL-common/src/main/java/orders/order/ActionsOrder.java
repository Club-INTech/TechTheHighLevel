package orders.order;

/**
 * Enum qui contient tous les ordres donnés aux actionneurs (baisser ou relever un bras par exemple)
 */
public enum ActionsOrder implements Order {
    //Exemples

    OuvrePorteAvant("olpAv"),
    FermePorteAvant("flpAv"),
    OuvrePorteArriere("olpAr"),
    FermePorteArriere("flpAr"),
    ActiveLaPompe("alp"),
    DesactiveLaPompe("dlp"),
    FermePorteDroite("flpd"),
    FermePorteGauche("flpg"),


    ;
    /**Ordre envoyé au LL*/
    private String orderStr;
    
    /**Durée de l'action en ms*/
    private int actionDuration;

    /**
     * Constructeur qui ne précise pas la durée l'action
     * @param orderStr action à faire
     */
    ActionsOrder(String orderStr){
        this(orderStr, 0);
    }

    /**
     * Constructeur qui précise l'action et sa durée
     * @param orderStr : action à faire 
     * @param actionDuration : durée de l'action
     */

    ActionsOrder(String orderStr, int actionDuration){
        this.orderStr=orderStr;
        this.actionDuration=actionDuration;
    }
    /**Getter de l'ordrer envoyé au LL
     * @return ordre en string
     * */
    public String getOrderStr(){
        return this.orderStr;
    }
    /**Getter de la durée de l'action
     * @return durée de l'action
     * */
    public int getActionDuration(){
        return this.actionDuration;
    }

   
}
