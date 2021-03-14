package no.hvl.dat102.medlem;

import java.util.Scanner;

import no.hvl.dat102.mengde.adt.MengdeADT;
import no.hvl.dat102.mengde.kjedet.KjedetIterator;
import no.hvl.dat102.mengde.kjedet.KjedetMengde;

public class Tekstgrensesnitt {
	
	private static Datakontakt data;
	private static Scanner tastatur;
	private static final String AVSLUTT = "-1";
	private static final String KOMMANDOER = "0";
	private static final String LES_MEDLEM = "1";
	private static final String SKRIV_HOBBYLISTE = "2";
	private static final String SKRIV_PAR_LISTE = "3";
	
	public Tekstgrensesnitt(Datakontakt data) {
		this.data = data;
		tastatur = new Scanner(System.in);
	}
	
	public void start() {
		System.out.println("Tekstgrensesnitt for Medlemsdata\n"
				+ "Skriv '0' for listen av kommandoer:");
		
		String tekst = "";
		boolean running = true;
		
		while (running) {
			tekst = tastatur.nextLine();
			
			if (tekst.equals(AVSLUTT)) {
				running = false;
				tastatur.close();
			} else if (tekst.equals(KOMMANDOER)) {
				System.out.println("AVSLUTT = -1\nKOMMANDOER = 0\nLES MEDLEM = 1\n"
						+ "SKRIV HOBBYLISTE = 2\nSKRIV PAR LISTE = 3");
			} else if (tekst.equals(LES_MEDLEM)) {
				Medlem medlem = lesMedlem();
				data.leggTilMedlem(medlem);
			} else if (tekst.equals(SKRIV_HOBBYLISTE)) {
				tekst = tastatur.nextLine();
				
				try {
					skrivHobbyListe(data.getMedlem(tekst));
				} catch (NullPointerException e) {
					System.out.println("Personen eksisterer ikke i systemet.");
				}
			} else if (tekst.equals(SKRIV_PAR_LISTE)) {
				skrivParListe(data);
			} else {
				System.out.println("Ugyldig kommando.");
			}
		}
	}
	
	public static Medlem lesMedlem() {
		
		MengdeADT<Hobby> hobbyer = new KjedetMengde<Hobby>();
		Hobby hobby;
		String tekst = "";
		boolean running = true;
		
		System.out.println("Legger inn et nytt medlem\nSkriv navn:");
		tekst = tastatur.nextLine();
		Medlem medlem = new Medlem(tekst);
		System.out.println("Skriv inn hobbyene en etter en\n"
				+ "Skriv 'zzz' når du er ferdig:");
		
		while (running) {
			tekst = tastatur.nextLine();
			
			if (!tekst.equals("zzz")) {
				hobby = new Hobby(tekst);
				hobbyer.leggTil(hobby);
			} else {
				running = false;
			}
		}
		
		medlem.setHobbyer(hobbyer);
		System.out.println("Fullført");
		
		return medlem;
	}
	
	public static void skrivHobbyListe(Medlem medlem) {
		System.out.println("Hobbyer: " + medlem.getHobbyer().toString());
	}
	
	public static void skrivParListe(Datakontakt data) {
		Datakontakt kopi = data;
		String liste = "";
		String partner = "";
		Medlem m1;
		Medlem m2;
		Medlem[] medlemmer = kopi.getMedlemstabell();
		int antall = kopi.getAntall();
		
		for (int i = 0; i < antall; i++) {
			m1 = medlemmer[i];
			
			if (m1.getStatusIndeks() > i || m1.getStatusIndeks() == -1) {
				
				liste += m1.getNavn() + " og ";
				kopi.finnPartnerFor(m1.getNavn());
				if (m1.getStatusIndeks() != -1) {
					m2 = medlemmer[m1.getStatusIndeks()];
					partner = m2.getNavn();
				} else {
					partner = "(ingen partner)";
				}
				
				liste += partner;
			}
			System.out.println(liste);
		}
	}
}
