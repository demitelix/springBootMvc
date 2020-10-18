package template.service;

import java.util.List;

import template.model.Driver;

public interface CRUDinterface {
	public boolean create(Driver driver);
	
	public Driver read(int id);
	
	public boolean delete(int id);
	
	public List<Driver> readAll();

	public void update(int id, String name);
}
