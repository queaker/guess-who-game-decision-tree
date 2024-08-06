package de.queaker.guesswho;

/*
 * 
 * Copyright (C) 2024 Sebastian Porombka
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

public enum PersonAttribute {

	FEMALE("Weiblich", "Female"),
	MALE("Männlich", "Male"),
	GLASSES("Brille", "Glasses"), 
	GLASSES_WHITE("Weiße Brille", "White Glasses"),
	
	BEARD ("Bart", "Beard"), 
	
	MOUSTACHE("Schnurrbart", "Moustache"), 
	
	SKIN_LIGHT("Helle Haut", "Light skin"),
	
	HAIR_BLACK("Schwarze Haare", "Black hair"),
	HAIR_BROWN("Braune Haare", "Brown hair"),
	HAIR_BLONDE("Blonde Haare", "Blonde hair"),
	HAIR_RED("Rote Haare", "Red hair"),
	HAIR_WHITE("Weiße Haare", "White hair"),
	HAIR_BLUE("Blaue Haare", "Blue hair"),
	HAIR_MULTICOLOR("Mehrfarbige Haare", "Multicoloured hair"),
	HAIR_BALD("Glatze","Bald head"),

	HAIR_SHOULDER_LENGTH("Schulterlange Haare", "Shoulder length hair"),
	HAIR_LONG("Lange Haare", "Long hair"),
	HAIR_SHORT("Kurze Haare", "Short hair"),

	EYEBROWS_WHITE("Weiße Augenbrauen", "White eyebrows"),
	
	EYES_GREEN("Grüne Augen", "Green eyes"),
	EYES_BROWN("Braune Augen", "Brown eyes"),
	EYES_BLUE("Blaue Augen", "Blue Eyes"),
	
	EAR_JEWELLERY("Ohrschmuck", "Ear jewellery"),
	
	HEADWEAR("Kopfbedeckung", "Headwear");

	String german;
	String english;

	PersonAttribute(String german, String english) {
		this.german = german;
		this.english = english;
	}
	
}
