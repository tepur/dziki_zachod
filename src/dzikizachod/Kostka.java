package dzikizachod;

import java.util.Random;

public class Kostka{
	private int iloscOczek;
	private static Random LOS = new Random();
	
	public Kostka(int ilosc){
		iloscOczek = ilosc;
	}
	public int losujWynik(){
		return LOS.nextInt(iloscOczek) + 1;
	}
}
