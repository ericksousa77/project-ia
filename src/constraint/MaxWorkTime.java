
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
        int maxWorkTime = this.person.getWorkTime();
        int currentWorkTime = this.person.getCurrentWorkTime();
        
        if(currentWorkTime+1 > maxWorkTime) {
        	if(assigned.equals("to_be_defined_" + person.getName())) {
        		return true;
        	}

            return false;
        }
        
       this.person.setCurrentWorkTime(currentWorkTime+1);
       return true;
                     
       
       
             
    }   

    
}