package fitnessTracker;

import java.io.Serializable;

import dataStructures.Iterator;

public interface Group extends Serializable {
	
	/**
	 * Consulta o ID do grupo
	 * @return ID do grupo
	 */
	String getIdGroup();
	
	/**
	 * Consulta o nome do grupo
	 * @return nome do grupo
	 */
	String groupName();
	
	/**
	 * Consulta o tamanho do grupo
	 * @return tamanho do grupo
	 */
	int size();
	
	/**
	 * Consulta se um atleta ja pertence ao grupo
	 * @param a atleta
	 * @return <code>true</code>, se o atleta ja esta no grupo,
	 * <code>false</code> se nao
	 */
	boolean hasAthlete(AthleteModifier a);
	
	/**
	 * Consulta o numero total de calorias gastas por todos os atletas do grupo
	 * @return numero de calorias gastas pelos atletas do grupo
	 */
	int getTotalCalories();
	
	/**
	 * Consulta o numero total de passos de todos os atletas do grupo
	 * @return numero de passos do grupo
	 */
	int getTotalSteps();
	
	/**
	 * Cria um iterador com os atletas pertecentes ao grupo, por ordem alfabetica
	 * @return iterador de atletas
	 */
	Iterator<Athlete> athletesList();
}
