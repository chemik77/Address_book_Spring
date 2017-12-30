package pl.chemik77.spring.addressbook.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "person_group")
public class Group implements Serializable {

	private static final long serialVersionUID = -6387486392354125259L;

	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	@Size(min = 2, max = 30)
	@Column(name = "name", nullable = false)
	private String name;

	@ManyToMany(mappedBy = "groups")
	private List<Person> persons;

	public Group() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", persons=" + persons + "]";
	}

}
