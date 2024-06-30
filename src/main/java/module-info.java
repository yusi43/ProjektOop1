module ch {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens ch to javafx.fxml;
    exports ch;
}
