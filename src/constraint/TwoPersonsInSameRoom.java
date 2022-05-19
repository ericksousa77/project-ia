
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
    	
        String personAssigned = assignment.getValue(this.person);  
        
        for (int i = 0; i < assignment.getVariables().size(); i++) {
        	
        	// esses dois primeiros ifs fazem a checagem para o assignment atual, verficando se mais alguem tem
        	//exceto para a mesma pessoa
        	// nenhuma pessoa pode ter a atribuição que outra pessoa ja teve
        	//a pessoa nao pode receber uma atribuição que essa ja recebeu
        	if((!this.person.equals(assignment.getVariables().get(i)) && assignment.getValue(assignment.getVariables().get(i)).equals(personAssigned)) || assignment.getVariables().get(i).getCurrentSchedule().contains(personAssigned)) {
        		return false;
        	}
        }
        return true;

    }
}
        
       
   
    
