package com.example.domain.repo;

import com.example.domain.entity.Client;
import com.example.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findByClient(Client client);

    @Query("select p from Order p left join fetch p.items where p.id  = :id")
    Optional<Order> findByIdFetchItems(@PathVariable("id") Integer id);
}
