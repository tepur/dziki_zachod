package dzikizachod;

import java.util.List;

public abstract class StrategiaBandyty extends Strategia{
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.DYNAMIT){
			Gracz aktGracz = wywolawca;
			for(int i = 1; i <= 3; i++){
				aktGracz = ListyCykliczne.nastepnyElement(gracze, aktGracz);
				if(aktGracz.rola() == Rola.SZERYF){
					return wywolawca;
				}
			}
			return null;
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
}