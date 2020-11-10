package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PrioritySorterTest {
  PrioritySorter testPrioritySorter;
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
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(1)
        .category("Homework")
        .build();
    testToDo3 = new ToDo.ToDoBuilder().id(2)
        .text("Systems, Assignment 10")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 8))
        .priority(1)
        .category("Homework")
        .build();
    testProcessor = Processor.getProcessor();
    testPrioritySorter = PrioritySorter.getPrioritySorter();
    testToDoList.add(testToDo1);
    testToDoList.add(testToDo2);
    testToDoList.add(testToDo3);
  }

  @Test
  public void getPrioritySorter() {
    TestCase.assertEquals(testPrioritySorter, PrioritySorter.getPrioritySorter());
  }

  @Test
  public void compare() {
    // testToDo2 (priority = 1) testToDo1 (priority = 2) testToDo3 (priority = 1)
    TestCase.assertEquals(1,
        PrioritySorter.getPrioritySorter().compare(testToDo2, testToDo1));

    TestCase.assertEquals(0,
        PrioritySorter.getPrioritySorter().compare(testToDo2, testToDo3));

    TestCase.assertEquals(-1,
        PrioritySorter.getPrioritySorter().compare(testToDo1, testToDo3));
  }

  @Test
  public void sortByPriority() {
    List<ToDo> result = PrioritySorter.getPrioritySorter().sortByPriority(testToDoList);
    TestCase.assertEquals(testToDo1, result.get(0));
  }

  /*
  @Test
  public void accept() {
    List<ToDo> result = PrioritySorter.getPrioritySorter().accept(Processor.getProcessor());
    TestCase.assertEquals(testToDo1, result.get(0));
  }
   */

  @Test
  public void testToString() {
    String expected = "SortByPriority";
    TestCase.assertEquals(expected, PrioritySorter.getPrioritySorter().toString());
  }

  @After
  public void resetCollectionOfToDos() {
    CollectionOfToDos.getCollectionOfToDos().reset();
  }
}