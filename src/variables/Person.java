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

    public Person(String name, String personName, Boolean isVacinated, int workTime,
			ArrayList<String> schedulePreferences, ArrayList<String> currentSchedule) {
		super(name);
		this.personName = personName;
		this.isVacinated = isVacinated;
		this.workTime = workTime;
		this.schedulePreferences = schedulePreferences;
		this.currentSchedule = currentSchedule;
	}
  
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

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
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
        return "Person{" + "name =" + personName + ", work time =" + workTime + ", schedule preference =" + schedulePreferences+ ", is vacinated =" + isVacinated + ", current schedule =" + currentSchedule +'}';
    }
	
	
    
    
    
    
    
    
}
