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
        Task task = new Task("Hausaufgaben", "Mathe machen", new Category("Schule"), LocalDate.now().plusDays(1));
        Category category = new Category("Schule");
        System.out.println(task);
        System.out.println(category);
    }
}