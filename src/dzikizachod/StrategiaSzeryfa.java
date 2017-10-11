package dzikizachod;

import java.util.List;

abstract class StrategiaSzeryfa extends Strategia{
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.DYNAMIT){
			return null;
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
}