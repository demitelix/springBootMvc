package template.storage;

import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import template.model.Driver;
import template.service.CRUDinterface;

@Component("storage")
public class DataStorage implements CRUDinterface {
	static int id=0;
	static List<Driver>base = new ArrayList<>();
	
	@PostConstruct
	private void baseFill(){
	base.add(new Driver(++id,"Dmitry"));
	base.add(new Driver(++id,"Milena"));
	base.add(new Driver(++id,"Olga"));
	base.add(new Driver(++id,"Fox"));
	}
	
	@Override
	public boolean create(Driver driver) {
		driver.setId(++id);
		base.add(driver);
		return true;
	}

	@Override
	public Driver read(int id) {
		Driver driver = null;
		for (Driver it:base) {
			if(it.getId().equals(id))
				return it;
		}
		
		return driver;
	}

	@Override
	public boolean delete(int id) {
		boolean status = false;
		Iterator it = base.iterator(); 
		while (it.hasNext()) 
        { 
            Driver x = (Driver)it.next();
            if (x.getId() == id) {
                it.remove();
                status = true;}
        } 
		//base.removeIf(p -> p.getId()==id);
		return status;
	}
	
	@Override
	public List<Driver> readAll() {
		return base;
	}
	
	public void update(int id, String name) {
		Driver driver = read(id);
		driver.setName(name);
		
	}
}
