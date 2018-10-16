package Prueba;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import application.Main;
import application.PuntajeController;
import modelo.Campo;
import modelo.Jugador;
import modelo.Pokemon;

class PruebaOrden {
	private PuntajeController puntaje;
	private Campo campo;
	private String[][] valores;
	private Jugador[][] j;
	private Main main;
	private Pokemon pokemon;

	
	//CORREGIRLO
	private void escenarioOrdenNatural1() {
		campo = new Campo();
		campo.nuevoJugador2("abril",0);
		campo.guardar();
		campo.nuevoJugador2("Carlos",50);
		campo.guardar();


		campo.seHaGuardado(true);

		valores = campo.getValores();
	}

	//CORREGIRLO 
	private void escenarioOrdenParcial1() {
		campo = new Campo();
		campo.nuevoJugador("abril");
		campo.guardar();
		campo.nuevoJugador("Carlos");
		campo.guardar();
		puntaje=new PuntajeController();
		valores = campo.getValores();
		
		

	}

	private void escenarioOrdenParcialPokemon1() {
		campo = new Campo();

		campo.ordenarPokemonPorNombre();

	}

	private void escenarioOrdenNaturalPokemon1() {
		campo = new Campo();

	}

	@Test
	void ordanemientoNaturalTest() {
		escenarioOrdenNatural1();
//Lo ponemos en valores[1][0] porque el metodo ordena de menor a mayor
		assertEquals("0", valores[1][0]);

	}

	@Test
	void ordenamientoParcialTest() {
		escenarioOrdenParcial1();

		assertEquals("abril", valores[0][0]);
	}

	@Test
	void ordenamientoParcialPokemonTest() {
		escenarioOrdenParcialPokemon1();
		assertEquals("charizard", campo.darPokemon()[2].nombre());
	}

	@Test

	void ordenamientoNaturalPokemonTest() {
		escenarioOrdenNaturalPokemon1();
		assertEquals("pikachu", campo.darPokemon()[0].nombre());
	}
}
