package dzikizachod;

import java.util.List;
import java.util.Set;

public class StrategiaSzeryfaDomyslna extends StrategiaSzeryfa{
	ZbiorStrzelcowWSzeryfa strzelcyWSzeryfa = new ZbiorStrzelcowWSzeryfa();
	
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.STRZEL){
			Set<Gracz> graczeWZasiegu = Strategia.graczeWZasiegu(wywolawca, gracze);
			if(strzelcyWSzeryfa.pusty()){
				return Strategia.losowyGracz(graczeWZasiegu);
			}
			else{
				graczeWZasiegu.retainAll(strzelcyWSzeryfa.zbior());
				return Strategia.losowyGracz(graczeWZasiegu);
			}
		} 
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
	@Override
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		strzelcyWSzeryfa.przyjmijInformacje(info, strzelec, cel);
	}
}