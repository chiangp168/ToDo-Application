package todoapplication.model;

import java.util.ArrayList;
import java.util.List;


/**
 * Main Model Class : Processor. Accepts inputs from the Controller and
 * executes necessary data changes
 * Follows Singleton design pattern (the lazy allocation).
 */
public class Processor {
  private static Processor processor;
  private CollectionOfToDos collectionOfToDos;
  private static String SPACE = " ";
  private static final String GIVEN = "Given";
  private List<ToDo> toDosToWorkWith;

  /**
   * Constructs a Processor object,implements the "lazy" allocation method.
   */
  private Processor() {
    this.collectionOfToDos = CollectionOfToDos.getCollectionOfToDos();
  }

  public static synchronized Processor getProcessor() {
    if (processor == null) {
      processor = new Processor();
    }
    return processor;
  }

  /**
   * Sets the ToDos to work with to the given list of ToDos
   * @param toDosToWorkWith - the given list of ToDos to work with
   */
  public void setToDosToWorkWith(List<ToDo> toDosToWorkWith) {
    this.toDosToWorkWith = toDosToWorkWith;
  }

  /**
   * Returns the list of ToDo's to work with
   * @return the list of ToDo's to work with
   */
  public List <ToDo> getToDosToWorkWith() {
    return this.toDosToWorkWith;
  }

  /**
   * Returns the collection of ToDos
   * @return the collection of ToDos
   */
  public CollectionOfToDos getCollectionOfToDos() {
    return collectionOfToDos;
  }

  /**
   * Adds a ToDo to the collection with the given specifications
   * @param text - the txt description of the ToDo (required)
   * @param completed - the completion status of the ToDo (optional)
   * @param due - the due date of the ToDo (optional)
   * @param priority - the ToDo's priority (optional)
   * @param category - the category of the ToDo (optional)
   */
  public void addTodo(String text, String completed, String due, String priority, String category) {
    ToDo newToDo = ToDoCreator.getToDoCreator().createNewToDo(text, completed,
        due, priority, category);
    CollectionOfToDos.getCollectionOfToDos().addToDo(newToDo);
  }

  /**
   * Updates the completion status of the given list of ToDo Ids
   * @param ids - a list of Ids of ToDo objects to update
   * @throws IDNotFoundException when an ID in the list is not contained in the Collection
   */
  public void updateCompletionStatusOfToDos(String ids) throws IDNotFoundException {
    String[] idsToUpdate = ids.split(SPACE);
    // Take the String list of IDs and produces the corresponding list of integer IDs
    Integer[] idsToUpdateAsIntegers = new Integer[idsToUpdate.length];
    for (int i = 0; i < idsToUpdate.length; i++) {
      idsToUpdateAsIntegers[i] = Integer.parseInt(idsToUpdate[i]);
    }
    // Update the list of ToDos
    ToDoUpdater.getToDoUpdater().updateToDos(idsToUpdateAsIntegers);
  }

  /**
   * Populates the Collection of ToDos with the rows of the given CSV filename
   * @param filename - the file path containing the rows of data to populate the Collection with
   * @throws FileErrorException when the given file path is invalid
   */
  public void populateCollectionOfToDosWithCSV(String filename)
      throws FileErrorException {
    List<String> csvLines = CSVFileReader.getCsvFileReader().readCSVFile(filename);
    CSVProcessor.getCsvProcessor().setCsvLines(csvLines);
    List<ToDo> toDosFromFile = CSVProcessor.getCsvProcessor().parseFile();
    this.populateCollectionOfToDos(toDosFromFile);
  }

  /**
   * Populates the collection of ToDos with the given list of ToDos
   * @param toDos - the list of ToDos to populate the Collection with
   */
  private void populateCollectionOfToDos(List<ToDo> toDos) {
    for (ToDo todo : toDos) {
      this.collectionOfToDos.addToDo(todo);
    }
  }

  /**
   * Helper Method to sort and filter as requested prior to display in the view
   * @param showIncomplete - command line indicator that the user wants to display only incomplete
   *                       ToDos
   * @param showCategory - command line indicator that the user wants to display only ToDos
   *                     of the specified category
   * @param sortByDate - command line indicator that the user wants to sort the ToDos by due date
   * @param sortByPriority -command line indicator that the user wants to sort the ToDos by priority
   */
  public void sortAndFilter(String showIncomplete, String showCategory,
      String sortByDate, String sortByPriority) {
    // Complete any requested filtering
    this.filter(showIncomplete, showCategory);
    // Complete any requested sorting
    this.sort(sortByDate, sortByPriority);
  }

  /**
   * Filters the ToDos in our collection according to the specified filter(s)
   * @param showIncomplete - optional argument flag whether incomplete ToDos need to be included
   *                      in the display
   * @param showCategory - optional argument flag whether a specific category of ToDos need to be
   *                     displayed
   */
  private void filter(String showIncomplete, String showCategory) {
    // Filter/ sort on entire Collection of ToDos unless a filter is applied
    processor.setToDosToWorkWith(processor.getCollectionOfToDos().getToDos());
    List<ToDo> filterResults;
    if (showIncomplete != null) {
      CompletionFilter.getCompletionFilter().setCompletionStatusToFilterOn(false);
      filterResults = CompletionFilter.getCompletionFilter()
                                      .filter(processor.getToDosToWorkWith());
      // Set ToDos to work with to reflect the filtered results
      processor.setToDosToWorkWith(filterResults);
    }
    if (showCategory != null) {
      CategoryFilter.getCategoryFilter().setCategoryToFilterOn(showCategory);
      filterResults = CategoryFilter.getCategoryFilter()
          .filter(processor.getToDosToWorkWith());
      // Set ToDos to work with to reflect the filtered results
      processor.setToDosToWorkWith(filterResults);
    }
  }

  /**
   * Sort method call on the model
   * @param sortByDate - optional argument flag to sort the ToDos list in the ascending
   *                   order of the due date.
   * @param sortByPriority - optional argument flag to sort the ToDos list in the ascending order
   *                       of priority
   */
  private void sort(String sortByDate, String sortByPriority) {
    if (sortByDate != null) {
      // sort
      DateSorter.getDateSorter().sortByDate(processor.getToDosToWorkWith());
    } else if (sortByPriority != null) {
      PrioritySorter.getPrioritySorter().sortByPriority(processor.getToDosToWorkWith());
    }
  }

  public void reset() {
    this.collectionOfToDos.reset();
    this.toDosToWorkWith = new ArrayList<>();
  }
}