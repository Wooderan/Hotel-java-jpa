package com.example.hotel_jpa.repositories;

import com.example.hotel_jpa.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRoomRepository extends JpaRepository<Room, Integer> {

    @Query("select r from Room r where r.number = :number")
    Room findByNumber(@Param("number") Integer number);
}
