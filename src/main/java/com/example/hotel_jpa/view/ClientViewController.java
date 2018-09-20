package com.example.hotel_jpa.view;


import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


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

    @FXML
    private Button chooseBtn;

    @FXML
    private TextField searchField;

    private App app;
    private Client currentClient;
    private ClientServiceImpl service;
    private Client choosenClient;

    public ClientViewController() {
    }

    @FXML
    private void initialize(){
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        passportColumn.setCellValueFactory(cellData -> cellData.getValue().passportProperty());

        table.getSelectionModel().selectedItemProperty().addListener((observableValue, oldclient, newClient) -> {
            if (newClient != null)
                this.setLabels(newClient);
        });

        newBtn.setOnAction( actionEvent -> {
            Client newClient = app.getNewClient();
            if (newClient != null)
                this.setLabels(newClient);
        });

        deleteBtn.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedCells().isEmpty()){
                //TODO warning "first select some client"
                return;
            }
            Client client = table.getSelectionModel().getSelectedItem();
            clientData.removeAll(client);
            service.delete(client.getId());
        });

        editBtn.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedCells().isEmpty()){
                //TODO warning "first select some client"
                return;
            }

            Client client = table.getSelectionModel().getSelectedItem();
            app.editClient(client);
        });

        chooseBtn.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedCells().isEmpty()){
                //TODO warning "select something"
                return;
            }
            choosenClient = table.getSelectionModel().getSelectedItem();
            closeStage(e);
        });

        searchField.setOnAction(e -> {
            if (searchField.getText().isEmpty())
                table.setItems(clientData);
            else{
                String request = searchField.getText();
                ObservableList<Client> result = FXCollections.observableArrayList(service.getByRequest(request));
                table.setItems(result);
            }
        });
    }

    public void setApp(App app) {
        this.app = app;
//
//        List<Client> clients = app.getClientData();
        clientData = app.getClientData();
        service = app.getClientService();
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

    public Client getChoosenClient() {
        return choosenClient;
    }

    private void closeStage(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
