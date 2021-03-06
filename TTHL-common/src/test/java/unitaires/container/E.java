/**
 * Copyright (c) 2018, INTech.
 * this file is part of INTech's HighLevel.
 *
 * INTech's HighLevel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * INTech's HighLevel is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with it.  If not, see <http://www.gnu.org/licenses/>.
 **/

package unitaires.container;

import pfg.config.Config;
import utils.container.Service;

/**
 * Classe de test pour le container
 */
public class E implements Service
{
    private boolean config;
    public E() {
        config = false;
    }

    @Override
    public void updateConfig(Config config) {
        this.config = true;
    }

    public boolean isConfig() {
        return config;
    }
}
