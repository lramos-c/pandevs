package org.generation.pandevs.service;

import org.generation.pandevs.DTO.OrderDTO;
import org.generation.pandevs.exceptions.UserNotFoundException;
import org.generation.pandevs.model.OrderEntity;
import org.generation.pandevs.model.UserEntity;
import org.generation.pandevs.repository.OrderRepository;
import org.generation.pandevs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    //Instanciamos UserRepository para inyectarlo y poder usar un metodo
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;

    }

    //GetAllOrders()

    public List<OrderEntity> getOrders(){
        return  this.orderRepository.findAll();
    }

    //Crear una orden que este relacionado con un User (dto)
    //Crear un OrderDto para recibir los datos de la orden asociados al idUser
    public OrderEntity createOrder(OrderDTO dto){

        //Buscar el user mediante Id
        UserEntity user = this.userRepository.findById(dto.getIdUser()).orElseThrow(() -> new UserNotFoundException(dto.getIdUser()));

        //Crear la Orden con los atributos de DTO
        OrderEntity order = new OrderEntity();
        order.setDate(dto.getDate());
        order.setTotal(dto.getTotal());
        order.setUser(user);

        return this.orderRepository.save(order);
    }

}
