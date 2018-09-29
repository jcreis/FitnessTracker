package fitnessTracker;

interface GroupModifier extends Group {

	/**
	 * Adiciona atleta ao grupo
	 * @param a atleta a adicionar
	 */
	void addAthleteToGroup(AthleteModifier a);
	
	/**
	 * Remove atleta do grupo
	 * @param a atleta a remover
	 */
	void removeAthleteFromGroup(AthleteModifier a);
	
	/**
	 * Atualiza informacao do numero de calorias do grupo, caso seja preciso
	 * @param calories calorias gastas a adicionar ao total do grupo
	 */
	void addCaloriesToGroup(int calories);
	
	/**
	 * Atualiza informacao do numero de passos do grupo, caso seja preciso
	 * @param steps passos a adicionar ao total do grupo
	 */
	void addStepsToGroup(int steps);
}
