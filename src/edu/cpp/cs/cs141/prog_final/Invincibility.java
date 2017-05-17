/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Invincibility extends PowerUps {
    
    private int protection;
    
    public Invincibility (int protection) {
        this.protection = protection;
    }
    
    public String toString() {
        String invincibilityInfo = "[I]";
        return invincibilityInfo;
    }

}
