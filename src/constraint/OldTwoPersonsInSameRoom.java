
package constraint;

import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.Assignment;
import aima.Constraint;


public class OldTwoPersonsInSameRoom implements Constraint<Person, String>{

    private Person firstPerson;
    private Person secondPerson;
    private List<Person> scope;

    public OldTwoPersonsInSameRoom(Person firstPerson, Person secondPerson) {
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
//    	System.out.println("passou40");
        String firstPersonAssigned = assignment.getValue(this.firstPerson);   
        String secondPersonAssigned = assignment.getValue(this.secondPerson); 
        
   	    System.out.println("Two Persons");
//        System.out.println("passou41");
        
//       System.out.println(this.firstPerson);
//       System.out.println(this.secondPerson);

        
        boolean verify = false;
        boolean firstPersonAlreadyHaveThisHour = false;
        boolean secondPersonAlreadyHaveThisHour = false;
        
        if(firstPersonAssigned == null) {
        	
        	
        	
        	
        	 verify = !this.firstPerson.getCurrentSchedule().contains(secondPersonAssigned);
        	 
        	 //se a pessoa tiver esse horario essa verificação fica falsa
        	 secondPersonAlreadyHaveThisHour = !this.secondPerson.getCurrentSchedule().contains(secondPersonAssigned);
        	 
        	 if(verify && secondPersonAlreadyHaveThisHour) {
        		 this.secondPerson.getCurrentSchedule().add(secondPersonAssigned);
        		 return true;
        	 }
        	 System.out.println("printando infos1");
        	 System.out.println(verify);
       	     System.out.println(this.firstPerson);
        	 System.out.println(this.secondPerson);
        	 return false;
        	
        }
        
        if(secondPersonAssigned == null) {
        	verify = !this.secondPerson.getCurrentSchedule().contains(firstPersonAssigned);
        	
        	firstPersonAlreadyHaveThisHour = !this.firstPerson.getCurrentSchedule().contains(firstPersonAssigned);
        	
        	if(verify && firstPersonAlreadyHaveThisHour) {
        		this.firstPerson.getCurrentSchedule().add(firstPersonAssigned);
        		return true;
        	}
        	System.out.println("printando infos2");
        	System.out.println(verify);
        	System.out.println(this.firstPerson);
        	System.out.println(this.secondPerson);
        	return false;
        	
        	
        }
        
        verify = !this.firstPerson.getCurrentSchedule().contains(secondPersonAssigned) && !this.secondPerson.getCurrentSchedule().contains(firstPersonAssigned); //(this.firstPerson.getIsVacinated() && this.secondPerson.getIsVacinated());
        
        
        firstPersonAlreadyHaveThisHour = !this.firstPerson.getCurrentSchedule().contains(firstPersonAssigned);
        secondPersonAlreadyHaveThisHour = !this.secondPerson.getCurrentSchedule().contains(secondPersonAssigned);
        
        if(verify && firstPersonAlreadyHaveThisHour && secondPersonAlreadyHaveThisHour) {
        	this.firstPerson.getCurrentSchedule().add(firstPersonAssigned);
        	this.secondPerson.getCurrentSchedule().add(secondPersonAssigned);
        	return true;
        	
        }
        
//        if(verify && secondPersonAlreadyHaveThisHour) {
//        	this.secondPerson.getCurrentSchedule().add(secondPersonAssigned);
//        	
//        }
        
        
        	
        
        System.out.println(verify);
        System.out.println("schedulu antes de retornar");
        System.out.println(this.firstPerson); 
        System.out.println(this.secondPerson);
        
//        System.out.println("passou42");
        return false;
    
    }
}
        
       
   
    
