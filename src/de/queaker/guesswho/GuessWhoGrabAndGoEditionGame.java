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

import static de.queaker.guesswho.PersonAttribute.BEARD;
import static de.queaker.guesswho.PersonAttribute.EAR_JEWELLERY;
import static de.queaker.guesswho.PersonAttribute.EYEBROWS_WHITE;
import static de.queaker.guesswho.PersonAttribute.EYES_BLUE;
import static de.queaker.guesswho.PersonAttribute.EYES_BROWN;
import static de.queaker.guesswho.PersonAttribute.EYES_GREEN;
import static de.queaker.guesswho.PersonAttribute.FEMALE;
import static de.queaker.guesswho.PersonAttribute.GLASSES;
import static de.queaker.guesswho.PersonAttribute.GLASSES_WHITE;
import static de.queaker.guesswho.PersonAttribute.HAIR_BALD;
import static de.queaker.guesswho.PersonAttribute.HAIR_BLACK;
import static de.queaker.guesswho.PersonAttribute.HAIR_BLONDE;
import static de.queaker.guesswho.PersonAttribute.HAIR_BLUE;
import static de.queaker.guesswho.PersonAttribute.HAIR_BROWN;
import static de.queaker.guesswho.PersonAttribute.HAIR_MULTICOLOR;
import static de.queaker.guesswho.PersonAttribute.HAIR_RED;
import static de.queaker.guesswho.PersonAttribute.HAIR_SHOULDER_LENGTH;
import static de.queaker.guesswho.PersonAttribute.HAIR_WHITE;
import static de.queaker.guesswho.PersonAttribute.HEADWEAR;
import static de.queaker.guesswho.PersonAttribute.MALE;
import static de.queaker.guesswho.PersonNames.ALBERT;
import static de.queaker.guesswho.PersonNames.AMY;
import static de.queaker.guesswho.PersonNames.BEN;
import static de.queaker.guesswho.PersonNames.CARMEN;
import static de.queaker.guesswho.PersonNames.DANIEL;
import static de.queaker.guesswho.PersonNames.EMMA;
import static de.queaker.guesswho.PersonNames.ERIC;
import static de.queaker.guesswho.PersonNames.JAN;
import static de.queaker.guesswho.PersonNames.JOE;
import static de.queaker.guesswho.PersonNames.LAURA;
import static de.queaker.guesswho.PersonNames.LEO;
import static de.queaker.guesswho.PersonNames.LILY;
import static de.queaker.guesswho.PersonNames.LIZ;
import static de.queaker.guesswho.PersonNames.MIA;
import static de.queaker.guesswho.PersonNames.MIKE;
import static de.queaker.guesswho.PersonNames.NICK;
import static de.queaker.guesswho.PersonNames.OLIVIA;
import static de.queaker.guesswho.PersonNames.ROSI;
import static de.queaker.guesswho.PersonNames.SAM;
import static de.queaker.guesswho.PersonNames.SOFIA;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GuessWhoGrabAndGoEditionGame implements IGame {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(GuessWhoGrabAndGoEditionGame.class);
	
	public Map<PersonNames, Person> getPersons() {

		Map<PersonNames, Person> persons = new HashMap<>();
		
		// GENDER
		addPersonAttribute(persons,
				FEMALE, 
				SOFIA,
				LIZ,
				ROSI,
				LAURA,
				OLIVIA,
				LILY,
				MIA,
				AMY,
				CARMEN,
				EMMA
				);
		
		addPersonAttribute(persons,
				MALE,		
				ALBERT,
				SAM,
				LEO,
				BEN,
				NICK,
				ERIC,
				MIKE,
				DANIEL,
				JAN,
				JOE
			);
		
		// HAIR
//		addPersonAttribute(persons,
//				HAIR_LONG, 
//				LIZ,
//				ROSI,
//				LAURA,
//				OLIVIA,
//				LILY,
//				MIA,
//				EMMA,	
//				DANIEL);
//
//		addPersonAttribute(persons,
//				HAIR_SHORT, 
//				CARMEN,
//				SAM,
//				LEO,
//				BEN,
//				NICK,
//				ERIC,
//				JAN);		

		addPersonAttribute(persons,
				HAIR_SHOULDER_LENGTH, 
				LIZ,
				ROSI,
				LAURA,
				LILY,
				MIA,
				EMMA);
		
		// SKIN
//		addPersonAttribute(persons,
//				SKIN_LIGHT, 
//				SOFIA,
//				LIZ,
//				ROSI,
//				AMY,
//				EMMA,	
//				ALBERT,
//				BEN,
//				NICK,
//				ERIC,
//				MIKE,
//				DANIEL
//				);

		
		// FACIAL HAIR
		addPersonAttribute(persons,
				BEARD,		
				ALBERT,
				LEO,
				DANIEL,
				JAN,
				JOE
			);		
		
		// HAIR
		addPersonAttribute(persons,
				HAIR_BLACK, 
				LAURA,
				MIA,
				SAM,
				MIKE
				);

		addPersonAttribute(persons,
				HAIR_BLUE, 
				ERIC
				);
		
		
		addPersonAttribute(persons,
				HAIR_BROWN, 
				SOFIA,
				ROSI,
				LILY,
				BEN
				);

		addPersonAttribute(persons,
				HAIR_RED, 
				EMMA,
				DANIEL
				);		
		
		addPersonAttribute(persons,
				HAIR_WHITE, 
				LIZ,
				CARMEN,
				LEO
				);		

		addPersonAttribute(persons,
				HAIR_BLONDE, 
				NICK
				);	

		addPersonAttribute(persons,
				HAIR_MULTICOLOR, 
				AMY,
				OLIVIA,
				JAN
				);	

		// EYES
		addPersonAttribute(persons,
				EYES_GREEN, 
				SOFIA,
				LAURA,
				LILY,
				ALBERT,
				SAM,
				DANIEL				
				);
		
		addPersonAttribute(persons,
				EYES_BROWN, 
				OLIVIA,
				MIA,
				AMY,
				CARMEN,
				EMMA,
				LEO, BEN, NICK, MIKE, JAN, JOE
				
				);
		
		addPersonAttribute(persons,
				EYES_BLUE, 
				LIZ,
				ROSI,
				ERIC
				);

		addPersonAttribute(persons,
				HAIR_BALD, 
				ALBERT,
				JOE
			);
		
		
		addPersonAttribute(persons,
				EYEBROWS_WHITE, 
				JOE
			);

		addPersonAttribute(persons,
				GLASSES_WHITE, 
				ROSI
			);		
		
		
		// ACCESSOIRES 
		addPersonAttribute(persons,
				GLASSES, 
				LIZ,
				ROSI,
				AMY,
				ALBERT,
				BEN,
				JOE
				);

		addPersonAttribute(persons,
				EAR_JEWELLERY, 
				SOFIA,
				ROSI,
				LAURA,
				CARMEN,
				NICK,
				JAN,
				BEN
				);
		
		addPersonAttribute(persons,
				HEADWEAR, 
				LILY,
				SAM,
				MIKE
				);
		
		return persons;
		
	}
	
	
	
	private static void addPersonAttribute(Map<PersonNames, Person> persons, PersonAttribute personAttribute, PersonNames... personNames) {
	
		for (PersonNames pn : personNames) {
			if (persons.containsKey(pn)) {
				persons.get(pn).addAttribute(personAttribute);
			} else {
				Person p = new Person(pn, personAttribute);
				persons.put(pn, p);
			}
		}
				
	}

}
