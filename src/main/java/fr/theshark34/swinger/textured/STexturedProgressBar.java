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

import static fr.theshark34.swinger.Swinger.*;
import fr.theshark34.swinger.abstractcomponents.AbstractProgressBar;
import java.awt.Graphics;
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
        // If the background texture is null, throwing an Illegal Argument Exception, else setting it
        if(backgroundTexture == null)
            throw new IllegalArgumentException("Background Texture == null");
        this.backgroundTexture = backgroundTexture;

        // If the foreground texture is null, throwing an Illegal Argument Exception, else setting it
        if(foregroundTexture == null)
            throw new IllegalArgumentException("Foreground Texture == null");
        this.foregroundTexture = foregroundTexture;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Drawing the background texture
        drawFullsizedImage(g, this, backgroundTexture);

        // Doing a cross mult to get the width/height of the foreground texture to use
        int fgSize = crossMult(getValue(), getMaximum(), isVertical() ? this.getHeight() : this.getWidth());

        // If the fgSize isn't 0
        if(fgSize > 0) {
            // Getting the sub image of the foreground
            BufferedImage subForeground = foregroundTexture.getSubimage(0, 0, isVertical() ? this.getWidth() : fgSize, isVertical() ? fgSize : this.getHeight());

            // Then drawing it
            g.drawImage(subForeground, 0, 0, subForeground.getWidth(), subForeground.getHeight(), this);
        }

        // If the string is painted and the string isn't null
        if(isStringPainted() && getString() != null) {
            // Activating the anti alias
            activateAntialias(g);

            // Picking the string color
            if(getStringColor() != null)
                g.setColor(getStringColor());

            // Drawing the string, centered
            drawCenteredString(g, getString(), this.getBounds());
        }
    }

    /**
     * Set the background texture
     *
     * @param backgroundTexture
     *            The new texture
     */
    public void setBackgroundTexture(BufferedImage backgroundTexture) {
        // If the given background texture is null, throwing an Illegal Argument Exception, else setting it
        if(backgroundTexture == null)
            throw new IllegalArgumentException("backgroundTexture == null");
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
        // If the given foreground texture is null, throwing an Illegal Argument Exception, else setting it
        if(foregroundTexture == null)
            throw new IllegalArgumentException("foregroundTexture == null");
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
