package csp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import variables.Person;
import aima.*;
import constraint.MaxWorkTime;
import constraint.Preferences;
import constraint.TwoPersonsInSameRoom;

//acho que isso tem que receber um array de 
public class PersonToSchedule extends CSP<Person, String>{
    public ArrayList<String> dayAvailableHours = new ArrayList<String>();
    
    //
    public List<Person> officePersons = new ArrayList<Person>();
    

    public PersonToSchedule(List<Person> persons) throws IOException {
        super();
        this.officePersons = persons;
        //lembrar de substituir esse 25 por uma variável
        for(int i = 1; i < 25; i++) {
        	this.dayAvailableHours.add(""+i);
        }
       
        System.out.println("passou5");
        //adição das variáveis ao problema
        for(int i = 0; i<officePersons.size();i++){
        	System.out.println(officePersons.get(i));
            this.addVariable(officePersons.get(i));            
        }
        
        System.out.println("passou6");
       
        //criação do domínio
        Domain<String> availableHoursDomain = new Domain<>(dayAvailableHours);
        
        System.out.println("passou8");
        
        //definição do domínio para cada variável
        for (Person person : getVariables())
            setDomain(person, availableHoursDomain); 
        
        System.out.println("passou9");
        
        //persons
        List<Person> employees;
        employees = getVariables();        
        
        System.out.println("passou10");
        
    
        for(int i=0; i<employees.size(); i++){
        	addConstraint(new MaxWorkTime(employees.get(i)));
            addConstraint(new Preferences(employees.get(i)));
            for(int j=0; j<employees.size(); j++){
                if(!employees.get(i).getName().equals(employees.get(j).getName())){
                    addConstraint(new TwoPersonsInSameRoom(employees.get(i), employees.get(j)));     
                    
                }
            }
        }
        System.out.println("passou11");
        
    }
 
}