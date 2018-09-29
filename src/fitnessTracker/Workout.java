package fitnessTracker;

import java.io.Serializable;

public interface Workout extends Serializable {
	
	/**
	 * Consulta a duracao do treino
	 * @return duracao do treino
	 */
	int getTime();
	
	/**
	 * Consulta o numero de calorias gastas durantes o treino
	 * @return calorias gastas durante o treino
	 */
	int getCalories();
	
	/**
	 * Consulta o nome da actividade do treino
	 * @return nome da actividade
	 */
	String getActivityName();
}
