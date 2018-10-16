package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HiloCampoController extends Thread{

    private Main main;
    @FXML
    private ImageView visor;
    @FXML
    private ImageView bandera;
    @FXML
    private Label puntaje;
    private long time;
    private CampoController campo;
	public HiloCampoController(CampoController campo, ImageView visor) {
		super();
		this.campo = campo;
		this.visor = visor;
		this.time = time;
		
	}
	
	public void run() {
		try {
			campo.cambiarDesplazamientoPokemon(this.visor);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
