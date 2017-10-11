package dzikizachod;

import java.util.List;

public abstract class StrategiaPomocnikaSzeryfa extends Strategia{
	
	LicznikBilansu licznik = new LicznikBilansu();
	
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		if(karta == Akcja.ULECZ){
			Gracz szeryf = Strategia.znajdzSzeryfa(gracze);
			if((Strategia.odleglosc(gracze, szeryf, wywolawca) == 1) && (szeryf.moznaPodleczyc(Gra.WARTOSC_LECZENIA))){
				return szeryf;
			}
		}
		else if(karta == Akcja.DYNAMIT){
			Gracz aktGracz = ListyCykliczne.nastepnyElement(gracze, wywolawca);
			int iloscGraczyPomiedzy = 0;
			int iloscGraczyPomiedzyPodejrzanych = 0;
			
			while(aktGracz.rola() != Rola.SZERYF){
				iloscGraczyPomiedzy++;
				if(licznik.podejrzany(aktGracz)){
					iloscGraczyPomiedzyPodejrzanych++;
				}
				aktGracz = ListyCykliczne.nastepnyElement(gracze, aktGracz);
			}
			
			if((iloscGraczyPomiedzy > 3) && ((3 * iloscGraczyPomiedzyPodejrzanych) >= (2 * iloscGraczyPomiedzy))){
				return wywolawca;
			}
			else{
				return null;
			}
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
	@Override
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		licznik.przyjmijInformacje(info, strzelec, cel);
	}
}