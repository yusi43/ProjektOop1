package ch;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private Category category;
    private LocalDate dueDate;

    public Task(String title, String description, Category category, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return title + " (" + category.getName() + ") - Due: " + dueDate;
    }
}
