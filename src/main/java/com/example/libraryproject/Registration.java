package com.example.libraryproject;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.Objects;

public class Registration {
    public int contador = 1;
    //Titutlo de la empresa
    @FXML private Text companyTheme;

    //Boton para cargar los elementos guardados en la tabla
    @FXML private Button buttonRefresh;

    //Boton para guardar los elementos de la tabla
    @FXML private Button buttonSave;

    //Boton para cerrar sesion
    @FXML private Button buttonSingOff;

    //Boton para agregar libro
    @FXML private Button buttonAdd;

    //Boton para modificar un libro
    @FXML private Button buttonModify;

    //Boton para eliminar un libro
    @FXML private Button buttonDelete;

    //Boton para registrar un prestamo de un libro
    @FXML private Button buttonLend;

    //Boton para buscar un libro en especifico
    @FXML private Button buttonToFind;

    //Definir variable tabla
    @FXML private TableView<Libro> table;
    private ObservableList<Libro> libritos = FXCollections.observableArrayList();
    private ObservableList<Libro> filtroLibritos = FXCollections.observableArrayList();

    //Definir variable RegistrationDate
    @FXML private TableColumn<Libro, String> registrationDateCol;
    @FXML private TextField registrationDateField;
    @FXML private Label registrationDateLabel;

    //Definir variable Title
    @FXML private TableColumn<Libro, String> titleCol;
    @FXML private TextField titleField;
    @FXML private Label titleLabel;

    ////Definir variable Author
    @FXML private TableColumn<Libro, String> authorCol;
    @FXML private TextField authorField;
    @FXML private Label authorLabel;

    //Definir variable PublicDate
    @FXML private TableColumn<Libro, String> publicDateCol;
    @FXML private TextField publicDateField;
    @FXML private Label publicDateLabel;

    //Definir variable ISBN
    @FXML private TableColumn<Libro, String> isbnCol;
    @FXML private TextField isbnField;
    @FXML private Label isbnLabel;

    //Definir variable Edition
    @FXML private TableColumn<Libro, String> editionCol;
    @FXML private TextField editionField;
    @FXML private Label editionLabel;

    //Definir variable Language
    @FXML private TableColumn<Libro, String> languageCol;
    @FXML private TextField languageField;
    @FXML private Label languageLabel;

    //Definir variable Volumes
    @FXML private TableColumn<Libro, String> volumesCol;
    @FXML private TextField volumesField;
    @FXML private Label volumesLabel;

    //Definir variable Loan People
    @FXML private TableColumn<Libro, String> loanPeopleCol;
    @FXML private TextField loanPeopleField;
    @FXML private Label loanPeopleLabel;

    //Definir variable Loan Date
    @FXML private TableColumn<Libro, String> loanDateCol;
    @FXML private TextField loanDateField;
    @FXML private Label loanDateLabel;

    //Definir variable Returned Date
    @FXML private TableColumn<Libro, String> returnedDateCol;
    @FXML private TextField returnedDateField;
    @FXML private Label returnedDateLabel;

    //Definir variable Status
    @FXML private TableColumn<Libro, String> statusCol;

    //Accion para cerrar sesion de la libreria
    @FXML
    public void setButtonSingOff(ActionEvent event){
        loadStage("hello-view.fxml", event);
    }

    //Accion para cargarlos elementos de la tabla
    @FXML
    private void setButtonRefresh(ActionEvent event){
        showElements(libritos,"Libros.txt");
    }

    //Accion para guardar los elementos de la tabla
    @FXML
    private void setButtonSave(ActionEvent event){
        saveElements(libritos, "Libros.txt");
    }

    //Metodo para no permitir campos vacios en los textFields
    private boolean areFieldsEmpty(TextField... fields){
        for(TextField field: fields){
            if(field.getText().isEmpty()){
                return true;
            }
        }
        return false;
    }

