package todoapplication.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a CSV Reader. This class follows the Singleton pattern and uses the
 * "eager" allocation.
 */
public class CSVFileReader {
  private static CSVFileReader csvFileReader = new CSVFileReader();

  /**
   * Creates a new CSVFileReader object
   */
  private CSVFileReader() {
  }

  /**
   * Returns the CSVFileReader
   * @return the CSVFileReader
   */
  public static CSVFileReader getCsvFileReader() {
    return csvFileReader;
  }

  /**
   * Reads the specified filename and stores the contents line by line into a List.
   * @param filename - the name of the file to read
   * @return the contents of the file stored line by line
   * @throws FileErrorException when an invalid file path is given
   */
  public List<String> readCSVFile(String filename) throws FileErrorException {
    List<String> csvLines = new ArrayList<>();
    try (BufferedReader inputFile = new BufferedReader(
        new FileReader(filename))) {

      String line;
      while ((line = inputFile.readLine()) != null) {
        csvLines.add(line);
      }
      return csvLines;

    } catch (FileNotFoundException fnfe) {
      throw new FileErrorException("File not found");
    } catch (IOException ioe) {
      throw new FileErrorException("File error");
    }
  }

  /**
   * Returns a String representation of the CSVFileReader object
   * @return a String representation of the CSVFileReader object
   */
  @Override
  public String toString() {
    return "CSVFileReader";
  }
}
