package com.example.domain.repo;

import com.example.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "select c from Client c where c.name like :name")
    List<Client> findByName(@Param("name") String name);


    boolean existsByName(String name);

    @Query("delete from Client c where c.name = :name")
    @Modifying
    void deleteByName(@Param("name") String name);

    @Query("select c from Client c left join fetch c.orders where c.id = :id")
    Client findClientFetchOrder(@Param("id") Integer id);


}
