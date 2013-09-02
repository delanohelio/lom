package com.nanuvem.lom.kernel;

import java.util.ArrayList;
import java.util.List;

import com.nanuvem.lom.kernel.dao.DaoFactory;
import com.nanuvem.lom.kernel.dao.EntityDao;

public class EntityService {

	private EntityDao dao;

	public EntityService(DaoFactory factory) {
		this.dao = factory.createEntityDao();
	}

	public void create(Entity entity) {
		dao.create(entity);
	}

	public List<Entity> listAll() {
		return dao.listAll();
	}

	public void remove(Entity entity) {
		dao.remove(entity);
	}

	public Entity update(String namespace, String name, Long id, Integer version) {
		return dao.update(namespace, name, id, version);
	}

	public Entity update(EntityDTO entityDTO) {
		return dao.update(entityDTO);
	}

	public Entity findEntityById(Long id) {
		return dao.findEntityById(id);
	}

	public List<Entity> listEntitiesByFragmentOfNameAndPackage(
			String namespaceFragment, String nameFragment) {
		return dao.listEntitiesByFragmentOfNameAndPackage(namespaceFragment,
				nameFragment);
	}

	public Entity readEntityByNamespaceAndName(String namespace, String name) {
		return dao.readEntityByNamespaceAndName(namespace, name);
	}
	
	public void deleteEntity(String namespaceAndName){
		dao.deleteEntity(namespaceAndName);
	}
}
