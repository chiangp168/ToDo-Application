package todoapplication.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A class that implements functionality to sort a collection of ToDos. This class utilizes the
 * Singleton design pattern
 */
public class PrioritySorter implements Comparator<ToDo> {
  private static PrioritySorter prioritySorter;

  /**
   * Private constructor to instantiate the SortByPriority object
   */
  private PrioritySorter() { }

  /**
   * Returns the SortByPriority object
   * @return the SortByPriority object
   */
  public static synchronized PrioritySorter getPrioritySorter() {
    if (prioritySorter == null) {
      prioritySorter = new PrioritySorter();
    }
    return prioritySorter;
  }

  /**
   * Compares its two arguments for order.  Returns a negative integer, zero, or a positive integer
   * as the first argument is less than, equal to, or greater than the second.
   *
   * @param todo1 - the first object to be compared.
   * @param todo2 - the second object to be compared.
   * @return a negative integer, zero, or a positive integer as the first argument has a priority
   * less than, equal to, or greater than the priority of the second.
   * @throws NullPointerException if an argument is null and this comparator does not permit null
   *                              arguments
   * @throws ClassCastException   if the arguments' types prevent them from being compared by this
   *                              comparator.
   */
  @Override
  public int compare(ToDo todo1, ToDo todo2) {
    int difference = todo2.getPriority() - todo1.getPriority();
    if(difference < 0) {
      return -1;
    } else if (difference > 0) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Given a list of ToDos, sorts the list by priority
   * @param toDos - the list of ToDos to sort by priority
   * @return the sorted list of ToDos in ascending order
   */
  public List<ToDo> sortByPriority(List<ToDo> toDos) {
    toDos.sort(PrioritySorter.getPrioritySorter());
    return toDos;
  }

  /**
   * Returns a string representation of the SortByPriority object
   * @return a string representation of the SortByPriority object
   */
  @Override
  public String toString() {
    return "SortByPriority";
  }
}
