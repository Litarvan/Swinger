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
package fr.theshark34.swinger.event;

/**
 * The SwingerEvent
 *
 * <p>
 *    An event executed by some components, like the buttons when
 *    they are clicked, etc... It can be a super-class for other
 *    event too.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class SwingerEvent {

    /**
     * The event when a button is clicked
     */
    public static final int BUTTON_CLICKED_EVENT = 0;

    /**
     * The source of the event
     */
    private Object source;

    /**
     * The type of the event (SwingerEvent.BUTTON_CLICKED_EVENT, etc...)
     */
    private int type;

    /**
     * A Swinger Event
     *
     * @param source
     *            The source of the event
     * @param type
     *            The type of the event (SwingerEvent.BUTTON_CLICKED_EVENT, etc...)
     */
    public SwingerEvent(Object source, int type) {
        this.source = source;
    }

    /**
     * Return the source of the event
     *
     * @return The event source component
     */
    public Object getSource() {
        return this.source;
    }

    /**
     * Return the type of the event (SwingerEvent.BUTTON_CLICKED_EVENT, etc...)
     *
     * @return The type of the event
     */
    public int getType() {
        return this.type;
    }

}
