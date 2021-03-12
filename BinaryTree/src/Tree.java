import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Tree {
	
		Person root = null;
		GridPane gridpane;
		
		public GridPane getGridpane() {
			return gridpane;
		}

		public void setGridpane(GridPane gridpane) {
			this.gridpane = gridpane;
		}

		public Person getRoot() {
			return root;
		}

		public void setRoot(Person root) {
			this.root = root;
		}

		public void addPerson(Person p) { 
			root = addPerson(root,p);

		}
		
		public void addPersonLastName(Person p) { 
			root = addPersonLastName(root,p);

		}
		
		public Person searchByFirstName(Person current, String firstName)
		{
			if(current == null)
				return null;
			
			
			//System.out.println(current.getFirstName());
			
			if(current.getFirstName().equals(firstName))
				return current;
			
			if(current.getFirstName().compareTo(firstName) >= 1)
				return searchByFirstName(current.getNameBefore(), firstName);
			
			return searchByFirstName(current.getNameAfter(), firstName);
		}
		
		public Person searchByLastName(Person current, String lastName)
		{	
			if(current == null)
				return null;
			//System.out.println(current.getFirstName());
			
			if(current.getLastName().equals(lastName))
				return current;
			
			if( current.getLastName().compareTo(lastName)>= 1)	
				return searchByLastName(current.getNameBefore(), lastName);	
			
			return searchByLastName(current.getNameAfter(), lastName);
		}

		public Person addPerson(Person current, Person newPerson) {
			
			if(current == null)
			{
				current = newPerson;
			}
			else
			{
				if(current.compareTo(newPerson) >= 1)
				{
					current.setNameBefore(addPerson(current.getNameBefore(), newPerson));
				}
				
				else
				{
					current.setNameAfter(addPerson(current.getNameAfter(), newPerson));
				}
				
			}	
			
			return current;

		}
		
		public Person addPersonLastName(Person current, Person newPerson) {
			
			if(current == null)
			{
				current = newPerson;
			}
			else
			{
				if(current.getLastName().compareTo(newPerson.getLastName()) >= 1)
				{
					current.setNameBefore(addPersonLastName(current.getNameBefore(), newPerson));
				}
				
//				if(current.getLastName().equals(newP.getLastName()))
//				{
//					return current.getFirstName().compareTo(newP.getFirstName());
//				}
				
				else
				{
					current.setNameAfter(addPersonLastName(current.getNameAfter(), newPerson));
				}
				
			}	
			
			return current;

		}
		
		public void searchFirstNameWithLength(Person current, int length, List<Person> results) {
			if(current != null) {
				if(current.getFirstName().length()== length) {
					results.add(current);
				}
				searchFirstNameWithLength (current.getNameBefore(), length, results);
				searchFirstNameWithLength (current.getNameAfter(), length, results);
			}
		}
		
		public void searchLastNameWithLength(Person current, int length, List<Person> resultsLast) {
			if(current != null) {
				if(current.getLastName().length()== length) {
					resultsLast.add(current);
				}
				searchLastNameWithLength (current.getNameBefore(), length, resultsLast);
				searchLastNameWithLength (current.getNameAfter(), length, resultsLast);
			}
		}
		
		public void firstNameLongerThanLastName(Person current, List<Person> firstNameLonger) {
			if(current != null) {
				if(current.getFirstName().length()> current.getLastName().length()) {
					firstNameLonger.add(current);
				}
				firstNameLongerThanLastName (current.getNameBefore(), firstNameLonger);
				firstNameLongerThanLastName (current.getNameAfter(), firstNameLonger);
			}
		}
		
		public void LastNameLongerThanFirstName(Person current, List<Person> LastNameLonger) {
			if(current != null) {
				if(current.getLastName().length()> current.getFirstName().length()) {
					LastNameLonger.add(current);
				}
				LastNameLongerThanFirstName (current.getNameBefore(), LastNameLonger);
				LastNameLongerThanFirstName (current.getNameAfter(), LastNameLonger);
			}
		}
		
		public void inOrderPrintNames(Person current, ArrayList<Text> textName) {
			if(current != null)
			{
				
				inOrderPrintNames(current.getNameBefore(), textName);
				Text nameT= new Text(current.getFirstName()+ " " + current.getLastName());
				textName.add(nameT);
				inOrderPrintNames(current.getNameAfter(), textName);
			}

		}
		
		public void prePrintNames(Person current, ArrayList<Text> textName) {
			if(current != null)
			{
				
				Text nameT= new Text(current.getFirstName()+ " " + current.getLastName());
				textName.add(nameT);
				prePrintNames(current.getNameBefore(), textName);
				prePrintNames(current.getNameAfter(), textName);
			}

		}
		
		public void postPrintNames(Person current, ArrayList<Text> textName) {
			if(current != null)
			{
				
				postPrintNames(current.getNameBefore(), textName);
				postPrintNames(current.getNameAfter(), textName);
				Text nameT= new Text(current.getFirstName()+ " " + current.getLastName());
				textName.add(nameT);
			}

		}
		
		public void breadthPrintName(Person current, ArrayList<Text> textName) {
			if (root == null) 
				return;
			ArrayList personList= new ArrayList();
			personList.add(root);
			personList.add(null);
			
//			
			while (personList.size() > 1) {
				if (personList.get(0) == null) // the delimiter
				{
					personList.add(null);
//					System.out.println("  ");
//			
				} 
				
				else {
					 current = (Person) personList.get(0);
					if (current.getNameBefore() != null) {
						personList.add(current.getNameBefore());
					}
					if (current.getNameAfter() != null) {
						personList.add(current.getNameAfter());
					}
//					System.out.print(current.getFirstName()+ " " + current.getLastName());
						Text tName = new Text(current.getFirstName() + " " + current.getLastName());
						textName.add(tName);
					
				}
				personList.remove(0);
			}
		}
		

		public void inOrderPrintAll() {

			ArrayList<Text> textNames = new ArrayList<Text>();

			this.inOrderPrintNames(root, textNames);
			
			
			int x = 0;
			int y = 0;
			for(int i=0; i<textNames.size(); i++)
			{
				if(i %10 == 0)
				{
					x = 0;
					y++;
				}
				gridpane.add(textNames.get(i), x + (i%10), y);
			}
		

		}
		
		public void prePrintNames() {
			
			ArrayList<Text> textNames = new ArrayList<Text>();
			
			this.prePrintNames(root, textNames);
			
			int x = 0;
			int y = 0;
			for(int i=0; i<textNames.size(); i++)
			{
				if(i %10 == 0)
				{
					x = 0;
					y++;
				}
				gridpane.add(textNames.get(i), x + (i%10), y);
			}
		

		}
		
		public void postPrintNames() {
			ArrayList<Text> textNames = new ArrayList<Text>();
			this.postPrintNames(root, textNames);
			
			int x = 0;
			int y = 0;
			for(int i=0; i<textNames.size(); i++)
			{
				if(i %10 == 0)
				{
					x = 0;
					y++;
				}
				gridpane.add(textNames.get(i), x + (i%10), y);
			}
		

		}
		
//		public void breadthPrint() {
//			ArrayList <Person> listP = new ArrayList <Person>();
//			ArrayList<Text> fNL= new ArrayList<Text>();
//			this.breadthPrintName(listP, fNL);
//			for(Person p: listP) {
//				Text a= new Text (p.getFirstName()+ " " + p.getLastName());
//				fNL.add(a);
//			}
//			int x = 0;
//			int y = 0;
//			for(int i=0; i<fNL.size(); i++)
//			{
//				if(i %10 == 0)
//				{
//					x = 0;
//					y++;
//				}
//				gridpane.add(fNL.get(i), x + (i%10), y);
//			}
//		}
		
		public void test()
		{
			Person p1 = new Person ("Neeha", "ABC");
			Person p2 = new Person ("Deepa", "ABC");
			Person p3 = new Person ("Pui-Li", "Lee");
			Person p4 = new Person ("Van","Le");
			Person p5 = new Person ("Ali", "Ahmed");
			Person p6 = new Person ("Michael", "Hommer");
			Person p7 = new Person ("Karsten", "L");
			addPerson(p1);
			addPerson(p2);
			addPerson(p3);
			addPerson(p4);
			addPerson(p5);
			addPerson(p6);
			addPerson(p7);
			
			
			
			Person pp = searchByFirstName(getRoot(), "Michael");
			if(pp != null)
				System.out.println("Found: " + pp);
			else
				System.out.println("Not found");
			
			
			//inOrderPrintAll();
			
		}
		
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			System.out.println("hey");
			
			(new Tree()).test();

		}

}