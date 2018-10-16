package application;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class HiloCampoController2 extends Thread{

    private Main main;
    @FXML
    private ImageView visor;
    @FXML
    private ImageView bandera;
    private long time;
    private CampoController2 campo;
	public HiloCampoController2(CampoController2 campo, ImageView visor) {
		super();
		this.campo = campo;
		this.visor = visor;
		this.time = time;
	}
	
	public void run() {
		try {
			campo.cambiarDesplazamientoPokemon2(this.visor);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
