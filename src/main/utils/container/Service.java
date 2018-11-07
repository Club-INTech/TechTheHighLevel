package utils.container;

import pfg.config.Config;

/**
 * Interface servant à définir un service : un service est un singleton qui doit implémenter la méthode updateConfig,
 * et être instancié par le container
 *
 * @author pf
 */
public interface Service
{
    /**
     * Cette méthode est appelée par le container après instanciation du service.
     * Elle sert à attribuer à des attributs des valeurs contenus dans la config.
     */
    void updateConfig(Config config);
}
