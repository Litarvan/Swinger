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
import fr.theshark34.swinger.abstractcomponents.AbstractCheckbox;
import java.awt.Graphics;
import java.awt.Image;

/**
 * The STexturedCheckbox
 *
 * <p>
 *    A simple textured checkbox, use isChecked to check if it is checked.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class STexturedCheckbox extends AbstractCheckbox {

    /**
     * The background checkbox image
     */
    private Image backgroundImage;

    /**
     * The image of the little check
     */
    private Image checkImage;

    /**
     * The STexturedCheckbox
     *
     * @param backgroundImage
     *            The background checkbox image
     * @param checkImage
     *            The image of the little check
     */
    public STexturedCheckbox(Image backgroundImage, Image checkImage) {
        super();

        // If the background image is null, throwing an Illegal Argument Exception, else setting it
        if(backgroundImage == null)
            throw new IllegalArgumentException("backgroundImage == null ");
        this.backgroundImage = backgroundImage;

        // If the check image is null, throwing an Illegal Argument Exception, else setting it
        if(checkImage == null)
            throw new IllegalArgumentException("checkImage == null");
        this.checkImage = checkImage;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Drawing the background
        drawFullsizedImage(g, this, backgroundImage);

        // If it is checked
        if(this.isChecked())
            // Drawing the little check image
            drawFullsizedImage(g, this, checkImage);
    }

    /**
     * Set the background checkbox image
     *
     * @param backgroundImage
     *            The new background
     */
    public void setBackgroundImage(Image backgroundImage) {
        // If the given background image is null, throwing an exception, else setting it
        if(backgroundImage == null)
            throw new IllegalArgumentException("backgroundImage == null ");
        this.backgroundImage = backgroundImage;

        repaint();
    }

    /**
     * Return the background checkbox image
     *
     * @return The background
     */
    public Image getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * Set the little check image
     *
     * @param checkImage
     *            The new check
     */
    public void setCheckImage(Image checkImage) {
        // If the given check image is null, throwing an exception, else setting it
        if(checkImage == null)
            throw new IllegalArgumentException("checkImage == null ");
        this.checkImage = checkImage;

        repaint();
    }

    /**
     * Return the little check image
     *
     * @return The check
     */
    public Image getCheckImage() {
        return this.checkImage;
    }

}
