package org.generation.pandevs.controller;

import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.generation.pandevs.DTO.UpdatePasswordUserDTO;
import org.generation.pandevs.exceptions.UserNotFoundException;
import org.generation.pandevs.model.UserEntity;
import org.generation.pandevs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserEntity> getUsers(){
        return this.userService.getAll();
    }

    @GetMapping("/{id}")
    public UserEntity getById(@PathVariable Long id){
        return this.userService.getById(id);
    }

    //Metodo para crear usuario mejorado
    @PostMapping
    public ResponseEntity<UserEntity> newUser(@Validated @RequestBody UserEntity user) {

        if(this.userService.getByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(this.userService.createUser(user));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        this.userService.deleteUser(id);
    }

    //Metodo para recuperar un user por email utilizando la query personalizada y la clase Response Entity
    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getByEmail(@PathVariable String email){
        UserEntity userByEmail = this.userService.getByEmail(email);
        if(userByEmail == null) {
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity<UserEntity>(userByEmail, HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user, @PathVariable Long id){
        try {
            return  ResponseEntity.ok(this.userService.updateUser(user,id));
        } catch (UserNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassword(@RequestBody UpdatePasswordUserDTO dto) {
        try {
            this.userService.updatePassword(dto);
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
