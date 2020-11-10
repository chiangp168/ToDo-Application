package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DateSorterTest {
  DateSorter testDateSorter;
  Processor testProcessor;
  ToDo testToDo1;
  ToDo testToDo2;
  ToDo testToDo3;
  List<ToDo> testToDoList;

  @Before
  public void setUp() throws Exception {
    testToDoList = new ArrayList<>();
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
        .dueDate(LocalDate.of(2020, 5, 17))
        .priority(1)
        .category("Homework")
        .build();
    testToDo3 = new ToDo.ToDoBuilder().id(2)
        .text("Systems, Assignment 10")
        .completed(false)
        .dueDate(LocalDate.of(2021, 1, 23))
        .priority(1)
        .category("Homework")
        .build();
    testProcessor = Processor.getProcessor();
    testDateSorter = DateSorter.getDateSorter();
    testToDoList.add(testToDo1);
    testToDoList.add(testToDo2);
    testToDoList.add(testToDo3);
  }

  @Test
  public void getDateSorter() {
    TestCase.assertEquals(testDateSorter, DateSorter.getDateSorter());
  }

  @Test
  public void compare() {
  }

  @Test
  public void sortByDate() {
  }

  @Test
  public void testToString() {
    String expected = "SortByDate";
    TestCase.assertEquals(expected, DateSorter.getDateSorter().toString());
  }

  @After
  public void resetCollectionOfToDos() {
    CollectionOfToDos.getCollectionOfToDos().reset();
  }
}