    //Metodo para guardar las cadenas de informacion
    public static void saveElements(ObservableList<Libro> libritos, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Libro libro : libritos) {
                String linea = libro.getRegistrationDate() + "," + libro.getTitle() + "," + libro.getAuthor() + "," + libro.getPublicDate() + "," +
                        libro.getIsbn() + "," + libro.getEdition() + "," + libro.getLanguage() + "," + libro.getVolumes() + "," + libro.getLoanPeople() +
                        "," + libro.getLoanDate() + "," + libro.getReturnedDate() + "," + libro.getStatus();
                writer.write(linea);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para mostrar la tabla con los valores
    public void showElements(ObservableList<Libro> libritos, String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linea;
            while((linea = reader.readLine()) != null){
                String[] elementos = linea.split(",");
                String registrationDate = elementos[0];
                String title = elementos[1];
                String author = elementos[2];
                String publicDate = elementos[3];
                String isbn= elementos[4];
                String edition = elementos[5];
                String language = elementos[6];
                String volumes = elementos[7];
                String loanPeople = elementos[8];
                String loanDate = elementos[9];
                String returnedDate = elementos[10];
                String status = elementos[11];

                if(contador==1){
                    inizialiceData();
                    contador++;
                }
                this.libritos.add(new Libro(registrationDate, title, author, publicDate, isbn, edition, language, volumes,
                        loanPeople, loanDate, returnedDate, status));
                this.table.setItems(libritos);
                this.table.refresh();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metodo para inicializar la asociacion de cada columna con su propiedad de la clase Libro
    private void inizialiceData(){
        registrationDateCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("registrationDate"));
        titleCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("author"));
        publicDateCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("publicDate"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("isbn"));
        editionCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("edition"));
        languageCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("language"));
        volumesCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("volumes"));
        loanPeopleCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("loanPeople"));
        loanDateCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("loanDate"));
        returnedDateCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("returnedDate"));
        statusCol.setCellValueFactory(new PropertyValueFactory<Libro, String>("status"));
    }

    //Accion para agregar nuevos libros
    @FXML
    public void setButtonAdd(){
        //Condicional para llamar al metodo en cada uno de los textFields
        if(!areFieldsEmpty(registrationDateField, titleField, authorField, publicDateField, isbnField, editionField, languageField,
                volumesField, loanPeopleField, loanDateField, returnedDateField)){
            //Condicional para que se pueda realizar, por una vez el ingreso de las nuevas tablas
            if(contador == 1){
                inizialiceData();
                contador++;
            }
            addInformatio();
            deleteContent();
        }
    }

    //Metodo para agregar un nuevo libro al observable y a la tabla
    private void addInformatio(){
        Libro libro = new Libro(registrationDateField.getText(), titleField.getText(), authorField.getText(), publicDateField.getText(),
                isbnField.getText(), editionField.getText(), languageField.getText(), volumesField.getText(), loanPeopleField.getText(),
                loanDateField.getText(), returnedDateField.getText(), "Activo");

        this.libritos.add(libro);
        this.table.setItems(libritos);
        this.table.refresh();
    }

    //Metodo para dejar en blanco los textos despues de un ingreso de libro
    private void deleteContent(){
        registrationDateField.setText("");
        titleField.setText("");
        authorField.setText("");
        publicDateField.setText("");
        isbnField.setText("");
        editionField.setText("");
        languageField.setText("");
        volumesField.setText("");
        loanPeopleField.setText("");
        loanDateField.setText("");
        returnedDateField.setText("");
    }

    //Metodo para seleccionar el libro a modificar o eliminar
    @FXML
    private void selection(MouseEvent event){
        if(this.table.getSelectionModel().getSelectedItem() != null){
            this.registrationDateField.setText(this.table.getSelectionModel().getSelectedItem().getRegistrationDate());
            this.titleField.setText(this.table.getSelectionModel().getSelectedItem().getTitle());
            this.authorField.setText(this.table.getSelectionModel().getSelectedItem().getAuthor());
            this.publicDateField.setText(this.table.getSelectionModel().getSelectedItem().getPublicDate());
            this.isbnField.setText(this.table.getSelectionModel().getSelectedItem().getIsbn());
            this.editionField.setText(this.table.getSelectionModel().getSelectedItem().getEdition());
            this.languageField.setText(this.table.getSelectionModel().getSelectedItem().getLanguage());
            this.volumesField.setText(this.table.getSelectionModel().getSelectedItem().getVolumes());
            this.loanPeopleField.setText(this.table.getSelectionModel().getSelectedItem().getLoanPeople());
            this.loanDateField.setText(this.table.getSelectionModel().getSelectedItem().getLoanDate());
            this.returnedDateField.setText(this.table.getSelectionModel().getSelectedItem().getReturnedDate());
        }
    }

    //Metodo para modificar un libro
    @FXML
    private void setButtonModify(ActionEvent event){
        if(this.table.getSelectionModel().getSelectedItem() != null){
            inizialiceData();

            Libro aux = new Libro(registrationDateField.getText(), titleField.getText(), authorField.getText(), publicDateField.getText(),
                    isbnField.getText(), editionField.getText(), languageField.getText(), volumesField.getText(), loanPeopleField.getText(),
                    loanDateField.getText(), returnedDateField.getText(), "Activo");

            this.table.getSelectionModel().getSelectedItem().setRegistrationDate(aux.getRegistrationDate());
            this.table.getSelectionModel().getSelectedItem().setTitle(aux.getTitle());
            this.table.getSelectionModel().getSelectedItem().setAuthor(aux.getAuthor());
            this.table.getSelectionModel().getSelectedItem().setPublicDate(aux.getPublicDate());
            this.table.getSelectionModel().getSelectedItem().setIsbn(aux.getIsbn());
            this.table.getSelectionModel().getSelectedItem().setEdition(aux.getEdition());
            this.table.getSelectionModel().getSelectedItem().setLanguage(aux.getLanguage());
            this.table.getSelectionModel().getSelectedItem().setVolumes(aux.getVolumes());
            this.table.getSelectionModel().getSelectedItem().setLoanPeople(aux.getLoanPeople());
            this.table.getSelectionModel().getSelectedItem().setLoanDate(aux.getLoanDate());
            this.table.getSelectionModel().getSelectedItem().setReturnedDate(aux.getReturnedDate());
            this.table.getSelectionModel().getSelectedItem().setStatus("Activo");

            this.table.refresh();
        }
    }

    //Metodo para eliminar un libro
    @FXML
    private void setButtonDelete(ActionEvent event){
        if(this.table.getSelectionModel().getSelectedItem() != null){
            this.libritos.remove(this.table.getSelectionModel().getSelectedItem());
            this.table.refresh();
        }
    }

    //Metodo para prestar un libro
    @FXML
    private void setButtonLend(ActionEvent event){
        if(this.table.getSelectionModel().getSelectedItem() != null) {
            inizialiceData();

            Libro aux = new Libro(registrationDateField.getText(), titleField.getText(), authorField.getText(), publicDateField.getText(),
                    isbnField.getText(), editionField.getText(), languageField.getText(), volumesField.getText(), loanPeopleField.getText(),
                    loanDateField.getText(), returnedDateField.getText(),"inactivo");

            this.table.getSelectionModel().getSelectedItem().setLoanPeople(aux.getLoanPeople());
            this.table.getSelectionModel().getSelectedItem().setLoanDate(aux.getLoanDate());
            this.table.getSelectionModel().getSelectedItem().setReturnedDate(aux.getReturnedDate());
            this.table.getSelectionModel().getSelectedItem().setStatus("Inactivo");

            this.table.refresh();
        }
    }

    //Metodo para buscar un libro en especifico
    @FXML
    private void setButtonToFind(ActionEvent event){
        if(this.titleField.getText().isEmpty()){
            this.table.setItems(libritos);
        }else{
            this.filtroLibritos.clear();
            for(Libro libross : this.libritos){
                if(libross.getTitle().toLowerCase().contains(this.titleField.getText().toLowerCase())){
                    this.filtroLibritos.add(libross);
                }
            }
            this.table.setItems(filtroLibritos);
        }
    }

    //Metodo que nos permite el cambio de ventana
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
