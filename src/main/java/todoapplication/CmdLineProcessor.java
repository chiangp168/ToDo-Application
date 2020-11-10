package todoapplication;
import java.util.HashMap;
import java.util.Objects;

/**
 * This class takes in the user input command line, and build a hash map to store the values.
 */
public class CmdLineProcessor {

  protected HashMap<String, String> commandLineData = new HashMap<String, String>();
  private static final String ADDNEWTODO = "--add-todo";
  private static final String DISPLAY = "--display";
  private static final String COMPLETED = "--completed";
  private static final String CSVFILE = "--csv-file";
  private static final String TODOTEXT = "--todo-text";
  private static final String DUE = "--due";
  private static final String PRIORITY = "--priority";
  private static final String CATEGORY = "--category";
  private static final String COMPLETETODO = "--complete-todo";
  private static final String SHOWINCOMPLETE = "--show-incomplete";
  private static final String SHOWCATEGORY = "--show-category";
  private static final String SORTDATE = "--sort-by-date";
  private static final String SORTPRIORITY = "--sort-by-priority";
  private static final String GIVEN = "Given";
  private static final String SPACE = " ";
  protected static final int MIN_NUMBER_ARGS = 2;
  //protected static final int MAX_NUMBER_ARGS = 17;

  /**
   * Constructs a new CmdLineProcessor object.
   */
  public CmdLineProcessor() {
    this.commandLineData = createHashMapKeys();
  }

  /**
   * Set up the possible command line argument flags as keys.
   * @return HashMap - with all possible keys with value of null.
   */
  private HashMap<String, String> createHashMapKeys() {
    commandLineData.put(CSVFILE, null);
    commandLineData.put(ADDNEWTODO, null);
    commandLineData.put(TODOTEXT, null);
    commandLineData.put(COMPLETED, null);
    commandLineData.put(DUE, null);
    commandLineData.put(PRIORITY, null);
    commandLineData.put(CATEGORY, null);
    commandLineData.put(COMPLETETODO, null);
    commandLineData.put(DISPLAY, null);
    commandLineData.put(SHOWINCOMPLETE, null);
    commandLineData.put(SHOWCATEGORY, null);
    commandLineData.put(SORTDATE, null);
    commandLineData.put(SORTPRIORITY, null);
    return commandLineData;
  }

  /**
   * Process the user input command line arguments and save them as values in the hash map.
   * @param args - an array of command line arguments
   * @return HashMap - a hash map with user input keys
   * @throws CommandLineException - when an invalid command line argument is given
   */
  public HashMap<String, String> processArguments(String[] args) throws CommandLineException {
    int next = 1;
    checkInputLength(args);
    int index = 0;
    while (index < args.length) {
      if (commandLineData.containsKey(args[index])) {
        switch (args[index]) {
          case DISPLAY:
          case COMPLETED:
          case SHOWINCOMPLETE:
          case SORTDATE:
          case SORTPRIORITY:
          case ADDNEWTODO:
            commandLineData.replace(args[index], GIVEN);
            break;
          case COMPLETETODO:
            if (index + next < args.length) {
              String oldValue = commandLineData.get(COMPLETETODO);
              if (oldValue != null) {
                commandLineData.replace(args[index], oldValue + SPACE + args[index + next]);
              } else {
                  commandLineData.replace(args[index], args[index + next]);
                }
            } else {
              throw new CommandLineException("Invalid argument1");
            }
            index++;
            break;
          default:
              if (index + next < args.length) {
                commandLineData.replace(args[index], args[index + next]);
              } else {
                throw new CommandLineException("Invalid argument2");
              }
            index++;
            break;
        }
      } else {
        throw new CommandLineException("Invalid argument3");
      }
      index++;
    }
    if (this.legalCombination()) {
      return this.commandLineData;
    } else {
      throw new CommandLineException("Invalid argument4");
    }
  }

  /**
   * Check the length of argument.
   * @param args - an array of command line arguments
   * @throws CommandLineException - when an invalid command line argument is given
   */
  private void checkInputLength(String[] args) throws CommandLineException {
    if (args.length < MIN_NUMBER_ARGS) {
      throw new CommandLineException("Incorrect number of arguments!");
    }
  }

  /**
   * Check legal combination of arguments.
   * @return boolean - return true when combination of arguments is valid, or return false
   * otherwise.
   */
  private boolean legalCombination() {
    return this.checkCSVFile() && this.checkSort() && this.checkAddToDo();
  }

  /**
   * Check if the CSV file to read from is provided.
   * @return boolean - true when provided, false otherwise.
   */
  private boolean checkCSVFile() {
    return commandLineData.get(CSVFILE) != null;
  }

  /**
   * Check if only one sort is given.
   * @return boolean - true when only one sort is given, false otherwise.
   */
  private boolean checkSort() {
    return commandLineData.get(SORTDATE) == null || commandLineData.get(SORTPRIORITY) == null;
  }

  /**
   * Check if the description of a Todo is given when adding a new Todo.
   * @return boolean - true when the description is given, false otherwise.
   */
  private boolean checkAddToDo() {
    if (commandLineData.get(ADDNEWTODO) != null) {
      return commandLineData.get(TODOTEXT) != null;
    }
    return true;
  }

  /**
   * Getter for the hash map.
   * @return HashMap - commandLineData
   */
  public HashMap<String, String> getCommandLineData() {
    return commandLineData;
  }

  /**
   * Determines if the given object equals the CmdLineProcessor object
   * @param o - the given object
   * @return true if the given object is equal to the CmdLineProcessor object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || o.getClass() != this.getClass()) {
      return false;
    }
    CmdLineProcessor that = (CmdLineProcessor) o;
    return Objects.equals(getCommandLineData(), that.getCommandLineData());
  }

  /**
   * Returns the hash code of the CmdLineProcessor object
   * @return the hash code of the CmdLineProcessor object
   */
  @Override
  public int hashCode() {
    return Objects.hash(getCommandLineData());
  }

  /**
   * Returns a string representation of the CmdLineProcessor object
   * @return a string representation of the CmdLineProcessor object
   */
  @Override
  public String toString() {
    return "CmdLineProcessor{" +
        "commandLineData=" + commandLineData +
        '}';
  }
}
