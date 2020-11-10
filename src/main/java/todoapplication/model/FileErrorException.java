package todoapplication.model;

/**
 * Custom Exception thrown if the code encounters a CSV file without data
 */
public class FileErrorException extends Exception {

  /**
   * Exception thrown if the code encounters a CSV file without data
   * @param message custom message countered at instances the exception is thrown
   */
  public FileErrorException(String message) {
    super(message);
  }
}
