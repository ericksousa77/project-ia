package csp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.*;
import constraint.MaxWorkTime;
import constraint.Preferences;
import constraint.TwoPersonsInSameRoom;


public class PersonToSchedule extends CSP<Person, String>{
    public ArrayList<String> dayAvailableHours = new ArrayList<String>();

    public List<Person> officePersons = new ArrayList<Person>();

    public PersonToSchedule(List<Person> persons, ArrayList<String> dayAvailableHours) throws IOException {
        super();
        this.officePersons = persons;
        this.dayAvailableHours = dayAvailableHours;
        
        //adição das variáveis ao problema
        for(int i = 0; i<officePersons.size();i++){
            this.addVariable(officePersons.get(i));            
        }
       
        //criação do domínio
//        Domain<String> availableHoursDomain = new Domain<>(dayAvailableHours);
                
        //definição do domínio para cada variável
        for (Person person : getVariables()) {
            setDomain(person, new Domain<>(person.getSchedulePreferences())); 
        }
             
        //persons
        List<Person> employees;
        employees = getVariables();        

        for(int i=0; i<employees.size(); i++){
            addConstraint(new Preferences(employees.get(i))); 
            addConstraint(new TwoPersonsInSameRoom(employees.get(i))); 
            addConstraint(new MaxWorkTime(employees.get(i)));
        }        
        
    }
 
}