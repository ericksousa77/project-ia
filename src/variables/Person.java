package variables;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import aima.*;

public class Person extends Variable {
    public String personName;
    public Boolean isVacinated;
    public int workTime;
    public ArrayList<String> schedulePreferences; 
    public ArrayList<String> currentSchedule;
    public int currentWorkTime;

    
    public Person(String personName, Boolean isVacinated, int workTime,
			ArrayList<String> schedulePreferences, ArrayList<String> currentSchedule) {
    	//esse super aqui ? esse atributo name ? 
		super(personName);
		this.personName = personName;
		this.isVacinated = isVacinated;
		this.workTime = workTime;
		this.schedulePreferences = schedulePreferences;
		this.currentSchedule = currentSchedule;
		this.currentWorkTime = 0;
		this.schedulePreferences.add("to_be_defined_"+this.personName);
	}
    
    //construtor sem a currentSchedule
//    public Person(String personName, Boolean isVacinated, int workTime,
//			ArrayList<String> schedulePreferences) {
//    	//esse super aqui ? esse atributo name ? 
//		super(personName);
//		this.personName = personName;
//		this.isVacinated = isVacinated;
//		this.workTime = workTime;
//		this.schedulePreferences = schedulePreferences;
//	}
  
    public String getPersonName() {
		return personName;
	}

	public void setPersonName(String name) {
		this.personName = name;
	}

	public Boolean getIsVacinated() {
		return isVacinated;
	}

	public void setIsVacinated(Boolean isVacinated) {
		this.isVacinated = isVacinated;
	}

	public int getWorkTime() {
		return workTime;
	}
	
	public int getCurrentWorkTime() {
		return currentWorkTime;
	}


	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	
	public void setCurrentWorkTime(int currentWorkTime) {
		this.currentWorkTime = currentWorkTime;
	}

	public ArrayList<String> getSchedulePreferences() {
		return schedulePreferences;
	}

	public void setSchedulePreferences(ArrayList<String> schedulePreferences) {
		this.schedulePreferences = schedulePreferences;
	}

	public ArrayList<String> getCurrentSchedule() {
		return currentSchedule;
	}

	public void setCurrentSchedule(ArrayList<String> currentSchedule) {
		this.currentSchedule = currentSchedule;
	}
    
	@Override
    public String toString() {
        return "Person{" + "name =" + personName + ", work time =" + workTime + ", schedule preference =" + schedulePreferences+ ", is vacinated =" + isVacinated + ", current schedule =" + currentSchedule + ", current work time =" + currentWorkTime + '}';
    }
	
	
    
    
    
    
    
    
}
