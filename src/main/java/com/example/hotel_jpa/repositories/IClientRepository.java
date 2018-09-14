package com.example.hotel_jpa.repositories;

import com.example.hotel_jpa.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {
    @Query("select c from Client c where c.lastName = :name")
    Client findByLastName(@Param("name") String lastName);

    @Query("select c from Client c where c.passport = :passport")
    Client findByPassport(@Param("passport") String passport);
}
