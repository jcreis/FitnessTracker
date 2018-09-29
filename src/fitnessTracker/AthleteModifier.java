package fitnessTracker;

interface AthleteModifier extends Athlete {

	/**
	 * Atualiza os dados pessoais do atleta
	 * @param peso peso do atleta
	 * @param altura altura do atleta
	 * @param idade idade do atleta
	 */
	void setAthlete(int peso, int altura, int idade);
	
	/**
	 * Regista atleta num grupo
	 * @param grp grupo de atletas
	 */
	void putInGroup(GroupModifier grp);
	
	/**
	 * Saida do atleta de um grupo, se tiver em algum
	 */
	void removeFromGroup();
	
	/**
	 * Regista um novo treino feito pelo atleta
	 * @param a tipo de atividade do treino
	 * @param tempo duracao do treino
	 * @param calorias calorias gastas ao longo do treino
	 */
	void addWorkout(Activity a, int tempo, int calorias);
	
	/**
	 * Adiciona um numero de passos aos que o atleta ja deu
	 * @param passos passos a adicionar
	 */
	void addSteps(int passos);
}
