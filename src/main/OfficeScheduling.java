package main;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import aima.Assignment;
import aima.FlexibleBacktrackingSolver;
import csp.PersonToSchedule;

import variables.Person;

public class OfficeScheduling {
	
	//aqui ta arraylist e no csp ta recebendo uma list, pode ? 
	private static  ArrayList<Person> employees = new ArrayList<Person>();

	public static void main(String[] args) {
		
		 
		
		
	
		
		//o looping dura até todos as pessoas tiverem alocadas com seu tempo de trabalho
		
		
//		while(true) {
			
		int amountOfPersons = 0;
		String personName = "";
		boolean isVacinated = false;
		int workTime = 0;
		//qual a maneira correta de inializar esse cara ? 
	    
	    ArrayList<String> schedulePreferences = new ArrayList<String>();
	    
	    String isVacinatedInput;
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Digite a quantidade de pessoas que trabalham na empresa: "); 
		amountOfPersons = Integer.parseInt(input.nextLine());
		
		
		for (int i = 0; i < amountOfPersons; i++) {
			personName = "";
			isVacinated = false;
			workTime = 0;
			
			
			
			//atenção aqui
			for (int j = 0; j < schedulePreferences.size(); j++) {
				schedulePreferences.remove(j);
			}
			
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
			
			
			
			Person person = new Person("fakeName", personName,isVacinated, workTime, schedulePreferences);
			
		    employees.add(person);
		    limpaConsole();
			
			
		}
		
//		PersonToSchedule csp = null;
//		FlexibleBacktrackingSolver strategy = new FlexibleBacktrackingSolver<Person, String>();
//        Assignment<Person, String> result = null;
//        Optional<Assignment<Person, String>> solution; 
		
			
			
			
			
			
//		}
		

	}
	
	static void limpaConsole(){
		for(int i=0;i<1000;i++) {
			System.out.println();
		}
	}

}
