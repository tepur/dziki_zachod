package dzikizachod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StrategiaBandytyDomyslna extends StrategiaBandyty{
	/*Implementacja zgodna z postem: https://moodle.mimuw.edu.pl/mod/forum/discuss.php?d=300,
	 *przy wyborze celu do strza³u bandyta wybiera jakiegokolwiek pomocnika szeryfa,
	 *chyba, ¿e mo¿e strzeliæ do samego szeryfa 
	 */
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){ 
		if(karta == Akcja.STRZEL){
			Set<Gracz> graczeWZasiegu = Strategia.graczeWZasiegu(wywolawca, gracze);
			Set<Gracz> kandydaci = new HashSet<>();
			for(Gracz g : graczeWZasiegu){
				if(g.rola() == Rola.SZERYF){
					return g;
				}
				else if(g.rola() == Rola.POMOCNIK_SZERYFA){
					kandydaci.add(g);
				}
			}
			return Strategia.losowyGracz(kandydaci);
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
}
