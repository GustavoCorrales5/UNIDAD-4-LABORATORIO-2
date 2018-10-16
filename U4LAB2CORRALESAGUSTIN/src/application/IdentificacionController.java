package application;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IdentificacionController {

	@FXML
	private TextField nick;

	@FXML
	private Button ingresar;

	private Main main;

	@FXML
	private Button cargar;

	@FXML
	private Button bJugador;
	@FXML
	private Button bPokemon;

	@FXML
	void cargar(ActionEvent event) {
		main = new Main();
		main.cargar();
	}

	public IdentificacionController() {
		// TODO Auto-generated constructor stub

	}

	@FXML
	void busquedaBinaria(ActionEvent event) {
		main = new Main();
		main.cargarBusquedaBinaria();
	}

	@FXML
	void busquedaBinariaPokemon(ActionEvent event) {
		main = new Main();

		try {
		Image image = new Image(getClass().getResourceAsStream("/" + main.cargarBusquedaBinariaPokemon() + ".gif"));
		ImageView i = new ImageView(image);
		Label a = new Label();
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("POKEMON");
        
         alert.setHeaderText("Pokemon encontrado:");
        alert.setGraphic(i);
        alert.showAndWait();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "NO EXISTE ESE POKEMON", "ADVERTENCIA", 2);
		}
		
	}

	@FXML
	void guardarNick(ActionEvent event) {
		boolean valido = true;
		for (int i = 0; i < nick.getText().length() && valido == true; i++) {
			if (nick.getText().charAt(i) == '0' || nick.getText().charAt(i) == '1' || nick.getText().charAt(i) == '2'
					|| nick.getText().charAt(i) == '3' || nick.getText().charAt(i) == '4'
					|| nick.getText().charAt(i) == '5' || nick.getText().charAt(i) == '6'
					|| nick.getText().charAt(i) == '7' || nick.getText().charAt(i) == '8'
					|| nick.getText().charAt(i) == '9' || nick.getText().charAt(i) == ' '
					|| nick.getText().charAt(i) == '@') {
				valido = false;

			}
		}

		if (valido && !nick.getText().isEmpty()) {
			main = new Main();

			main.nueJugador(nick.getText());
			main.inicio();
		} else {
			JOptionPane.showMessageDialog(null,
					"Ingrese nombre valido: \n 1) Sin números. \n 2) Un valor que no este en blanco.", "ADVERTENCIA",
					2);
		}

	}

}