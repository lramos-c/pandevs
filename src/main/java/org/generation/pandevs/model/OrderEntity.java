package org.generation.pandevs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order",nullable = false)
    private Long idOrder;

    @Column(name = "date",nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(name = "total",nullable = false,columnDefinition = "DECIMAL(5,2)")
    private Double total;


    public OrderEntity(Long idOrder, LocalDateTime date, Double total,UserEntity user) {
        this.idOrder = idOrder;
        this.date = date;
        this.total = total;
        this.user = user;
    }

    public OrderEntity() {

    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }


    @Override
    public String toString() {
        return "OrderEntity{" +
                "idOrder=" + idOrder +
                ", date=" + date +
                ", total=" + total +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(idOrder, that.idOrder) && Objects.equals(date, that.date) && Objects.equals(total, that.total) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, date, total, user);
    }

    @ManyToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id_user")
    @JsonIgnore
    private UserEntity user;

    //Getter y Setter de la relacion


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

