package pl.chemik77.spring.addressbook.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import pl.chemik77.spring.addressbook.model.Person;

@Repository("personDao")
public class PersonDaoImpl extends AbstractDao<Integer, Person> implements PersonDao {

	/*
	 * SELECT p FROM Person p WHERE p.id=?
	 */
	@Override
	public Person findById(int id) {
		Person person = getByKey(id);
		if (person != null ) {
			initializeCollection(person.getGroups());
		}
		return person;
	}
	
    private void initializeCollection(Collection<?> collection) {
        if(collection == null) {
            return;
        }
        collection.iterator().hasNext();
    }

	/*
	 * INSERT INTO Person VALUES (person)
	 */
	@Override
	public void savePerson(Person person) {
		persist(person);
	}

	/*
	 * DELETE FROM Person p WHERE p.id=?
	 */
	@Override
	public void deletePersonById(int id) {
		Person person = getByKey(id);
		delete(person);
	}

	/*
	 * SELECT p FROM Person p ORDER BY p.id ASC
	 */
	@Override
	public List<Person> findAllPersons() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> p = query.from(Person.class);
		query.select(p);
		query.orderBy(cb.asc(p.get("id")));
		TypedQuery<Person> tq = getEntityManager().createQuery(query);
		List<Person> persons = tq.getResultList();
		return persons;
	}

}
