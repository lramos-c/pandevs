package org.generation.pandevs.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;



/*
 * Convertir una clase de Java en una entidad del modelo ER utilizando Spring Data JPA. Para ello necesitamos algunas anotaciones.
 * --- @Entity permite definir una clase como entidad
 * --- @Table(name = "name_table") permite definir el nombre de la tabla que se genera a partir de la entidad
 * */

@Entity
@Table(name = "users")
public class UserEntity {

    /*
     * Definimos las variables de instancia como columnas de la tabla. Hay que configurar las propiedades y valores de cada una de ellas
     * --- Tenemos que definir idUser como columna PK, AI
     * --- Tenemos que definir las siguientes columnas con sus propiedades específicas
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_User")

    private Long idUser;

    @Column(name = "username",length = 50,nullable = false,unique = true)
    private String username;

    @Column(name= "email",length = 100,nullable = false,unique = true)
    private String email;

    @Column(name = "password",length = 60,nullable = false,unique = false)
    private String password;

    public UserEntity(){

    }

    public UserEntity(Long idUser, String username, String email, String password, List<OrderEntity> orders) {
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.password = password;
        this.orders = orders;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(idUser, that.idUser) && Objects.equals(username, that.username) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, username, email, password, orders);
    }

    ///Relacion de User con Order 1:N
    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;


    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}


