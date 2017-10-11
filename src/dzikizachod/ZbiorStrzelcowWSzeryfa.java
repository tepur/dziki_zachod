package dzikizachod;

import java.util.HashSet;
import java.util.Set;

//Wyznacza, kto strzela³ do szeryfa na podstawie podanych mu informacji
public class ZbiorStrzelcowWSzeryfa{
	Set<Gracz> strzelcyWSzeryfa = new HashSet<Gracz>();
		
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		if(info == Informacja.STRZAL && strzelec != null && cel.rola() == Rola.SZERYF){
			strzelcyWSzeryfa.add(strzelec);
		}
	}

	public boolean zawiera(Gracz g){
		return strzelcyWSzeryfa.contains(g);
	}

	public boolean pusty(){
		return strzelcyWSzeryfa.isEmpty();
	}
	public Set<Gracz> zbior(){
		return strzelcyWSzeryfa;
	}
}
