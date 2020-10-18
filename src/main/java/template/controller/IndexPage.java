package template.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties.Storage;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import template.model.Driver;
import template.service.CRUDinterface;
import template.storage.DataStorage;

@Controller
public class IndexPage {
	
	@Autowired
	CRUDinterface crud;
	
	
	@GetMapping("/")
	public String main() {
		return "index";
		
	}
	public static String err;
	
	@GetMapping("/save")
	public String saveForm(@ModelAttribute("driver") Driver driver) 
	{
		return "saveform";	
	}
	
	@PostMapping("/saveresult")
	public String saveResult(@ModelAttribute("driver") Driver driver, Model model) 
	{
		if (driver.getName().equals("")) {
			model.addAttribute("toAns", "Error creating a driver with blank name");
		}
		else {
		model.addAttribute("toAns","Driver creation status is: "+crud.create(driver));
		}
		return "saveresult";
	}
	
	@GetMapping("/printall")
	public String printAll(Model model) {
	    model.addAttribute("all", crud.readAll());
	    return "printall";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		crud.delete(id);
		String status ="ok";
		model.addAttribute("status", status);
		return "deleted";
		
	}

	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		model.addAttribute("driver",crud.read(id));
		return "editform";	
	}
	
	@PutMapping("/{id}")
	public String edit(@PathVariable("id") int id, @ModelAttribute("driver") Driver driver) {
	
		crud.update(driver.getId(), driver.getName());
		return "redirect:/printall";
	}
	
}
