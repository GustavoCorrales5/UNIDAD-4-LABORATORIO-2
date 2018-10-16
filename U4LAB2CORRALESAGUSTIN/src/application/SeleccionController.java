package application;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

public class SeleccionController {
	@FXML
	private Button[] botones; 
    @FXML
    private GridPane grid;
    
    public Main main;
    
    public SeleccionController() {
    
    }
    public void initialize() {
    	botones = new Button[3];
  	  mostrar();
  	 
    }
    public void mostrar() {
      
    	main = new Main();
    	
    	int a = main.numeroPokemon;
 
    
    
    	for(int i = 0; i< botones.length; i++ ) {
    		botones[i] = new Button();
    		 Image image = new Image(getClass().getResourceAsStream("/"+i+".gif"));
    	       
   		  BackgroundSize s = new BackgroundSize(200,200,false,false,false,false);
 		 BackgroundImage nb = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null,s);  
    	        
    	       botones[i].setBackground(new Background (nb));
    	       
    		
    	     
    		botones[i].setOnAction(new EventHandler<ActionEvent>() {
			
				
			

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Button bnb = (Button)event.getSource();
					
					main.pokemonSeleccionado(darIndices(bnb));
					
				}
			});
    

    		botones[i].setPrefSize(nb.getSize().getWidth(),nb.getSize().getHeight());

    	grid.add(botones[i], 0, i);
    	
    	
    }
    	
    
    }

  
  public int darIndices(Button a) {
		int indice = 0 ;
		boolean parar = false;

		for(int i = 0; i<botones.length&&parar == false;i++) {
			
				if(botones[i]== a) {
					indice = i;
					
					parar = true;
				}
			}
		
		return indice;
		
	}
  

}