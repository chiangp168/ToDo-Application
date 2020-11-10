package todoapplication.model;

import java.util.ArrayList;
import java.util.List;
/**
 * A class representing a collection of ToDos. A collection of ToDos consists of an ArrayList
 * of ToDos and a count of ToDos stored. The creation of a CollectionOfToDos uses the Singleton
 * design pattern (the "lazy" allocation of instance).
 */
public class CollectionOfToDos {
  private static CollectionOfToDos collectionOfToDos = new CollectionOfToDos();
  private List<ToDo> toDos;
  private int numberOfToDos;
  private static final int OFFSET = 1;

  /**
   * Constructs an empty Collection of ToDos
   */
  private CollectionOfToDos() {
    this.toDos = new ArrayList<>();
    this.numberOfToDos = 0;
  }

  public static CollectionOfToDos getCollectionOfToDos() {
    return collectionOfToDos;
  }

  /**
   * Returns the list of ToDos
   * @return the list of ToDos
   */
  public List<ToDo> getToDos() {
    return this.toDos;
  }

  /**
   * Returns the number of ToDos in the collection
   * @return the number of ToDos in the collection
   */
  public int getNumberOfToDos() {
    return this.numberOfToDos;
  }

  /**
   * Sets the ID of the given ToDo and adds it to the Collection of ToDos
   * @param todo - the given ToDo to add to the collection
   */
  public void addToDo(ToDo todo) {
    this.numberOfToDos++;
    this.toDos.add(todo);
  }

  /**
   * Updates the ToDo with the given ID as completed
   * @param id - the ID of the ToDo to update
   * @throws IDNotFoundException when the given ID to update is not in the collection
   */
  public void updateToDoCompletion(Integer id) throws IDNotFoundException {
    // Check ID is in the collection
    int theToDoIDLocation = this.findID(id);
    if (theToDoIDLocation == -1) {
      throw new IDNotFoundException("ID not in collection");
    }
    ToDo toDoToUpdate = this.toDos.get(theToDoIDLocation);
    toDoToUpdate.markCompleted();
  }

  /**
   * Checks the list of ToDos for a ToDo with the given ID and returns its location in the ToDos
   * list.
   * @param id - the ID to look for
   * @return the index of where the ToDo with the given ID is located, -1 if the ID is not found.
   */
  private int findID(Integer id) {
      for (int i = 0; i < this.toDos.size(); i++) {
        if (this.toDos.get(i).getID().equals(id)) {
          return i;
        }
      }
    return -1;
  }

  /**
   * Resets the CollectionOfToDos to empty
   */
  public void reset() {
    this.toDos = new ArrayList<>();
    this.numberOfToDos = 0;
  }

  /**
   * Returns a string representation of a Collection of ToDos
   * @return a string representation of a Collection of ToDos
   */
  @Override
  public String toString() {
    return "CollectionOfToDos: \n" +
        "ToDos: " + this.toDos + "\n" +
        "Total Number of ToDos=" + this.numberOfToDos;
  }
}
