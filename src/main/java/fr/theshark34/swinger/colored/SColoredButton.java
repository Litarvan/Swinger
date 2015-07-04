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

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.abstractcomponents.AbstractButton;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

/**
 * The SColoredButton
 *
 * <p>
 *    A colored button with text.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class SColoredButton extends AbstractButton {

    /**
     * The button main color
     */
    private Color color;

    /**
     * The button color when the mouse is on
     */
    private Color colorHover;

    /**
     * The button color when it is disabled
     */
    private Color colorDisabled;

    /**
     * The SColoredButton
     *
     * <p>
     *     When the mouse will be on the button, the button will be
     *     a little more white, and when it will be disabled, it will
     *     be a little more gray.
     * </p>
     *
     * @param color
     *            The button color
     */
    public SColoredButton(Color color) {
        this(color, null, null);
    }

    /**
     * The SColoredButton
     *
     * <p>
     *     When the mouse will be on the button, the button texture will
     *     become the given 'colorHover' color, and when it will be disabled,
     *     it will be a little more gray.
     * </p>
     *
     * @param color
     *            The button color
     * @param colorHover
     *            The button color when the mouse is on it
     */
    public SColoredButton(Color color, Color colorHover) {
        this(color, colorHover, null);
    }

    /**
     * The SColoredButton
     *
     * <p>
     *     When the mouse will be on the button, the button texture will
     *     become the given 'colorHover' color, and when it will be disabled,
     *     the texture will become the given 'colorDisabled' color.
     * </p>
     *
     * @param color
     *            The button color
     * @param colorHover
     *            The button color when the mouse is on it
     * @param colorDisabled
     *            The button color when it is disabled
     */
    public SColoredButton(Color color, Color colorHover, Color colorDisabled) {
        // If the color is null, throwing an Illegal Argument Exception, else setting it
        if(color == null)
            throw new IllegalArgumentException("Color == null");
        this.color = color;

        // If the color hover is null, creating it, else, setting it
        if(colorHover == null)
            this.colorHover = color.brighter();
        else
            this.colorHover = colorHover;

        // If the color disabled is null, creating it, else, setting it
        if(colorDisabled == null)
            this.colorDisabled = color.darker();
        else
            this.colorDisabled = colorDisabled;
    }

    public void paintComponent(Graphics g) {
        // Getting the corresponding color
        Color color;
        if(!this.isEnabled())
            color = colorDisabled;
        else if (super.isHover())
            color = colorHover;
        else
            color = this.color;

        // Drawing the button
        g.setColor(color);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // If the text is not null
        if(getText() != null) {
            // Activating the anti alias
            ((Graphics2D) g).setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) g).setRenderingHint(
                    RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Getting the Font Metrics
            FontMetrics fm = g.getFontMetrics();

            // Getting the string bounds
            Rectangle2D stringBounds = fm.getStringBounds(getText(), g);

            // Getting the center pos for this rectangle
            Point centerPos = Swinger.getRecCenterPos(this.getBounds(), stringBounds.getBounds());

            // Picking the string color
            if (getStringColor() != null)
                g.setColor(getStringColor());

            // Drawing the text, centered
            g.drawString(getText(), (int) centerPos.getX(), (int) centerPos.getY());
        }
    }

}
