package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Role;
import com.hakim.datauploder.model.User;
import com.hakim.datauploder.repository.RoleRepo;
import com.hakim.datauploder.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    public Role save(Role role){
        return roleRepo.save(role);
    }

    @Cacheable("role.getById")
    public Role getRole(long id){
        return roleRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Could not get role by id : "+id));
    }
    public List<Role> getAllRole(){
        return roleRepo.findAll();
    }
    public void addRoleToUser(long userId,long roleId){

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Could not get user by id : " + userId));
        Role role = roleRepo.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Could not get role by id : " + roleId));

        user.getRoles().add(role);
    }
}
