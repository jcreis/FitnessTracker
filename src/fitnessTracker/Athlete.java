package fitnessTracker;

import dataStructures.Iterator;
import java.io.Serializable;

public interface Athlete extends Serializable {

	/**
	 * Consulta o ID do atleta
	 * @return ID do atleta
	 */
	String getIdTracker();
	
	/**
	 * Consulta o nome do atleta
	 * @return nome do atleta
	 */
	String getName();
	
	/**
	 * Consulta o genero do atleta
	 * @return genero do atleta
	 */
	String getGender();
	
	/**
	 * Consulta o peso do atleta
	 * @return peso do atleta
	 */
	int getWeight();
	
	/**
	 * Consulta a idade do atleta
	 * @return idade do atleta
	 */
	int getAge();
	
	/**
	 * Consulta se o atleta esta inserido num grupo
	 * @return <code>true</code>, se esta,
	 * <code>false</code> se nao
	 */
	boolean hasGroup();
	
	/**
	 * Consulta o nome do grupo em que o atleta esta inserido
	 * @return nome do grupo do atleta
	 */
	String getGroupName();
	
	/**
	 * Consulta o grupo do atleta
	 * @return grupo do atleta
	 */
	GroupModifier getGroup();
	
	/**
	 * Consulta o numero total de calorias gastas em todos os treinos feitos pelo atleta
	 * @return total de calorias gastas pelo atleta
	 */
	int getCalories();
	
	/**
	 * Consulta o numero de passos dados pelo atleta
	 * @return numero de passos dados pelo atleta
	 */
	int getSteps();
	
	/**
	 * Consulta a altura do atleta
	 * @return altura do atleta
	 */
	int getHeight();
	
	/**
	 * Cria um iterador com os treinos registados pelo atleta, do mais recente para o mais antigo
	 * @return iterador de treinos
	 */
	Iterator<Workout> getTWorkouts();
	
	/**
	 * Cria um iterador com os treinos registados pelo atleta, por ordem decrescente de calorias
	 * @return iterador de treinos
	 */
	Iterator<Workout> getCWorkouts();
	/**
	 * Consulta se o atleta ja realizou algum treino
	 * @return <code>true</code>, se tem treinos realizados,
	 * <code>false</code> se nao
	 */
	boolean hasWorkouts();
}
