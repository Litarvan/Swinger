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
package fr.theshark34.swinger.colored;

import static fr.theshark34.swinger.Swinger.*;
import fr.theshark34.swinger.abstractcomponents.AbstractProgressBar;
import java.awt.Color;
import java.awt.Graphics;

/**
 * The SColoredBar
 *
 * <p>
 *    A colored bar with text.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class SColoredBar extends AbstractProgressBar {

    /**
     * The background color
     */
    private Color background;

    /**
     * The foreground color
     */
    private Color foreground;

    /**
     * The SColoredBar
     *
     * <p>
     *     The background color of the bar will be the given
     *     background color, and the foreground color will be
     *     the background color but a little brighter.
     * </p>
     *
     * @param background
     *            The bar background color
     */
    public SColoredBar(Color background) {
        this(background, null);
    }

    /**
     * The SColoredBar
     *
     * <p>
     *     The background color of the bar will be the given
     *     background color, and the foreground color will be
     *     the given foreground color
     * </p>
     *
     * @param background
     *            The bar background color
     * @param foreground
     *            The bar foreground color
     */
    public SColoredBar(Color background, Color foreground) {
        // If the background color is null, throwing an Illegal Argument Exception, else setting it
        if(background == null)
            throw new IllegalArgumentException("background == null");
        this.background = background;

        // If the foreground color is null, creating it, else, setting it
        if(foreground == null)
            this.foreground = background.brighter();
        else
            this.foreground = foreground;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the background
        fillFullsizedRect(g, this, background);

        // Doing a cross mult to get the width/height of the foreground texture to use
        int fgSize = crossMult(getValue(), getMaximum(), isVertical() ? this.getHeight() : this.getWidth());

        // If the fgSize isn't 0
        if(fgSize > 0) {
            // Drawing the foreground
            g.setColor(foreground);
            g.fillRect(0, 0, isVertical() ? this.getWidth() : fgSize, isVertical() ? fgSize : this.getHeight());
        }

        // If the string is painted and the string isn't null
        if(isStringPainted() && getString() != null) {
            // Activating the anti alias
            activateAntialias(g);;

            // Picking the string color
            if(getStringColor() != null)
                g.setColor(getStringColor());

            // Drawing the string, centered
            drawCenteredString(g, getString(), this.getBounds());
        }
    }

    /**
     * Set the bar background color
     *
     * @param background
     *            The new background color
     */
    public void setBackground(Color background) {
        // If the given background color is null, throwing an Illegal Argument Exception, else setting it
        if(background == null)
            throw new IllegalArgumentException("background == null");
        this.background = background;

        repaint();
    }

    /**
     * Return the bar background color
     *
     * @return The bar background
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Set the bar foreground color
     *
     * @param foreground
     *            The new foreground color
     */
    public void setForeground(Color foreground) {
        // If the given foreground color is null, throwing an Illegal Argument Exception, else setting it
        if(foreground == null)
            throw new IllegalArgumentException("foreground == null");
        this.foreground = foreground;

        repaint();
    }

    /**
     * Return the bar foreground color
     *
     * @return The bar foreground
     */
    public Color getForeground() {
        return foreground;
    }
}
