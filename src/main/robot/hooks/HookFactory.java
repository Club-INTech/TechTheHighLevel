
package robot.hooks;



import robot.OrdersEnums.ActionsOrder;
import robot.OrdersEnums.Speed;
import robot.SymmetrizedActuatorOrderMap;
import pfg.config.Config;
import robot.OrderWrapper;
import utils.Log;
import utils.container.Service;

import java.util.ArrayList;

/**
 * Classe permettant de gérer les hooks via une enum : pour créer un hook, il suffit de l'ajouter dans l'enum HookNames
 * Les hooks sont configurés (=envoyés au LL) via la méthode configureHook, a appelé en début de match ou de script
 */
public class HookFactory implements Service {

    /** Log */
    private Log log;

    /** Wrapper */
    private OrderWrapper orderWrapper;


    /**symetry*/
    private boolean symetry;

    /** Liste des Hooks */
    private ArrayList<HookNames> configuredHook = new ArrayList<HookNames>();

    /** Map pour la symétrie des actionneurs */
    private SymmetrizedActuatorOrderMap mActuatorCorrespondenceMap = new SymmetrizedActuatorOrderMap();

    /** Constructeur */
    public HookFactory (OrderWrapper orderWrapper, Config config, Log log){
        this.orderWrapper=orderWrapper;
        updateConfig(config);
    }

    /**
     * Configure les hooks en paramètres (envoie toute les infos au LL)
     */
    public void configureHook(HookNames... hooks) {

        String sentOrder;
        for(HookNames hook:hooks){


            if (hook.getOrder() instanceof Speed){
                sentOrder = "ctrv " + ((Speed) hook.getOrder()).translationSpeed + " " + (float) ((Speed) hook.getOrder()).rotationSpeed;
            }
            else if (hook.getOrder() instanceof ActionsOrder){
                if (symetry) {
                    sentOrder = mActuatorCorrespondenceMap.getSymmetrizedActuatorOrder((ActionsOrder) hook.getOrder()).getOrderStr();
                }
                else {
                    sentOrder = hook.getOrder().getOrderStr();
                }
            }else{
                log.critical("Mauvaise enum, la méthode doit implémenter Order");
                break;
            }

            if (configuredHook.contains(hook)){
                log.warning("Hook déjà configuré : on ne fait rien");
                break;
            }
            if(symetry){
                hook.setPosition(hook.getPosition().symetrize());
                log.debug("la position envoyée au bas niveau pour le hook"+hook.getPosition());
            }
            orderWrapper.configureHook(hook.getId(), hook.getPosition(), hook.getTolerency(), hook.getOrientation(),hook.getTolerencyAngle(),sentOrder);
            log.debug("Hook " + hook.getDeclaringClass() + " : Configuré");
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
                log.warning("Hook " + hook.getDeclaringClass().getName() + " : Non configuré ! Ne peut etre activé");
                break;
            }
            orderWrapper.enableHook(hook);
            log.debug("Hook " + hook.getDeclaringClass().getName() + " : Activé");
        }
    }

    /**
     * Desactive les hooks en paramètres
     * Balance un WARNING si le hook n'a pas été configuré
     */
    public void disableHook(HookNames... hooks){
        for(HookNames hook:hooks){
            if(!configuredHook.contains(hook)){
                log.warning("Hook " + hook.getDeclaringClass().getName() + " : Non configuré ! Ne peut etre désactivé");
                break;
            }
            orderWrapper.disableHook(hook);
            log.debug("Hook " + hook.getDeclaringClass().getName() + " : Désactivé");
        }
    }

    /**
     * Active tous les hooks configurés
     */
    public void enableConfiguredHook(){
        for(HookNames hook:configuredHook){
            orderWrapper.enableHook(hook);
            log.debug("Hook " + hook.getDeclaringClass().getName() + " : Activé");
        }
    }

    /**
     * Désactive tous les hooks configurés
     */
    public void disableConfiguredHook(){
        for(HookNames hook:configuredHook){
            orderWrapper.disableHook(hook);
            log.debug("Hook " + hook.getDeclaringClass().getName() + " : Désactivé");
        }
    }

    @Override
    public void updateConfig(Config config) {
    }
}


