package dzikizachod;

import java.util.List;
import java.util.Set;

public class StrategiaBandytyCierpliwa extends StrategiaBandyty{
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.STRZEL){
			Set<Gracz> graczeWZasiegu = Strategia.graczeWZasiegu(wywolawca, gracze);
			return Strategia.znajdzSzeryfa(graczeWZasiegu);
			//W razie, gdy szeryfa nie ma w graczeWZasiegu, znajdzSzeryfa i tak zwróci null
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
}