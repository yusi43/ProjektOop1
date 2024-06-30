package ch;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskForm {
    private TaskManager taskManager;
    private Task task;

    public TaskForm(TaskManager taskManager, Task task) {
        this.taskManager = taskManager;
        this.task = task;
    }

    public void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Aufgabe bearbeiten");

        TextField titleInput = new TextField();
        titleInput.setPromptText("Titel");
        titleInput.setText(task != null ? task.getTitle() : "");

        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Beschreibung");
        descriptionInput.setText(task != null ? task.getDescription() : "");

        ComboBox<Category> categoryComboBox = new ComboBox<>();
        categoryComboBox.getItems().addAll(taskManager.getCategories());
        categoryComboBox.setValue(task != null ? task.getCategory() : null);

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Fälligkeitsdatum");
        dueDatePicker.setValue(task != null ? task.getDueDate() : null);

        Button saveButton = new Button("Speichern");
        saveButton.setOnAction(e -> {
            if (task == null) {
                taskManager.addTask(new Task(titleInput.getText(), descriptionInput.getText(), categoryComboBox.getValue(), dueDatePicker.getValue()));
            } else {
                task.setTitle(titleInput.getText());
                task.setDescription(descriptionInput.getText());
                task.setCategory(categoryComboBox.getValue());
                task.setDueDate(dueDatePicker.getValue());
            }
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleInput, descriptionInput, categoryComboBox, dueDatePicker, saveButton);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.showAndWait();
    }
}

