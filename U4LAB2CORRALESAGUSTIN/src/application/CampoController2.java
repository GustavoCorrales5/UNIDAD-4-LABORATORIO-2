package application;

import java.security.SecureRandom;

import javax.swing.JOptionPane;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class CampoController2 {

    
    private Main main;
    @FXML
    private  ImageView visor;
    @FXML
    private ImageView bandera;
    @FXML
    private Label puntaje;
    @FXML
    private Button salvar;
    private boolean parada;

    
    public CampoController2() {
   
    }
	public void initialize() {
		iniciar();
	}
	
    
    public void iniciar() {
    	
    	main = new Main();
		SecureRandom random = new SecureRandom();
		
        Image image = new Image(getClass().getResourceAsStream("/"+main.numeroPOkemonSeleccionado()+".gif"));
        visor.setImage(image);
        visor.translateXProperty().setValue(200);
        visor.translateYProperty().setValue(600);
        main.pokemonAtrapador(CampoController2.this, this.visor);
        Image image2 = new Image(getClass().getResourceAsStream("/bandera.gif"));
        bandera.setImage(image2);
        bandera.translateXProperty().setValue(1600);
        bandera.translateYProperty().setValue(500);;
       
  
    }
    


    @FXML
    void atraparPokemon(MouseEvent event) throws InterruptedException {
    	if(visor.translateXProperty().get() >= (1600)) {
    		visor.setDisable(true);
    	}
    	else {
    	main = new Main();
    	main.cambiarPokemonAtrapado(true);
    	Image im = new Image(getClass().getResourceAsStream("/pokebola.gif"));
    	this.visor.setImage(im);
    	visor.setDisable(true);
   

    	}
    

    }
    

    public void cambiarDesplazamientoPokemon2(ImageView a) throws InterruptedException {
    	main = new Main();
    	parada = false;
    	this.visor = a;
     	
    	for(int i = (int)main.darPosicionInicial(); main.LlegoAlaMeta()==false&&parada ==false;i +=5) {
    		Thread.sleep(150);
    	
    		visor.translateXProperty().setValue(main.cambioDesplazamiento());
    		if(main.darPuntajePokemonSeleccionado() >0) {
    		Platform.runLater(new Runnable() {
    		    @Override
    		    public void run() {
    		        // if you change the UI, do it here !
    		    	
    	    		aumentarPuntaje();
    	    		

    	    		
    		    }
    		});
    		}
    }
    	
		Platform.runLater(new Runnable() {
		    @Override
		    public void run() {
    	if(main.LlegoAlaMeta() == true) {
    		puntaje.setText("0");
    		main.cambiarPuntajePokemonSeleccionado(0);
    		
    	}
		    }
		});
    	

    }
    public ImageView darImagen() {
    	return this.visor;
    }
    
    public void aumentarPuntaje() {
        puntaje.setText(""+main.darPuntajePokemonSeleccionado());
        
    }
    
    @FXML
    void salvar(ActionEvent event) {
    	parada = true;
    	main.seHaGuardado();
    	main.guardar();
      	main.abrirPantallaPuntaje();
      	
    	int a = Integer.parseInt(puntaje.getText());
    	main.cambiarPuntajeJugador(a);
    }
  
    
    
 
    

}