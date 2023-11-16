package com.projects.blogapplication.services.impl;

import com.projects.blogapplication.Exceptions.ResourceNotFoundException;
import com.projects.blogapplication.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projects.blogapplication.payloads.UserDTO;
import com.projects.blogapplication.repositories.UserRepo;
import com.projects.blogapplication.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.UsertoDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDto, Integer userDtoId) {
        User user = this.userRepo.findById(userDtoId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userDtoId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepo.save(user);
        return this.UsertoDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userDtoId) {
        User user = this.userRepo.findById(userDtoId).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", userDtoId));

        return this.UsertoDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        return users.stream().map(this::UsertoDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userDtoId) {
        User user = this.userRepo.findById(userDtoId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userDtoId));
        this.userRepo.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }
    public UserDTO UsertoDTO(User user) {
        //        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return this.modelMapper.map(user,UserDTO.class);
    }
}
