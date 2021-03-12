
public class Person implements Comparable <Person> {
	private String firstName; // add first name and last name
	private String lastName;
	private Person nameBefore;
	private Person nameAfter;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Person getNameBefore() {
		return nameBefore;
	}
	public void setNameBefore(Person nameBefore) {
		this.nameBefore = nameBefore;
	}
	public Person getNameAfter() {
		return nameAfter;
	}
	public void setNameAfter(Person nameAfter) {
		this.nameAfter = nameAfter;
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

	
	@Override
	public int compareTo(Person o) {
		// TODO Auto-generated method stub
		return this.getFirstName().compareTo(o.getFirstName());
	}
	
}
