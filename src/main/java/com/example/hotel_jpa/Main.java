package com.example.hotel_jpa;

import com.example.hotel_jpa.exceptions.NoSuchClientException;
import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.chekin.impls.CheckInService;
import com.example.hotel_jpa.services.client.impls.ClientService;
import com.example.hotel_jpa.services.rooms.impls.RoomService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        ClientService clientService = new ClientService();
        CheckInService checkInService = new CheckInService();

        List<Room> listRoom = Arrays.asList(
                new Room(1, 2, Room.Comfortable.SUITE, 190d),
                new Room(2, 2, Room.Comfortable.SUITE, 200d),
                new Room(3, 2, Room.Comfortable.HALF_SUITE, 130d),
                new Room(4, 1, Room.Comfortable.REGULAR, 90d),
                new Room(5, 1, Room.Comfortable.REGULAR, 70d)
        );

        listRoom.stream().forEach(room -> roomService.create(room));

        System.out.println("Rooms");
        roomService.getAll().stream().forEach(room -> System.out.println(room));

        System.out.println();
        System.out.println("Request: 1 people, Suit, 195 dollars");
        roomService.filter(1, Room.Comfortable.SUITE, 195d)
                .stream().forEach(room -> System.out.println(room));

        List<Client> listClient = Arrays.asList(
                new Client("Eugen", "Krivulia", "Kostyantynovich", "UK123456"),
                new Client("Ivan", "Ivanov", "Ivanovich", "UK23456"),
                new Client("Petro", "Petrov", "Petrovich", "UK345678")
        );

        listClient.stream().forEach(client -> clientService.create(client));

        System.out.println("\nClients");
        clientService.getAll().stream().forEach(client -> System.out.println(client));

        try {
            System.out.println("\nCurrent chekins");
            Client client = clientService.getClient("Krivulia").get(0);
            Room room = roomService.filter(1, Room.Comfortable.SUITE, 195d).get(0);
            checkInService.create(new CheckIn(client.getId(), room.getId(), LocalDate.now(), LocalDate.now().plusDays(3)));
            checkInService.getAll().stream().forEach(checkIn -> System.out.println(checkIn));
        } catch (NoSuchClientException e) {
            e.printStackTrace();
        }
    }
}
