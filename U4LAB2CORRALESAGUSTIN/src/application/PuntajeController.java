package application;

import java.awt.Panel;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import modelo.ComparadorNombre;
import modelo.Jugador;

public class PuntajeController {
	@FXML
	private GridPane Orden;
	@FXML
	private Label[][] jugadores;

	@FXML
	private Button Ordernar;

	@FXML
	private BorderPane border;
	private Main main;
	private int k;
	private ComparadorNombre comparadorNombreParcial;
	private Jugador[][] ju;
	private Jugador[] jug;

	public PuntajeController() {

	}

	public void initialize() {
		iniciar();
	}

	public void iniciar() {
		main = new Main();
		ju = main.darJugadores();

		String valores[][] = main.getValoresDeJugadores();
		jugadores = new Label[100][100];
		Label titulo1 = new Label("NOMBRES");
		titulo1.setStyle("-fx-font-weight: bold;");
		titulo1.setPadding(new Insets(15, 15, 15, 15));
		Label titulo2 = new Label("PUNTAJES");
		titulo2.setStyle("-fx-font-weight: bold;");
		titulo2.setPadding(new Insets(15, 15, 15, 15));
		Orden.add(titulo1, 0, 0);
		Orden.add(titulo2, 1, 0);
		if (valores[0][0] != null) {
			for (int i = 0; i <= valores[0].length && valores[0][i] != null; i++) {
				jugadores[0][i] = new Label(valores[0][i]);
				jugadores[0][i].setPadding(new Insets(0.0, 0.0, 0.0, 15));
				jugadores[1][i] = new Label(valores[1][i]);
				jugadores[1][i].setPadding(new Insets(0.0, 0.0, 0.0, 15));

				Orden.add(jugadores[0][i], 0, (i+1));

				Orden.add(jugadores[1][i], 1, (i+1));

			}
		} else {

			jugadores[0][0] = new Label(valores[0][0]);
			jugadores[0][0].setPadding(new Insets(0.0, 0.0, 0.0, 15));
			jugadores[1][0] = new Label(valores[1][0]);
			jugadores[1][0].setPadding(new Insets(0.0, 0.0, 0.0, 15));
			Orden.add(jugadores[0][0], 0, 1);

			Orden.add(jugadores[1][0], 1, 1);
		}

		Orden.setAlignment(Pos.CENTER);
		Orden.setGridLinesVisible(true);
		ordenarParcialmente();

	}

	public void ordenarParcialmente() {
		comparadorNombreParcial = new ComparadorNombre();
		Ordernar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Ordernar.setVisible(false);
				Ordernar.setDisable(true);
				Label[][] A = jugadores;

				ordenar();


				Orden.setGridLinesVisible(false);
				Orden.getChildren().clear();
				for (int x = 0; x <= A[0].length && A[0][x] != null; x++) {

					A[0][x].setPadding(new Insets(0.0, 0.0, 0.0, 15));

					A[1][x].setPadding(new Insets(0.0, 0.0, 0.0, 15));

					Orden.add(A[0][x], 0, x+1);

					Orden.add(A[1][x], 1, x+1);

					CambiarPosicionBoton(1 + x);

				}

				Label titulo1 = new Label("NOMBRES");
				titulo1.setStyle("-fx-font-weight: bold;");
				titulo1.setPadding(new Insets(15, 15, 15, 15));
				Label titulo2 = new Label("PUNTAJES");
				titulo2.setStyle("-fx-font-weight: bold;");
				titulo2.setPadding(new Insets(15, 15, 15, 15));
				Orden.add(titulo1, 0, 0);
				Orden.add(titulo2, 1, 0);

				Orden.setAlignment(Pos.CENTER);
				Orden.setGridLinesVisible(true);

			}
		});
	}
	
	public void ordenar() {
		int p, j;
		Jugador aux;
		
		Label[][] A = jugadores;
		for (p = 1; p < ju[0].length && ju[0][p] != null; p++) { // desde el segundo elemento hasta
			aux = ju[0][p]; // el final, guardamos el elemento y
			j = p - 1; // empezamos a comprobar con el anterior
			while ((j >= 0) && (ju[0][j] != null) && (((comparadorNombreParcial.compare(aux, ju[0][j]) < 0)))) { // mientras
				// queden
				// posiciones
				// y
				// el
				// valor de aux sea menor que los
				ju[0][j + 1] = ju[0][j]; // de la izquierda, se desplaza a
				j--; // la derecha
			}
			ju[0][j + 1] = aux; // colocamos aux en su sitio
			
		}
		jug = new Jugador[100];
		for (p = 0; p < ju[0].length && ju[0][p] != null; p++) {
		A[0][p] = new Label(ju[0][p].darNick());
		System.out.println(ju[0][p].darNick() + "  "+ ju[0][p].darPuntaje());
		A[1][p] = new Label(Integer.toString(ju[0][p].darPuntaje()));
		Orden.add(A[0][p], 0, p+1);

		Orden.add(A[1][p], 1, p+1);
		jug[p] = ju[0][p];
		}
		
	}

	public void CambiarPosicionBoton(int a) {
		this.k = a;
	}

	public Jugador[][] getJugadores() {
		return ju;
	}

	public Button darButton() {
		return Ordernar;
	}

	public int darPosicionBoton() {
		return k;
	}

	public Jugador[] darJugadorBinario() {
		return this.jug;
	}


}
