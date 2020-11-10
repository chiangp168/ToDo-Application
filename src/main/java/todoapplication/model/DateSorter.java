package todoapplication.model;
import java.util.Comparator;
import java.util.List;

/**
 * A class that implements functionality to sort a collection of ToDos. This class utilizes
 * the singleton pattern (eager instantiation)
 */
public class DateSorter implements Comparator<ToDo> {
  private static DateSorter dateSorter;

  /**
   * Private constructor to instantiate the Sort by Date object
   */
  private DateSorter() {};

  /**
   * Returns the DateSorter sort by date object
   * @return the DateSorter sort by date object
   */
  public static DateSorter getDateSorter() {
    if (dateSorter == null) {
      dateSorter = new DateSorter();
    }
    return dateSorter;
  }

  /**
   * Compare method override for comparing on date
   * @param toDo1 first object to be compared
   * @param toDo2 second object to be compared
   * @return a negative integer, zero, or a positive integer as the first argument has priority
   * less than, equal to, or greater than the priority of the second.
   */
  @Override
  public int compare(ToDo toDo1, ToDo toDo2) {
    if(toDo2.getDueDate().isBefore(toDo1.getDueDate()) == true) {
      return 1;
    } else if (toDo2.getDueDate().isAfter(toDo1.getDueDate()) == true) {
      return -1;
    } else {
      return 0;
    }
  }

  /**
   * Given a list of ToDos, sorts the list by date
   * @param toDos - the list of ToDos to sort by due date
   * @return the sorted list of ToDos
   */
  public List<ToDo> sortByDate(List<ToDo> toDos) {
    toDos.sort(DateSorter.getDateSorter());
    return toDos;
  }

  @Override
  public String toString() {
    return "SortByDate";
  }
}

