package modelo;

import java.util.Comparator;

public class ComparadorNombre implements Comparator<Jugador> {

	@Override
	public int compare(Jugador arg0, Jugador arg1) {
		// TODO Auto-generated method stub
		String a0 = arg0.darNick().toLowerCase();
		String a1 = arg1.darNick().toLowerCase();
		return a0.compareTo(a1);
	}

}
