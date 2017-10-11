package dzikizachod;

import java.util.List;
import java.util.Set;

public class StrategiaBandytySprytna extends StrategiaBandyty{
	Gracz wlasciciel = null;
	Gracz celBandyta = null;
	
	private static int iloscKartStrzalu(Gracz gracz){
		int ilosc = 0;
		for(Akcja k : gracz.karty()){
			if(k == Akcja.STRZEL){
				ilosc++;
			}
		}
		return ilosc;
	}
	
	public void ustawWlasciciela(Gracz wlasciciel){
		this.wlasciciel = wlasciciel;
	}
	
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(wlasciciel == null){
			wlasciciel = wywolawca; //Dedukuje swojego w³aœciciela, jeœli nie zosta³ on podany
		}
		
		if(karta == Akcja.STRZEL){
			Set<Gracz> graczeWZasiegu = Strategia.graczeWZasiegu(wywolawca, gracze);
			
			Gracz szeryf = Strategia.znajdzSzeryfa(graczeWZasiegu);
			if(szeryf != null){
				return szeryf;
			}

			if(celBandyta != null && celBandyta.zywy()){
				return celBandyta;
			}
			
			for(Gracz g : graczeWZasiegu){
				if(g.rola() == Rola.BANDYTA && iloscKartStrzalu(wywolawca) >= g.liczbaZyc()){
					celBandyta = g;
					break;
				}
			}
			if(celBandyta != null){
				return celBandyta;
			}
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
	@Override
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		if(info == Informacja.SMIERC && strzelec != null && strzelec == wlasciciel && cel == celBandyta){
			celBandyta = null;
			if(wlasciciel != null){
				wlasciciel.zmienStrategie(new StrategiaBandytyDomyslna());
			}
		}
	}
}