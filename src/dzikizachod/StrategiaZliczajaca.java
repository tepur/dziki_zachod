package dzikizachod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*StrategiaZliczajaca polega na wyznaczaniu jako celu do strza³u losowego z tych graczy, 
 * którzy zabili wiêcej pomocników szeryfa ni¿ bandytow lub strzelili do szeryfa
 */
public class StrategiaZliczajaca extends Strategia{
	LicznikBilansu licznik = new LicznikBilansu();
	ZbiorStrzelcowWSzeryfa strzelcyWSzeryfa = new ZbiorStrzelcowWSzeryfa();
	
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.STRZEL){
			Set<Gracz> graczeWZasiegu = Strategia.graczeWZasiegu(wywolawca, gracze);
			Set<Gracz> kandydaci = new HashSet<>();
			
			for(Gracz g : graczeWZasiegu){
				if(licznik.podejrzany(g) || strzelcyWSzeryfa.zawiera(g)){
					kandydaci.add(g);
				}
			}
			
			if(kandydaci.isEmpty()){
				return null;
			}
			else{
				return Strategia.losowyGracz(kandydaci);
			}
		}
		return null; //StrategiaZliczajaca odpowiada tylko za strzelanie
	}
	@Override
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		licznik.przyjmijInformacje(info, strzelec, cel);
		strzelcyWSzeryfa.przyjmijInformacje(info, strzelec, cel);
	}
}
