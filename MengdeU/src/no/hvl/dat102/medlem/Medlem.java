package no.hvl.dat102.medlem;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Medlem {
	private String navn;
	private MengdeADT<Hobby> hobbyer;
	private int statusIndeks;
	
	public Medlem(String navn) {
		this.navn = navn;
		hobbyer = new KjedetMengde<Hobby>();
		statusIndeks = -1;
	}
	
	public Medlem(String navn, int statusIndeks, MengdeADT<Hobby> hobbyer) {
		this.navn = navn;
		this.statusIndeks = statusIndeks;
		this.hobbyer = hobbyer;
	}
	
	public String getNavn() {
		return navn;
	}
	
	public void setNavn(String nyttNavn) {
		navn = nyttNavn;
	}
	
	public void leggTilHobby(Hobby hobby) {
		hobbyer.leggTil(hobby);
	}
	
	public void setHobbyer(MengdeADT<Hobby> hobbyMengde) {
		hobbyer = hobbyMengde;
	}
	
	public MengdeADT<Hobby> getHobbyer() {
		return hobbyer;
	}
	
	public int getStatusIndeks() {
		return statusIndeks;
	}
	
	public void setStatusIndeks(int nyIndeks) {
		statusIndeks = nyIndeks;
	}
	
	public boolean equals(Medlem medlem) {
		if (getNavn().equals(medlem.getNavn()))
			return true;
		
		return false;
	}
	
	public boolean passerTil(Medlem medlem2) {
		if (this.equals(medlem2))
			return false;
		else if (statusIndeks != -1 || medlem2.getStatusIndeks() != -1)
			return false;
		return hobbyer.equals(medlem2.getHobbyer());
	}
	
	public void printMedlem() {
		System.out.printf("Medlemsnavn: %s\nStatusindeks: &d\n"
				+ "Hobbyer:\n%s", navn, statusIndeks, hobbyer.toString());
	}
}
