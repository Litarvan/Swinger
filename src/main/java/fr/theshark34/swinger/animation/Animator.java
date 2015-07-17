/*
 * Copyright 2015 TheShark34
 *
 * This file is part of Swinger.

 * Swinger is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Swinger is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Swinger.  If not, see <http://www.gnu.org/licenses/>.
 */
package fr.theshark34.swinger.animation;

/**
 * The Animator
 *
 * <p>
 *    The animator, is a powerful tool to animate everything you wanna,
 *    a little bit like jQuery.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class Animator {

    /**
     * Increment a number, from a given, to an other, with a loop, and for each loop,
     * execute the given action with the current incremented number in parameter.
     *
     * @param from
     *            The number to start incrementing
     * @param to
     *            The max number
     * @param speed
     *            The number to add each time
     * @param loopAction
     *            The action to execute for each loop
     */
    public void query(final int from, final int to, final int speed, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(int query = from; query < to; query += speed)
                    loopAction.onLoop(query);
            }
        };
        t.start();
    }

}
