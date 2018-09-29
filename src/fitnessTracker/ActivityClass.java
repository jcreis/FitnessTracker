package fitnessTracker;

public class ActivityClass implements Activity {
	
	private static final long serialVersionUID = 1L;
	
	private String idActividade, nome;
	private int met;
	
	public ActivityClass(String idActividade, String nome, int met) {
		this.idActividade = idActividade;
		this.nome = nome;
		this.met = met;
	}
	
	public String getIdActividade() {
		return idActividade;	
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getMET() {
		return met;
	}
}
