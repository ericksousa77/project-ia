package main;

import java.io.IOException;

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
	
	
	

	public static void main(String[] args) {
		
		 	
		//o looping dura até todos as pessoas tiverem alocadas com seu tempo de trabalho
		
	
			
		int amountOfPersons = 0;
		String personName = "";
		boolean isVacinated = false;
		int workTime = 0;
		int amountOfPreferences = 0;
		//qual a maneira correta de inializar esse cara ? 
	    
	    ArrayList<String> schedulePreferences = new ArrayList<String>();
	    ArrayList<String> currentSchedule = new ArrayList<String>();
	    
	    String isVacinatedInput;
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Digite a quantidade de pessoas que trabalham na empresa: "); 
		amountOfPersons = Integer.parseInt(input.nextLine());
		
		
		for (int i = 0; i < amountOfPersons; i++) {
			personName = "";
			isVacinated = false;
			workTime = 0;
			amountOfPreferences = 0;
			
			
			
			//atenção aqui
//			for (int j = 0; j < schedulePreferences.size(); j++) {
//				schedulePreferences.remove(j);
//			}
			schedulePreferences = new ArrayList<String>();
			currentSchedule = new ArrayList<String>();
			
//			System.out.println("passou");
			
			System.out.println("Digite o nome da pessoa: "); 
			personName = input.nextLine();
			
//			System.out.println(personName);
			
			System.out.println("Essa pessoa esta vacinada ? Digite 1 para sim e 2 para não "); 
			isVacinatedInput = input.nextLine();
			
//			isVacinatedInput.equals("1") ? isVacinated = true : isVacinated = false;
			
			if (isVacinatedInput.equals("1")) {
				isVacinated = true;
			}
			
			if (isVacinatedInput.equals("2")) {
				isVacinated = false;
			}
			
//			System.out.println(isVacinated);
			
			System.out.println("Digite quantas (numero) horas por dia essa pessoa trabalha: ");
			workTime = Integer.parseInt(input.nextLine());
			
//			System.out.println(workTime);
			
			System.out.println("Digite quantas preferencias de horario você tem: ");
			amountOfPreferences = Integer.parseInt(input.nextLine());
			
			
			System.out.println("Digite a preferencia de horário em formato 24 horas: ");
			String currentPreference = "";
			for (int k = 0; k < amountOfPreferences; k++) {
				if(k>0)
					System.out.println("Digite proxima preferencia de horário: ");
				currentPreference = input.nextLine();
//				System.out.println(currentPreference);
				try {
					schedulePreferences.add(currentPreference);
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
			}
			
			System.out.println("OK! Todas as preferencias já foram digitadas");
			
			
			
			Person person = new Person(personName,isVacinated, workTime, schedulePreferences, currentSchedule);
			
//			for(int m = 0; m < person.getSchedulePreferences().size(); m++ ){
//				System.out.println(person.getSchedulePreferences().get(m));
//			}
			
			
		    employees.add(person);
		    limpaConsole();
			
			
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
			
//			for (int g = 0; g < employees.size(); g++) {
//				dayAvailableHours.add("to_be_defined_"+employees.get(g).getPersonName());
//			}
			
			
			
			System.out.println("merdaaaa");
			System.out.println(employees);
			System.out.println(noDuplicateDayAvailableHours);
			
			try {
                csp = new PersonToSchedule(employees, noDuplicateDayAvailableHours);
            } catch(IOException ex) {
                Logger.getLogger(OfficeScheduling.class.getName()).log(Level.SEVERE, null, ex);
            }
			
//			System.out.println("passou20");
			
			//Gera a solução
            solution = strategy.solve(csp);
            
            
            
            
            
//            System.out.println("passou21");
            
            try{
                result = solution.get();                
            }catch(java.util.NoSuchElementException e){                
                System.out.println("Não foi possível alocar");
                break;                
            }  
//            System.out.println("pritnando or result");
//            System.out.println(result.getValue(employees.get(0)));
            
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
		
		System.out.println(employees);
		
			
			

		

	}
	
	static void limpaConsole(){
		for(int i=0;i<1000;i++) {
			System.out.println();
		}
	}

}
