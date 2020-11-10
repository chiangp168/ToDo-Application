package todoapplication.model;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class ToDoListToCSVWriterTest {
  ToDoListToCSVWriter testWriter;
  ToDo testToDo1;
  ToDo testToDo2;
  String filename;
  List<ToDo> testToDoList;
  String testHeader;

  @Before
  public void setUp() throws Exception {
    filename = "test_csv.txt";
    testHeader = "\"id\",\"text\",\"completed\",\"due\",\"priority\",\"category\"";
    testToDo1 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    testToDo2 = new ToDo.ToDoBuilder().id(2)
        .text("Java, Assignment 9")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(1)
        .category("Homework")
        .build();
    testToDoList = new ArrayList<>();
    testToDoList.add(testToDo1);
    testToDoList.add(testToDo2);
    testWriter = new ToDoListToCSVWriter(testHeader, testToDoList, filename);
  }

  @Test
  public void getItemsList() {
    TestCase.assertEquals(testToDoList,testWriter.getItemsList());
  }

  @Test
  public void getHeader() {
    TestCase.assertEquals(testHeader, testWriter.getHeader());
  }

  @Test
  public void writeCSV() throws IOException, FileErrorException {
    String expectedRow1 = "\"1\",\"Wash dishes\",\"false\",\"4/6/2020\",\"2\",\"Housework\"";
    String expectedRow2 = "\"2\",\"Java, Assignment 9\",\"false\",\"4/7/2020\",\"1\",\"Homework\"";
    testWriter.writeCSV();
    try (BufferedReader inputFile = new BufferedReader(
        new FileReader(filename))) {

      String line = inputFile.readLine();
      TestCase.assertEquals(testHeader, line);

      line = inputFile.readLine();
      TestCase.assertEquals(expectedRow1, line);

      line = inputFile.readLine();
      TestCase.assertEquals(expectedRow2, line);

    } catch (FileNotFoundException fnfe) {
      throw new FileErrorException("File not found");
    } catch (IOException ioe) {
      System.out.println("Something went wrong! :" + ioe.getMessage());
      throw new FileErrorException("File error");
    }
  }
}