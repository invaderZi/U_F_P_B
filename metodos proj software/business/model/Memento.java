
package jobufpb3.business.model;

/**
 * [MEMENTO DESIGN PATTERN CLASS]
 */
public class Memento {
    private final String state;

   public Memento(String stateToSave) {
       state = stateToSave;
   }
   public String getSavedState() { 
       return state;
   }
    
}
