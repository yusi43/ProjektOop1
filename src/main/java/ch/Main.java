package ch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Locale.Category;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.addTask(new Task("Hausaufgaben", "Mathe machen", new Category("Schule"), LocalDate.now().plusDays(1)));
        taskManager.addTask(new Task("Einkaufen", "Milch und Brot kaufen", new Category("Einkauf"), LocalDate.now().plusDays(2)));

        for (Task task : taskManager.getTasks()) {
            if (task.getTitle().contains("Haus")) {
                System.out.println(task);
            }
        }
    }
}
