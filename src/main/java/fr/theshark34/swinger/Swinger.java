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
package fr.theshark34.swinger;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * The Swinger class
 *
 * <p>
 *    The Swinger main class. Contains some constants, useful
 *    methods, etc...
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public class Swinger {

    /**
     * The Swinger version
     */
    public static final String VERSION = "1.0.0-BETA";

    /**
     * Fully transparent color
     */
    public static final Color TRANSPARENT = new Color(0, 0, 0, 0);

    /**
     * Little transparent opacity
     */
    public static final int LITTLE_TRANSPARENT = 50;

    /**
     * Little transparent white
     */
    public static final Color LITTLE_TRANSPARENT_WHITE = getTransparentWhite(LITTLE_TRANSPARENT);

    /**
     * The color for the components when the mouse is over
     */
    public static final Color HOVER_COLOR = LITTLE_TRANSPARENT_WHITE;

    /**
     * The color for the disabled components
     */
    public static final Color DISABLED_COLOR = getTransparentInstance(Color.GRAY, LITTLE_TRANSPARENT);

    /**
     * The path of the resources to find with getResource();
     */
    private static String resourcePath;

    /**
     * Sets the system look n feel (UI Style)
     */
    public static void setSystemLookNFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("[Swinger] WARNING: Can't set the system look n feel : " + e);
        }
    }

    /**
     * Return the white color with the given transparency
     *
     * @param transparency
     *            The transparency for the white
     * @return The white color with the given transparency
     */
    public static Color getTransparentWhite(int transparency) {
        return getTransparentInstance(Color.WHITE, transparency);
    }

    /**
     * Return the given color with the given transparency
     *
     * @param color
     *            The color to set the transparency
     * @param transparency
     *            The transparency to set to the color
     * @return The given color with the given transparency
     */
    public static Color getTransparentInstance(Color color, int transparency) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency);
    }

    /**
     * Copy an image
     *
     * @param image
     *            The image to copy
     * @return The copied image
     */
    public static BufferedImage copyImage(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    /**
     * Fill the given image with the given color
     *
     * @param image
     *            The image to fill
     * @param color
     *            The fill color
     * @param imageObserver
     *            The image observer for the image
     * @return The filled image
     */
    public static Image fillImage(Image image, Color color, ImageObserver imageObserver) {
        Graphics g = image.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, image.getWidth(imageObserver), image.getHeight(imageObserver));

        return image;
    }

    /**
     * Set a path for the resource to load with getResource()
     *
     * <p>
     *     If you do setResourcePath("resources/images/"), then doing
     *     getResource("image.png") will load resources/images/image.png.
     * </p>
     *
     * @param resourcePath
     *            The new resource path
     */
    public static void setResourcePath(String resourcePath) {
        Swinger.resourcePath = resourcePath.endsWith("/") ? resourcePath.substring(0, resourcePath.length() - 1) : resourcePath;
    }

    /**
     * Return the current resource path (Can be null)
     *
     * @return The current resource path
     * @see Swinger#setResourcePath(String)
     */
    public static String getResourcePath() {
        return resourcePath;
    }

    /**
     * Loads an image, same as :
     *
     * <code>
     *   ImageIO.read(Swinger.class.getResourceAsStream(Swinger.getResourcePath() + "/animage"));
     * </code>
     *
     * @return The load resource
     * @throws IllegalArgumentException If it failed to load it
     */
    public static BufferedImage getResource(String resource) {
        try {
            return ImageIO.read(Swinger.class.getResourceAsStream(getResourcePath() + "/" + resource));
        } catch (IOException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't load the given resource (" + getResourcePath() + "/" + resource + ") : " + e);
        }
    }

    /**
     * Do a percentage from a value and a maximum
     *
     * @param value
     *            The value
     * @param maximum
     *            The maximum value
     * @return The made percentage
     */
    public static int percentage(int value, int maximum) {
        return crossMult(value, maximum, 100);
    }

    /**
     * Do a cross multiplication
     *
     * @param value
     *            The value
     * @param maximum
     *            The maximum value
     * @param coefficient
     *            The coefficient
     * @return The result
     */
    public static int crossMult(int value, int maximum, int coefficient) {
        return (int) ((double) value / (double) maximum * (double) coefficient);
    }

    /**
     * Gets the center position of a rectangle on a parent rectangle
     *
     * @param parent
     *            The parent rectangle
     * @param rectangle
     *            The rectangle to center
     * @return The rectangle center pos
     */
    public static Point getRecCenterPos(Rectangle parent, Rectangle rectangle) {
        double x = parent.getWidth() / 2 - rectangle.getWidth() / 2;
        double y = parent.getHeight() / 2 - rectangle.getHeight() / 2;

        return new Point((int) x, (int) y);
    }

}
