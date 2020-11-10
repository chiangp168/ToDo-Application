package todoapplication.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * A class representing a Writer that takes a list of ToDos and writes a CSV files displaying the
 * ToDo information.
 */
public class ToDoListToCSVWriter {
  private List<ToDo> itemsList;
  private String filename;
  private static final String header = "\"id\",\"text\",\"completed\",\"due\",\"priority\","
    + "\"category\"";
  private final String COMMA = ",";
  private final String SLASH = "/";
  private final String QUOTE = "\"";

  /**
   * Constructs a new ToDoListToCSVWriter object with the given header, the given list of ToDos,
   * and the given filename to output the results to.
   * @param header - a String representing the header of the CSV file
   * @param itemList - a List of ToDo objects whose information we want to write to a CSV
   * @param filename - the name of the file we want to write to
   */
  public ToDoListToCSVWriter(String header, List<ToDo> itemList, String filename) {
    this.itemsList = itemList;
    this.filename = filename;
  }

  /**
   *
   * returns the list of ToDos
   * @return the list of ToDos
   */
  public List<ToDo> getItemsList() {
    return this.itemsList;
  }

  /**
   * Returns the header
   * @return the header
   */
  public String getHeader() {
    return header;
  }

  /**
   * Writes the CSV with the List of ToDos and header.
   * @throws IOException when there is an error in writing the file
   */
  public void writeCSV() throws IOException {
    try(BufferedWriter outputFile =
        new BufferedWriter(new FileWriter(this.filename));)
    {
      outputFile.write(header + "\n");
      for (ToDo item : this.itemsList) {
        outputFile.write(this.generateCSVLine(item));
      }
    } catch  (IOException ioe) {
      throw new IOException("Cannot write to file,");
    }
  }

  /**
   * Generates a line for the CSV for the specified ToDo in the following format:
   * "id", "text", "completed", "due", "priority", "category"
   * @param item - A ToDo item
   * @return the corresponding line in the CSV file for the specified ToDo object
   */
  private String generateCSVLine(ToDo item) {
    return this.formatNonStringInfo(item.getID()) + COMMA +
        this.formatStringInformation(item.getText()) + COMMA +
        this.formatNonStringInfo(item.isCompleted()) + COMMA +
        this.formatDueDate(item.getDueDate()) + COMMA +
        this.formatNonStringInfo(item.getPriority()) + COMMA +
        this.formatStringInformation(item.getCategory()) + "\n";
  }

  /**
   * Formats the specified due date to the form: "day/month/year"
   * @param dueDate - the due date to format
   * @return the due date in the form: "day/month/year"
   */
  private String formatDueDate(LocalDate dueDate) {
    return QUOTE + dueDate.getMonthValue() + SLASH + dueDate.getDayOfMonth() +
        SLASH + dueDate.getYear() + QUOTE;
  }

  /**
   * Formats the given String information to the form: "info"
   * @param info - the String information to format
   * @return the given String information in the form: "info"
   */
  private String formatStringInformation(String info) {
    return QUOTE + info + QUOTE;
  }

  /**
   * Formats the given non-String information to the form: "info"
   * @param info - the non-String information to format
   * @return the given non-String information in the form: "info"
   */
  private String formatNonStringInfo(boolean info) {
    return QUOTE + String.valueOf(info) + QUOTE;
  }

  /**
   * Formats the given non-String information to the form: "info"
   * @param info - the non-String information to format
   * @return the given non-String information in the form: "info"
   */
  private String formatNonStringInfo(Integer info) {
    return QUOTE + String.valueOf(info) + QUOTE;
  }

  /**
   * Formats the given non-String information to the form: "info"
   * @param info - the non-String information to format
   * @return the given non-String information in the form: "info"
   */
  private String formatNonStringInfo(int info) {
    return QUOTE + String.valueOf(info) + QUOTE;
  }

}