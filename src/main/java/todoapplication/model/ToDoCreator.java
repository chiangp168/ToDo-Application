package todoapplication.model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * A ToDo Creator class that has two methods, one to create a ToDo from a line in a CSV file
 * and another method to create a new ToDo with the specified fields.
 */
public class ToDoCreator {
  private static ToDoCreator toDoCreator = new ToDoCreator();
  private static final String NO_INFO_PROVIDED_CSV = "?";
  private static final String FALSE = "false";
  private static final String NONE = "None";
  private static final String SLASH = "/";
  private static final int NOT_PROVIDED = 3;

  /**
   * Constructs a new ToDoCreator class
   */
  private ToDoCreator() {
  }

  /**
   * Returns the ToDoCreator class instance
   * @return the ToDoCreator class instance
   */
  public static ToDoCreator getToDoCreator() {
    return toDoCreator;
  }

  /**
   * Creates a ToDo object from the given hash map of line data
   * @param line - the hash map containing the data
   * @return the ToDo object corresponding to the given data
   */
  public ToDo createToDoFromLine(HashMap<String, String> line) {
    return new ToDo.ToDoBuilder().id(this.setID(line.get("id")))
                                 .text(line.get("text"))
                                 .completed(this.determineCompletionStatus(line.get("completed")))
                                 .dueDate(this.setDueDate(line.get("due")))
                                 .priority(this.setPriority(line.get("priority")))
                                 .category(this.setCategory(line.get("category")))
                                 .build();
  }

  /**
   * Parses the String ID given and sets the ID of the ToDo
   * @param id - the ID data given (as a String)
   * @return the ID of the ToDo
   */
  private Integer setID(String id) {
    return Integer.parseInt(id);
  }

  /**
   * Determines the completion status of the ToDo with the given data, if the completion
   * status is not given, sets the completion status to false.
   * @param completionStatus - the completion status information given
   * @return the completion status of the ToDo
   */
  private boolean determineCompletionStatus(String completionStatus) {
    if (completionStatus == null || completionStatus.equals(NO_INFO_PROVIDED_CSV) || completionStatus.equals(FALSE)) {
      return false;
    } else if (completionStatus.equals(NO_INFO_PROVIDED_CSV)) {
        return false;
    } else {
      return true;
    }
  }

  /**
   * Sets the category of the ToDo, if the category information is not given, sets the category
   * to the default.
   * @param category - the category information
   * @return the category of the ToDo
   */
  private String setCategory(String category) {
    if (category == null || category.equals(NO_INFO_PROVIDED_CSV)) {
      return NONE;
    } else {
      return category;
    }
  }

  /**
   * Sets the priority of the ToDo, if the priority information is not given, set the priority to
   * the default
   * @param priority - the priority information
   * @return the priority of the ToDo
   */
  private int setPriority(String priority) {
    if (priority == null || priority.equals(NO_INFO_PROVIDED_CSV)) {
      return NOT_PROVIDED;
    } else {
      return Integer.parseInt(priority);
    }
  }

  /**
   * Sets the due date of the ToDo, if the due date is not given, sets the due date to the
   * default. Assumes date is given in the format: "mm/dd/yyyy"
   * @param due - the due date information
   * @return the due date of the ToDo
   */
  private LocalDate setDueDate(String due) {
    if (due == null || due.equals(NO_INFO_PROVIDED_CSV)) {
      // If no date is provided set due date to max date
      return LocalDate.MAX;
    } else {
      String[] dateInfo = due.split(SLASH);
      return LocalDate.of(Integer.parseInt(dateInfo[2]),
                          Integer.parseInt(dateInfo[0]),
                          Integer.parseInt(dateInfo[1]));
    }
  }

  /**
   * Creates a new ToDo with the specified text, completion status, due date, priority, and
   * category. Assigns the new ToDo object an ID. Sets missing values to defaults.
   * @param text - the text description of the ToDo
   * @param completed - the completion status of the ToDo
   * @param due - the due date of the ToDo
   * @param priority - the priority of the ToDo
   * @param category - the category of the ToDo
   * @return a ToDo object with the specified fields
   */
  public ToDo createNewToDo(String text, String completed, String due,
                            String priority, String category) {
    Integer idCountCurrent = (CollectionOfToDos.getCollectionOfToDos().getNumberOfToDos());
    // Find a new ID to assign the ToDo using the number of ToDos as a starting point
    idCountCurrent++;
    while (this.idTaken(idCountCurrent)) {
      idCountCurrent++;
    }
    return new ToDo.ToDoBuilder().id(idCountCurrent)
                                 .text(text)
                                 .completed(this.determineCompletionStatus(completed))
                                 .dueDate(this.setDueDate(due))
                                 .priority(this.setPriority(priority))
                                 .category(this.setCategory(category))
                                 .build();
  }

  /**
   * Determines if an ID is already assigned to a ToDo in the collection
   * @param id - the ID to check
   * @return true if a ToDo in the collection has the same ID, false otherwise
   */
  private boolean idTaken(Integer id) {
    for (int i = 0; i < CollectionOfToDos.getCollectionOfToDos().getNumberOfToDos(); i++) {
      if (CollectionOfToDos.getCollectionOfToDos().getToDos().get(i).getID().equals(id)) {
        return true;
      }
    }
    return false;
  }
}
