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

import com.sun.awt.AWTUtilities;
import java.awt.Window;

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
     * A slow speed for animations
     */
    public static final int SLOW = 20;

    /**
     * A normal speed for animations
     */
    public static final int NORMAL = 10;

    /**
     * A fast speed for animations
     */
    public static final int FAST = 5;

    /**
     * Increment a number, from 0, to a given number, with a loop, and for each loop,
     * execute the given action with the current incremented number in parameter.
     *
     * @param to
     *            The max number
     * @param loopAction
     *            The action to execute for each loop
     */
    public static void query(final long to, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(long query = 0; query < to; query += 1)
                    loopAction.onLoop(query);
            }
        };
        t.start();
    }

    /**
     * Increment a number, from 0, to a given number, with a loop, and for each loop,
     * wait a given time, and then execute the given action with the current incremented
     * number in parameter.
     *
     * @param to
     *            The max number
     * @param toWait
     *            The time to wait each loop (in milliseconds)
     * @param loopAction
     *            The action to execute for each loop
     */
    public static void query(final long to, final long toWait, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(long query = 0; query < to; query += 1) {
                    loopAction.onLoop(query);
                    try {
                        sleep(toWait);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t.start();
    }

    /**
     * Increment a number, from a given, to an other, with a loop, and for each loop,,
     * wait a given time, and then execute the given action with the current incremented
     * number in parameter.
     *
     * @param from
     *            The number to start incrementing
     * @param to
     *            The max number
     * @param toWait
     *            The time to wait each loop (in milliseconds)
     * @param loopAction
     *            The action to execute for each loop
     */
    public static void query(final long from, final long to, final long toWait, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(long query = from; query < to; query += 1) {
                    loopAction.onLoop(query);
                    try {
                        sleep(toWait);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t.start();
    }

    /**
     * Increment a number with a given number, from a given, to an other, with a loop, and for each loop,
     * wait a given time, and then execute the given action with the current incremented
     * number in parameter.
     *
     * @param from
     *            The number to start incrementing
     * @param to
     *            The max number
     * @param speed
     *            The number to add each time
     * @param toWait
     *            The time to wait each loop (in milliseconds)
     * @param loopAction
     *            The action to execute for each loop
     */
    public static void query(final long from, final long to, final long speed, final long toWait, final QueryLoopAction loopAction) {
        Thread t = new Thread() {
            @Override
            public void run() {
                for(long query = from; query < to; query += speed) {
                    loopAction.onLoop(query);
                    try {
                        sleep(toWait);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        t.start();
    }

    /**
     * Fade in a given frame
     *
     * @param toFade
     *            The frame to fade
     */
    public static void fadeInFrame(Window toFade) {
        fade(toFade, NORMAL, false);
    }

    /**
     * Fade in a given frame, with a given speed
     *
     * @param toFade
     *            The frame to fade
     * @param speed
     *            The speed of the fade (Can be Animator.SLOW, Animator.NORMAL,
     *            Animator.FAST, or any number you want)
     */
    public static void fadeInFrame(Window toFade, int speed) {
        fade(toFade, speed, false);
    }

    /**
     * Fade out a given frame
     *
     * @param toFade
     *            The frame to fade
     */
    public static void fadeOutFrame(Window toFade) {
        fade(toFade, NORMAL, true);
    }

    /**
     * Fade in a given frame, with a given speed
     *
     * @param toFade
     *            The frame to fade
     * @param speed
     *            The speed of the fade (Can be Animator.SLOW, Animator.NORMAL,
     *            Animator.FAST, or any number you want)
     */
    public static void fadeOutrame(Window toFade, int speed) {
        fade(toFade, speed, true);
    }

    private static void fade(final Window toFade, final int speed, final boolean inverted) {
        query(100L, speed, new QueryLoopAction() {
            @Override
            public void onLoop(long query) {
                AWTUtilities.setWindowOpacity(toFade, inverted ?
                        100 - ((float) query / 100) : ((float) query / 100));
            }
        });
    }

}
