package todoapplication.model;

/**
 * A class representing the checked exception that occurs when a given ID is not found in
 * the Collection
 */
public class IDNotFoundException extends Exception {

  /**
   * Constructs a new IDNotFoundException
   * @param message - a message informing the user that the ID of the ToDo they want to update
   *                cannot be found.
   */
  public IDNotFoundException(String message) {
    super(message);
  }

}
