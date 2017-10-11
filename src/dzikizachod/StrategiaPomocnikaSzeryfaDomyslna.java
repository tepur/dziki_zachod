package dzikizachod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class StrategiaPomocnikaSzeryfaDomyslna extends StrategiaPomocnikaSzeryfa{
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.STRZEL){
			Set<Gracz> graczeWZasiegu = Strategia.graczeWZasiegu(wywolawca, gracze);
			
			Set<Gracz> kandydaci = new HashSet<>(graczeWZasiegu);
			kandydaci.remove(Strategia.znajdzSzeryfa(kandydaci));
			
			return Strategia.losowyGracz(kandydaci);
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
}
