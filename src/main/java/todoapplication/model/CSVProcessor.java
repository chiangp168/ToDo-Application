package todoapplication.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * The CSV file processor class. This class contains methods to parse the lines of a CSV file
 * and extract he header information and information in each line.
 * Follows Singleton design pattern (eager allocation)
 */
public class CSVProcessor {
  public static CSVProcessor csvProcessor = new CSVProcessor();
  private List<String> csvLines;
  private String header;
  private HashMap<String, String> lineData;
  private List<String> headerList;
  private static final String INFO = "\"(.*?)\"";
  private static final String COMMA = ",";

  /**
   * Constructs a new CSVProcessor object
   * Header list and line data Items instantiated with the class
   */
  private CSVProcessor () {
    this.headerList = new ArrayList<>();
    this.lineData = new HashMap<>();
 }

  /**
   * Instance method of the class
   * @return the csvProcessor that has been created
   */
  public static CSVProcessor getCsvProcessor() {
   return csvProcessor;
 }

  /**
   * Setter method for the csv lines
   * @param csvLines lines containing the ToDos from the csv file
   */
  public void setCsvLines(List<String> csvLines) {
    this.csvLines = csvLines;
  }

  /**
   * Method to parse CSV lines to form List of ToDos
   * @return a List of ToDos parsed from lines in the csv read as list of strings
   * @throws FileErrorException Exception thrown if the code encounters a CSV file without data
   */
  public List<ToDo> parseFile() throws FileErrorException {
    // Check CSV lines have been read in
    List<ToDo> toDosFromFile = new ArrayList<>();
    if (this.csvLines == null) {
      throw new FileErrorException("Invalid file");
    }
    this.parseHeader(this.csvLines.get(0));
    // Parse each row of data
    for (int i = 1; i < this.csvLines.size(); i++) {
      ToDo toDoToAdd = this.parseLine(this.csvLines.get(i));
      toDosFromFile.add(toDoToAdd);
    }
    return toDosFromFile;
  }

  /**
   * Helper method parses a line of the CSV file and calls the string to ToDo parser helper method
   * @param line - the line from a CSV file to parse
   */
  private ToDo parseLine(String line) {
      // Creates a ToDo as the key and corresponding ToDo field value as the value.
    Pattern pattern = Pattern.compile(INFO);
    Matcher matcher = pattern.matcher(line);
    int i = 0;
    while(matcher.find()) {
      this.headerList.add(matcher.group(1));
      this.lineData.put(this.headerList.get(i), matcher.group(1));
      i++;
    }
   return this.createToDoFromLine(this.lineData);
  }

  /**
   * Helper method reads the fields of header of the csv input file, and then sets them as the
   * keys of a hashMap
   * @param firstLine -the first line (header) of the file to parse
   */
  private void parseHeader(String firstLine) {
    this.header = firstLine;
      Pattern pattern = Pattern.compile(INFO);
      Matcher matcher = pattern.matcher(firstLine);
      while(matcher.find()) {
        this.headerList.add(matcher.group(1));
        this.lineData.put(matcher.group(1), "null");
      }
    }

  /**
   * Helper method to create a to do object from the elements of the HashMap created
   * from reading the lines of the CSV file
   * @param lineInfo - A hash map containing information from a line of the CSV file.
   *
   * @return the ToDo object created from the line
   */
  private ToDo createToDoFromLine(HashMap<String, String> lineInfo){
     return ToDoCreator.getToDoCreator().createToDoFromLine(lineInfo);
   }

}


