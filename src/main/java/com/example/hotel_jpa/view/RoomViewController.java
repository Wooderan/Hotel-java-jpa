package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RoomViewController {

    @FXML
    private TableView<Room> table;
    private ObservableList<Room> roomData;

    @FXML
    private TableColumn<Room, Integer> numberColumn;

    @FXML
    private TableColumn<Room, String> nameColumn;

    @FXML
    private TableColumn<Room, Integer> numberPeopleColumn;

    @FXML
    private TableColumn<Room, Room.Comfortable> comfortableColumn;

    @FXML
    private TableColumn<Room, Room.State> stateColumn;

    @FXML
    private TableColumn<Room, Double> priceColumn;

    @FXML
    private Button chooseBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button createBtn;

    private App app;
    private RoomServiceImpl service;
    private Room choosenRoom;

    public RoomViewController() {
    }

    @FXML
    private void initialize(){
        numberColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        nameColumn.setCellValueFactory(cellDate -> cellDate.getValue().nameProperty());
        numberPeopleColumn.setCellValueFactory(cellData -> cellData.getValue().numberOfPeoplesProperty().asObject());
        comfortableColumn.setCellValueFactory(cellData -> cellData.getValue().comfortableProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        chooseBtn.setOnAction(actionEvent -> {
            Room selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem == null){
                //TODO warning message "select some room first"
                return;
            }

            this.choosenRoom = selectedItem;
            close();
        });

        cancelBtn.setOnAction(actionEvent -> this.close());

        createBtn.setOnAction(e -> {
            app.getNewRoom();
        });

        editBtn.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedItem() == null) {
                //TODO warning "select something"
                return;
            }

            app.editRoom(table.getSelectionModel().getSelectedItem());
        });

        deleteBtn.setOnAction(e -> {
            if (table.getSelectionModel().isEmpty()){
                //TODO warning "select something"
                return;
            }

            Room r = table.getSelectionModel().getSelectedItem();
            service.delete(r.getId());
            roomData.remove(r);
        });
    }

    private void close(){
        //TODO some action to change anchorPane to checkins
    }

    public void setApp(App app){
        this.app = app;
        this.service = app.getRoomService();
        this.roomData = app.getRoomData();

        table.setItems(roomData);
    }
}
