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

import data.XYO;
import locomotion.Locomotion;
import orders.OrderWrapper;
import orders.order.ActionsOrder;
import orders.order.Speed;
import pfg.config.Config;
import utils.container.Service;
import utils.math.Vec2;

/**
 * Classe regroupant tout les services et fonctionnalitées de base du robot
 * TODO : Compléter
 *
 * @author rem
 */
public abstract class Robot implements Service {

    /**
     * Service qui permet au robot de bouger
     */
    protected Locomotion locomotion;

    /**
     * Service qui permet au robot d'envoyer des ordres au LL
     */
    protected OrderWrapper orderWrapper;

    /**
     * Position et Orientation du robot
     */
    protected XYO xyo;

    /**
     * @param locomotion
     *              service de mouvement du robot
     * @param orderWrapper
     *              service d'envoie d'ordre vers le LL
     */
    public Robot(Locomotion locomotion, OrderWrapper orderWrapper) {
        this.locomotion = locomotion;
        this.orderWrapper = orderWrapper;
    }

    public void moveToPoint(Vec2 point) {
        //TODO
    }

    public void moveLengthwise(int distance) {
        //TODO
    }

    public void turn(double angle) {
        //TODO
    }

    protected void useActuator(ActionsOrder order) {
        //TODO
    }

    public void setSpeed(Speed speed) {
        //TODO
    }

    public void setPositionAndOrientation(Vec2 pos, double orientation) {
        //TODO
    }
    // And so on...

    @Override
    public void updateConfig(Config config) {}
}
