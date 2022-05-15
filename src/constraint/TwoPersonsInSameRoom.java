
package constraint;

import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.Assignment;
import aima.Constraint;


public class TwoPersonsInSameRoom implements Constraint<Person, String>{

    private Person firstPerson;
    private Person secondPerson;
    private List<Person> scope;

    public TwoPersonsInSameRoom(Person firstPerson, Person secondPerson) {
            this.firstPerson = firstPerson;
            this.secondPerson = secondPerson;
            scope = new ArrayList<>(2);
            scope.add(firstPerson);
            scope.add(secondPerson);
    }

    @Override
    public List<Person> getScope() {
            return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment<Person, String> assignment) {
    	System.out.println("passou40");
    	//firstPersonAssigned ta recebendo null
        String firstPersonAssigned = assignment.getValue(this.firstPerson);   
        String secondPersonAssigned = assignment.getValue(this.secondPerson); 
        
        System.out.println("passou41");
        
        System.out.println(firstPersonAssigned);
        
        System.out.println(secondPersonAssigned);
        
        System.out.println(this.firstPerson.getIsVacinated());
        
        System.out.println(this.secondPerson.getIsVacinated());
        
        boolean verify = !firstPersonAssigned.equals(secondPersonAssigned) || (this.firstPerson.getIsVacinated() && this.secondPerson.getIsVacinated());
        
        System.out.println("passou42");
        return verify;
    
    }
}
        
       
   
    
