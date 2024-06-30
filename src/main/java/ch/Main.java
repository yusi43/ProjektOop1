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
        Category category = new Category("Schule");
        Task task = new Task("Hausaufgaben", "Mathe machen", category, LocalDate.now().plusDays(1));
        System.out.println(task);
        System.out.println(category);
    }
}