package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import aima.Assignment;
import aima.FlexibleBacktrackingSolver;
import csp.PersonToSchedule;

import variables.Person;

public class OfficeScheduling {
	
	//aqui ta arraylist e no csp ta recebendo uma list, pode ? 
	private static  ArrayList<Person> employees = new ArrayList<Person>();
	private static ArrayList<String> dayAvailableHours = new ArrayList<String>();
	
	

	public static void main(String[] args) {
		
		 

		
		//o looping dura até todos as pessoas tiverem alocadas com seu tempo de trabalho
		
		for(int l = 1; l < 25; l++) {
        	dayAvailableHours.add(""+l);
        }

			
		int amountOfPersons = 0;
		String personName = "";
		boolean isVacinated = false;
		int workTime = 0;
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
			
			
			
			//atenção aqui
//			for (int j = 0; j < schedulePreferences.size(); j++) {
//				schedulePreferences.remove(j);
//			}
			schedulePreferences = new ArrayList<String>();
			
			System.out.println("passou");
			
			System.out.println("Digite o nome da pessoa: "); 
			personName = input.nextLine();
			
			System.out.println(personName);
			
			System.out.println("Essa pessoa esta vacinada ? Digite 1 para sim e 2 para não "); 
			isVacinatedInput = input.nextLine();
			
//			isVacinatedInput.equals("1") ? isVacinated = true : isVacinated = false;
			
			if (isVacinatedInput.equals("1")) {
				isVacinated = true;
			}
			
			if (isVacinatedInput.equals("2")) {
				isVacinated = false;
			}
			
			System.out.println(isVacinated);
			
			System.out.println("Digite quantas (numero) horas por dia essa pessoa trabalha: ");
			workTime = Integer.parseInt(input.nextLine());
			
			System.out.println(workTime);
			
			
			System.out.println("Digite a preferencia de horário em formato 24 horas: ");
			String currentPreference = "";
			for (int k = 0; k < workTime; k++) {
				if(k>0)
					System.out.println("Digite proxima preferencia de horário: ");
				currentPreference = input.nextLine();
				System.out.println(currentPreference);
				try {
					schedulePreferences.add(currentPreference);
				}
				catch(Exception e) {
					System.out.println(e);
				}
			
			}
			
			System.out.println("OK! Todas as preferencias já foram digitadas");
			
			
			
			Person person = new Person(personName,isVacinated, workTime, schedulePreferences, currentSchedule);
			
			for(int m = 0; m < person.getSchedulePreferences().size(); m++ ){
				System.out.println(person.getSchedulePreferences().get(m));
			}
			
			
		    employees.add(person);
		    limpaConsole();
			
			
		}
		
		
		
		PersonToSchedule csp = null;
		System.out.println("passou1");
		FlexibleBacktrackingSolver strategy = new FlexibleBacktrackingSolver<Person, String>();
		System.out.println("passou2");
        Assignment<Person, String> result = null;
        Optional<Assignment<Person, String>> solution; 
        
        System.out.println("passou3");
		
        boolean notAvailableAllocations = dayAvailableHours.isEmpty();
        
		while(!notAvailableAllocations) {
			
			try {
                csp = new PersonToSchedule(employees);
            } catch(IOException ex) {
                Logger.getLogger(OfficeScheduling.class.getName()).log(Level.SEVERE, null, ex);
            }
			
			System.out.println("passou20");
			
			//Gera a solução
            solution = strategy.solve(csp);
            
            System.out.println("passou21");
            
            try{
                result = solution.get();                
            }catch(java.util.NoSuchElementException e){                
                System.out.println("Não foi possível alocar");
                break;                
            }  
            
        
            for(int i=0; i<employees.size(); i++){
                String hourOfDay = result.getValue(result.getVariables().get(i));         
                employees.get(i).currentSchedule.add(hourOfDay);
                
//                de alguma forma tenho que remover a hora atribuida das 24hrs do dia
//                removo do dominio?
                for(int j = 0; j < dayAvailableHours.size(); j++){
                    if(hourOfDay.equals(dayAvailableHours.get(j))){
                    	dayAvailableHours.remove(j);
                        j--;
                    }
                }
            }
             			
		}
			
			

		

	}
	
	static void limpaConsole(){
		for(int i=0;i<1000;i++) {
			System.out.println();
		}
	}

}
