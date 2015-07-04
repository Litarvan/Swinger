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
package fr.theshark34.swinger.textured;

import fr.theshark34.swinger.Swinger;
import fr.theshark34.swinger.abstractcomponents.AbstractProgressBar;
import java.awt.AlphaComposite;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The STexturedProgressBar
 *
 * <p>
 *    A textured progress bar =)
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class STexturedProgressBar extends AbstractProgressBar {

    /**
     * The background texture
     */
    private BufferedImage backgroundTexture;

    /**
     * The foreground texture
     */
    private BufferedImage foregroundTexture;

    /**
     * The STexturedProgressBar
     *
     * @param backgroundTexture
     *            The background texture
     * @param foregroundTexture
     *            The foreground texture
     */
    public STexturedProgressBar(BufferedImage backgroundTexture, BufferedImage foregroundTexture) {
        this.backgroundTexture = backgroundTexture;
        this.foregroundTexture = foregroundTexture;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the background texture
        g.drawImage(backgroundTexture, 0, 0, backgroundTexture.getWidth(), backgroundTexture.getHeight(), this);

        // Doing a cross mult to get the width/height of the foreground texture to use
        int fgSize = Swinger.crossMult(getValue(), getMaximum(), isVertical() ? foregroundTexture.getHeight() : foregroundTexture.getWidth());

        // If the fgSize isn't 0
        if(fgSize > 0) {
            // Getting the sub image of the foreground
            BufferedImage subForeground = foregroundTexture.getSubimage(0, 0, isVertical() ? foregroundTexture.getWidth() : fgSize, isVertical() ? fgSize : foregroundTexture.getHeight());

            // Then drawing it
            g.drawImage(subForeground, 0, 0, subForeground.getWidth(), subForeground.getHeight(), this);
        }

        // If the string is painted
        if(isStringPainted()) {
            // Getting the Font Metrics
            FontMetrics fm = g.getFontMetrics();

            // Getting the string bounds
            Rectangle2D stringBounds = fm.getStringBounds(getString(), g);

            // Getting the center pos for this rectangle
            Point centerPos = Swinger.getRecCenterPos(stringBounds.getBounds(), this.getBounds());

            // Drawing the text, centered
            g.drawString(getString(), (int) centerPos.getX(), (int) centerPos.getY());
        }
    }

    /**
     * Set the background texture
     *
     * @param backgroundTexture
     *            The new texture
     */
    public void setBackgroundTexture(BufferedImage backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
        repaint();
    }

    /**
     * Return the background texture
     *
     * @return The background texture
     */
    public BufferedImage getBackgroundTexture() {
        return this.backgroundTexture;
    }

    /**
     * Sets the foreground texture
     *
     * @param foregroundTexture
     *            The new texture
     */
    public void setForegroundTexture(BufferedImage foregroundTexture) {
        this.foregroundTexture = foregroundTexture;
        repaint();
    }

    /**
     * Return the foreground texture
     *
     * @return The foreground texture
     */
    public BufferedImage getForegroundTexture() {
        return this.foregroundTexture;
    }

}
