package todoapplication.model;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CSVFileReaderTest {
  String testFileName;
  String header;
  String row1;

  @Before
  public void setUp() throws Exception {
    testFileName = "test_csv.txt";
    header = "\"id\",\"text\",\"completed\",\"due\",\"priority\",\"category\"";
    row1 = "\"1\",\"Wash dishes\",\"false\",\"4/6/2020\",\"2\",\"Housework\"";
  }

  @Test
  public void readCSVFile() throws FileErrorException {
    List<String> results = CSVFileReader.getCsvFileReader().readCSVFile(testFileName);
    TestCase.assertEquals(header, results.get(0));
    TestCase.assertEquals(header, results.get(0));
    TestCase.assertEquals(3, results.size());
  }

  @Test (expected = FileErrorException.class)
  public void readCSVFileInvalidFile() throws FileErrorException {
    List<String> results = CSVFileReader.getCsvFileReader().readCSVFile("invalid.txt");
  }

  @Test
  public void testToString() {
    String expected = "CSVFileReader";
    TestCase.assertEquals(expected, CSVFileReader.getCsvFileReader().toString());
  }

}