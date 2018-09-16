package com.example.hotel_jpa.view;


import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.Client;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


import java.io.Serializable;
import java.util.List;

public class ClientViewController implements Serializable {

    @FXML
    private TableView<Client> table;
    private ObservableList<Client> clientData;

    @FXML
    private TableColumn<Client, String> firstNameColumn;

    @FXML
    private TableColumn<Client, String> lastNameColumn;

    @FXML
    private TableColumn<Client, String> passportColumn;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label patronymicLabel;

    @FXML
    private Label passportLabel;

    @FXML
    private Label commentLabel;

    @FXML
    private Button newBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    private App app;
    private Client currentClient;

    public ClientViewController() {
    }

    @FXML
    private void initialize(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        passportColumn.setCellValueFactory(cellData -> cellData.getValue().passportProperty());

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldclient, newClient) -> {
            this.setLabels(newClient);
        });

        newBtn.setOnAction( actionEvent -> {
            Client newClient = app.getNewClient();
            if (newClient != null)
                this.setLabels(newClient);
        });
    }

    public void setApp(App app) {
        this.app = app;
//
//        List<Client> clients = app.getClientData();
        clientData = app.getClientData();
        table.setItems(clientData);
        this.setLabels(clientData.get(0));

//        clients = table.getItems();

//        table.refresh();
    }

    public void setLabels(Client client){
        if (currentClient == client)
            return;
        currentClient = client;

        firstNameLabel.setText(client.getFirstName());
        lastNameLabel.setText(client.getLastName());
        patronymicLabel.setText(client.getPatronymic());
        passportLabel.setText(client.getPassport());
        commentLabel.setText(client.getComment());

    }
}
