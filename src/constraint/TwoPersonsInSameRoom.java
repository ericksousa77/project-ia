
package constraint;

import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.Assignment;
import aima.Constraint;


public class TwoPersonsInSameRoom implements Constraint<Person, String>{

    private Person person;
    private List<Person> scope;

    public TwoPersonsInSameRoom(Person person) {
            this.person = person;
            scope = new ArrayList<>(2);
            scope.add(person);

    }

    @Override
    public List<Person> getScope() {
            return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment<Person, String> assignment) {
    	
    	System.out.println("Two Persons");

        String personAssigned = assignment.getValue(this.person);  
        
//        boolean achou = false;
        for (int i = 0; i < assignment.getVariables().size(); i++) {
//        	Person p = assignment.getVariables().get(i);
        	if((!this.person.equals(assignment.getVariables().get(i)) && assignment.getValue(assignment.getVariables().get(i)).equals(personAssigned)) || this.person.getCurrentSchedule().contains(personAssigned)) {
        		System.out.println("false constraint");
        		return false;
        	}
        }
        System.out.println("true constraint");
        return true;
//        
//        System.out.println(this.firstPerson);
//        System.out.println(this.secondPerson);
//        
//        System.out.println("atribuidos");
//        System.out.println(firstPersonAssigned);
//        System.out.println(secondPersonAssigned);
//        
//       
//        
//       if(this.firstPerson.equals(this.secondPerson)) {
//    	   System.out.println("if do equals");
//    	   return false;
//       }
//        
//        if(firstPersonAssigned == null || secondPersonAssigned == null) {
//        	System.out.println("if dos dois nulos");
//        	return true;
//        }
//        
//        
//        if(firstPersonAssigned.equals(secondPersonAssigned)) {
//           System.out.println("if de atribuições iguais");
//     	   return false;
//        }
//        
//        System.out.println("retorno geral");
//        
//        return !this.firstPerson.getCurrentSchedule().contains(secondPersonAssigned) || !this.secondPerson.getCurrentSchedule().contains(firstPersonAssigned);
//        
    
        
//        return !firstPersonAssigned.equals(secondPersonAssigned);
        
    
    }
}
        
       
   
    
