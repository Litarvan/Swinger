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
import fr.theshark34.swinger.abstractcomponents.AbstractCheckbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * The SColoredCheckbox
 *
 * <p>
 *    A colored checkbox, use isChecked to check if it is checked.
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class SColoredCheckbox extends AbstractCheckbox {

    /**
     * The check image
     */
    public static final BufferedImage CHECK_IMAGE = getResourceIgnorePath("/fr/theshark34/swinger/resources/ui/check.png");

    /**
     * The default background color
     */
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

    /**
     * The default color of the check
     */
    public static final Color DEFAULT_CHECK_COLOR = Color.BLACK;

    /**
     * The background color
     */
    private Color backgroundColor = DEFAULT_BACKGROUND_COLOR;

    /**
     * The color of the check
     */
    private Color checkColor = DEFAULT_CHECK_COLOR;

    /**
     * Empty color, the color of the check will be the DEFAULT_CHECK_COLOR,
     * and the background color will be the DEFAULT_BACKGROUND_COLOR.
     */
    public SColoredCheckbox() {
    }

    /**
     * Constructor, with the background color. The check color will the
     * DEFAULT_CHECK_COLOR.
     *
     * @param backgroundColor
     *            The background color
     */
    public SColoredCheckbox(Color backgroundColor) {
        this(backgroundColor, null);
    }

    /**
     * Constructor with the background, and the check color.
     *
     * @param backgroundColor
     *            The background color
     * @param checkColor
     *            The color of the little check
     */
    public SColoredCheckbox(Color backgroundColor, Color checkColor) {
        super();

        // Setting the colors, by their default if they are null, or if not, by the given colors
        this.backgroundColor = backgroundColor == null ? DEFAULT_BACKGROUND_COLOR : backgroundColor;
        this.checkColor = checkColor == null ? DEFAULT_CHECK_COLOR : checkColor;
    }

    @Override
    public void paintComponent(Graphics g) {
        // Picking the background color
        g.setColor(this.backgroundColor);

        // Drawing the background
        fillFullsizedRect(g, this);

        // If the color isn't the white, coloring the check image, then drawing it
        Image check = backgroundColor.equals(Color.WHITE) ? CHECK_IMAGE : colorImage(copyImage(CHECK_IMAGE), checkColor.getRed(), checkColor.getGreen(), checkColor.getBlue());
        drawFullsizedImage(g, this, check);
    }

    /**
     * Set the background color of the checkbox
     *
     * @param backgroundColor
     *            The new background color
     */
    public void setBackgroundColor(Color backgroundColor) {
        // Setting the color, by its default if it is null, or if not, by the given color
        this.backgroundColor = backgroundColor == null ? DEFAULT_BACKGROUND_COLOR : backgroundColor;
    }

    /**
     * Return the background color of the checkbox
     *
     * @return The background color
     */
    public Color getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Set the background color of the check
     *
     * @param checkColor
     *            The new check color
     */
    public void setCheckColor(Color checkColor) {
        // Setting the color, by its default if it is null, or if not, by the given color
        this.checkColor = checkColor == null ? DEFAULT_CHECK_COLOR : checkColor;
    }

    /**
     * Return the color of the check
     *
     * @return The check color
     */
    public Color getCheckColor() {
        return this.checkColor;
    }

}
