package modelo;

public class HiloCampo extends Thread {
	
	private Pokemon poke;

	private Campo camp;
	
	long time;
	
	public HiloCampo(Campo camp,Pokemon poke, long tiempoCorriendo) {
		super();
		this.camp = camp;
		this.poke = poke;
		this.time = tiempoCorriendo;
		
	}
	public void run() {
		camp.desplazamiento(this.time);
	
	}
	

}
