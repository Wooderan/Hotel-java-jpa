package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;

public class CheckinViewController {

    @FXML
    private TableView<CheckIn> table;

    @FXML
    private TableColumn<CheckIn, String> clientColumn;

    @FXML
    private TableColumn<CheckIn, String> roomColumn;

    @FXML
    private TableColumn<CheckIn, LocalDate> settlementColumn;

    @FXML
    private TableColumn<CheckIn, LocalDate> releaseColumn;

    @FXML
    private TableColumn<CheckIn, String> noteColumn;

    @FXML
    private TableColumn<CheckIn, CheckIn.State> stateColumn;

    @FXML
    private Button registrationBtn;

    @FXML
    private Button bookingBtn;

    @FXML
    private Button settlementBtn;

    @FXML
    private Button releaseBtn;

    @FXML
    private Button cancelBookingBtn;

    @FXML
    private Button clientsBtn;

    @FXML
    private Button roomsBtn;

    private App app;
    private CheckInServiceImpl service;
    private ObservableList<CheckIn> chekins;

    public void setApp(App app) {
        this.app = app;
        service = app.getCheckInService();
        chekins = app.getCheckinData();
        table.setItems(chekins);
    }

    @FXML
    private void initialize(){
        clientColumn.setCellValueFactory(cellData ->cellData.getValue().clientsName() );
        roomColumn.setCellValueFactory(cellData ->cellData.getValue().roomsName() );
        settlementColumn.setCellValueFactory(cellData ->cellData.getValue().dateOfSettlementProperty() );
        releaseColumn.setCellValueFactory(cellData ->cellData.getValue().dateOfReleaseProperty() );
        noteColumn.setCellValueFactory(cellData ->cellData.getValue().noteProperty() );
        stateColumn.setCellValueFactory(cellData ->cellData.getValue().stateProperty());
    }
}
