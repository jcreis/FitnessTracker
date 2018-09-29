package fitnessTracker;

import java.io.Serializable;
import dataStructures.*;
import fitnessTracker.exceptions.*;

public interface Sistema extends Serializable {

	/**
	 * Regista um atleta no sistema
	 * @param idTracker ID do atleta
	 * @param peso peso do atleta
	 * @param altura altura do atleta
	 * @param idade idade do atleta
	 * @param sexo genero do atleta
	 * @param nome nome do atleta
	 * @throws InvalidValuesException caso os valores de peso, altura, idade sejam nao positivos ou o valor de sexo seja invalido
	 * @throws AthleteAlreadyExistsException caso o atleta ja esteja registado no sistema
	 */
	void addAthlete(String idTracker, int peso, int altura, int idade, String sexo, String nome) throws InvalidValuesException, AthleteAlreadyExistsException;

	/**
	 * Atualiza a informacao de um dado atleta
	 * @param idTracker ID do atleta
	 * @param peso peso do atleta
	 * @param altura altura do atleta
	 * @param idade idade do atleta
	 * @throws InvalidValuesException caso os valores de peso, altura, idade sejam nao positivos
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 */
	void updateAthlete(String idTracker, int peso, int altura, int idade) throws InvalidValuesException, AthleteDoesNotExistException;

	/**
	 * Elimina atleta do sistema
	 * @param idTracker ID do atleta
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 */
	void removeAthlete(String idTracker) throws AthleteDoesNotExistException;

	/**
	 * Consulta a informacao de um dado atleta
	 * @param idTracker ID do atleta
	 * @return atleta a consultar
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 */
	Athlete consultAthlete(String idTracker) throws AthleteDoesNotExistException;

	/**
	 * Regista uma atividade no sistema
	 * @param idActividade ID da atividade
	 * @param nome nome da atividade
	 * @param met equivalente metabolico da atividade
	 * @throws InvalidMETException caso o valor do met nao seja positivo
	 * @throws ActivityAlreadyExistsException caso a atividade ja esteja registada no sistema
	 */
	void createActivity(String idActividade, String nome, int met) throws InvalidMETException, ActivityAlreadyExistsException;

	/**
	 * Adiciona um treino ao perfil de um dado atleta
	 * @param idTracker ID do atleta
	 * @param idActividade ID da atividade
	 * @param duracao duracao do treino
	 * @throws InvalidTimeException caso o valor da duracao nao seja positivo
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 * @throws ActivityDoesNotExistException caso a atividade nao esteja registada no sistema
	 */
	void addWorkout(String idTracker, String idActividade, int duracao) throws InvalidTimeException, AthleteDoesNotExistException, ActivityDoesNotExistException;

	/**
	 * Cria um iterador com os treinos registados por um dado atleta
	 * @param idTracker ID do atleta
	 * @param tipo criterio de ordencao de treinos, 'T' ordena por ordem cronologica, 'C' pelo total de calorias gastas em ordem decrescente
	 * @return iterador de treinos
	 * @throws InvalidOptionException caso o valor do tipo seja invalido
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 * @throws AthleteDoesNotWorkoutException caso o atleta nao tenha registado nenhum treino no seu perfil
	 */
	Iterator<Workout> listWorkouts(String idTracker, String tipo) throws InvalidOptionException, AthleteDoesNotExistException, AthleteDoesNotWorkoutException;

	/**
	 * Atualiza o numero de passos dados por um dado atleta
	 * @param idTracker ID do atleta
	 * @param passos passos a adicionar
	 * @throws InvalidStepsException caso o valor de passos nao seja positivo
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 */
	void updateSteps(String idTracker, int passos) throws InvalidStepsException, AthleteDoesNotExistException;

	/**
	 * Regista um grupo no sistema
	 * @param idGrupo ID do grupo
	 * @param nomeGrupo nome do grupo
	 * @throws GroupAlreadyExistException caso o grupo ja esteja registado no sistema
	 */
	void addGroup(String idGrupo, String nomeGrupo) throws GroupAlreadyExistException;

	/**
	 * Regista um atleta num grupo do sistema
	 * @param idTracker ID do atleta
	 * @param idGrupo ID do grupo
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 * @throws GroupDoesNotExistException caso o grupo nao esteja registado no sistema
	 * @throws AthleteHasGroupAlreadyException caso o atleta ja esteja registado num grupo
	 */
	void joinGroup(String idTracker, String idGrupo) throws AthleteDoesNotExistException, GroupDoesNotExistException, AthleteHasGroupAlreadyException;

	/**
	 * Retira um atleta dum grupo
	 * @param idTracker ID do atleta
	 * @param idGrupo ID do grupo
	 * @throws AthleteDoesNotExistException caso o atleta nao esteja registado no sistema
	 * @throws GroupDoesNotExistException caso o grupo nao esteja registado no sistema
	 * @throws AthleteDoesNotBelongToGroupException caso o atleta nao pertenca ao grupo
	 */
	void leaveGroup(String idTracker, String idGrupo) throws AthleteDoesNotExistException, GroupDoesNotExistException, AthleteDoesNotBelongToGroupException;

	/**
	 * Consulta a informacao dum grupo do sistema
	 * @param idGrupo ID do grupo
	 * @return grupo a consultar
	 * @throws GroupDoesNotExistException caso o grupo nao esteja registado no sistema
	 */
	Group consultGroup(String idGrupo) throws GroupDoesNotExistException;

	/**
	 * Listagem de atletas de um dado grupo
	 * @param idGrupo ID do grupo
	 * @return iterador com os atletas do grupo
	 * @throws GroupDoesNotExistException caso o grupo nao esteja registado no sistema
	 */
	Iterator<Athlete> listGroup(String idGrupo) throws GroupDoesNotExistException, GroupHasNoAthletesException;
	
	/**
	 * Listagem de grupos pelo numero de passos acumulados por cada grupo, por ordem decrescente
	 * @return unico grupo do sistema
	 * @throws NoGroupsInSystemException caso nao esteja nenhum grupo registado no sistema
	 */
	Iterator<Group> listWalkers() throws NoGroupsInSystemException;
	
	/**
	 * Listagem de grupo pelo numero de calorias gastas por cada grupo, por ordem decrescente
	 * @return unico grupo do sistema
	 * @throws NoGroupsInSystemException caso nao esteja nenhum grupo registado no sistema
	 */
	Iterator<Group> listWarriors() throws NoGroupsInSystemException;
}
