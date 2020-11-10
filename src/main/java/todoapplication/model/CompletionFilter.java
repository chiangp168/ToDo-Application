package todoapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a filter that filters a Collection of ToDos by completion status.
 * Follows the Singleton design pattern (uses a "lazy" allocation)
 * */
public class CompletionFilter {
  private static CompletionFilter completionFilter;
  private boolean completionStatusToFilterOn;

  /**
   * Constructs a new CompletionFilter object
   */
  private CompletionFilter() {

  }

  /**
   * Returns the instance of the Completion Filter object
   * @return the instance of the Completion Filter object
   */
  public static synchronized CompletionFilter getCompletionFilter() {
    if (completionFilter == null) {
      completionFilter = new CompletionFilter();
    }
    return completionFilter;
  }

  /**
   * Returns the completion status that is being filtered on
   * @return the completion status that is being filtered on
   */
  public boolean getCompletionStatus() {
    return this.completionStatusToFilterOn;
  }

  /**
   * Sets the completion status to filter on
   * @param completionStatusToFilterOn - the completion status to filter on
   */
  public void setCompletionStatusToFilterOn(boolean completionStatusToFilterOn) {
    this.completionStatusToFilterOn = completionStatusToFilterOn;
  }

  /**
   * Filters the collection of ToDos on the completion status, returns a list of all ToDos from the
   * collection with the given completion status
   * @param toDos - the list of ToDos to filter
   * @return a list of all ToDos from the collection with the given completion status.
   */
  public List<ToDo> filter(List<ToDo> toDos) {
    List<ToDo> results = new ArrayList<>();
    for (ToDo todo : toDos) {
      if (todo.isCompleted() == this.completionStatusToFilterOn) {
        results.add(todo);
      }
    }
    return results;
  }

  /**
   * Returns a String representation of the Completion Filter object
   * @return a String representation of the Completion Filter object
   */
  @Override
  public String toString() {
    return "CompletionFilter: " +
        "completionStatusToFilterOn: " + this.completionStatusToFilterOn;
  }
}