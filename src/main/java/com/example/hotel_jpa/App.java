package com.example.hotel_jpa;

import com.example.hotel_jpa.models.Client;
import com.example.hotel_jpa.services.impls.CheckInServiceImpl;
import com.example.hotel_jpa.services.impls.ClientServiceImpl;
import com.example.hotel_jpa.services.impls.RoomServiceImpl;
import com.example.hotel_jpa.view.ClientCreatorController;
import com.example.hotel_jpa.view.ClientViewController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class App extends Application {

    private static String[] args;
    private ObservableList<Client> clients;

    //UI
    private Stage primaryStage;
    private BorderPane rootLayout;

    //services for access to DB
    @Autowired
    private CheckInServiceImpl checkInService;
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private RoomServiceImpl roomService;


    private ConfigurableApplicationContext context;

    public App() {
    }

    public static void main(String[] args) {

//        SandBoxClientController controller = ApplicationContextHolder.getContext().getBean(SandBoxClientController.class);
//        controller.makeSomeMagic();
        App.args = args;
        launch(App.class, args);
    }



    @Override
    public void init() throws Exception {
        context = SpringApplication.run(App.class, args);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    @Override
    public void start(Stage primaryStage) {
        clients = clientService.getAllObserved();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("HotelMy");

        initRootLayout();
        showPersonOverview();
    }

    private void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientView.fxml"));
            AnchorPane clientsOverview = loader.load();

            rootLayout.setCenter(clientsOverview);

            ClientViewController clientViewController = loader.getController();
            clientViewController.setApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/WrapMenu.fxml"));
            this.rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Client> getClientData(){
        return clients;
    }

    public Client getNewClient() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientCreatorView.fxml"));
            ClientCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Client newClient = controller.getNewClient();
            return newClient;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }

    public CheckInServiceImpl getCheckInService() {
        return checkInService;
    }

    public ClientServiceImpl getClientService() {
        return clientService;
    }

    public RoomServiceImpl getRoomService() {
        return roomService;
    }

    public void editClient(Client client) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/ClientCreatorView.fxml"));
            ClientCreatorController controller;
            Parent parent;
            parent = loader.load();
            controller = loader.getController();
            controller.setApp(this, client);
            Scene scene = new Scene(parent);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
