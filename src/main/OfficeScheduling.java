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
			currentSchedule = new ArrayList<String>();
			

			
//			System.out.println("Digite o nome da pessoa: "); 
			personName = lineSplited[0];
			

			
//			System.out.println("Essa pessoa esta vacinada ? Digite 1 para sim e 2 para não "); 
			isVacinatedInput = lineSplited[1];
			
//			isVacinatedInput.equals("1") ? isVacinated = true : isVacinated = false;
			
			if (isVacinatedInput.equals("1")) {
				isVacinated = true;
			}
			
			if (isVacinatedInput.equals("2")) {
				isVacinated = false;
			}
			

			
//			System.out.println("Digite quantas (numero) horas por dia essa pessoa trabalha: ");
			workTime = Integer.parseInt(lineSplited[2]);
			
			
//			System.out.println("Digite quantas preferencias de horario você tem: ");
			amountOfPreferences = Integer.parseInt(lineSplited[3]);
			
			
//			System.out.println("Digite a preferencia de horário em formato 24 horas: ");
			String currentPreference = "";
			for (int k = 0; k < amountOfPreferences; k++) {

				currentPreference = lineSplited[k+4];
//				System.out.println(currentPreference);
				try {
					schedulePreferences.add(currentPreference);
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
			}
				
			Person person = new Person(personName,isVacinated, workTime, schedulePreferences, currentSchedule);
			
			System.out.println(person);
//			for(int m = 0; m < person.getSchedulePreferences().size(); m++ ){
//				System.out.println(person.getSchedulePreferences().get(m));
//			}
			
			
		    employees.add(person);
//		    limpaConsole();
			
			
		}

		
		PersonToSchedule csp = null;
		
		System.out.println("passou1");
		
//		BackjumpingBacktrackingSolver strategy = new BackjumpingBacktrackingSolver<Person, String>();
		FlexibleBacktrackingSolver strategy = new FlexibleBacktrackingSolver<Person, String>();
//        FlexibleBacktrackingSolver strategy = new FlexibleBacktrackingSolver<Person, String>().set(CspHeuristics.mrv()).set(new AC3Strategy<>());
		
		
		System.out.println("passou2");
		
        Assignment<Person, String> result = null;
        
        Optional<Assignment<Person, String>> solution; 
        
        System.out.println("passou3");
		
        
        
        ArrayList<String> dayAvailableHours = new ArrayList<String>();
        
        //começar com o dominio máximo (24 horas) e depois vai diminuindo a medida que as pessoas vao sendo alocadas
//        for(int i = 1; i < 25; i++) {
//        	dayAvailableHours.add(""+i);
//        }
        
        //tratar para quando duas pessoas colocarem a mesma horas como preferencia
        
        
        for (int g = 0; g < employees.size(); g++) {
        	for (int h = 0; h<employees.get(g).getSchedulePreferences().size(); h++) {
        		dayAvailableHours.add(employees.get(g).getSchedulePreferences().get(h));
        	}
			
		}
        
        ArrayList<String> noDuplicateDayAvailableHours = new ArrayList<String>(new HashSet<>(dayAvailableHours));
        
        
      
        
		while(true) {
			
			System.out.println("merdaaaa");
			System.out.println(employees);
			System.out.println(noDuplicateDayAvailableHours);
			
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
                System.out.println("testando");
                
                if(hourOfDay.charAt(0) != 't') {

	                employees.get(i).getCurrentSchedule().add(result.getValue(employees.get(i)));
	                employees.get(i).setCurrentWorkTime(employees.get(i).getCurrentSchedule().size());
	
	                noDuplicateDayAvailableHours.remove(hourOfDay);
                }
                
                
                if(employees.get(i).getCurrentWorkTime() < employees.get(i).getWorkTime()) {
                	checkWorkTime = false;
                }
                
                
            }
                      
            System.out.println(checkWorkTime);
            System.out.println(noDuplicateDayAvailableHours.isEmpty());
            if(noDuplicateDayAvailableHours.isEmpty() || checkWorkTime ) {
            	
            	System.out.println("caiu no if do check");
				break;
			}
            
            
            //condição de parada
            
            boolean a=true; 
            for(int i=0; i<result.getVariables().size(); i++){               
                String personAux = result.getValue(result.getVariables().get(i));
                //mudar o t para liberar pessoas com o a primeira letra t
                if(personAux.charAt(0)!='t')
                    a=false; 
            }
            if(a)
                break;
            
            
             			
		}
		System.out.println("**SOLUÇÃO FINAL**");
		System.out.println(employees);
	

	}
	
	static void limpaConsole(){
		for(int i=0;i<1000;i++) {
			System.out.println();
		}
	}

}
