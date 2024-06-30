package ch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private TaskManager taskManager = new TaskManager();

    @Override
    public void start(Stage primaryStage) {
        showLoginScreen(primaryStage);
    }

    private void showLoginScreen(Stage primaryStage) {
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 0);

        TextField nameField = new TextField();
        grid.add(nameField, 1, 0);

        Label passwordLabel = new Label("Passwort:");
        grid.add(passwordLabel, 0, 1);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);

        loginButton.setOnAction(event -> {
            String name = nameField.getText();
            String password = passwordField.getText();

            if (name.matches(".*\\d.*")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler");
                alert.setHeaderText("Ungültiger Name");
                alert.setContentText("Der Name darf keine Zahlen enthalten.");
                alert.showAndWait();
            } else if (password.length() < 6) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fehler");
                alert.setHeaderText("Ungültiges Passwort");
                alert.setContentText("Das Passwort muss mindestens 6 Zeichen lang sein.");
                alert.showAndWait();
            } else {
                showMainScreen(primaryStage);
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMainScreen(Stage primaryStage) {
        primaryStage.setTitle("To-Do Liste");

        taskManager.addCategory(new Category("Arbeit"));
        taskManager.addCategory(new Category("Privat"));

        ListView<Task> listView = new ListView<>();
        listView.setItems(javafx.collections.FXCollections.observableArrayList(taskManager.getTasks()));

        Button addButton = new Button("Aufgabe hinzufügen");
        addButton.setOnAction(event -> {
            TaskForm form = new TaskForm(taskManager, null);
            form.display();
            listView.setItems(javafx.collections.FXCollections.observableArrayList(taskManager.getTasks()));
        });

        ContextMenu contextMenu = new ContextMenu();
        MenuItem editItem = new MenuItem("Bearbeiten");
        editItem.setOnAction(event -> {
            Task selectedTask = listView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                TaskForm form = new TaskForm(taskManager, selectedTask);
                form.display();
                listView.setItems(javafx.collections.FXCollections.observableArrayList(taskManager.getTasks()));
            }
        });

        MenuItem deleteItem = new MenuItem("Löschen");
        deleteItem.setOnAction(event -> {
            Task selectedTask = listView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                taskManager.removeTask(selectedTask);
                listView.setItems(javafx.collections.FXCollections.observableArrayList(taskManager.getTasks()));
            }
        });

        contextMenu.getItems().addAll(editItem, deleteItem);
        listView.setContextMenu(contextMenu);

        VBox vbox = new VBox(listView, addButton);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
