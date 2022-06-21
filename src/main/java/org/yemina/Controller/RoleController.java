package org.yemina.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yemina.Entities.Role;
import org.yemina.Repository.RoleRepository;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@CrossOrigin("*")
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    @PostMapping("/add")
    public Role addRole(Role role){
       return roleRepository.save(role);
    }

    @GetMapping ("/get")
	 Collection<Role> getAll() {
		return roleRepository.findAll();
	}

    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable String id){
		roleRepository.deleteById(id);
    }
}
