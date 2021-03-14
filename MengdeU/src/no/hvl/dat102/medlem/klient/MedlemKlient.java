package no.hvl.dat102.medlem.klient;

import no.hvl.dat102.medlem.Datakontakt;
import no.hvl.dat102.medlem.Tekstgrensesnitt;

public class MedlemKlient {
	public static void main(String[] args) {
		Datakontakt data = new Datakontakt();
		Tekstgrensesnitt tekstgr = new Tekstgrensesnitt(data);
		tekstgr.start();
	}
}
