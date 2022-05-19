package constraint;

import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.Assignment;
import aima.Constraint;

public class Preferences implements Constraint<Person, String> {
	//chegar se o assignment ja existe na current scheduling 
	
	//	return this.person.getSchedulePreferences().constains(assigned);

    private Person person;
    private List<Person> scope;
   
    
    public Preferences(Person person){
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
        ArrayList<String> preferences = this.person.getSchedulePreferences();                
        
        boolean isPreference = false; 
        
        isPreference = preferences.contains(assigned);    
        //checagem para a questão de não disponibilidade
        if(assigned.equals("to_be_defined_" + person.getName())) {
          return true;
        }

        return isPreference;
           
    }   
    
}