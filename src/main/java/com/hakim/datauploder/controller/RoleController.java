package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.Role;
import com.hakim.datauploder.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Role role){

        Role savedRole = roleService.save(role);
        return ResponseEntity.ok(savedRole);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getById(@PathVariable long roleId){
        Role role = roleService.getRole(roleId);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<Role> allRole = roleService.getAllRole();
        return ResponseEntity.ok(allRole);
    }

    @GetMapping("/add-role-to-user")
    public ResponseEntity<?> addRoleToUser(@RequestParam long roleId,@RequestParam long userId){
        roleService.addRoleToUser(userId,roleId);
        return ResponseEntity.ok(Collections.singletonMap("message","Role is successfully added to user."));
    }
}
