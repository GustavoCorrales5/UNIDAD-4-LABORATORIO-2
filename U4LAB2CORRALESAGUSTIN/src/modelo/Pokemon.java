package modelo;

import java.io.Serializable;

public class Pokemon implements Serializable, Comparable<Pokemon> {

	private String nombre;
	private int numero;
	private double posicion;
	private boolean seleccionado;
	private boolean atrapado;
	private double velocidad;
	private int puntaje;
	private boolean pokeBola;
	private static final String POKE = "/pokebola.gif";
	
	public Pokemon(String nombre, int numero,boolean seleccionado, double posicion, double velocidad, int puntaje) {
		this.nombre = nombre;
		this.numero = numero;
		this.seleccionado = seleccionado;
		this.posicion = posicion;
		this.velocidad = velocidad;
		this.puntaje = puntaje;
	}
	
	public void pokemonSeleccionado(boolean f) {
		this.seleccionado = f;
	}
	
	public boolean darSeleccionado() {
		return this.seleccionado;
	}
	
	public double darPosicion() {
		return this.posicion;
	}
	public void cambiarPosicion(double a) {
		this.posicion = a;
	}
	
	public int darNumero() {
		return this.numero;
	}
	
	public String nombre() {
		return this.nombre;
	}
	
	public void atrapado(boolean a) {
		this.atrapado = a;
	}
	public boolean darAtrapado() {
		return this.atrapado;
	}
	public double darVelocidad() {
		return velocidad;
	}
	public void cambiarVelocidad(double a) {
		this.velocidad = a;
	}
	public void velocidadAleatoria() {
		this.velocidad += Math.random()*(40.0-30.0); 
	}
	public int darPuntaje() {
		return this.puntaje;
	}
	public void aumentarPuntaje() {
		this.puntaje += 2*velocidad/10;
	}
	public void bajarPuntaje() {
		if(puntaje >=  2*velocidad/10) {
		this.puntaje -= 2*velocidad/10;
		}
		else {
			this.puntaje = 0;
		}
	}
	public void cambiarPuntaje(int a) {
		this.puntaje =a;
	}
	
	public boolean estaEnPokebola() {
		return pokeBola;
	}
	
	public void cambiarEstadoPokebola(boolean a) {
		this.pokeBola = a;
	}
	


	@Override
	public int compareTo(Pokemon o) {
		// TODO Auto-generated method stub
		String a0 = this.nombre().toLowerCase();
		String a1 = o.nombre().toLowerCase();
		return a0.compareTo(a1);
	}
}
