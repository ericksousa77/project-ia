
package constraint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import variables.Person;
import aima.Assignment;
import aima.Constraint;

public class MaxWorkTime implements Constraint<Person, String> {

    private Person person;
    private List<Person> scope;
   
    
    public MaxWorkTime(Person person){
        this.person = person;        
        scope = new ArrayList<>(1);
        scope.add(person);       
    }
    
    @Override
    public List<Person> getScope() {
        return scope; 
        
    }

    @Override
    public boolean isSatisfiedWith(Assignment<Person, String> assignment) {
        String assigned = assignment.getValue(person); 
        int maxWorkTime = person.getWorkTime();
        int currentWorkTime = 0;
        
        currentWorkTime = person.currentSchedule.size()+1; 
       
        if(currentWorkTime>maxWorkTime) {
            return false;
        }
        
        this.person.getCurrentSchedule().add(assigned);
        return true;
                     
       
       
             
    }   

    
}