package main;

import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import aima.Assignment;
import aima.BackjumpingBacktrackingSolver;
import aima.FlexibleBacktrackingSolver;
import aima.MinConflictsSolver;
import csp.PersonToSchedule;
import aima.CspHeuristics;
import aima.AC3Strategy;

import variables.Person;

public class OfficeScheduling {
	
	
	private static  ArrayList<Person> employees = new ArrayList<Person>();
	

	public static void main(String[] args) throws FileNotFoundException {
		
		 	
		//o looping dura até todos as pessoas tiverem alocadas com seu tempo de trabalho
			
		int amountOfPersons = 0;
		String personName = "";
		boolean isVacinated = false;
		int workTime = 0;
		int amountOfPreferences = 0;
	    
	    ArrayList<String> schedulePreferences = new ArrayList<String>();
	    ArrayList<Integer> schedulePreferencesTemp = new ArrayList<Integer>();
	    ArrayList<String> currentSchedule = new ArrayList<String>();
	    
	    
	    String isVacinatedInput;
	    
	    Scanner in = new Scanner(new FileReader("employees.txt"));

		
		while (in.hasNextLine()) {
			
			 String line = in.nextLine();
			 if(line.isEmpty()) {
				 break;
			 }
			 String lineSplited [] = line.split(",");
			 
			
			personName = "";
			isVacinated = false;
			workTime = 0;
			amountOfPreferences = 0;
			
			
			schedulePreferences = new ArrayList<String>();
			schedulePreferencesTemp = new ArrayList<Integer>();
			currentSchedule = new ArrayList<String>();
			
	
			personName = lineSplited[0];
			
			isVacinatedInput = lineSplited[1];
			
			
			if (isVacinatedInput.equals("1")) {
				isVacinated = true;
			}
			
			if (isVacinatedInput.equals("2")) {
				isVacinated = false;
			}
			
		
			workTime = Integer.parseInt(lineSplited[2]);
			
			amountOfPreferences = Integer.parseInt(lineSplited[3]);
			
			Integer currentPreference = -1;
			for (int k = 0; k < amountOfPreferences; k++) {

				currentPreference = Integer.parseInt(lineSplited[k+4]);
				try {
					schedulePreferencesTemp.add(currentPreference);
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
			}
			
			schedulePreferencesTemp.sort(null);
			
			for (Integer i : schedulePreferencesTemp) {
				schedulePreferences.add(i.toString());
			}
				
			Person person = new Person(personName,isVacinated, workTime, schedulePreferences, currentSchedule);
						
		    employees.add(person);

			
			
		}

		
		PersonToSchedule csp = null;
				
		FlexibleBacktrackingSolver strategy = new FlexibleBacktrackingSolver<Person, String>();
	
        Assignment<Person, String> result = null;
        
        Optional<Assignment<Person, String>> solution; 
    
        ArrayList<String> dayAvailableHours = new ArrayList<String>();
           
        for (int g = 0; g < employees.size(); g++) {
        	for (int h = 0; h<employees.get(g).getSchedulePreferences().size(); h++) {
        		dayAvailableHours.add(employees.get(g).getSchedulePreferences().get(h));
        	}
			
		}
        
        ArrayList<String> noDuplicateDayAvailableHours = new ArrayList<String>(new HashSet<>(dayAvailableHours));

		while(true) {
			
			try {
                csp = new PersonToSchedule(employees, noDuplicateDayAvailableHours);
            } catch(IOException ex) {
                Logger.getLogger(OfficeScheduling.class.getName()).log(Level.SEVERE, null, ex);
            }
			

			//Gera a solução
            solution = strategy.solve(csp);
            
   
            
            try{
                result = solution.get();                
            }catch(java.util.NoSuchElementException e){                
                System.out.println("Não foi possível alocar");
                break;                
            }  
           
            //checa se todo mundo ja bateu seu limite de horas
            boolean checkWorkTime = true;
            for(int i=0; i<employees.size(); i++){
            	
                String hourOfDay = result.getValue(result.getVariables().get(i));
                
                if(hourOfDay.charAt(0) != 't') {

	                employees.get(i).getCurrentSchedule().add(result.getValue(employees.get(i)));
	                employees.get(i).setCurrentWorkTime(employees.get(i).getCurrentSchedule().size());
	
	                noDuplicateDayAvailableHours.remove(hourOfDay);
                }
                
                
                if(employees.get(i).getCurrentWorkTime() < employees.get(i).getWorkTime()) {
                	checkWorkTime = false;
                }
                
                
            }

            if(noDuplicateDayAvailableHours.isEmpty() || checkWorkTime ) {
				break;
			}
            
            
            //condição de parada
            
            boolean a=true; 
            for(int i=0; i<result.getVariables().size(); i++){               
                String personAux = result.getValue(result.getVariables().get(i));
                if(personAux.charAt(0)!='t')
                    a=false; 
            }
            if(a)
                break;
            
            
             			
		}
		System.out.println("**SOLUÇÃO FINAL**");
	
		for (Integer d = 1; d<=24; d++) {
			if(d <=12) {
				for (Person employee : employees) {
					for(Integer k = 0; k < employee.getCurrentSchedule().size(); k++){
						if (employee.getCurrentSchedule().get(k).equals(d.toString())) {
							System.out.printf("%s AM - %s\n",d, employee.getPersonName());
						}
						
					}
				}
				
			}
			
			if(d > 12) {
				for (Person employee : employees) {
					for(Integer k = 0; k < employee.getCurrentSchedule().size(); k++){
						if (employee.getCurrentSchedule().get(k).equals(d.toString())) {
							System.out.printf("%s PM - %s\n",d-12, employee.getPersonName());
						}
						
					}
				}
			}
			
			
			
			
		}

	}
	
}
