package orders;

import pfg.config.Config;
import orders.order.ActionsOrder;
import orders.order.Order;
import utils.container.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe qui permet de symétriser tous les ordres
 */
public class SymmetrizedActuatorOrderMap implements Service {

    /** Map contenant un actionneur pour clé, et son symétrique pour valeur */
    Map<ActionsOrder, ActionsOrder> mCorrespondenceMap = new HashMap<ActionsOrder, ActionsOrder>();

    /**
     * construit la map de correspondances
     */
    public SymmetrizedActuatorOrderMap(){
        mCorrespondenceMap.put(ActionsOrder.FermePorteDroite, ActionsOrder.FermePorteGauche);

    }

    /**
     *
     * @param order l'actionneur à symétriser
     * @return l'actionneur à symétriser
     */
    public Order getSymmetrizedActuatorOrder(ActionsOrder order)
    {
        return mCorrespondenceMap.get(order);
    }

    @Override
    public void updateConfig(Config config) {

    }
}
