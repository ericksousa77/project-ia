package constraint;

import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.Assignment;
import aima.Constraint;


public class TwoPersonsInSameRoom implements Constraint<Person, String>{

    private ArrayList<Person> employees;
    private List<Person> scope;

    public TwoPersonsInSameRoom(ArrayList<Person> employees) {
            this.employees = employees;
            
            //os escopos são todas as variaveis envolvidas na constratint
            scope = new ArrayList<>();
            for(Person employee : employees) {
            	scope.add(employee);
            }
            

    }

    @Override
    public List<Person> getScope() {
            return scope;
    }

    @Override
    public boolean isSatisfiedWith(Assignment<Person, String> assignment) {
    	
    	for (Person employee : assignment.getVariables()) {
		
    		for (Person employee2 : assignment.getVariables()) {
//    			//checo se a atribuição gerada agora da pessoa dois esta entre as atribuições passadas da pessoa 1
    			boolean officeIsOcuped = employee.getCurrentSchedule().contains(assignment.getValue(employee2));
    	        
    	        if(officeIsOcuped) {
    	        	return false;
    	        }
    	        
    	        //checo a atribuiçãoa atual da pessoa 1 com a atribuição atual da pessoa 2
    	        officeIsOcuped = assignment.getValue(employee).equals(assignment.getValue(employee2));
    	        
    	        if (!employee.equals(employee2) && officeIsOcuped) {
    	        	return false;
    	        }
		
    		}
    	}
    	
    	return true;

    }
}
        
       
   
    
