package com.github.zikl00.DU1.logika;

public class Slovicko {
	String ceskeSlovicko;
	String anglickeSlovicko;
	
	public Slovicko(String ceskeSlovicko, String anglickeSlovicko) {
		this.ceskeSlovicko = ceskeSlovicko;
		this.anglickeSlovicko = anglickeSlovicko;
	}
	public String getAngPreklad() {
		return anglickeSlovicko;
	}
	public String getCzPreklad() {
		return ceskeSlovicko;
	}
}