import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

//import dataStructures.*;
import dataStructures.Iterator;
import fitnessTracker.exceptions.*;
import fitnessTracker.*;

/** 
 * @author #####
 * @author #####
 */

public class Main {
	
	//comandos
	private static final String INSERIRATLETA            = "IU";
	private static final String ALTERARATLETA            = "UU";
	private static final String REMOVERATLETA            = "RU";
	private static final String CONSULTARATLETA          = "CU";
	private static final String CRIARATIVIDADE           = "IA";
	private static final String ADICIONARTREINO          = "AW";
	private static final String CONSULTARTREINOSATLETA   = "CW";
	private static final String ATUALIZARPASSOS          = "AS";
	private static final String CRIARGRUPO               = "IG";
	private static final String ADESAOGRUPO              = "AG";
	private static final String DESISTEGRUPO             = "DG";
	private static final String CONSULTAGRUPO            = "CG";
	private static final String LISTARGRUPO              = "LG";
	private static final String LISTARCAMINHANTES        = "LC";
	private static final String LISTARGUERREIROS         = "LW";
	private static final String TERMINAREXECUCAO         = "XS";
	
	//msg de sucesso e excepcoes
	private static final String ADDA      = "Insercao de atleta com sucesso.\n";
	private static final String UPDA      = "Atleta atualizado com sucesso.\n";
	private static final String REMA      = "Atleta removido com sucesso.\n";
	private static final String ADDACT    = "Atividade criada com sucesso.\n";
	private static final String WORKADD   = "Treino adicionado com sucesso.\n";
	private static final String VI        = "Valores invalidos.\n";
	private static final String AAE       = "Atleta existente.\n";
	private static final String ADNE      = "Atleta inexistente.\n";
	private static final String INVM      = "MET invalido.\n";
	private static final String ACTE      = "Atividade existente.\n";
	private static final String ACTDNE    = "Atividade inexistente.\n";
	private static final String INVT      = "Tempo invalido.\n";
	private static final String INVO      = "Opcao invalida.\n";
	private static final String AST       = "Atleta sem treinos.\n";
	private static final String INVS      = "Numero de passos invalido.\n";
	private static final String APS       = "Atualizacao de passos com sucesso.\n";
	private static final String GCS       = "Grupo criado com sucesso.\n";
	private static final String GE        = "Grupo existente.\n";
	private static final String ADDS      = "Adesao realizada com sucesso.\n";
	private static final String GDNE      = "Grupo inexistente.\n";
	private static final String GHNA      = "Grupo nao tem adesoes.\n";
	private static final String AHGA      = "Atleta ja tem grupo.\n";
	private static final String DESS 	  = "Desistencia realizada com sucesso.\n";
	private static final String ADNB      = "Atleta nao pertence ao grupo.\n";
	private static final String NEG       = "Nao existem grupos.\n";
	private static final String OUT       = "Gravando e terminando...\n";
	private static final String FTXT 	  = "f.txt";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Sistema system = load();
		String comand = processMenu(in);
		while (!comand.equals(TERMINAREXECUCAO)) {
			switch (comand) {
			case INSERIRATLETA:
				newAthlete(in, system);
				break;
			case ALTERARATLETA:
				updateAthlete(in, system);
				break;
			case REMOVERATLETA:
				removeAthlete(in, system);
				break;
			case CONSULTARATLETA:
				consultAthlete(in, system);
				break;
			case CRIARATIVIDADE:
				newActivity(in, system);
				break;
			case ADICIONARTREINO:
				newWorkout(in, system);
				break;
			case CONSULTARTREINOSATLETA:
				consultAthleteWorkouts(in, system);
				break;
			case ATUALIZARPASSOS:
				updateSteps(in, system);
				break;
			case CRIARGRUPO:
				newGroup(in, system);
				break;
			case ADESAOGRUPO:
				joinGroup(in, system);
				break;
			case DESISTEGRUPO:
				leaveGroup(in, system);
				break;
			case CONSULTAGRUPO:
				consultGroup(in, system);
				break;
			case LISTARGRUPO:
				listGroup(in, system);
				break;
			case LISTARCAMINHANTES:
				listWalkers(in, system);
				break;
			case LISTARGUERREIROS:
				listWarriors(in, system);
				break;
			default:
				break;
			}
			comand = processMenu(in);
		}
		System.out.println(OUT);
		store(system);
		in.close();
	}
	
	private static String processMenu(Scanner in) {
		String x = in.next().toUpperCase();
		return x;
	}

	private static void newAthlete(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			int peso = in.nextInt();
			int altura = in.nextInt();
			int idade = in.nextInt();
			String sexo = in.next();
			String nome = in.nextLine().trim();
			system.addAthlete(idTracker, peso, altura, idade, sexo, nome);
			System.out.println(ADDA);
		}
		catch(InvalidValuesException e) {
			System.out.println(VI);
		}
		catch(AthleteAlreadyExistsException e) {
			System.out.println(AAE);
		}	
	}

	private static void updateAthlete(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			int peso = in.nextInt();
			int altura = in.nextInt();
			int idade = in.nextInt();
			system.updateAthlete(idTracker, peso, altura, idade);
			System.out.println(UPDA);
		}
		catch(InvalidValuesException e) {
			System.out.println(VI);
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}
	}
	
	private static void removeAthlete(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			system.removeAthlete(idTracker);
			System.out.println(REMA);
		}
		catch(AthleteDoesNotExistException e) { 
			System.out.println(ADNE);
		}
	}
	
	private static void consultAthlete(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			
			Athlete a = system.consultAthlete(idTracker);
			if(a.hasGroup())
				System.out.println(a.getName() + ": " + a.getGender() + ", " + a.getWeight() + " kg, " + a.getAge() + " anos, " + a.getCalories() + " cal, " + a.getSteps() + " ps (" + a.getGroupName() + ")\n" );		
			else System.out.println(a.getName() + ": " + a.getGender() + ", " + a.getWeight() + " kg, " + a.getAge() + " anos, " + a.getCalories() + " cal, " + a.getSteps() + " ps\n");
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}	
	}
	
	private static void newActivity(Scanner in, Sistema system) {
		try {
			String idActividade = in.next();
			int met = in.nextInt();
			String nome = in.nextLine().trim();
			system.createActivity(idActividade, nome, met);
			System.out.println(ADDACT);
		}
		catch(InvalidMETException e) {
			System.out.println(INVM);
		}
		catch(ActivityAlreadyExistsException e) {
			System.out.println(ACTE);
		}	
	}
	
	private static void newWorkout(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			String idActividade = in.next();
			int duracao = in.nextInt();
			system.addWorkout(idTracker, idActividade, duracao);
			System.out.println(WORKADD);
		}
		catch(InvalidTimeException e) {
			System.out.println(INVT);
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}
		catch(ActivityDoesNotExistException e) {
			System.out.println(ACTDNE);
		}
	}
	
	private static void consultAthleteWorkouts(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			String tipo = in.next();
			
			Iterator<Workout> it = system.listWorkouts(idTracker, tipo);
			
			Workout w = null;
			while(it.hasNext()) {
				w = it.next();
				System.out.println(w.getActivityName() + " " + w.getCalories() + " cal");
			}
			System.out.println();
		}
		catch(InvalidOptionException e) {
			System.out.println(INVO);
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}
		catch(AthleteDoesNotWorkoutException e) {
			System.out.println(AST);
		}
	}
	
	private static void updateSteps(Scanner in, Sistema system) {
		try{
			String idTracker = in.next();
			int passos = in.nextInt();
			system.updateSteps(idTracker, passos);
			System.out.println(APS);
		}
		catch(InvalidStepsException e) {
			System.out.println(INVS);
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}
	}
	
	private static void newGroup(Scanner in, Sistema system) {
		try {
			String idGrupo = in.next();
			String nomeGrupo = in.next();
			system.addGroup(idGrupo, nomeGrupo);
			System.out.println(GCS);
		}
		catch(GroupAlreadyExistException e) {
			System.out.println(GE);
		}
	}
	
	private static void joinGroup(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			String idGrupo = in.next();
			system.joinGroup(idTracker, idGrupo);
			System.out.println(ADDS);
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}
		catch(GroupDoesNotExistException e) {
			System.out.println(GDNE);
		}
		catch(AthleteHasGroupAlreadyException e) {
			System.out.println(AHGA);
		}
	}
	
	private static void leaveGroup(Scanner in, Sistema system) {
		try {
			String idTracker = in.next();
			String idGrupo = in.next();
			system.leaveGroup(idTracker, idGrupo);
			System.out.println(DESS);
		}
		catch(AthleteDoesNotExistException e) {
			System.out.println(ADNE);
		}
		catch(GroupDoesNotExistException e) {
			System.out.println(GDNE);
		}
		catch(AthleteDoesNotBelongToGroupException e) {
			System.out.println(ADNB);
		}
		
	}
	
	private static void consultGroup(Scanner in, Sistema system) {
		try {
			String idGrupo = in.next();
			
			Group g = system.consultGroup(idGrupo);
			System.out.println("Grupo " + g.groupName() + ": " + g.getTotalCalories() + " cal, " + g.getTotalSteps() + " ps\n");
		}
		catch(GroupDoesNotExistException e) {
			System.out.println(GDNE);
		}
	}
	
	private static void listGroup(Scanner in, Sistema system) {
		try {
			String idGrupo = in.next();
			
			Athlete a;
			Iterator<Athlete> it = system.listGroup(idGrupo);
			while(it.hasNext()) {
				a = it.next();
				System.out.println(a.getName() + ": " + a.getGender() + ", " + a.getWeight() + " kg, " + a.getAge() + " anos, " + a.getCalories() + " cal, " + a.getSteps() + " ps");
			}
			System.out.println();
		}
		catch(GroupDoesNotExistException e) {
			System.out.println(GDNE);
		}
		catch(GroupHasNoAthletesException e) {
			System.out.println(GHNA);
		}
	}
	
	private static void listWalkers(Scanner in, Sistema system) {
		try {
			Group g;
			Iterator<Group> it = system.listWalkers();
			while(it.hasNext()) {
				g = it.next();
				System.out.println("Grupo " + g.groupName() + ": " + g.getTotalCalories() + " cal, " + g.getTotalSteps() + " ps");
			}
			System.out.println();
		}
		catch(NoGroupsInSystemException e) {
			System.out.println(NEG);
		}
	}
	
	private static void listWarriors(Scanner in, Sistema system) {
		try {
			Group g;
			Iterator<Group> it = system.listWarriors();
			while(it.hasNext()) {
				g = it.next();
				System.out.println("Grupo " + g.groupName() + ": " + g.getTotalCalories() + " cal, " + g.getTotalSteps() + " ps");
			}
			System.out.println();
		}
		catch(NoGroupsInSystemException e) {
			System.out.println(NEG);
		}
	}
	
	private static Sistema load() {
		Sistema system=null;
		
		try {
			ObjectInputStream file = new ObjectInputStream(new FileInputStream(FTXT));
			system = (Sistema) file.readObject();
			file.close();
		}
		catch(IOException e) {
			system = new SistemaClass();
		}
		catch(ClassNotFoundException e) {
			system = new SistemaClass();
		}

		return system;
	}
	
	private static void store(Sistema system) {
		try {
			ObjectOutputStream file = new ObjectOutputStream(new FileOutputStream(FTXT));
			file.writeObject(system);
			file.flush();
			file.close();
		}
		catch(IOException e) {

		}
	}
}
