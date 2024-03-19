package com.example.libraryproject;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.Objects;

public class HelloController {
    //Titutlo de la biblioteca
    @FXML private Text companyTheme;

    //Texto dedicado a los clientes
    @FXML private Text companySubTheme;

    //Titulo del ingreso del usuario
    @FXML private Label userText;

    //Titulo del ingreso de la contraseña
    @FXML private Label passwordText;

    //Titulo de inicio de sesion
    @FXML private Label loginText;

    //Ingreso del usuario
    @FXML private TextField userField;

    //Ingreso de la contraseña
    @FXML private PasswordField passwordField;

    //Boton para iniciar sesion
    @FXML private Button loginBotton;

    //Informacion de acceso
    private String accessUser = "sebas";
    private String accessPasword = "12345";

    //Metodo de arranque con los datos que se inician al comenzar el prgrama
    public void initialize() {
        companyTheme.setText("SO'SA LIBRARY'S");
        companySubTheme.setText("LA LECTURA ES EL CAMINO\nA LA SABIDURÍA");
        userText.setText("USER:");
        passwordText.setText("PASWORD:");
        loginText.setText("LOGIN");
    }

    //Accion del boton login
    @FXML
    private void setLoginBotton(ActionEvent event){
        if(userField.getText().equals(accessUser) && passwordField.getText().equals(accessPasword)){
            loadStage("registration.fxml", event);
        }else{
            userField.setText("");
            passwordField.setText("");
        }
    }

    //Metodo para realizar el cambio de ventana
    private void loadStage(String url, Event event){
        try{
            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window;
            stage.hide();

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(url)));
            Scene scene = new Scene(root, 1500, 700);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

            newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    Platform.exit();
                }
            });
        }catch(IOException ex){
        }
    }
}