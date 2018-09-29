package fitnessTracker;

import dataStructures.*;

public class AthleteClass implements AthleteModifier {

	private static final long serialVersionUID = 1L;

	private String idTracker, nome;
	private String sexo;
	private int peso, altura, idade, calorias, passos;
	private IterableStack<Workout> treinosT;
	private InvertibleOrderedDictionary<Integer, List<Workout>> treinosC;
	GroupModifier g;

	public AthleteClass(String idTracker, int peso, int altura, int idade, String sexo, String nome) {
		this.idTracker = idTracker;
		this.nome = nome;
		this.peso = peso;
		this.altura = altura;
		this.idade = idade;
		this.sexo = sexo;
		calorias = 0;
		passos = 0;
		treinosT = new IterableStackInList<Workout>();
		treinosC = new AVLTree<Integer, List<Workout>>();
		g = null;
	}

	public String getIdTracker() {
		return idTracker;
	}

	public void setAthlete(int peso, int altura, int idade) {
		this.peso = peso;
		this.altura = altura;
		this.idade = idade;
	}

	public String getName() {
		return nome;
	}

	public String getGender() {
		String sex = null;

		if(sexo.matches("M|m"))
			sex = "Masculino";

		if(sexo.matches("F|f"))
			sex = "Feminino";

		return sex;
	}

	public int getWeight() {
		return peso;
	}

	public int getHeight() {
		return altura;
	}

	public int getAge() {
		return idade;
	}

	public boolean hasGroup() {
		if(g!=null)
			return true;
		return false;
	}

	public String getGroupName(){
		if(g!=null)
			return g.groupName();
		return null;
	}

	public GroupModifier getGroup(){
		return g;
	}
	public void putInGroup(GroupModifier grp) {
		this.g = grp;
	}

	public void removeFromGroup() {
		this.g = null;
	}

	public int getCalories() {
		return calorias;
	}

	public int getSteps() {
		return passos;
	}

	public void addWorkout(Activity a, int tempo, int calorias) {
		Workout w = new WorkoutClass(a, tempo, calorias);
		treinosT.push(w);
		
		List<Workout> l;
		if(treinosC.find(calorias) == null){
			l = new DoublyLinkedList<Workout>();
		}
		else
			l = treinosC.find(calorias);
		l.addFirst(w);
		treinosC.insert(calorias, l);
		
		this.calorias += calorias;
		if(this.hasGroup())
			g.addCaloriesToGroup(calorias);
	}

	public Iterator<Workout> getTWorkouts() {
		return treinosT.iterator();
	}
	
	public Iterator<Workout> getCWorkouts(){
		Iterator<Entry<Integer, List<Workout>>> it = treinosC.inverseIterator();

		Iterator<Workout> retIt = new InverseBSTListValuesIterator<Integer,Workout>(it);
		return retIt;
	}

	public void addSteps(int passos) {
		this.passos += passos;
		if(this.hasGroup())
			g.addStepsToGroup(passos);
	}

	public boolean hasWorkouts() {
		return treinosT.size() > 0; // || treinosC.size() > 0
	}
}
