package dzikizachod;

import java.util.Random;
import java.util.Set;

public class Zbiory{
	private static Random LOS = new Random();
	public static <T> T losowyElementZbioru(Set<T> s){
		if(!s.isEmpty()){
			int iloscPrzesuniec = LOS.nextInt(s.size());
			for(T elem : s){
				if(iloscPrzesuniec == 0){
					return elem;
				}
				iloscPrzesuniec--;
			}
		}
		return null;
	}
}
