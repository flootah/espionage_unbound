/**
 * 
 */
package edu.cpp.cs.cs141.prog_final;

import java.io.Serializable;

/**
 * @author Corey Perez
 *
 */
public class SaveState implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private GameBoard gb;
    public String toString() {
        
        StringBuilder out = new StringBuilder();
        
        out.append(gb);
        
        return out.toString();
    }

}
