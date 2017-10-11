package dzikizachod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Gracz{
	private static Random LOS = new Random();
	
	private Strategia strategia;
	private int liczbaZyc;
	private int maksLiczbaZyc;
	private int zasieg = 1;
	
	private boolean maDynamit = false;
	
	private List<Akcja> karty = new ArrayList<Akcja>(); 
	
	public Gracz(int maksLiczbaZyc){
		this.maksLiczbaZyc = maksLiczbaZyc;
		liczbaZyc = maksLiczbaZyc;
	}
	public Gracz(Strategia s, int maksLiczbaZyc){
		this(maksLiczbaZyc);
		strategia = s;
	}
	public boolean zywy(){
		return (liczbaZyc > 0);
	}
	public boolean maDynamit(){
		return maDynamit;
	}
	public int rzucKostka(Kostka kostka){
		return kostka.losujWynik();
	}
	public int iloscKart(){
		return karty.size();
	}
	public void wezKarte(Akcja karta){
		karty.add(karta);
	}
	public void otrzymajObrazenia(int obrazenia){
		liczbaZyc -= obrazenia;
	}
	public List<Akcja> karty(){
		return karty;
	}
	public void posortujKarty(){
		Collections.sort(karty);
	}
	public Gracz podejmijDecyzje(Akcja karta, List<Gracz> gracze){
		return strategia.podejmijDecyzje(karta, this, gracze);
	}
	public void oddajDynamit(){
		maDynamit = false;
	}
	public void wezDynamit(){
		maDynamit = true;
	}
	abstract public Rola rola();
	
	public int liczbaZyc(){
		return liczbaZyc;
	}
	public void wyrzucKarte(int i){
		karty.remove(i);
	}
	public void zwiekszZasieg(int i){
		zasieg += i;
	}
	public void ulecz(int i){
		liczbaZyc += i;
	}
	public int maksLiczbaZyc(){
		return maksLiczbaZyc;
	}
	public void przyjmijInformacje(Informacja info, Gracz strzelec, Gracz cel){
		strategia.przyjmijInformacje(info, strzelec, cel);
		
	}
	public int zasieg(){
		return zasieg;
	}
	public void zmienStrategie(Strategia nowaStrategia){
		strategia = nowaStrategia;
	}
	
	public static int losujLiczbeZyc(int min, int max){
		return LOS.nextInt(max - min + 1) + min;
	}
	public boolean moznaPodleczyc(int i){
		if((liczbaZyc + i) <= maksLiczbaZyc){
			return true;
		}
		return false;
	}
}
