package dzikizachod;

import java.util.List;

public class ListyCykliczne{
	private static <T> T elementZPrzesunieciem(List<T> l, T elem, int przesuniecie){
		int indeks = l.indexOf(elem);
		if(indeks == (-1)){
			return null;
		}
		return l.get((((indeks + przesuniecie) % l.size()) + l.size()) % l.size());
	}
	public static <T> T nastepnyElement(List<T> l, T elem){
		return elementZPrzesunieciem(l, elem, 1);
	}
	public static <T> T poprzedniElement(List<T> l, T elem){
		return elementZPrzesunieciem(l, elem, -1);
	}
}
