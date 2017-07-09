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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

/**
 * The AbstractCheckbox
 *
 * <p>
 *    The super-class for the checkbox, contains the checkboxes
 *    mechanisms (checked, not checked)
 * </p>
 *
 * @version 1.0.0-BETA
 * @author TheShark34
 */
public abstract class AbstractCheckbox extends JComponent implements MouseListener {

    /**
     * If the box is checked
     */
    private boolean checked;

    public AbstractCheckbox() {
        this.addMouseListener(this);
    }

    /**
     * Set the box checked, or not
     *
     * @param checked
     *            If the box need to be now checked, or not
     */
    public void setChecked(boolean checked) {
        this.checked = checked;

        repaint();
    }

    /**
     * Return if the box is checked, or not
     *
     * @return True if it is, false if not
     */
    public boolean isChecked() {
        return this.checked;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        setChecked(!checked);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
