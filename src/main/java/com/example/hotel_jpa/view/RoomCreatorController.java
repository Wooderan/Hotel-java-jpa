package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.border.EmptyBorder;
import java.util.Arrays;

public class RoomCreatorController {

    @FXML
    private Button cancelBtn;

    @FXML
    private Button createBtn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField peoplesField;

    @FXML
    private ChoiceBox<Room.Comfortable> comfortableBox;

    @FXML
    private TextField priceField;

    private App app;
    private Room newRoom;
    private Room oldRoom;
    private ObservableList<Room> rooms;
    private RoomServiceImpl service;

    @FXML
    private void initialize(){

        ObservableList<Room.Comfortable> list =
                FXCollections.observableArrayList(
                        Room.Comfortable.SUITE,
                        Room.Comfortable.HALF_SUITE,
                        Room.Comfortable.REGULAR
                );
        comfortableBox.setItems(list);

        cancelBtn.setOnAction(e -> {
            closeStage(e);
        });

        createBtn.setOnAction(e -> {
            if (this.emptyFields()){
                //TODO warning "empty fields"
                return;
            }
            if (this.invalidFieldValues()){
                //TODO warning "invalid or empty fields"
                return;
            }

            if (oldRoom != null){//editing
                this.newRoom = this.grabRoom();
                service.editRoom(newRoom);
                int i = rooms.indexOf(oldRoom);
                rooms.remove(i);
                rooms.add(i, newRoom);
            }else {//creating
                //TODO add room number validation for uniq
                this.newRoom = this.grabRoom();

                service.addRoom(this.newRoom);
                rooms.add(newRoom);
            }

            closeStage(e);
        });
    }

    private Room grabRoom(){
        return new Room(
                this.nameField.getText(),
                Integer.parseInt(this.numberField.getText()),
                Integer.parseInt(this.peoplesField.getText()),
                this.comfortableBox.getValue(),
                Double.parseDouble(this.priceField.getText())
        );
    }

    private boolean emptyFields(){
        return  this.nameField.getText().isEmpty() ||
                this.numberField.getText().isEmpty() ||
                this.peoplesField.getText().isEmpty() ||
                this.comfortableBox.getSelectionModel().isEmpty() ||
                this.priceField.getText().isEmpty();
    }

    private boolean invalidFieldValues(){
        try {
            Integer.parseInt(this.numberField.getText());
            Integer.parseInt(this.peoplesField.getText());
            Double.parseDouble(this.priceField.getText());
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private void closeStage(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void setApp(App app) {
        this.app = app;
        this.rooms = app.getRoomData();
        this.service = app.getRoomService();
    }

    public void setApp(App app, Room selectedItem) {
        setApp(app);
        oldRoom = selectedItem;

        if (oldRoom != null){
            createBtn.setText("Edit...");
            nameField.setText(oldRoom.getName());
            numberField.setText(String.valueOf(oldRoom.getNumber()));
            peoplesField.setText(String.valueOf(oldRoom.getNumberOfPeoples()));
            comfortableBox.setValue(oldRoom.getComfortable());
            priceField.setText(String.valueOf(oldRoom.getPrice()));
            numberField.setDisable(true);
        }
    }

    public Room getNewRoom() {
        return null;
    }
}
