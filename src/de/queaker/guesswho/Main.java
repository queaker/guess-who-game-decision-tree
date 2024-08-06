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

import java.io.PrintWriter;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

	private static final Logger logger = LogManager.getLogger("Main");

	private static IGame game;
	
	public static void main(String[] args) throws Exception {
				       
		// game type
		game = new GuessWhoGrabAndGoEditionGame();
		Map<PersonNames, Person> persons = game.getPersons();
		logger.info("Game Model: " + game.getClass());
		
		// solve
		Node root = new Node(null, persons.values(), PersonAttribute.values());
		
		// draw dot file
		PrintWriter writer = new PrintWriter("decision-tree.txt", "UTF-8");
		writer.println("digraph g {");
		writer.println("  margin=0 # in inches");		
		writer.println("  graph [pad=\"0.5\", nodesep=\"0.1\", ranksep=\"0\"];");
		
		draw(writer, root);
		writer.println("}");
		writer.flush();
		
		// run graphviz
		ShellExec exec = new ShellExec(true, true);
		String dir = System.getProperty("user.dir");
		exec.execute("/opt/homebrew/bin/dot", dir , true, "-Tpdf",  "decision-tree.txt", "-odecision-tree.pdf");
		logger.info("ExitCode: " + exec.getExitCode());
		logger.info("Output: " + exec.getOutput());
		logger.error("Error: " + exec.getError());
				
	}
	
	private static void draw(PrintWriter writer, Node node) {

		int id = node.id;
		
		if (node.person != null) {

			// node with person
			
			// html table with content
			StringBuffer label = new StringBuffer();
			label.append("<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" cellpadding=\"1\">");
			label.append("<tr> <td> " + node.person.name + " </td> </tr>");
			label.append("<tr> <td> <font point-size=\"8\">");
			
			for (int i = 0; i < node.person.attributes.size(); i++) {				
				label.append(node.person.attributes.get(i));
				
				// br in the middle
				if (i < node.person.attributes.size() - 0)
					label.append(" <br/>");
				
			}
			
			label.append("</font> </td> </tr>");
			label.append("</table>>");

			// node with html table
			writer.println("\""+ id + "\" "
					+ "[ "
					+   "shape = \"plain\" "
					+   "label = " + label.toString() + 					
					""
					+ " ];");
			
			return;

			
		} else {
		
			PersonAttribute attribute = node.attribute;

			// node with decision		
			writer.println("\""+ id + "\" "
					+ "[ "
					+   "shape = \"box\" "
					+   "label = \"" 
						+ attribute.german 
						+ "\n " + attribute.english 
//						+ "\n " + id 
//						+ " / " + "f: " + String.valueOf(node.bestRating.rating) 
					+	"\" "
					+ " ];");

			
			// yes-path
			writer.println("\""+ id + "\"" + " -> " + "\""+ node.yesDecision.id  + "\"" + " [label = \"Ja \n Yes \n ("+
					node.yesDecision.remainingPersons.size() +
			")\", color=darkgreen];");
			draw(writer, node.yesDecision);

			// no-path			
			writer.println("\""+ id + "\"" + " -> " + "\""+ node.noDecision.id + "\"" + " [label = \"Nein \n No \n ("+
					node.noDecision.remainingPersons.size() +
					")\", color=red];");			
			draw(writer, node.noDecision);
			
		}
		
	}	
	
}
