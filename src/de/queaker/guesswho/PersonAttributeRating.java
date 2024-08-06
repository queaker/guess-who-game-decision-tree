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

import java.util.ArrayList;
import java.util.List;

public class PersonAttributeRating {

	public PersonAttribute personAttribute;
	public Float rating;
	public List<Person> yes;
	public List<Person> no;

	public PersonAttributeRating(PersonAttribute personAttribute, Float rating, ArrayList<Person> yes, ArrayList<Person> no) {
		super();
		this.personAttribute = personAttribute;
		this.rating = rating;
		this.yes = yes;
		this.no = no;
	}

	@Override
	public String toString() {
		return "PersonAttributeRating [personAttribute=" + personAttribute + ", rating=" + rating + "]";
	}

}
