package com.example.hotel_jpa.repositories;

import com.example.hotel_jpa.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    @Query("select c from Client c where c.lastName = :name")
    Client findByLastName(@Param("name") String lastName);

    @Query("select c from Client c where c.passport like :passport")
    Client findByPassport(@Param("passport") String passport);

    @Query("select c from Client c where c.firstName like :name")
    List<Client> findAllByFirstName(@Param("name") String name);

    @Query("select c from Client c where c.lastName like :lastname")
    List<Client> findAllByLastName(@Param("lastname") String lastname);


}
