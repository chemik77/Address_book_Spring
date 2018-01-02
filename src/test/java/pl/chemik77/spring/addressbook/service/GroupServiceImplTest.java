package pl.chemik77.spring.addressbook.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pl.chemik77.spring.addressbook.dao.GroupDao;
import pl.chemik77.spring.addressbook.model.Group;

public class GroupServiceImplTest {

	@Mock
	GroupDao dao;

	@InjectMocks
	GroupServiceImpl groupService;

	@Spy
	List<Group> groups = new ArrayList<>();

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		groups = getGroupsList();
	}

	@Test
	public void findById() {
		Group group = groups.get(0);
		when(dao.findById(anyInt())).thenReturn(group);
		Assert.assertEquals(groupService.findById(group.getId()), group);
	}

	@Test
	public void findByName() {
		Group group = groups.get(0);
		when(dao.findByName(anyString())).thenReturn(group);
		Assert.assertEquals(groupService.findByName(anyString()), group);
	}

	@Test
	public void findAll() {
		when(dao.findAll()).thenReturn(groups);
		Assert.assertEquals(groupService.findAll(), groups);
	}

	@Test
	public void saveGroup() {
		doNothing().when(dao).saveGroup(any(Group.class));
		groupService.saveGroup(any(Group.class));
		verify(dao, atLeastOnce()).saveGroup(any(Group.class));
	}

	@Test
	public void deleteById() {
		doNothing().when(dao).deleteById(anyInt());
		groupService.deleteById(anyInt());
		verify(dao, atLeastOnce()).deleteById(anyInt());
	}

	@Test
	public void deleteByName() {
		doNothing().when(dao).deleteByName(anyString());
		groupService.deleteByName(anyString());
		verify(dao, atLeastOnce()).deleteByName(anyString());
	}

	private List<Group> getGroupsList() {
		Group g1 = new Group();
		g1.setId(1);
		g1.setName("TestGroup1");

		Group g2 = new Group();
		g2.setId(2);
		g2.setName("TestGroup2");

		groups.add(g1);
		groups.add(g2);
		return groups;
	}
}
