package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

	private Main main;
    @FXML
    private Button lanzar;

    @FXML
    private Button atrapar;

    @FXML
    void atraparP(ActionEvent event) {

    	main.seleccionPokemon2();

    }

    @FXML
    void lanzamiento(ActionEvent event) {

    

    	
    	main.seleccionPokemon();
    
    }

}