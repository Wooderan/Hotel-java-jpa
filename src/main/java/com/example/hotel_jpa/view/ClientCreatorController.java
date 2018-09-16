package com.example.hotel_jpa.view;

import com.example.hotel_jpa.App;
import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.Serializable;

public class ClientCreatorController implements Serializable {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField patronymicField;

    @FXML
    private TextField passportField;

    @FXML
    private TextArea commentField;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button createBtn;

    private App app;
    private Client newClient;
    private ObservableList<Client> clients;
    private ClientServiceImpl service;

    public void setApp(App app){
        this.app = app;
        this.clients = app.getClientData();
        this.service = app.getClientService();
    }

    @FXML
    private void initialize(){
        newClient = null;
        cancelBtn.setOnAction(e -> {
            closeStage(e);
        });

        createBtn.setOnAction(e -> {
            if (this.emptyFields()){
                return;
            }

            Client client = new Client(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    patronymicField.getText(),
                    passportField.getText());

            if (service.getByPassport(passportField.getText()) != null){
                //TODO
            }
        });
    }

    public Client getNewClient() {
        return newClient;
    }

    private void closeStage(ActionEvent e){
        Node node = (Node) e.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    private boolean emptyFields(){
        if (firstNameField.getText().isEmpty())
            return true;
        if (lastNameField.getText().isEmpty())
            return true;
        if (patronymicField.getText().isEmpty())
            return true;
        if (passportField.getText().isEmpty())
            return true;
        if (commentField.getText().isEmpty())
            return true;
        return false;
    }
}
