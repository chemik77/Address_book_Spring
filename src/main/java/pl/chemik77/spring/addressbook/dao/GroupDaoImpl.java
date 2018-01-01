package pl.chemik77.spring.addressbook.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import pl.chemik77.spring.addressbook.model.Group;

@Repository("groupDao")
public class GroupDaoImpl extends AbstractDao<Integer, Group> implements GroupDao {

	/*
	 * SELECT g FROM Group g ORDER BY g.name ASC
	 */
	@Override
	public List<Group> findAll() {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Group> query = cb.createQuery(Group.class);
		Root<Group> g = query.from(Group.class);
		query.select(g);
		query.orderBy(cb.asc(g.get("name")));
		TypedQuery<Group> tq = getEntityManager().createQuery(query);
		List<Group> groups = tq.getResultList();
		return groups;
	}

	/*
	 * SELECT g FROM Group g WHERE g.name=?
	 */
	@Override
	public Group findByName(String name) {
		CriteriaBuilder cb = getCriteriaBuilder();
		CriteriaQuery<Group> query = cb.createQuery(Group.class);
		Root<Group> g = query.from(Group.class);
		ParameterExpression<String> parameter = cb.parameter(String.class);
		query.select(g).where(cb.equal(g.get("name"), parameter));
		TypedQuery<Group> tq = getEntityManager().createQuery(query);
		tq.setParameter(parameter, name);
		Group group = null;
		try {
			group = tq.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (NonUniqueResultException nure) {
			group = tq.getResultList().get(0);
		}
		return group;
	}

	/*
	 * SELECT g FROM Group g WHERE g.id=?
	 */
	@Override
	public Group findById(int id) {
		Group group = getByKey(id);
		return group;
	}

	/*
	 * INSERT INTO Group VALUES (group)
	 */
	@Override
	public void saveGroup(Group group) {
		persist(group);
	}

	/*
	 * DELETE FROM Group g WHERE g.id=?
	 */
	@Override
	public void deleteById(int id) {
		Group group = getByKey(id);
		delete(group);
	}

	/*
	 * DELETE FROM Group g WHERE g.name=?
	 */
	@Override
	public void deleteByName(String name) {
		Group group = findByName(name);
		delete(group);
	}

}
