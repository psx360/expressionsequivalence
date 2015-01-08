/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ps.expressionsequivalence.ui;

/**
 *
 * @author Admin
 */
public class Valid {
    public void setValid() {
        valid = true;
    }

    public void setInvalid() {
        valid = false;
    }

    public boolean isValid() {return valid;}

    private boolean valid = false;
}
