package com.example.hotel_jpa.repositories;

import com.example.hotel_jpa.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
