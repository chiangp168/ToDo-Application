package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountedCompleter;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompletionFilterTest {
  List<ToDo> testToDoList;
  ToDo testToDo1;
  ToDo testToDo2;
  ToDo testToDo3;

  @Before
  public void setUp() throws Exception {
    testToDoList = new ArrayList<>();
    testToDo1 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(true)
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
    testToDoList.add(testToDo1);
    testToDoList.add(testToDo2);
    testToDoList.add(testToDo3);
  }

  @Test
  public void getCompletionStatus() {
    CompletionFilter.getCompletionFilter().setCompletionStatusToFilterOn(true);
    TestCase.assertTrue(CompletionFilter.getCompletionFilter().getCompletionStatus());
  }

  @Test
  public void setCompletionStatusToFilterOn() {
    CompletionFilter.getCompletionFilter().setCompletionStatusToFilterOn(false);
    TestCase.assertFalse(CompletionFilter.getCompletionFilter().getCompletionStatus());
  }

  @Test
  public void filter() {
    CompletionFilter.getCompletionFilter().setCompletionStatusToFilterOn(false);
    List<ToDo> results = CompletionFilter.getCompletionFilter().filter(testToDoList);
    TestCase.assertEquals(2, results.size());
    TestCase.assertTrue(results.contains(testToDo2));
    TestCase.assertTrue(results.contains(testToDo3));
    TestCase.assertFalse(results.contains(testToDo1));
  }

  @Test
  public void testToString() {
    String expected = "CompletionFilter: " +
        "completionStatusToFilterOn: " + "false";
    CompletionFilter.getCompletionFilter().setCompletionStatusToFilterOn(false);
    TestCase.assertEquals(expected, CompletionFilter.getCompletionFilter().toString());
  }

  @After
  public void resetCollectionOfToDos() {
    CollectionOfToDos.getCollectionOfToDos().reset();
  }
}