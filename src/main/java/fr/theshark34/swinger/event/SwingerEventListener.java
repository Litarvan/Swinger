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
 * The SwingerEventListener
 *
 * <p>
 *    A listener to listen for any SwingerEvent.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public interface SwingerEventListener {

    /**
     * Executed when an event is called
     *
     * @param event
     *            The called event
     */
    void onEvent(SwingerEvent event);

}
