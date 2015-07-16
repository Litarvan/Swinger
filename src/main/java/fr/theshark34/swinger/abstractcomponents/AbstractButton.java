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
package fr.theshark34.swinger.abstractcomponents;

import fr.theshark34.swinger.event.SwingerEvent;
import fr.theshark34.swinger.event.SwingerEventListener;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * The AbstractButton
 *
 * <p>
 *    The super-class for the buttons, contains the button
 *    mechanisms (hover state, event listeners, etc...)
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public abstract class AbstractButton extends JComponent implements MouseListener {

    /**
     * The button text
     */
    private String text;

    /**
     * The color of the text
     */
    private Color textColor;

    /**
     * The event listeners, to execute when the button was clicked
     */
    private ArrayList<SwingerEventListener> eventListeners = new ArrayList<SwingerEventListener>();

    /**
     * If the mouse is on the button
     */
    private boolean hover = false;

    public AbstractButton() {
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // If the button is enabled
        if(this.isEnabled())
            // Executing all the action listeners
            for(SwingerEventListener eventListener : this.eventListeners)
                eventListener.onEvent(new SwingerEvent(this, SwingerEvent.BUTTON_CLICKED_EVENT));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        hover = true;

        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;

        repaint();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        repaint();
    }

    /**
     * Set the text of the button
     *
     * @param text
     *            The new button text
     */
    public void setText(String text) {
        // If the given text is null, throwing an Illegal Argument Exception, else setting it
        if(text == null)
            throw new IllegalArgumentException("text == null");
        this.text = text;

        repaint();
    }

    /**
     * Return the text of the button
     *
     * @return The button text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text color
     *
     * @param textColor
     *            The new string color
     */
    public void setTextColor(Color textColor) {
        // If the given string color is null, throwing an Illegal Argument Exception, else setting it
        if(textColor == null)
            throw new IllegalArgumentException("textColor == null");
        this.textColor = textColor;

        repaint();
    }

    /**
     * Return the text color (default is null)
     *
     * @return The text color
     */
    public Color getTextColor() {
        return textColor;
    }

    /**
     * Add an event listener for this button, to execute when
     * it will be clicked
     *
     * @param eventListener
     *            The event listener to add
     */
    public void addEventListener(SwingerEventListener eventListener) {
        // If the given event listener is null, throwing an Illegal Argument Exception, else setting it
        if(eventListener == null)
            throw new IllegalArgumentException("eventListener == null");

        this.eventListeners.add(eventListener);
    }

    /**
     * Returns all the event listeners of this button
     *
     * @return An array list of the event listeners of this button
     */
    public ArrayList<SwingerEventListener> getEventListeners() {
        return this.eventListeners;
    }

    /**
     * Return if the mouse is on the button
     *
     * @return If the button is hover
     */
    public boolean isHover() {
        return this.hover;
    }

}
