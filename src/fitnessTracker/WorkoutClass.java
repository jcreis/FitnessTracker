package fitnessTracker;

public class WorkoutClass implements Workout {

	private static final long serialVersionUID = 1L;
	
	Activity a;
	int tempo, calorias;
	
	public WorkoutClass(Activity a, int tempo, int calorias) {
		this.a = a;
		this.tempo = tempo;
		this.calorias = calorias;
	}
	
	public int getTime() {
		return tempo;
	}
	public int getCalories() {
		return calorias;
	}
	
	public String getActivityName() {
		return a.getNome();
	}
}
