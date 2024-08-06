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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Person {

	Logger logger;
	
	PersonNames name;

	List<PersonAttribute> attributes = new ArrayList<PersonAttribute>();

	public Person(PersonNames name, PersonAttribute... attributes) {

		logger = LogManager.getLogger("Person " + name);
		this.name = name;
		addAttribute(attributes);
				
	}

	public void addAttribute(PersonAttribute... personAttribute) {

		for (PersonAttribute pa : personAttribute) {

			if (!attributes.contains(pa)) {
				attributes.add(pa);
				logger.trace("adding " + pa);
			} else {
				logger.trace("ignoring " + pa);
			}
		}

	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", attributes=" + attributes + "]";
	}

}
