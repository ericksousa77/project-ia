
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
        int currentWorkTime = 0;
        
        System.out.println(maxWorkTime);
        
        if(this.person.getCurrentSchedule().isEmpty() && maxWorkTime > 0) {
        	System.out.println("entrou no true");
        	return true;
        }
        
        
        System.out.println("passou35");
        
        
        currentWorkTime = this.person.currentSchedule.size() + 1; 
              
        
        System.out.println("passou36");
       
        if(currentWorkTime>maxWorkTime) {
        	System.out.println("passou37");
            return false;
        }
        
        System.out.println("passou38");
        
        this.person.getCurrentSchedule().add(assigned);
        System.out.println("passou39");
        return true;
                     
       
       
             
    }   

    
}