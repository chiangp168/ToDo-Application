package todoapplication;

import java.util.HashMap;
import todoapplication.model.FileErrorException;
import todoapplication.model.IDNotFoundException;
import todoapplication.model.Processor;
import todoapplication.model.ToDo;
import todoapplication.view.Display;
import todoapplication.view.NothingToDisplayException;

/**
 * A class in charge of controlling the program, directing actions for the Model
 * Singleton Pattern Design (uses "eager" allocation)
 */
public class Controller {
  private static Controller controller = new Controller();
  private HashMap<String, String> commandLineData;
  private Processor processor;
  private static final String GIVEN = "Given";

  /**
   * Constructs a new Controller
   */
  private Controller() { }

  /**
   * Eager Singleton instantiation
   * @return the controller object instance
   */
  public static Controller getController() {
    return controller;
  }

  /**
   * Set command line data which was passed in from the user
   * @param commandLineData command line data as a HashMap of command line flags as keys
   *                        and values of arguments passed in for the tags where applicable
   */
  private void setCommandLineData(HashMap<String, String> commandLineData) {
    this.commandLineData = commandLineData;
  }

  /**
   * Get instance of the Processor class to interact with the Model
   */
  private void setUpProcessor() {
    this.processor = Processor.getProcessor();
  }

  /**
   * Helper Method to update the completion status of ToDos if the complete flag is
   * passed through as an argument
   * @throws IDNotFoundException exception for id not found
   */
  private void update() throws IDNotFoundException {
    if (this.commandLineData.get("--complete-todo") != null) {
      this.processor.updateCompletionStatusOfToDos(this.commandLineData.get("--complete-todo"));
    }
  }

  /**
   * Helper method to coordinate sorting and filtering of the ToDos prior to display by the
   * display method
   */
  private void coordinateDisplay() throws NothingToDisplayException {
    if (this.commandLineData.get("--display").equals(GIVEN)) {
      this.processor.sortAndFilter(this.commandLineData.get("--show-incomplete"),
                                   this.commandLineData.get("--show-category"),
                                   this.commandLineData.get("--sort-by-date"),
                                   this.commandLineData.get("--sort-by-priority"));
      Display<ToDo> displayToDos = new Display<>(processor.getToDosToWorkWith());
      displayToDos.display();
    }
  }

  /**
   * Main run method of the Controller that passes relevant directives and
   * coordinates the behavior of the Model
   * @param commandLineData HashMap of the command line data; argument flags are keys and values
   *                        are the necessary strings entered along with arguments
   * @throws FileErrorException Exception thrown if the code encounters a CSV file without data
   * @throws IDNotFoundException Exception for id not found
   * @throws NothingToDisplayException Exception for nothing to display
   */
  public void run(HashMap<String, String> commandLineData)
      throws IDNotFoundException, FileErrorException, NothingToDisplayException {
    this.setCommandLineData(commandLineData);
    this.setUpProcessor();
    // Process the given CSV, add eachToDo to the CollectionOfToDos
    this.processor.populateCollectionOfToDosWithCSV(commandLineData.get("--csv-file"));

    // Use the HashMap of command line data to execute the specified tasks
    // Do any additions
    if (this.commandLineData.get("--add-todo") != null) {
      this.processor.addTodo(commandLineData.get("--todo-text"),
          commandLineData.get("--completed"),
          commandLineData.get("--due"),
          commandLineData.get("--priority"),
          commandLineData.get("--category"));
    }
    // Do any updates
    this.update();

    // Check if display has been requested, coordinate all filtering and sorting needed to render
    // display and display specified results.
    this.coordinateDisplay();
  }

  public void run() {
  }
}