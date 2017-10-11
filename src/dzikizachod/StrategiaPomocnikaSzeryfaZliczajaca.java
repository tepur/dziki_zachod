package dzikizachod;

import java.util.List;

class StrategiaPomocnikaSzeryfaZliczajaca extends StrategiaPomocnikaSzeryfa{
	StrategiaZliczajaca strZliczajaca = new StrategiaZliczajaca();
	@Override
	public Gracz podejmijDecyzje(Akcja karta, Gracz wywolawca, List<Gracz> gracze){
		Gracz cel = strZliczajaca.podejmijDecyzje(karta, wywolawca, gracze);
		if(cel != null){
			return cel;
		}
		return super.podejmijDecyzje(karta, wywolawca, gracze);
	}
	@Override
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		strZliczajaca.przyjmijInformacje(info, strzelec, cel);
	}
}