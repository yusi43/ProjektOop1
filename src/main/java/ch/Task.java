package ch;

import java.time.LocalDate;
import java.util.Locale.Category;

public class Task {
    private String title;
    private String description;
    private LocalDate dueDate;
    private Category category;

    public Task(String title, String description, Category category, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
         }
    }

