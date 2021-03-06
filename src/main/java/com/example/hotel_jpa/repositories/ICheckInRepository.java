package com.example.hotel_jpa.repositories;

import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICheckInRepository extends JpaRepository<CheckIn, Long> {

    @Query("select ch from CheckIn ch where ch.client = :client")
    CheckIn findByClient(@Param("client") Client client);

    @Query("select ch from CheckIn ch where ch.room = :room")
    CheckIn findByRoom(@Param("room") Room room);

    @Modifying
    @Query("update CheckIn c set c.state = :state where c.id = :id")
    void setState(@Param("id") long id, @Param("state")CheckIn.State state);

    @Query("select ch from CheckIn ch where ch.state = :state")
    List<CheckIn> findByState(@Param("state") CheckIn.State state);
}
