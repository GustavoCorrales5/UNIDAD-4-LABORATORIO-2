package modelo;

public class HiloCampo2 extends Thread {
	
	private Pokemon poke;
	
	private Campo camp;
	
	long time;
	
	public HiloCampo2(Campo camp,Pokemon poke, long tiempoCorriendo) {
		super();
		this.camp = camp;
		this.poke = poke;
		this.time = tiempoCorriendo;
		
	}
	public void run() {
		camp.desplazamiento2(this.time);
	
	}
	

}
