package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CheckinCreatorController {
    private App app;
    private CheckInServiceImpl checkInService;
    private ClientServiceImpl clientService;
    private RoomServiceImpl roomService;
    private ObservableList<CheckIn> chekins;
    private ObservableList<Client> clients;
    private List<Room> rooms;
    private int clientsCount = 0;

    @FXML
    private Button createBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private ListView<Client> clientsList;

    @FXML
    private TableView<Room> roomsTable;

    @FXML
    private TableColumn<Room, String> roomColumn;

    @FXML
    private TableColumn<Room, Integer> placesColumn;

    @FXML
    private TableColumn<Room, String> comfortableColumn;

    @FXML
    private TableColumn<Room, Double> priceColumn;

    @FXML
    private DatePicker settlementDate;

    @FXML
    private DatePicker releaseDate;

    @FXML
    private ChoiceBox<CheckIn.State> stateChoice;

    @FXML
    private TextArea noteField;

    @FXML
    private TextField placesField;

    @FXML
    private void initialize(){
        clientsList.setCellFactory(clientListView -> new ListCell<Client>(){
            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);
                if (empty)
                    setText(null);
                else
                    setText(client.getFirstName() + " " + client.getLastName());
            }
        });

        roomColumn.setCellValueFactory(cellData -> cellData.getValue().fullName());
        placesColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfPeoplesProperty().asObject());
        comfortableColumn.setCellValueFactory(cellData -> cellData.getValue().comfortableProperty().asString());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        addBtn.setOnAction(e -> {
            Client newClient = app.chooseClient();
            if (newClient != null) {
                clients.add(newClient);
            }
            this.clientsCount++;
            this.updatePlaces();
        });

        removeBtn.setOnAction(e -> {
            if (clientsList.getSelectionModel().isEmpty())
                return;

            clients.remove(clientsList.getSelectionModel().getSelectedItem());

            this.clientsCount--;
            this.updatePlaces();
        });

        settlementDate.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (!this.checkDates())
                settlementDate.setValue(oldDate);
        });

        releaseDate.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (!this.checkDates())
                releaseDate.setValue(oldDate);
        });

        stateChoice.setItems(FXCollections.observableList(Arrays.asList(
                CheckIn.State.ACTIVE,
                CheckIn.State.BOOKED
        )));
        stateChoice.getSelectionModel().select(0);

//        placesField.setOnAction(e -> {
//            if (this.placesField.getText().isEmpty())
//                roomsTable.setItems(FXCollections.observableList(rooms));
//            else{
//
//                try {
//                    Integer places = Integer.parseInt(this.placesField.getText());
//                    if (places < this.clientsCount){
//                        this.placesField.setText(String.valueOf(this.clientsCount));
//                        return;
//                    }
//                    List<Room> newList = new ArrayList<>();
//                    this.rooms.forEach(room -> {
//                    if (room.getNumberOfPeoples() >= places)
//                            newList.add(room);
//                });
//                    roomsTable.setItems(FXCollections.observableArrayList(newList));
//                } catch (NumberFormatException ex) {
//                    roomsTable.setItems(null);
//                    return;
//                }
//
//            }
//
//        });
        placesField.textProperty().addListener((observableValue, oldS, newS) -> {
            if (this.placesField.getText().isEmpty())
                roomsTable.setItems(FXCollections.observableList(rooms));
            else{
                try {
                    Integer places = Integer.parseInt(this.placesField.getText());
                    if (places < this.clientsCount){
                        this.placesField.setText(oldS);
                        return;
                    }
                    List<Room> newList = new ArrayList<>();
                    this.rooms.forEach(room -> {
                        if (room.getNumberOfPeoples() >= places)
                            newList.add(room);
                    });
                    roomsTable.setItems(FXCollections.observableArrayList(newList));
                } catch (NumberFormatException ex) {
                    roomsTable.setItems(null);
                    return;
                }

            }

        });
    }

    public void setApp(App app){
        this.app = app;
        this.checkInService = app.getCheckInService();
        this.roomService = app.getRoomService();
        this.chekins = app.getCheckinData();
        this.clients = FXCollections.observableArrayList();

        clientsList.setItems(this.clients);

        settlementDate.setValue(LocalDate.now());

        rooms = new ArrayList<>(roomService.getAllFree());
        roomsTable.setItems(FXCollections.observableList(rooms));
    }

    private boolean checkDates() {
        LocalDate settlementPick = settlementDate.getValue();
        LocalDate releasePick = releaseDate.getValue();

        if (settlementPick.compareTo(LocalDate.now()) < 0){
            //TODO warning "settelement date must be current or later"
            System.out.println("warning settelement date must be current or later");
            return false;
        }

        if (releasePick != null && settlementPick.compareTo(releasePick) > 0){
            //TODO warning "wrong dates"
            System.out.println("wrong dates");
            return false;
        }
        return true;
    }

    private void updatePlaces(){

        if (this.placesField.getText().isEmpty())
            this.placesField.setText(String.valueOf(this.clientsCount));
        else{
            try {
                Integer places = Integer.parseInt(this.placesField.getText());
                if (places != this.clientsCount)
                    this.placesField.setText(String.valueOf(this.clientsCount));
            } catch (NumberFormatException ex) {
                roomsTable.setItems(null);
                return;
            }

        }
    }

}
