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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;


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
    private TextField totalField;

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
                this.clientsCount++;
                this.updateRooms();
            }
        });

        removeBtn.setOnAction(e -> {
            if (clientsList.getSelectionModel().isEmpty())
                return;

            clients.remove(clientsList.getSelectionModel().getSelectedItem());

            this.clientsCount--;
            this.updateRooms();
        });

        settlementDate.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (!this.checkDates())
                settlementDate.setValue(oldDate);
            else{
                this.updateRooms();
            }
        });

        releaseDate.valueProperty().addListener((observableValue, oldDate, newDate) -> {
            if (!this.checkDates())
                releaseDate.setValue(oldDate);
            else{
                this.updateRooms();
            }
        });

        stateChoice.setItems(FXCollections.observableList(Arrays.asList(
                CheckIn.State.ACTIVE,
                CheckIn.State.BOOKED
        )));
        stateChoice.getSelectionModel().select(0);

        placesField.textProperty().addListener((observableValue, oldS, newS) -> this.updateRooms());

        createBtn.setOnAction(e -> {
            if (!this.validate())
                return;

            Set<Client> clients = new HashSet<>(this.clientsList.getItems());
            Room selectedRoom = this.roomsTable.getSelectionModel().getSelectedItem();

            CheckIn newCheckin = new CheckIn(
                    clients,
                    selectedRoom,
                    this.settlementDate.getValue(),
                    this.releaseDate.getValue()
            );
            newCheckin.setState(this.stateChoice.getValue());

            this.checkInService.addCheckIn(newCheckin);
            this.chekins.add(newCheckin);

            if (newCheckin.getState() == CheckIn.State.ACTIVE)
                roomService.setState(selectedRoom, Room.State.BUSY);

            app.updateRoomData();
            this.closeStage(e);
        });
        cancelBtn.setOnAction(this::closeStage);

        roomsTable.getSelectionModel().selectedItemProperty().addListener((observableValue, room, t1) -> {
            this.updatePrice();
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
        releaseDate.setValue(LocalDate.now());

        rooms = new ArrayList<>(roomService.getAll());
        roomsTable.setItems(FXCollections.observableList(rooms));
        this.placesField.setText("1");
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

    private void updateRooms(){

        int clientsCount = this.clientsCount;
        LocalDate settelementDate = this.settlementDate.getValue();
        LocalDate releaseDate = this.releaseDate.getValue();
        int places = this.getPlaces();


        if (this.rooms == null)
            return;
        List<Room> roomList = new ArrayList<>(rooms);
        List<CheckIn> checkinList = app.getCheckinData();

        checkinList.forEach(checkIn -> {
            if ( !  ((settelementDate.compareTo(checkIn.getDateOfSettlement()) < 0
                            && releaseDate.compareTo(checkIn.getDateOfSettlement()) < 0)
                    ||
                    (settelementDate.compareTo(checkIn.getDateOfRelease()) > 0
                            && releaseDate.compareTo(checkIn.getDateOfRelease()) > 0)

            )){
                roomList.removeIf(room -> room.getId().equals(checkIn.getRoom().getId()));
            }

        });


        List<Room> finalList = new ArrayList<>();
        roomList.forEach(room -> {
            if (room.getNumberOfPeoples() >= places)
                finalList.add(room);
        });

        this.roomsTable.setItems(FXCollections.observableArrayList(finalList));

        this.updatePrice();
    }

    private void updatePrice(){
        Room selectedRoom = roomsTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null)
            return;

        Double price = 0d;
        int days = this.releaseDate.getValue().compareTo(this.settlementDate.getValue()) + 1;
        price = selectedRoom.getPrice() * days;
        this.totalField.setText(String.valueOf(price));
    }

    private int getPlaces() {

        if (this.placesField.getText().isEmpty()){
            this.placesField.setText("1");
            return 1;
        }

        try {
            int places = Integer.parseInt(this.placesField.getText());
            if (places < clientsCount){
                this.placesField.setText(String.valueOf(clientsCount));
                places = clientsCount;
            }
            return places;
        } catch (NumberFormatException ex) {
            this.placesField.setText("1");
            return 1;
        }
    }


    private boolean validate() {
        if (this.clientsCount <= 0){
            //TODO warning "need at least one client"
            System.out.println("validate() - need at least one client");
            return false;
        }

        if (this.releaseDate.getValue() == null){
            //TODO warning "release date is not set"
            System.out.println("validate() - release date is not set");
            return false;
        }

        if (this.roomsTable.getSelectionModel().getSelectedCells().isEmpty()){
            //TODO warning "no room is selected"
            System.out.println("validate() - no room is selected");
            return false;
        }

        return true;
    }

    private void closeStage(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
