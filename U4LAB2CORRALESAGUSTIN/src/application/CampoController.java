package application;

import javax.swing.JOptionPane;

import com.sun.prism.paint.Stop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CampoController {

    
    private Main main;
    @FXML
    private  ImageView visor;
    @FXML
    private ImageView bandera;
    

    @FXML
    private Label puntaje;
    
    private Label p;
    @FXML
    private Button salvar;
   private  boolean guardo;


    
    
    public CampoController() {
   
    }
	public void initialize() {
		iniciar();
	}
	
    
    public void iniciar() {
    	
    	
        Image image = new Image(getClass().getResourceAsStream("/pokebola.gif"));
       
       
        
       visor.setImage(image);
       visor.translateXProperty().setValue(200);
       visor.translateYProperty().setValue(600);
       
       Image image2 = new Image(getClass().getResourceAsStream("/bandera.gif"));
       bandera.setImage(image2);
       bandera.translateXProperty().setValue(1600);
       bandera.translateYProperty().setValue(500);;



     

        
    }
    
    
    @FXML
    void salvar(ActionEvent event) {
 
    	if(visor.isDisable()) {
    	guardo = true;
    	
    	main.seHaGuardado();

    	main.guardar();

    	main.abrirPantallaPuntaje();
    	int a = Integer.parseInt(puntaje.getText());
    	main.cambiarPuntajeJugador(a);
    	
    	}
    }
 

    @FXML
    void liberarPokemon(MouseEvent event) throws InterruptedException {
    	main = new Main();
    	
    	Image im = new Image(getClass().getResourceAsStream("/"+main.pokemonLiberado(CampoController.this, this.visor)+".gif"));
    	
    	this.visor.setImage(im);
    	visor.setDisable(true);
 
    	

    }
    
    public void cambiarDesplazamientoPokemon(ImageView a) throws InterruptedException {
    	main = new Main();
    	guardo =false;
    	this.visor = a;
     	
    	for(int i = 0;main.LlegoAlaMeta()==false&&guardo==false;i +=5) {
    		Thread.sleep(150);
    		
    		visor.translateXProperty().setValue(main.cambioDesplazamiento());
    		Platform.runLater(new Runnable() {
    		    @Override
  
    		    public void run() {
    		        // if you change the UI, do it here !
    		    	
    	    		aumentarPuntaje();
    	    	
    		    }
    		});
   
    	

    }
//    	main.aviso();
 
    }
    public ImageView darImagen() {
    	return this.visor;
    }
    
    public void aumentarPuntaje() {
        puntaje.setText(""+main.darPuntajePokemonSeleccionado());
    }
    
 
    

}