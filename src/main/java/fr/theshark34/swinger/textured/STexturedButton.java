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
import fr.theshark34.swinger.abstractcomponents.AbstractButton;

import java.awt.AlphaComposite;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * The STexturedButton
 *
 * <p>
 *    A textured button =)
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class STexturedButton extends AbstractButton {

    /**
     * The button texture
     */
    private Image texture;

    /**
     * The button texture when the mouse is on
     */
    private Image textureHover;

    /**
     * The button texture when it is disabled
     */
    private Image textureDisabled;

    /**
     * A Textured Button
     *
     * <p>
     *     When the mouse will be on the button, the button will be
     *     a little more white, and when it will be disabled, it will
     *     be a little more gray.
     * </p>
     *
     * @param texture
     *            The button texture
     */
    public STexturedButton(BufferedImage texture) {
        this(texture, null, null);
    }

    /**
     * A Textured Button
     *
     * <p>
     *     When the mouse will be on the button, the button texture will
     *     become the given 'textureHover' texture, and when it will be disabled,
     *     it will be a little more gray.
     * </p>
     *
     * @param texture
     *            The button texture
     * @param textureHover
     *            The button texture when the mouse is on
     */
    public STexturedButton(BufferedImage texture, BufferedImage textureHover) {
        this(texture, textureHover, null);
    }

    /**
     * A Textured Button
     *
     * <p>
     *     When the mouse will be on the button, the button texture will
     *     become the given 'textureHover' texture, and when it will be disabled,
     *     the texture will become the given 'textureDisabled' texture.
     * </p>
     *
     * @param texture
     *            The button texture
     * @param textureHover
     *            The button texture when the mouse is on
     * @param textureDisabled
     *            The button texture when it is disabled
     */
    public STexturedButton(BufferedImage texture, BufferedImage textureHover, BufferedImage textureDisabled) {
        super();

        // If the texture is null, throwing an Illegal Argument Exception, else setting it
        if(texture == null)
            throw new IllegalArgumentException("Texture == null");
        this.texture = texture;

        // If the texture hover is null, setting it to the texture, but with a transparent rectangle
        // on it of the Swinger.HOVER_COLOR (by default WHITE) color. Else setting it
        if(textureHover == null)
            this.textureHover = Swinger.fillImage(Swinger.copyImage(texture), Swinger.HOVER_COLOR, this.getParent());
        else
            this.textureHover = textureHover;

        // If the texture disabled is null, setting it to the texture, but with a transparent rectangle
        // on it of the Swinger.DISABLED_COLOR (by default GRAY) color. Else setting it
        if(textureDisabled == null)
            this.textureDisabled = Swinger.fillImage(Swinger.copyImage(texture), Swinger.DISABLED_COLOR, this.getParent());
        else
            this.textureDisabled = textureDisabled;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Getting the corresponding texture
        Image texture;
        if(!this.isEnabled())
            texture = textureDisabled;
        else if (super.isHover())
            texture = textureHover;
        else
            texture = this.texture;

        // Then drawing it
        g.drawImage(texture, 0, 0, this.getWidth(), this.getHeight(), this.getParent());

        // If the text is not null
        if(getText() != null) {
            // Activating the anti alias
            Swinger.activateAntialias(g);

            // Picking the string color
            if (getStringColor() != null)
                g.setColor(getStringColor());

            // Drawing the text, centered
            Swinger.drawCenteredString(g, getText(), this.getBounds());
        }
    }

    /**
     * Sets the texture of this button
     *
     * @param texture
     *            The new texture
     */
    public void setTexture(Image texture) {
        this.texture = texture;
        repaint();
    }

    /**
     * Sets the texture of this button when the mouse is on
     *
     * @param textureHover
     *            The new hover texture
     */
    public void setTextureHover(Image textureHover) {
        this.textureHover = textureHover;
        repaint();
    }

    /**
     * Sets the texture of this button when it is disabled
     *
     * @param textureDisabled
     *            The new disabled texture
     */
    public void setTextureDisabled(Image textureDisabled) {
        this.textureDisabled = textureDisabled;
        repaint();
    }

    /**
     * Return the texture of this button
     *
     * @return The button texture
     */
    public Image getTexture() {
        return this.texture;
    }

    /**
     * Return the texture of the button when the mouse is on
     *
     * @return The button hover texture
     */
    public Image getTextureHover() {
        return this.textureHover;
    }

    /**
     * Return the texture of the button when it is disabled
     *
     * @return The button disabled texture
     */
    public Image getTextureDisabled() {
        return this.textureDisabled;
    }

    /**
     * Set bounds by the given cords, and the texture size.
     * Same as :
     * <code>
     *     setBounds(x, y, getTexture().getWidth(getParent()), getTexture().getHeight(getParent()));
     * </code>
     *
     * @param x
     *            The button x cord
     * @param y
     *            The button y cord
     */
    public void setBounds(int x, int y) {
        this.setBounds(x, y, this.texture.getWidth(this.getParent()), this.texture.getHeight(this.getParent()));
    }

}
