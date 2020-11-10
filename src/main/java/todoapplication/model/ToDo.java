package todoapplication.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A class representing a Todo. A Todo has text, a description of the task to be done and completed,
 * indicating if the Todo was completed. A Todo also has optional information detailing the ToDo's
 * due date, priority, and category.
 */
public class ToDo {
  private final String text;
  private boolean completed;
  private final LocalDate dueDate;
  private final int priority;
  private final String category;
  private final Integer id;

  /**
   * Creates a new ToDo object with the given ToDo Builder
   * @param builder - the ToDo builder
   */
  private ToDo (ToDoBuilder builder) {
    this.text = builder.text;
    this.completed = builder.completed;
    this.dueDate = builder.dueDate;
    this.priority = builder.priority;
    this.category = builder.category;
    this.id = builder.id;
  }

  public void markCompleted() {
    this.completed = true;
  }

  /**
   * Returns the text description for the ToDo
   * @return the text description for the ToDo
   */
  public String getText() {
    return this.text;
  }

  /**
   * Returns the completion status of the ToDo
   * @return the completion status
   */
  public boolean isCompleted() {
    return this.completed;
  }

  /**
   * Returns the ToDo's due date
   * @return the due date
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * Returns the priority of the ToDo (1 is highest and 3 is lowest)
   * @return the priority of the ToDo (1 is highest and 3 is lowest)
   */
  public int getPriority() {
    return this.priority;
  }

  /**
   * Returns the ID of the ToDo
   * @return the ID of the ToDo
   */
  public Integer getID() {
    return this.id;
  }

  /**
   * Returns the category of the ToDo
   * @return the category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * A static class representing a Builder to construct ToDo objects
   */
  public static class ToDoBuilder {
    private String text;
    private boolean completed;
    private LocalDate dueDate;
    private int priority;
    private String category;
    private Integer id;

    /**
     * Sets the ID of the ToDo Builder
     * @param id - the ID for the ToDo
     * @return a new ToDoBuilder object with the given ID
     */
    public ToDoBuilder id(final Integer id) {
      this.id = id;
      return this;
    }

    /**
     * Sets the text description of the ToDo Builder
     * @param text - the text description for the ToDo
     * @return a new ToDoBuilder object with the given text description
     */
    public ToDoBuilder text(final String text) {
      this.text = text;
      return this;
    }

    /**
     * Sets the completion status of the ToDo Builder
     * @param completed - the completion status of the ToDo
     * @return a new ToDoBuilder object with the given completion status
     */
    public ToDoBuilder completed(boolean completed) {
      this.completed = completed;
      return this;
    }

    /**
     * Sets the due date of the ToDo Builder
     * @param dueDate - the ToDo's due date
     * @return a new ToDoBuilder object with the given due date
     */
    public ToDoBuilder dueDate(LocalDate dueDate) {
      this.dueDate = dueDate;
      return this;
    }

    /**
     * Sets the priority of the ToDo Builder
     * @param priority - the ToDo's priority
     * @return a new ToDoBuilder object with the given priority
     */
    public ToDoBuilder priority(final int priority) {
      this.priority = priority;
      return this;
    }

    /**
     * Sets the category of the ToDo Builder
     * @param category - the ToDo's category
     * @return a new ToDoBuilder object with the given category
     */
    public ToDoBuilder category(final String category) {
      this.category = category;
      return this;
    }

    /**
     * Builds a new ToDo object
     * @return a new ToDo object
     */
    public ToDo build() {
      return new ToDo(this);
    }

  }

  /**
   * Determines if a ToDo is equal to the given object
   * @param o - the given object to compare the ToDo object with
   * @return true if the ToDo object is equal to the given object, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    ToDo that = (ToDo) o;
    return isCompleted() == that.isCompleted() &&
        this.getText().equals(that.getText()) &&
        this.getID().equals(that.getID()) &&
        Objects.equals(this.getCategory(), that.getCategory()) &&
        Objects.equals(this.getDueDate(), that.getDueDate()) &&
        Objects.equals(this.getPriority(), that.getPriority());
  }

  /**
   * Returns the hash code of a ToDo object
   * @return the hash code of a ToDo object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getText(), isCompleted(), getDueDate(), getPriority(), getCategory(),
        getID());
  }

  /**
   * Returns a string representation of a ToDo object
   * @return a string representation of a ToDo object
   */
  @Override
  public String toString() {
    return "ToDo ID: " + this.id +
        " Text: " + this.text +
        " Completed: " + this.completed +
        " Due Date: " + this.dueDate.toString() +
        " Priority: " + this.priority +
        " Category: " + this.category;
  }
}
