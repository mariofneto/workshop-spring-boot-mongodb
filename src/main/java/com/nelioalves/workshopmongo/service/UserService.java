package com.nelioalves.workshopmongo.service;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDto;
import com.nelioalves.workshopmongo.repository.UserRepository;
import com.nelioalves.workshopmongo.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        User user =  userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não " +
                "encontrado"));
        return user;
    }

    public User insert(User obj){
        return userRepository.insert(obj);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User update(User obj){
        User newUser = userRepository.findById(obj.getId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não " +
                "encontrado"));
        updateData(newUser, obj);
        return userRepository.save(newUser);
    }

    private void updateData(User newUser, User obj) {
        newUser.setName(obj.getName());
        newUser.setEmail(obj.getEmail());
    }

    public User fromDTO(UserDto objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
