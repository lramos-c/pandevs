package org.generation.pandevs.service;

import org.generation.pandevs.DTO.UpdatePasswordUserDTO;
import org.generation.pandevs.exceptions.UserNotFoundException;
import org.generation.pandevs.model.UserEntity;
import org.generation.pandevs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Metodo para obtener todos los users
    public List<UserEntity> getAll() {
        return this.userRepository.findAll();
    }


    //Metodo para obetener un usuario por id
    public UserEntity getById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    //Metodo para obetener un usuario por email
    public UserEntity getByEmail(String email){
        return this.userRepository.getByEmail(email);
    }


    //Metodo para crear un nuevo usuario
    public UserEntity createUser(UserEntity newUser){
        return this.userRepository.save(newUser);
    }

    //Metodo para borrar un usuario
    public void deleteUser(Long id){
        if (this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        }
        else throw new UserNotFoundException(id);
    }

    // PUT -> Actualizando la entidad (Entity)
    public UserEntity updateUser(UserEntity user, Long id){
        return userRepository.findById(id).map( userMap -> {
            userMap.setUsername(user.getUsername());
            userMap.setEmail(user.getEmail());
            userMap.setPassword(user.getPassword());
            return userRepository.save(userMap);
        }).orElseThrow( () -> new UserNotFoundException(id));
    }

//    public Optional<Object> upsE(UserEntity user, Long id){
//        return userRepository.findById(id).map( userMap -> {
//            userMap.setUsername(user.getUsername());
//            userMap.setEmail(user.getEmail());
//            userMap.setPassword(user.getPassword());
//            return userRepository.save(userMap);
//        });
//    }

    // PATCH -> Actualizando algunos campos (DTO)
    // Crear dentro de service un package DTO
    public void updatePassword(UpdatePasswordUserDTO dto){
        if(!this.userRepository.existsById(dto.getIdUser())){
            throw   new UserNotFoundException(dto.getIdUser());
        }
        this.userRepository.updatePassword(dto.getIdUser(), dto.getPassword());
    }


}
