package fitnessTracker;

import java.io.Serializable;

public interface Activity extends Serializable {
	
	/**
	 * Consulta o ID da atividade
	 * @return ID da atividade
	 */
	String getIdActividade();
	
	/**
	 * Consulta o nome da atividade
	 * @return nome da atividade
	 */
	String getNome();
	
	/**
	 * Consulta o equivalente metabolico pela realizacao da atividade
	 * @return equivalente metabolico da atividade
	 */
	int getMET();
}
