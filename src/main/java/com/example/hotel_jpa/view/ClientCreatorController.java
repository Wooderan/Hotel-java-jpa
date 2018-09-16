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


    public void setApp(App app, Client client) {
        setApp(app);
        createBtn.setText("Edit");
        newClient = client;

        firstNameField.setText(newClient.getFirstName());
        lastNameField.setText(newClient.getLastName());
        patronymicField.setText(newClient.getPatronymic());
        passportField.setText(newClient.getPassport());
        commentField.setText(newClient.getComment());
    }

    @FXML
    private void initialize(){
        newClient = null;
        cancelBtn.setOnAction(e -> {
            closeStage(e);
        });

        createBtn.setOnAction(e -> {
            if (this.emptyFields()){
                //TODO warning "some fields are empty"
                return;
            }

            if (newClient != null){
                int i = clients.indexOf(newClient);
                clients.remove(i);
                service.delete(newClient.getId());

                newClient = new Client(
                        firstNameField.getText(),
                        lastNameField.getText(),
                        patronymicField.getText(),
                        passportField.getText());

                clients.add(i, newClient);
                service.addClient(newClient);
                closeStage(e);
                return;
            }


            if (service.getByPassport(passportField.getText()) != null){
                //TODO warning "user with such passport exists"
                return;
            }

            newClient = new Client(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    patronymicField.getText(),
                    passportField.getText());

            service.addClient(newClient);
            clients.add(newClient);
            closeStage(e);
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
