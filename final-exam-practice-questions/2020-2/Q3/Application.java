
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.LinkedList;
import java.util.List;


public class Application {

	public RBTree<Integer, Person> createTree(List<Person> personList) {

		RBTree<Integer, Person> tree = new RBTree<>();
		if (personList == null || personList.size() == 0) return tree;
		// ########## YOUR CODE STARTS HERE ##########
		for (Person person : personList) {
			tree.insert(person.id, person);
		}
		
		// ########## YOUR CODE ENDS HERE ##########

		return tree;
	}

	public List<Person> readCsv(String fileName) {

		List<Person> list = new LinkedList<>();

		// ########## YOUR CODE STARTS HERE ##########
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				String[] cols = line.split(";");
				list.add(new Person(Integer.parseInt(cols[0]), cols[1], Integer.parseInt(cols[2]), cols[3]));

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}


		// ########## YOUR CODE ENDS HERE ##########

		return list;
	}

	public void saveXML(RBTree<Integer, Person> tree) {

		if (tree == null) {
			return;
		}

		List<Node<Integer, Person>> list =tree.levelTraversal();
		// ########## YOUR CODE STARTS HERE ##########
		File f = new File("people.xml");
		DocumentBuilderFactory db = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder documentBuilder = db.newDocumentBuilder();
			Document document = documentBuilder.newDocument(); // new document
			Element rootElem = document.createElement("people");
			document.appendChild(rootElem);
			for (Node<Integer, Person> node : list) {
				Element personElem = document.createElement("person");
				if (node.key == null) continue;

				Element idElem = document.createElement("id");
				idElem.appendChild(document.createTextNode(node.key.toString()));
				personElem.appendChild(idElem);

				Element nameElem = document.createElement("name");
				nameElem.appendChild(document.createTextNode(node.data.name));
				personElem.appendChild(nameElem);

				Element ageElem = document.createElement("age");
				ageElem.appendChild(document.createTextNode(node.data.age.toString()));
				personElem.appendChild(ageElem);

				Element occupationElem = document.createElement("occupation");
				occupationElem.appendChild(document.createTextNode(node.data.occupation));
				personElem.appendChild(occupationElem);

				rootElem.appendChild(personElem);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			// output property
			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ########## YOUR CODE ENDS HERE ##########
	}

}
