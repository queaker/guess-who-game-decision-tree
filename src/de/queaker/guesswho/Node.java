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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Node {

	Logger logger;

	private static int maximumId = 1;
	final int id = maximumId++;
	
	Node parent;

	List<Person> remainingPersons = new ArrayList<>();
	List<PersonAttribute> remainingPersonAttributes = new ArrayList<>();

	PersonAttribute attribute;
	PersonAttributeRating bestRating;

	Node yesDecision;
	Node noDecision;

	Person person;

	public Node(Node parent, List<Person> remainingPersons, List<PersonAttribute> remainingPersonAttributes) {

		logger = LogManager.getLogger("Decision " + id);

		// remember parent
		this.parent = parent;

		// copy list
		this.remainingPersons = new ArrayList<Person>(remainingPersons);
		this.remainingPersonAttributes = new ArrayList<PersonAttribute>(remainingPersonAttributes);

		// remaining
		logger.debug("Remaining Persons: (" + remainingPersons.size() + ")");
		for (Person p : remainingPersons) {
			logger.debug("  " + p);
		}
		
		// remainingPersons == 1?
		if (remainingPersons.size() == 1) {
			person = remainingPersons.get(0);
			logger.debug("Last Person: " + person);
			return;
		}

		// candidates
		List<PersonAttributeRating> personAttributeRating = computePersonAttributeCandidates();

		logger.debug("Person Attribute Ratings (sorted): (" + personAttributeRating.size() + ")");
		for (PersonAttributeRating r : personAttributeRating) {
			logger.debug("  " + r);
		}

		// fetch candidate
		bestRating = personAttributeRating.get(0);
		this.attribute = bestRating.personAttribute;
		yesDecision = new Node(this, bestRating.yes, remainingPersonAttributes);
		noDecision = new Node(this, bestRating.no, remainingPersonAttributes);
		

	}

	public Node(Node parent, Collection<Person> remainingPersons, PersonAttribute[] remainingPersonAttributes) {

		this(parent, new ArrayList<Person>(remainingPersons), Arrays.asList(remainingPersonAttributes));

	}

	// welches attribut teilt am besten in der mitte?
	private List<PersonAttributeRating> computePersonAttributeCandidates() {

		List<PersonAttributeRating> ratings = new ArrayList<>();

		logger.trace("chooseNextPersonAttribute");

		for (PersonAttribute paCandidate : remainingPersonAttributes) {

			logger.trace("  paCandidate:" + paCandidate);

			ArrayList<Person> yes = new ArrayList<Person>();
			ArrayList<Person> no = new ArrayList<Person>();

			for (Person p : remainingPersons) {
				if (p.attributes.contains(paCandidate))
					yes.add(p);
				else
					no.add(p);
			}

			logger.trace("    yes: " + yes.size());
			logger.trace("     no: " + no.size());

			if (yes.size() == 0 || no.size() == 0) {
				logger.trace("    invalid");
				continue;
			}

			float numberOfRemainingPersons = remainingPersons.size();
			float numberOfYesPersons = yes.size();
			float numberOfNoPersons = no.size();
			
			float yesFraction = numberOfYesPersons/ numberOfRemainingPersons;
			float noFraction = numberOfNoPersons/ numberOfRemainingPersons;
			
			float f = yesFraction * noFraction;
			logger.trace("      f: " + f);

			PersonAttributeRating r = new PersonAttributeRating(paCandidate, f, yes, no);
			ratings.add(r);

		}

		Collections.sort(ratings, new PersonAttributeRatingComparator());

		return ratings;

	}

	@Override
	public String toString() {
		return "Node [remainingPersons=" + remainingPersons + ", remainingPersonAttributes="
				+ remainingPersonAttributes + ", attribute=" + attribute + ", yesDecision=" + yesDecision
				+ ", noDecision=" + noDecision + ", person=" + String.valueOf(person) + ", id=" + id + "]";
	}

}
