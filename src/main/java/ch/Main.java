package ch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Locale.Category;
import java.io.IOException;

public class Main extends Application {
    private TaskManager taskManager = new TaskManager();

    @Override
    public void start(Stage primaryStage) {
        // Dummy categories
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
        primaryStage.setTitle("To-Do Liste");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
