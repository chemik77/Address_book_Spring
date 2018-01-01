package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
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
		return person;
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
	 * SELECT p FROM Person p ORDER BY p.lastName ASC
	 */
	@Override
	public List<Person> findAllPersons() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> p = query.from(Person.class);
		query.select(p);
		query.orderBy(cb.asc(p.get("lastName")));
		TypedQuery<Person> tq = getEntityManager().createQuery(query);
		List<Person> persons = tq.getResultList();
		return persons;
	}

}
