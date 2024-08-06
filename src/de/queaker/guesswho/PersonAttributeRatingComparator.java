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

import java.util.Comparator;

public class PersonAttributeRatingComparator implements Comparator<PersonAttributeRating>{

	@Override
	public int compare(PersonAttributeRating o1, PersonAttributeRating o2) {
		return compare(o1.rating, o2.rating);
	}
	
	private static int compare(float a, float b) {
	    return a > b ? -1
	         : a < b ? 1
	         : 0;
	  }

}
