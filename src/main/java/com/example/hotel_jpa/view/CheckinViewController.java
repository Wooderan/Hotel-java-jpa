package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.CheckIn;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.models.Room;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class CheckinViewController {

    @FXML
    private TableView<CheckIn> table;

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

    @FXML
    private ListView<Client> clientsList;

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
        roomColumn.setCellValueFactory(cellData -> cellData.getValue().getRoom().fullName());
        settlementColumn.setCellValueFactory(cellData ->cellData.getValue().dateOfSettlementProperty() );
        releaseColumn.setCellValueFactory(cellData ->cellData.getValue().dateOfReleaseProperty() );
        noteColumn.setCellValueFactory(cellData ->cellData.getValue().noteProperty() );
        stateColumn.setCellValueFactory(cellData ->cellData.getValue().stateProperty());

        clientsList.setCellFactory(listView -> new ListCell<Client>(){
            @Override
            protected void updateItem(Client client, boolean empty) {
                super.updateItem(client, empty);
                if (empty)
                    setText(null);
                else
                    setText(client.getFirstName() + " " + client.getLastName());
            }
        });

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldCheckin, newCheckin) -> {
            ObservableList<Client> clients =FXCollections.observableArrayList(newCheckin.getClient());
            clientsList.setItems(clients);
        });

        registrationBtn.setOnAction(e -> {
            app.createNewCheckin();
        });
    }
}
