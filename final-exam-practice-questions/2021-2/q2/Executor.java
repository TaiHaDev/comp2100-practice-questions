import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Executor {

	private final List<Command> commands;

	private final DataBase db = DataBase.getInstance();

	public Executor(List<Command> commands) {
		this.commands = commands;
	}

	public void execute() {
		for (Command c : this.commands) {
			if (c instanceof LoadCommand) {
				loadFrom((LoadCommand) c);
			} else if (c instanceof SaveCommand) {
				saveTo((SaveCommand) c);
			}
		}
	}

	/**
	 * save the persons from the database to the xml file
	 * 
	 * @param sac
	 */
	private void saveTo(SaveCommand sac) {

		List<Person> persons = this.db.load(sac.getKey());

		File f = new File(sac.getFileName()); // reference to file path
		if (f.exists()) {
			f.delete();
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
            // the upper part should be same for every question #


			// usually we will have a list provided or as an argument.
			// Iterate through each element in that list.
			// Create a node and set the attribute of that based on the object attributes  for each element:
			Element rootElem = doc.createElement("persons");
			doc.appendChild(rootElem);
			for (Person person : persons) {
				Element personElement = doc.createElement("person");
				// these code is boilerplate code, you can change it by changing the element name and the attribute from the object in the list.
				Element nameElem = doc.createElement("name"); // name can be changed to any attribute name
				nameElem.appendChild(doc.createTextNode(person.getName())); // a text representation of the attribute
				personElement.appendChild(nameElem);

				Element genderElem = doc.createElement("gender");
				genderElem.appendChild(doc.createTextNode(person.getGender()));
				personElement.appendChild(genderElem);

				Element ageElem = doc.createElement("age");
				ageElem.appendChild(doc.createTextNode(String.valueOf(person.getAge())));
				personElement.appendChild(ageElem);

				Element occupationElem = doc.createElement("occupation");
				occupationElem.appendChild(doc.createTextNode(person.getOccupation()));
				personElement.appendChild(occupationElem);
				// remember to append this node to the root node that we created at first

				rootElem.appendChild(personElement);
			}

			// the lower part should be the same for every question
			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");


			 transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * load the persons from the xml file into the database
	 * 
	 * @param lc
	 */
	private void loadFrom(LoadCommand lc) {

		List<Person> persons = new LinkedList<>();

		File f = new File(lc.getFileName());
		if (!f.exists()) {
			return;
		}

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);

			doc.getDocumentElement().normalize();

			// TODO
			// ########## YOUR CODE STARTS HERE ##########
			NodeList nodeList = doc.getElementsByTagName("person");
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					Person curPerson = new Person();
					curPerson.setName(elem.getElementsByTagName("name").item(0).getTextContent());  // change "name" to string that used in the saving process, the rest should be the same
					curPerson.setGender(elem.getElementsByTagName("gender").item(0).getTextContent());
					curPerson.setOccupation(elem.getElementsByTagName("occupation").item(0).getTextContent());
					curPerson.setAge(Integer.parseInt(elem.getElementsByTagName("age").item(0).getTextContent()));
					persons.add(curPerson);
				}
			}
			// ########## YOUR CODE ENDS HERE ##########

			this.db.save(lc.getKey(), persons);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Command> getCommands() {
		return commands;
	}
}
