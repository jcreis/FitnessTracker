package fitnessTracker;

import dataStructures.*;
import fitnessTracker.exceptions.*;

public class SistemaClass implements Sistema{
		
	private static final long serialVersionUID = 1L;
	
	private static final int INITIAL_MAX_ATHLETES = 6000;
	private static final int INITIAL_MAX_ACTIVITIES = 5000;
	private static final int INICIAL_MAX_GROUPS = 3000;
	
	private Dictionary<String,Activity> actividades;
	private Dictionary<String,AthleteModifier> atletas;
	private Dictionary<String,GroupModifier> groups;
	
	private InvertibleOrderedDictionary<Integer, List<Group>> walkers;
	private InvertibleOrderedDictionary<Integer, List<Group>> warriors;

	
	public SistemaClass() {
		atletas = new ChainedHashTable<String,AthleteModifier>(INITIAL_MAX_ATHLETES);
		actividades = new ChainedHashTable<String,Activity>(INITIAL_MAX_ACTIVITIES);
		groups = new ChainedHashTable<String,GroupModifier>(INICIAL_MAX_GROUPS);
		
		walkers = new AVLTree<Integer, List<Group>>();
		warriors = new AVLTree<Integer, List<Group>>();
	}
	
	public void addAthlete(String idTracker, int peso, int altura, int idade, String sexo, String nome) throws InvalidValuesException, AthleteAlreadyExistsException {
		if(peso <= 0 || altura <= 0 || idade <= 0 || !sexo.matches("F|M|f|m"))
			throw new InvalidValuesException();
		
		if(atletas.find(idTracker.toLowerCase()) != null)
			throw new AthleteAlreadyExistsException();
		
		atletas.insert(idTracker.toLowerCase(), new AthleteClass(idTracker.toLowerCase(), peso, altura, idade, sexo, nome));
	
	}

	public void updateAthlete(String idTracker, int peso, int altura, int idade) throws InvalidValuesException, AthleteDoesNotExistException {
		if(peso <= 0 || altura <= 0 || idade <= 0)
			throw new InvalidValuesException();
		
		if(atletas.find(idTracker.toLowerCase())==null)
			throw new AthleteDoesNotExistException();
		
		atletas.find(idTracker.toLowerCase()).setAthlete(peso, altura, idade);
	}

	public void removeAthlete(String idTracker) throws AthleteDoesNotExistException {
		if(atletas.find(idTracker.toLowerCase())==null)
			throw new AthleteDoesNotExistException();
		
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		GroupModifier g;
		if(a.hasGroup()){
			 g = a.getGroup();
			 removeWalkersOldInfo(g);
			 removeWarriorsOldInfo(g);
		     g.removeAthleteFromGroup(a);
		     insertWalkersNewInfo(g);
		     insertWarriorsNewInfo(g);
		}
		
		atletas.remove(idTracker.toLowerCase());
	}

	public Athlete consultAthlete(String idTracker) throws AthleteDoesNotExistException {	
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		
		if(a==null)
			throw new AthleteDoesNotExistException();
		
		return a;
	}

	public void createActivity(String idActividade, String nome, int met) throws InvalidMETException, ActivityAlreadyExistsException {
		if(actividades.find(idActividade.toLowerCase())!=null)
			throw new ActivityAlreadyExistsException();
			
		if(met <= 0)
			throw new InvalidMETException();
			
		actividades.insert(idActividade.toLowerCase(), new ActivityClass(idActividade.toLowerCase(), nome, met));
	}

	public void addWorkout(String idTracker, String idActividade, int duracao) throws InvalidTimeException, AthleteDoesNotExistException, ActivityDoesNotExistException {
		if(duracao <= 0)
			throw new InvalidTimeException();
		
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		
		if(a==null)
			throw new AthleteDoesNotExistException();
		
		Activity at = actividades.find(idActividade.toLowerCase());
		
		if(at==null)
			throw new ActivityDoesNotExistException();

		int cal = Calories.calculateCalories(a.getWeight(), a.getHeight(), a.getGender(), a.getAge(), at.getMET(), duracao);
		
		Group g;
		if(a.hasGroup()) {
			g = a.getGroup();
			removeWarriorsOldInfo(g);
			a.addWorkout(at, duracao, cal); //em addWorkout, as calorias do grupo sao atualizadas
			insertWarriorsNewInfo(g);
		}
		else a.addWorkout(at, duracao, cal);
	}

	public Iterator<Workout> listWorkouts(String idTracker, String tipo) throws InvalidOptionException, AthleteDoesNotExistException, AthleteDoesNotWorkoutException {
		//T ordem cronologica  C ordem decrescente
		if(!tipo.matches("T|t|C|c"))
			throw new InvalidOptionException();
		
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		
		if(a==null)
			throw new AthleteDoesNotExistException();
			
		if(!a.hasWorkouts())
			throw new AthleteDoesNotWorkoutException();
		if(tipo.matches("T|t"))
			return a.getTWorkouts();
		else
			return a.getCWorkouts();
	}

