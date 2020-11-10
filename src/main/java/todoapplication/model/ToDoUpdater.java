package todoapplication.model;

/**
 * A class representing a ToDo Updater. A ToDo Updater has a method to update
 * the completion status of the ToDo object of the given ID. This class follows the Singleton
 * design pattern.
 */
public class ToDoUpdater {
  private static ToDoUpdater toDoUpdater;

  /**
   * Constructs a new ToDo Updater
   */
  private ToDoUpdater() {
  }

  /**
   * Returns the instance of the ToDoUpdater
   * @return the instance of the ToDoUpdater
   */
  public static synchronized ToDoUpdater getToDoUpdater() {
    if (toDoUpdater == null) {
      toDoUpdater = new ToDoUpdater();
    }
    return toDoUpdater;
  }

  /**
   * Updates the completion status (to true) of the the ToDo(s) of the given ID(s).
   * @param idsToUpdate - the Ids of the ToDo objects to update
   * @throws IDNotFoundException when an ID is given that is not associated with a ToDo object
   */
  protected void updateToDos(Integer[] idsToUpdate) throws IDNotFoundException {
    for (Integer id : idsToUpdate) {
      CollectionOfToDos.getCollectionOfToDos().updateToDoCompletion(id);
    }
  }

}
