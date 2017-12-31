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

	@Override
	public Person findById(int id) {
		Person person = getByKey(id);
		return person;
	}

	/*
	 * SELECT P FROM Person p WHERE p.lastName EQUAL :lastName
	 */
	@Override
	public List<Person> findByLastName(String lastName) {

		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> p = query.from(Person.class);
		ParameterExpression<String> parameter = cb.parameter(String.class);
		query.select(p).where(cb.equal(p.get("lastName"), parameter));
		TypedQuery<Person> tq = getEntityManager().createQuery(query);
		tq.setParameter(parameter, lastName);
		List<Person> persons = tq.getResultList();
		return persons;

	}

	@Override
	public void savePerson(Person person) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePersonById(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Person> findAllPersons() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Person> query = cb.createQuery(Person.class);
		Root<Person> p = query.from(Person.class);
		query.select(p);
		TypedQuery<Person> tq = getEntityManager().createQuery(query);
		List<Person> persons = tq.getResultList();
		return persons;
	}

}
