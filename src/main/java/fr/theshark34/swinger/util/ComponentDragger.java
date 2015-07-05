package fr.theshark34.swinger.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;

/**
 * The Component Dragger
 *
 * <p>
 *     This class when added as a mouse motion listener to a
 *     JComponent will allow it to be dragged by the mouse.
 * </p>
 *
 * To add it :
 *
 * <code>
 *     ComponentDragger dragger = new ComponentDragger(component);
 *     component.addMouseMotionListener(mover);
 * </code>
 *
 * @author TheShark34
 * @version 1.0.0-BETA
 */
public class ComponentDragger extends MouseAdapter {

    /**
     * The component to move
     */
    private JComponent component;

    /**
     * Basic constructor
     *
     * @param component
     *            The component to move
     */
    public ComponentDragger(JComponent component) {
        this.component = component;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        component.setLocation((int) component.getLocation().getX() + e.getX(),
                (int) component.getLocation().getY() + e.getY());
    }

}
