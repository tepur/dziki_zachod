package dzikizachod;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Strategia{
	
	public static Set<Gracz> graczeWZasiegu(Gracz wywolawca, List<Gracz> gracze){
		Set<Gracz> graczeWZasiegu = new HashSet<>();
		
		if((wywolawca.zasieg() * 2) >= gracze.size()){
			graczeWZasiegu.addAll(gracze);
			graczeWZasiegu.remove(wywolawca);
		}
		else{
			Gracz nastepnyGracz = wywolawca;
			Gracz poprzedniGracz = wywolawca;
			for(int i = 1; i <= wywolawca.zasieg(); i++){
				nastepnyGracz = ListyCykliczne.nastepnyElement(gracze, nastepnyGracz);
				poprzedniGracz = ListyCykliczne.poprzedniElement(gracze, poprzedniGracz);
				
				graczeWZasiegu.add(nastepnyGracz);
				graczeWZasiegu.add(poprzedniGracz);
			}
		}
		
		return graczeWZasiegu;
	}
	public static Gracz losowyGracz(Set<Gracz> s){
		return Zbiory.losowyElementZbioru(s);
	}
	
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.ZASIEG_PLUS_DWA || karta == Akcja.ZASIEG_PLUS_JEDEN){
			return wywolawca;
		}
		if(karta == Akcja.ULECZ){
			if(wywolawca.moznaPodleczyc(Gra.WARTOSC_LECZENIA)){
				return wywolawca;
			}
		}
		return null;
	}

	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		//Domyœlna strategia nie potrzebuje ¿adnych informacji
	}
	public static Gracz znajdzSzeryfa(Collection<Gracz> gracze){
		return Gra.znajdzSzeryfa(gracze);
	}
	//Zwraca gracze.size() + 1, jeœli któregoœ z elementów gracz1, gracz2 nie ma na liœcie gracze
	public static int odleglosc(List<Gracz> gracze, Gracz gracz1, Gracz gracz2){
		int indeksGracza1 = gracze.indexOf(gracz1);
		int indeksGracza2 = gracze.indexOf(gracz2);
		
		if(indeksGracza1 == (-1) || indeksGracza2 == (-1)){
			return gracze.size() + 1;
		}
		if(indeksGracza1 > indeksGracza2){
			int tmp = indeksGracza1;
			indeksGracza1 = indeksGracza2;
			indeksGracza2 = tmp;
		}
		return Math.min((indeksGracza2 - indeksGracza1), (gracze.size() - indeksGracza2 + indeksGracza1));
	}
}


