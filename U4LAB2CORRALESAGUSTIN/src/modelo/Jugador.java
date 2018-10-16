package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Jugador implements Serializable, Comparable<Jugador> {

	private String nick;
	private int puntaje;
	private int juego;
	private boolean juegoTerminado;
	private Pokemon pokemonElegido;

	public Jugador() {

	}

	public Jugador(String nick, int puntaje, Pokemon pokemon, int juego, boolean juegoTerminado) {

		this.nick = nick;
		this.puntaje = puntaje;
		this.pokemonElegido = pokemon;
		this.juego = juego;
		this.juegoTerminado = juegoTerminado;

	}

	public String darNick() {
		return this.nick;
	}

	public int darPuntaje() {
		return this.puntaje;
	}

	public void cambiarNick(String a) {
		this.nick = a;
	}

	public void cambiarPuntaje(int a) {
		this.puntaje = a;
	}

	public int darJuego() {
		return this.juego;
	}

	public void cambiarJuego(int a) {
		this.juego = a;
	}

	public boolean darJuegoTerminado() {
		return this.juegoTerminado;
	}

	public void cambiarJuegoTerminado(boolean a) {
		this.juegoTerminado = a;
	}

	public Pokemon darPokemonElegido() {
		return this.pokemonElegido;
	}

	public void cambiarPokemonElegido(Pokemon a) {
		this.pokemonElegido = a;
	}

	@Override
	public int compareTo(Jugador o) {

		int ret = 0;
		if (this.puntaje > o.darPuntaje()) {
			ret = 1;
		} else if (this.puntaje < o.darPuntaje()) {

			ret = -1;
		} else {
			ret = this.darNick().compareTo(o.darNick());
		}
		
		return ret;
	}

}
