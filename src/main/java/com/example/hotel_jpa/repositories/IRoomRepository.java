package com.example.hotel_jpa.repositories;

import com.example.hotel_jpa.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.number = :number")
    Room findByNumber(@Param("number") Integer number);

    @Modifying
    @Query("update Room r set r.name = :name, r.numberOfPeoples = :peoples, r.comfortable = :comfortable," +
            " r.price = :price where r.number = :number")
    void updateRoom(
            @Param("number") Integer number,
            @Param("name") String name,
            @Param("peoples")Integer peoples,
            @Param("comfortable")Room.Comfortable comfortable,
            @Param("price")Double price
    );

    @Modifying
    @Query("update Room r set r.state = :state where r.id = :id")
    void setState(@Param("id") long id, @Param("state")Room.State state);


    List<Room> findByState(Room.State state);
}
