package todoapplication.view;

import java.util.List;
import java.util.Objects;

/**
 * A generic Display class. A Display has a generic List of data and a method to display the
 * data contents of the List.
 * @param <T> - the type of data to display
 */
public class Display<T> {
  private List<T> data;

  /**
   * Constructs a new Display object with the given data
   * @param data - the given data
   */
  public Display(List<T> data) {
    this.data = data;
  }

  /**
   * Displays the data by printing a report to the console
   * @throws NothingToDisplayException when the data list is empty
   */
  public void display() throws NothingToDisplayException {
    if (this.data.size() == 0) {
      throw new NothingToDisplayException("No data to display");
    }
    System.out.println(this.toString());
  }

  /**
   * Returns a string representation of the Display object
   * @return a string representation of the Display object
   */
  @Override
  public String toString() {
    return "Report: \n" +
        this.data.toString();
  }

  /**
   * Determines if a Display object is equal to the given object
   * @param o - the object to compare to the Display object
   * @return true if the object is equal to the Display object and false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    Display<?> display = (Display<?>) o;
    return data.equals(display.data);
  }

  /**
   * Returns the hash code of the Display object
   * @return the hash code of the Display object
   */
  @Override
  public int hashCode() {
    return Objects.hash(data);
  }
}
