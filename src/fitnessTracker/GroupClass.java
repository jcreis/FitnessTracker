package fitnessTracker;

import dataStructures.*;

public class GroupClass implements GroupModifier {
	
	private static final long serialVersionUID = 1L;
	
	private String idGrupo, nome;
	private OrderedDictionary<String,Athlete> athletes;
	private int totalCalories, totalSteps;
	
	public GroupClass(String idGrupo, String nome) {
		this.idGrupo = idGrupo;
		this.nome = nome;
		athletes = new AVLTree<String,Athlete>();
		totalCalories = 0;
		totalSteps = 0;
	}
	
	public String getIdGroup() {
		return idGrupo;
	}
	
	public String groupName() {
		return nome;
	}
	
	public int size() {
		return athletes.size();
	}
	
	public void addAthleteToGroup(AthleteModifier a) {
		athletes.insert(a.getName(), a);
		totalCalories += a.getCalories();
		totalSteps += a.getSteps();
	}
	
	public boolean hasAthlete(AthleteModifier a) {
		if(athletes.find(a.getName())!=null)
			return true;
		return false;
	}
	
	public void removeAthleteFromGroup(AthleteModifier a) {
		athletes.remove(a.getName());
		a.removeFromGroup(); 
		totalCalories -= a.getCalories();
		totalSteps -= a.getSteps();
	}
	
	public void addCaloriesToGroup(int calories) {
		totalCalories += calories;
	}
	
	public void addStepsToGroup(int steps) {
		totalSteps += steps;
	}
	
	public int getTotalCalories() {
		return totalCalories;
	}
	
	public int getTotalSteps() {
		return totalSteps;
	}
	
	public Iterator<Athlete> athletesList() {
		Iterator<Entry<String, Athlete>> it = athletes.iterator();
		
		Iterator<Athlete> itRet = new BSTKeyOrderValuesIterator<String, Athlete>(it);
		return itRet;
	}
}
