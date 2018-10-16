package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Campo {

	private static final double longitudX = 2000;
	private static final double longitudY = 900;
	private static final double posicionXPokemon = 200;
	private static final double posicionXBandera = 1400;
	private Pokemon[] pokemon = new Pokemon[3];
	private Pokemon pokemonSeleccionado;
	private double desplazamientoX;
	private int numeroPokemon;
	private int numeroPokemonSeleccionado;
	private Jugador jugador;
	private boolean llegoALaMeta;
	private boolean atrapado;
	private boolean perdio;
	private boolean enJuego;
	private static final int JUEGO_1 = 1;
	private static final int JUEGO_2 = 2;
	private boolean juegoIniciado;
	private int numeroJuegoActivo;
	private boolean yaSeGuardo;
	private String valores[][];
	private Jugador jugadores[][];
	private FileInputStream fileInStr;
	private ObjectInputStream entrada;
	private Jugador jugadoresBinario[];
	private ComparadorNombre nombreComparacion;
	private boolean guardado;
	public Campo() {

		Pokemon pika = new Pokemon("pikachu", 0, false, Campo.posicionXPokemon, 50.0, 0);
		pokemon[0] = pika;
		Pokemon chari = new Pokemon("charizard", 1, false, Campo.posicionXPokemon, 20.0, 0);
		pokemon[1] = chari;
		Pokemon umbreon = new Pokemon("umbreon", 2, false, Campo.posicionXPokemon, 20.0, 0);
		pokemon[2] = umbreon;

		this.numeroPokemon = 3;
	}

	public String[][] getValores() {
		return valores;
	}
	
	public Pokemon[] darPokemon() {
		return this.pokemon;
	}

	public void seHaGuardado(boolean a) {
		this.guardado = a;
	}
	public Pokemon seleccionarPokemon(int a) {

		boolean parar = false;
		for (int i = 0; i < 3 && parar == false; i++) {
			if (pokemon[i].darNumero() == a) {
				parar = true;
				pokemon[i].pokemonSeleccionado(true);
				pokemonSeleccionado = pokemon[i];

				numeroPokemonSeleccionado = i;

			}

		}

		jugador.cambiarPokemonElegido(pokemonSeleccionado);
		return pokemonSeleccionado;

	}

	public void desplazamiento(long time) {
		jugador.cambiarJuego(JUEGO_1);
		modificarNumeroJuego(JUEGO_1);
		jugador.cambiarPokemonElegido(pokemonSeleccionado);
		this.desplazamientoX = pokemonSeleccionado.darPosicion();
		boolean parar = false;
		pokemonSeleccionado.velocidadAleatoria();
		this.guardado = false;
		double velocidad = pokemonSeleccionado.darVelocidad();
		for (double i = pokemonSeleccionado.darPosicion(); desplazamientoX <= posicionXBandera
				&& parar == false&& guardado ==false; i += 5) {
			this.desplazamientoX += velocidad;

			this.esperarSegundos(1);

			if (desplazamientoX >= (posicionXBandera - velocidad)) {

				parar = true;
				llegoALaMeta = true;
			}
			
			pokemonSeleccionado.cambiarPosicion(this.desplazamientoX);
			pokemonSeleccionado.aumentarPuntaje();
			jugador.cambiarPuntaje(pokemonSeleccionado.darPuntaje());
		}

	}

	public void desplazamiento2(long time) {
		jugador.cambiarJuego(JUEGO_2);
		modificarNumeroJuego(JUEGO_2);
		jugador.cambiarPokemonElegido(pokemonSeleccionado);
		this.desplazamientoX = pokemonSeleccionado.darPosicion();
		boolean parar = false;
		pokemonSeleccionado.velocidadAleatoria();
		pokemonSeleccionado.cambiarPuntaje(500);
		double velocidad = pokemonSeleccionado.darVelocidad();
		this.guardado = false;
		for (double i = pokemonSeleccionado.darPosicion(); desplazamientoX <= longitudX && parar == false&&guardado==false; i += 5) {
			this.desplazamientoX += velocidad;
			pokemonSeleccionado.bajarPuntaje();
			jugador.cambiarPuntaje(pokemonSeleccionado.darPuntaje());
			pokemonSeleccionado.bajarPuntaje();
			this.esperarSegundos(1);
			if (desplazamientoX == longitudX) {
				parar = true;
				this.perdio = true;
				pokemonSeleccionado.cambiarPuntaje(0);
				jugador.cambiarPuntaje(pokemonSeleccionado.darPuntaje());
			}
			if (pokemonAtrapado()) {
				parar = true;
				this.atrapado = true;
			}

			pokemonSeleccionado.cambiarPosicion(this.desplazamientoX);

		}
		if (pokemonAtrapado() == false) {
			pokemonSeleccionado.cambiarPuntaje(0);
		}

	}

	public void esperarSegundos(int segundos) {
		try {
			Thread.sleep(segundos * 150);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public void guardar() {
		boolean pararTodo = false;
		FileOutputStream fileOutS = null;
		ObjectOutputStream salida = null;
		Jugador elJugador[][] = null;
		boolean ojo = false;
		fileInStr = null;
		entrada = null;

		try {
			fileInStr = new FileInputStream("archivos/jugadores.dat");
			try {
				entrada = new ObjectInputStream(fileInStr);
			} catch (Exception e) {

			}
			if (entrada != null) {
				elJugador = (Jugador[][]) entrada.readObject();
				int indice1;
				int indice2;
				if (elJugador[0][0].darPokemonElegido() != null) {

					boolean parar = false;

					yaSeGuardo = false;
					Jugador jugadorP = new Jugador(jugador.darNick(), jugador.darPuntaje(), jugador.darPokemonElegido(),
							jugador.darJuego(), jugador.darJuegoTerminado());
					Jugador f = null;
					System.out.println("JUGADORES:\n");
					for (int i = 0; i < elJugador.length && parar == false; i++) {
						for (int j = 0; j < elJugador[0].length && parar == false; j++) {

					
							if ((elJugador[i][j] != null) && (elJugador[i][j].darNick().equals(jugador.darNick()))) {

								elJugador[i][j] = jugador;
								parar = true;
								yaSeGuardo = true;
								
							}

							else if (elJugador[i][j] == null) {
								if (yaSeGuardo == false) {
									elJugador[i][j] = jugador;
									parar = true;

								}
								if (yaSeGuardo) {
									parar = true;
								}
							}
						}

					}
				}
			} else {

				elJugador = new Jugador[100][100];
				elJugador[0][0] = jugador;
				pararTodo = false;

				try {

					fileOutS = new FileOutputStream("archivos/jugadores.dat");
					salida = new ObjectOutputStream(fileOutS);

					salida.writeObject(elJugador);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("EEEE");
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("BBBB");
				} finally {
					try {
						if (fileOutS != null) {
							fileOutS.close();
						}
						if (salida != null) {
							salida.close();
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
				jugadores = elJugador;

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fileInStr != null) {
					fileInStr.close();
				}
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		if (pararTodo == false) {

			int p, j;
			Jugador aux;

			for (p = 1; p <= elJugador[0].length && elJugador[0][p] != null; p++) { // desde el segundo elemento hasta
				aux = elJugador[0][p]; // el final, guardamos el elemento y
				j = p - 1; // empezamos a comprobar con el anterior
				while ((j >= 0) && (elJugador[0][j] != null) && (((aux.compareTo(elJugador[0][j]) < 0)))) { // mientras
																											// queden
																											// posiciones
																											// y
																											// el
					// valor de aux sea menor que los
					elJugador[0][j + 1] = elJugador[0][j]; // de la izquierda, se desplaza a
					j--; // la derecha
				}
				elJugador[0][j + 1] = aux; // colocamos aux en su sitio
			}
		}

		jugadores = elJugador;
		valores = new String[100][100];
		jugadoresBinario = new Jugador[100];
		for (int x = 0; x <= elJugador[0].length && elJugador[0][x] != null; x++) {
			valores[0][x] = elJugador[0][x].darNick();
			valores[1][x] = "" + elJugador[0][x].darPuntaje();
			jugadoresBinario[x] = elJugador[0][x];
			
		}

		try {

			fileOutS = new FileOutputStream("archivos/jugadores.dat");
			salida = new ObjectOutputStream(fileOutS);
			salida.writeObject(elJugador);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fileOutS != null) {
					fileOutS.close();
				}
				if (salida != null) {
					salida.close();
				}
			} catch (IOException e) {
				System.out.println("HOLA");
			}
		}
	}

	public int darNumeroPokemon() {

		return this.numeroPokemon;
	}

	public Pokemon darPokemonSeleccionado() {
		return this.pokemonSeleccionado;
	}

	public double darDesplazamiento() {
		return this.desplazamientoX;
	}

	public boolean darDiferenciaDistancia() {
		return (desplazamientoX >= (posicionXBandera - pokemonSeleccionado.darVelocidad()));
	}

	public double distanciaRecorrida() {
		return ((posicionXBandera - 200) - (posicionXPokemon));
	}

	public boolean pokemonAtrapado() {
		return pokemonSeleccionado.darAtrapado();
	}

	public void cambiarPokemonAtrapado(boolean a) {
		this.pokemonSeleccionado.atrapado(a);
	}

	public int numeroPokemonSeleccionado() {
		return this.numeroPokemonSeleccionado;
	}

	public void cambiarNumeroPokemonSeleccionado(int a) {
		this.numeroPokemonSeleccionado = a;
	}

	public void nuevoJugador(String f) {
		this.jugador = new Jugador(f, 0, null, 0, false);
	}
	
	public void nuevoJugador2(String f,int i) {
		this.jugador = new Jugador(f, i, null, 0, false);

	}
	public void nuevoJugador2(String f,Pokemon p) {
		this.jugador = new Jugador(f, 0,p, 0, false);

	}

	public boolean enJuego() {
		if (atrapado || perdio || llegoALaMeta) {
			enJuego = false;
			jugador.cambiarJuegoTerminado(enJuego);
			return false;
		} else {
			enJuego = true;
			jugador.cambiarJuegoTerminado(enJuego);
			return true;
		}

	}

	public void cargar() {
		FileInputStream fileInStr = null;
		ObjectInputStream entrada = null;
		Jugador elJugador[][] = null;

		try {
			String nick = JOptionPane.showInputDialog("Introduzca su nick");

			fileInStr = new FileInputStream("archivos/jugadores.dat");
			entrada = new ObjectInputStream(fileInStr);

			elJugador = (Jugador[][]) entrada.readObject();
			boolean parar = false;

			for (int i = 0; i < elJugador.length && parar == false; i++) {
				for (int j = 0; j < elJugador[0].length && parar == false; j++) {
					if (elJugador[i][j] != null && elJugador[i][j].darNick().equals(nick)) {
						cambiarNumeroPokemonSeleccionado(elJugador[i][j].darPokemonElegido().darNumero());
						modificarNumeroJuego(elJugador[i][j].darJuego());
						jugador = elJugador[i][j];
						pokemonSeleccionado = elJugador[i][j].darPokemonElegido();

						parar = true;
					}

				}
			}
			if (parar == false) {
				JOptionPane.showMessageDialog(null, "Ingrese nombre valido", "ADVERTENCIA", 2);
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			
				JOptionPane.showMessageDialog(null, "No hay ningun jugador por el momento", "ADVERTENCIA", 2);
			
		} finally {
			try {
				if (fileInStr != null) {
					fileInStr.close();
				}
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void cargarBinarioNombre() {
		FileInputStream fileInStr = null;
		ObjectInputStream entrada = null;
		Jugador elJugador[][] = null;


		try {
		
			String nick = JOptionPane.showInputDialog("Introduzca su nick");

			fileInStr = new FileInputStream("archivos/jugadores.dat");
			
			entrada = new ObjectInputStream(fileInStr);


			elJugador = (Jugador[][]) entrada.readObject();
			nombreComparacion = new ComparadorNombre();
			jugadoresBinario = new Jugador[100];
			for (int x = 0; x <= elJugador[0].length && elJugador[0][x] != null; x++) {
				jugadoresBinario[x] = elJugador[0][x];
				
			}
			int p, j;
			Jugador aux;
			int c = 0;
			for (p = 1; p < jugadoresBinario.length && jugadoresBinario[p] != null; p++) { // desde el segundo elemento hasta
				aux = jugadoresBinario[p]; // el final, guardamos el elemento y
				if(jugadoresBinario[p+1] == null) {
					c = p;
				}
				j = p - 1; // empezamos a comprobar con el anterior

				while ((j >= 0) && (jugadoresBinario[j] != null) && (((nombreComparacion.compare(aux, jugadoresBinario[j]) < 0)))) { // mientras
					// queden
					// posiciones
					// y
					// el
					// valor de aux sea menor que los
					jugadoresBinario[j + 1] = jugadoresBinario[j]; // de la izquierda, se desplaza a
					
					j--; // la derecha
					
				}
				jugadoresBinario[j + 1] = aux; // colocamos aux en su sitio
				
			}
	
		
			Jugador jug = new Jugador(nick,0,null,0,false);
			jug.cambiarNick(nick);
			  int n = jugadoresBinario.length;
			  int centro;
			  int inferior=0;
			  int superior=c;
			  int valor = 0;
			  boolean parad = false;
			 
			   while(inferior<=superior&&parad==false){
			     centro=(superior+inferior)/2;
			    
			     
			     if(nombreComparacion.compare(jugadoresBinario[centro], jug) == 0) {
			    	 valor = centro;
			    	 
			    	 parad = true;}
			     
			     else if(nombreComparacion.compare(jugadoresBinario[centro], jug)>0 ){
			        superior=centro-1;
			       
			     }
			     else {
			       inferior=centro+1;
			      
			     }
			   
			   }
			   if(parad ==true) {
			   JOptionPane.showMessageDialog(null, "NOMBRE:  "+ jugadoresBinario[valor].darNick()+"  PUNTAJE:    " + jugadoresBinario[valor].darPuntaje(), "JUGADOR BUSCADO", 2);
			   }
			if (parad == false) {
				
				JOptionPane.showMessageDialog(null, "Ingrese nombre valido", "ADVERTENCIA", 2);
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
		
			JOptionPane.showMessageDialog(null, "No hay ningun jugador por el momento", "ADVERTENCIA", 2);
			
		} finally {
			try {
				if (fileInStr != null) {
					fileInStr.close();
				}
				if (entrada != null) {
					entrada.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public int cargarBinarioPokemon() {
		
			String nick = JOptionPane.showInputDialog("Introduzca el nombre del pokemon");
			
			

			ordenarPokemonPorNombre();
		
			Pokemon pok = new Pokemon(nick,0,false,0,0,0);
				boolean valido = true;
			  int n = pokemon.length;
			  int centro;
			  int inferior=0;
			  int superior=n-1;
			  int valor = 0;
			  boolean parad = false;
			  Pokemon pokemonEscogido = null;
			  
			   while(inferior<superior&&parad==false){
			     centro=(superior+inferior)/2;
			    
			     
			     if(pokemon[centro].compareTo(pok) == 0) {
			    	 valor = centro;
			    	 pokemonEscogido = pokemon[centro];
			    	 parad = true;
			    	 }
			     
			     else if(pokemon[centro].compareTo(pok)>0 ){
			        if(pok.compareTo(pokemon[superior]) ==0){
			    	 pokemonEscogido = pokemon[superior];
			    	 parad = true;
			        }
			        else {
			        	valido = false;
			        	parad = true;
			        }
			     }
			     else {
			       if(pokemon[centro].compareTo(pok)<0) {
			    	   if(pok.compareTo(pokemon[inferior]) ==0){
			    	 pokemonEscogido = pokemon[inferior];
			    	 parad = true;
			       }
				       else {
				    	   valido = false;
				    	   parad = true;
				       }
			     }

			   
			   }
			    
			   }

			return pokemonEscogido.darNumero();

	}

	public int darNumeroJuegoActivo() {
		return this.numeroJuegoActivo;
	}

	public void modificarNumeroJuego(int a) {
		this.numeroJuegoActivo = a;
	}

	public Jugador darJugador() {
		return this.jugador;
	}

	public void cambiarJugador(Jugador a) {
		this.jugador = a;
	}

	public Jugador[][] darJugadores() {
		// TODO Auto-generated method stub
		return jugadores;
	}
	public void cambiarPuntajeJugador(int a) {
		pokemonSeleccionado.cambiarPuntaje(a);
		jugador.cambiarPuntaje(pokemonSeleccionado.darPuntaje());
	}
	
	public void ordenarPokemonPorNombre() {
		int p, j;
		Pokemon aux;
		int c = 0;
		for (p = 1; p < pokemon.length && pokemon[p] != null; p++) { // desde el segundo elemento hasta
			aux = pokemon[p]; // el final, guardamos el elemento y

			j = p - 1; // empezamos a comprobar con el anterior

			while ((j >= 0) && (pokemon[j] != null) && (pokemon[j].compareTo(aux) < 0)) { // mientras
				// queden
				// posiciones
				// y
				// el
				// valor de aux sea menor que los
				pokemon[j + 1] = pokemon[j]; // de la izquierda, se desplaza a
				
				j--; // la derecha
				
			}
			pokemon[j + 1] = aux; // colocamos aux en su sitio
			
		}
	}

}
