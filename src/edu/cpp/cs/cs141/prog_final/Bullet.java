/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

/**
 * @author Corey Perez
 *
 */
public class Bullet extends PowerUps {
    
    private Gun gun;
    
    public Bullet (Gun gun) {
        this.gun = gun;
    }
    
    public String toString() {
        String bulletInfo = "[G]";
        return bulletInfo;
    }

}
