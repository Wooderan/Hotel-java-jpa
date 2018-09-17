package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RoomCreatorController {

    @FXML
    private Button cancelBtn;

    private App app;
    private Room newRoom;
    private ObservableList<Room> rooms;
    private RoomServiceImpl service;

    @FXML
    private void initialize(){
        cancelBtn.setOnAction(e -> {
            closeStage(e);
        });
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

    public Room getNewRoom() {
        return null;
    }
}
