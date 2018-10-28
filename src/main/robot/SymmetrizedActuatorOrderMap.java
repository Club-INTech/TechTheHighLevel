package robot;


import robot.OrdersEnums.ActionsOrder;
import robot.OrdersEnums.Order;

import java.util.HashMap;
import java.util.Map;
public class SymmetrizedActuatorOrderMap {

    /** Map contenant un actionneur pour clé, et son symétrique pour valeur */
    Map<ActionsOrder, ActionsOrder> mCorrespondenceMap = new HashMap<ActionsOrder, ActionsOrder>();

    /**
     * construit la map de correspondances
     */
    public SymmetrizedActuatorOrderMap(){

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


}