	public void updateSteps(String idTracker, int passos) throws InvalidStepsException, AthleteDoesNotExistException {
		if(passos <= 0)
			throw new InvalidStepsException();
		
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		if(a == null)
			throw new AthleteDoesNotExistException();
		
		Group g;
		if(a.hasGroup()) {
			g = a.getGroup();
			removeWalkersOldInfo(g);
			a.addSteps(passos); //em addSteps, os passos do grupo sao atualizados
			insertWalkersNewInfo(g);
		}
		else a.addSteps(passos);
	}
	
	public void addGroup(String idGrupo, String nomeGrupo) throws GroupAlreadyExistException {
		if(groups.find(idGrupo.toLowerCase()) != null)
			throw new GroupAlreadyExistException();
		
		GroupModifier g = new GroupClass(idGrupo.toLowerCase(), nomeGrupo);
		
		groups.insert(idGrupo.toLowerCase(), g);
		insertWalkersNewInfo(g);
		insertWarriorsNewInfo(g);
	}

	public void joinGroup(String idTracker, String idGrupo) throws AthleteDoesNotExistException, GroupDoesNotExistException, AthleteHasGroupAlreadyException {
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		GroupModifier g = groups.find(idGrupo.toLowerCase());
		if(a==null)
			throw new AthleteDoesNotExistException();
		
		if(g == null)
			throw new GroupDoesNotExistException();
		
		if(a.hasGroup())
			throw new AthleteHasGroupAlreadyException();

		a.putInGroup(g);
		
		if(a.getSteps() > 0)
			removeWalkersOldInfo(g);
		
		if(a.getCalories() > 0)
			removeWarriorsOldInfo(g);
		
		g.addAthleteToGroup(a);
		
		if(a.getSteps() > 0)
			insertWalkersNewInfo(g);
		
		if(a.getCalories() > 0)
			insertWarriorsNewInfo(g);
	}
	
	public void leaveGroup(String idTracker, String idGrupo) throws AthleteDoesNotExistException, GroupDoesNotExistException, AthleteDoesNotBelongToGroupException {
		AthleteModifier a = atletas.find(idTracker.toLowerCase());
		GroupModifier g = groups.find(idGrupo.toLowerCase());
		if(a==null)
			throw new AthleteDoesNotExistException();
		
		if(g==null || !g.getIdGroup().equals(idGrupo.toLowerCase()))
			throw new GroupDoesNotExistException();
		
		if(!g.hasAthlete(a))
			throw new AthleteDoesNotBelongToGroupException();
		
		a.removeFromGroup();
		
		if(a.getSteps() > 0)
			removeWalkersOldInfo(g);
		
		if(a.getCalories() > 0)
			removeWarriorsOldInfo(g);
		
		g.removeAthleteFromGroup(a);	
		
		if(a.getSteps() > 0)
			insertWalkersNewInfo(g);
		
		if(a.getCalories() > 0)
			insertWarriorsNewInfo(g);
	}

	public Group consultGroup(String idGrupo) throws GroupDoesNotExistException {	
		GroupModifier g = groups.find(idGrupo.toLowerCase());
		
		if(g == null)
			throw new GroupDoesNotExistException();
		
		return g;
	}
	
	public Iterator<Athlete> listGroup(String idGrupo) throws GroupDoesNotExistException, GroupHasNoAthletesException {
		GroupModifier g = groups.find(idGrupo.toLowerCase());

		if(g == null)
			throw new GroupDoesNotExistException();
		
		if(g.size() == 0)
			throw new GroupHasNoAthletesException();
		
		return g.athletesList();
	}
	
	public Iterator<Group> listWalkers() throws NoGroupsInSystemException {
		if(walkers.isEmpty()){
			throw new NoGroupsInSystemException();
		}
		
		Iterator<Entry<Integer, List<Group>>> it = walkers.inverseIterator();
		return new InverseBSTListValuesIterator<Integer, Group>(it);
	}
	
	public Iterator<Group> listWarriors() throws NoGroupsInSystemException {
		if(warriors.isEmpty()){
			throw new NoGroupsInSystemException();
		}
		
		Iterator<Entry<Integer, List<Group>>> it = warriors.inverseIterator();
		return new InverseBSTListValuesIterator<Integer, Group>(it);
	}
	
	protected void removeWalkersOldInfo(Group g) {
		int steps = g.getTotalSteps();
		List<Group> l;
		
		l = walkers.find(steps);
		l.remove(g);

		if(l.isEmpty()) //se a lista ficar vazia, remover a entrada na AVL
			walkers.remove(steps);
	}
	
	protected void insertWalkersNewInfo(Group g) {
		int steps = g.getTotalSteps();
		List<Group> l;
		if(walkers.find(steps) == null)
			l = new DoublyLinkedList<Group>();
		else l = walkers.find(steps);
		l.addLast(g);
		walkers.insert(steps, l);
	}
	
	protected void removeWarriorsOldInfo(Group g) {
		int cal = g.getTotalCalories();
		List<Group> l;
		l = warriors.find(cal);
		l.remove(g);

		if(l.isEmpty()) //se a lista ficar vazia, remover a entrada na AVL
			warriors.remove(cal);
	}
	
	protected void insertWarriorsNewInfo(Group g) {
		int cal = g.getTotalCalories();
		List<Group> l;
		if(warriors.find(cal) == null)
			l = new DoublyLinkedList<Group>();
		else l = warriors.find(cal);
		l.addLast(g);
		warriors.insert(cal, l);
	}
}
