package com.example.hotel_jpa;

import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import com.example.hotel_jpa.view.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/*
 * Main controller, that coordinates other controllers
 */

@SpringBootApplication
public class App extends Application {

    //utils
    private ConfigurableApplicationContext context;
    private static String[] args;

    //entities
    private ObservableList<Client> clients;
    private ObservableList<Room> rooms;
    private ObservableList<CheckIn> checkins;

    //UI
    private Stage primaryStage;
    private BorderPane rootLayout;

    //services
    @Autowired
    private CheckInServiceImpl checkInService;
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private RoomServiceImpl roomService;

    public App() {}

    public static void main(String[] args) {
        App.args = args;
        launch(App.class, args);
    }

    @Override
    public void init(){
        context = SpringApplication.run(App.class, args);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    @Override
    public void start(Stage primaryStage) {
        clients = clientService.getAllObserved();
        rooms = roomService.getAllObserved();
        checkins = checkInService.getAllActive();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("HotelMy");

        initRootLayout();
        showCheckinOverview();
    }



    //UI methods --------------------------------------------------------

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/WrapMenu.fxml"));
            this.rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCheckinOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ChekinView.fxml"));
            AnchorPane checkinsOverview = loader.load();

            rootLayout.setCenter(checkinsOverview);

            CheckinViewController clientViewController = loader.getController();
            clientViewController.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //test methods -------------------------------------------(just for tests)--------------------

//    private void addSomeRooms(){
//        List<Room> list = Arrays.asList(
//                new Room("Cleopatra", 1, 1, Room.Comfortable.SUITE, 200),
//                new Room("Paris", 2, 2, Room.Comfortable.SUITE, 210),
//                new Room("Siesta", 3, 3, Room.Comfortable.HALF_SUITE, 130)
//        );
//
//        list.forEach(room -> roomService.addRoom(room));
//    }
//
//    private void addSomeClients(){
//        List<Client> list = Arrays.asList(
//          new Client("Eugen", "Krivulia", "Kostiantynovich", "UK123"),
//          new Client("Ivan", "Ivanov", "Ivanovich", "UK456"),
//          new Client("Alla", "Ivanova", "Ivanovna", "UK789"),
//          new Client("Petro", "Petrov", "Petrovich", "UK1234"),
//          new Client("Basil", "Petrov", "Basilovich", "UK7890"),
//          new Client("Maks", "Petrov", "Maksimovich", "UK5678")
//        );
//
//        list.forEach(client -> clientService.addClient(client));
//    }
//
//    private void addSomeCheckins(){
//        List<Room> listRoom = roomService.getAll();
//        List<Client> listClient = clientService.getAll();
//        List<CheckIn> checkIns = Arrays.asList(
//                new CheckIn(listRoom.get(0), LocalDate.now(), LocalDate.now().plusDays(1)),
//                new CheckIn(listRoom.get(1), LocalDate.now(), LocalDate.now().plusDays(2)),
//                new CheckIn(listRoom.get(2), LocalDate.now(), LocalDate.now().plusDays(3))
//        );
//
//        checkIns.get(0).addClient(listClient.get(0));
//
//        checkIns.get(1).addClient(listClient.get(1));
//        checkIns.get(1).addClient(listClient.get(2));
//
//        checkIns.get(2).addClient(listClient.get(3));
//        checkIns.get(2).addClient(listClient.get(4));
//        checkIns.get(2).addClient(listClient.get(5));
//
//        checkIns.forEach(c -> checkInService.addCheckIn(c));
//    }
//
//    private void showAll(){
//        clientService.getAll().forEach(System.out::println);
//        roomService.getAll().forEach(System.out::println);
//        checkInService.getAll().forEach(System.out::println);
//    }



    //controller getters ------------------------------------------------------------------------------

    public Stage                    getPrimaryStage() {
        return primaryStage;
    }
    public ObservableList<Client>   getClientData(){
        return clients;
    }
    public ObservableList<Room>     getRoomData(){
        return rooms;
    }
    public ObservableList<CheckIn>  getCheckinData(){return checkins;}
    public CheckInServiceImpl       getCheckInService() {
        return checkInService;
    }
    public ClientServiceImpl        getClientService() {
        return clientService;
    }
    public RoomServiceImpl          getRoomService() {
        return roomService;
    }



    //Controller methods ------------------------------------------------------

    public void createNewCheckin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/CheckinCreatorView.fxml"));
            CheckinCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client createNewClient() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientCreatorView.fxml"));
            ClientCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            return controller.getNewClient();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Room createNewRoom(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/RoomCreatorView.fxml"));
            RoomCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            return controller.getNewRoom();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void editClient(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientCreatorView.fxml"));
            ClientCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this, client);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editRoom(Room selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/RoomCreatorView.fxml"));
            RoomCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this, selectedItem);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Room newRoom= controller.getNewRoom();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client chooseClient() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientView.fxml"));
            ClientViewController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this, ClientViewController.Mods.CHOOSER);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            return controller.getChoosenClient();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void manageClient() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientView.fxml"));
            ClientViewController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this, ClientViewController.Mods.MANAGER);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void manageRoom() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/RoomView.fxml"));
            RoomViewController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRoomData() {
        rooms = roomService.getAllObserved();
    }

    public void updateCheckInData() {
        checkins = checkInService.getAllActive();
    }
}
