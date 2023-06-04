import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author nanwang
 *
The goal of this task is to write a program that loads/stores a list of students in XML format. `StudentCollection.java` class contains
a list of `Student` instances. Each student has his/her `age` and `name`, which need to be saved as attributes of XML node. Additionally,
each student can have three possible properties: `height`, `weight` and `courses`. `courses` property contains a list of `course` elements.
Each course has a course `name` attribute and a `grade` value. Note that not every student has all three properties. Some properties of students may be missing
(for example, see the test cases in StudentsTest.java). You job is to implement the below two methods in `StudentCollection.java`:

* `saveToFile`
* `loadFromFile`

Note that these methods should take into account the available properties of a given student. You are allowed to add additional asserts
and test cases to test your solutions. You can use `getAttribute(String name)` and `setAttribute(String name, String value)` of `Element`
class to get and set the attributes of XML node. **Please upload `StudentCollection.java` to wattle!**
 */
public class StudentCollection {

	private final List<Student> students;

	public StudentCollection(List<Student> students) {
		this.students = students;
	}

	public List<Student> getStudents() {
		return students;
	}

	/**
	 * Implement this method to save the list of students to XML file
	 * HINT: `setAttribute(String name, String value)` in `Element` can be used to set `name` and `age` properties
	 * @param file
	 */
	public void saveToFile(File file) {
		//START YOUR CODE
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element rootElem = doc.createElement("students");
			doc.appendChild(rootElem);
			for (Student student : students) {
				Element curElem = doc.createElement("student");

				Element ageElem = doc.createElement("age");
				ageElem.appendChild(doc.createTextNode(String.valueOf(student.getAge())));
				curElem.appendChild(ageElem);

				Element nameElem = doc.createElement("name");
				nameElem.appendChild(doc.createTextNode(String.valueOf(student.getName())));
				curElem.appendChild(nameElem);

				if (student.getHeight() != null) {
					Element heightElem = doc.createElement("height");
					heightElem.appendChild(doc.createTextNode(String.valueOf(student.getHeight())));
					curElem.appendChild(heightElem);
				}

				if (student.getWeight() != null) {
					Element weightElem = doc.createElement("weight");
					weightElem.appendChild(doc.createTextNode(String.valueOf(student.getWeight())));
					curElem.appendChild(weightElem);
				}

				if (student.getCourses() != null) {
					Element coursesElem = doc.createElement("courses");
					curElem.appendChild(coursesElem);
					for (Course course : student.getCourses()) {
						Element courseElem = doc.createElement("course");


						Element courseNameElem = doc.createElement("name");
						courseNameElem.appendChild(doc.createTextNode(course.getName()));
						courseElem.appendChild(courseNameElem);

						Element gradeElem = doc.createElement("grade");
						gradeElem.appendChild(doc.createTextNode(String.valueOf(course.getGrade())));
						courseElem.appendChild(gradeElem);

						coursesElem.appendChild(courseElem);
					}
				}
				rootElem.appendChild(curElem);
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();

			transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");


			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		} catch (ParserConfigurationException | TransformerException e) {
			throw new RuntimeException(e);
		}
		//END YOUR CODE
	}

	/**
	 * Implement this method to load from the XML file to create a `StudentCollection`
	 * HINT: `getAttribute(String name)`in `Element` can be used to get `name` and `age` properties
	 * @param file
	 * @return
	 */
	public static StudentCollection loadFromFile(File file) {
		//START YOUR CODE
		List<Student> parsedStudent = new LinkedList<>(); // more efficient than dynamic array :)
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			NodeList studentNode = doc.getElementsByTagName("student");
			for (int i = 0; i < studentNode.getLength(); i++) {
				Node node = studentNode.item(i);
				if (node instanceof Element) {
					Element elem = (Element) node;
					Student curStudent = new Student();
					curStudent = curStudent.withName(elem.getElementsByTagName("name").item(0).getTextContent());
					curStudent = curStudent.withAge(Integer.valueOf(elem.getElementsByTagName("age").item(0).getTextContent()));
					if (elem.getElementsByTagName("height").getLength() > 0) {
						curStudent = curStudent.withHeight(Integer.valueOf(elem.getElementsByTagName("height").item(0).getTextContent()));
					}
					if (elem.getElementsByTagName("weight").getLength() > 0) {
						curStudent = curStudent.withWeight(Integer.valueOf(elem.getElementsByTagName("weight").item(0).getTextContent()));
					}
					if (elem.getElementsByTagName("courses").getLength() > 0) {
						NodeList courseNode = elem.getElementsByTagName("course");
						for (int idx = 0; idx < courseNode.getLength(); idx++) {
							if (courseNode.item(idx) instanceof Element curElem) {
								Course curCourse = new Course(curElem.getElementsByTagName("name").item(0).getTextContent(),
										Integer.parseInt(curElem.getElementsByTagName("grade").item(0).getTextContent()));
								curStudent.addCourse(curCourse);
							}
						}
					}
					parsedStudent.add(curStudent);
				}
			}
		} catch (ParserConfigurationException | IOException | SAXException e) {
			throw new RuntimeException(e);
		}
		//END YOUR CODE
		return new StudentCollection(parsedStudent);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof StudentCollection) {
			StudentCollection studentCollection = (StudentCollection) o;
			return this.students.equals(studentCollection.students);
		}

		return false;
	}

	@Override
	public String toString() {
		return "StudentCollection{" +
				"students=" + students +
				'}';
	}
}
