
package orders.hooks;



import orders.order.Order;
import pfg.config.Config;
import orders.OrderWrapper;
import utils.Log;
import utils.container.Service;

import java.util.ArrayList;

/**
 * Classe permettant de gérer les hooks via une enum : pour créer un hook, il suffit de l'ajouter dans l'enum HookNames
 * Les hooks sont configurés (=envoyés au LL) via la méthode configureHook, à appeler en début de match ou de script
 */
public class HookFactory implements Service {

    /** OrderWrapper */
    private OrderWrapper orderWrapper;


    /** Liste des Hooks */
    private ArrayList<HookNames> configuredHook = new ArrayList<HookNames>();



     /**
     *Constructeur en privé car déjà instancié par le container
     */
    private HookFactory (OrderWrapper orderWrapper){
        this.orderWrapper=orderWrapper;
    }

    /**
     * Configure les hooks en paramètre (envoie toutes les infos au LL)
     */
    public void configureHook(HookNames... hooks) {

        Order sentOrder;
        for(HookNames hook:hooks){

            sentOrder=hook.getOrder();

            if (configuredHook.contains(hook)){
                Log.HOOK.warning("Hook déjà configuré : on ne fait rien");
                break;
            }
            orderWrapper.configureHook(hook.getId(), hook.getPosition(), hook.getTolerency(), hook.getOrientation(),hook.getTolerencyAngle(),sentOrder);
            Log.HOOK.debug("Hook " + hook.getDeclaringClass() + " : Configuré");
            configuredHook.add(hook);
        }
    }

    /**
     * Active les hooks en paramètres
     * Balance un WARNING si le hook n'a pas été configuré (et ne fait rien du coup...)
     */
    public void enableHook(HookNames... hooks){
        for(HookNames hook:hooks){
            if (!configuredHook.contains(hook)){
                Log.HOOK.warning("Hook " + hook.getDeclaringClass().getName() + " : Non configuré ! Ne peut etre activé");
                break;
            }
            orderWrapper.enableHook(hook);
            Log.HOOK.debug("Hook " + hook.getDeclaringClass().getName() + " : Activé");
        }
    }

    /**
     * Desactive les hooks en paramètres
     * Balance un WARNING si le hook n'a pas été configuré
     */
    public void disableHook(HookNames... hooks){
        for(HookNames hook:hooks){
            if(!configuredHook.contains(hook)){
                Log.HOOK.warning("Hook " + hook.getDeclaringClass().getName() + " : Non configuré ! Ne peut etre désactivé");
                break;
            }
            orderWrapper.disableHook(hook);
            Log.HOOK.debug("Hook " + hook.getDeclaringClass().getName() + " : Désactivé");
        }
    }

    /**
     * Active tous les hooks configurés
     */
    public void enableConfiguredHook(){
        for(HookNames hook:configuredHook){
            orderWrapper.enableHook(hook);
            Log.HOOK.debug("Hook " + hook.getDeclaringClass().getName() + " : Activé");
        }
    }

    /**
     * Désactive tous les hooks configurés
     */
    public void disableConfiguredHook(){
        for(HookNames hook:configuredHook){
            orderWrapper.disableHook(hook);
            Log.HOOK.debug("Hook " + hook.getDeclaringClass().getName() + " : Désactivé");
        }
    }

    @Override
    public void updateConfig(Config config) {

    }
}


