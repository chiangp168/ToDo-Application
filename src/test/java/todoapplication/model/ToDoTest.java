package todoapplication.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.concurrent.TimeoutException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class ToDoTest {
  ToDo testTodo;

  @Before
  public void setUp() throws Exception {
    testTodo = new ToDo.ToDoBuilder().id(1)
                       .text("Wash dishes")
                       .completed(false)
                       .dueDate(LocalDate.of(2020, 4, 6))
                       .priority(2)
                       .category("Housework")
                       .build();
  }

  @Test
  public void markCompleted() {
    testTodo.markCompleted();
    TestCase.assertTrue(testTodo.isCompleted());
  }

  @Test
  public void getText() {
    String expected = "Wash dishes";
    TestCase.assertEquals(expected, testTodo.getText());
  }

  @Test
  public void isCompleted() {
    TestCase.assertFalse(testTodo.isCompleted());
  }

  @Test
  public void getDueDate() {
    LocalDate expected = LocalDate.of(2020, 4, 6);
    TestCase.assertEquals(expected, testTodo.getDueDate());
  }

  @Test
  public void getPriority() {
    int expected = 2;
    TestCase.assertTrue(expected == testTodo.getPriority());
  }

  @Test
  public void getID() {
    Integer expected = 1;
    TestCase.assertEquals(expected, testTodo.getID());
  }

  @Test
  public void getCategory() {
    String expected = "Housework";
    TestCase.assertEquals(expected, testTodo.getCategory());
  }

  @Test
  public void testEqualsReflexive() {
    TestCase.assertTrue(testTodo.equals(testTodo));
  }

  @Test
  public void testEqualsConsistent() {
    TestCase.assertTrue(testTodo.equals(testTodo));
    TestCase.assertTrue(testTodo.equals(testTodo));
    TestCase.assertTrue(testTodo.equals(testTodo));
  }

  @Test
  public void testEqualsSymmetric() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertTrue(testTodo.equals(testTodo2));
    TestCase.assertTrue(testTodo2.equals(testTodo));
  }

  @Test
  public void testEqualsTransitive() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    ToDo testTodo3 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertTrue(testTodo.equals(testTodo2));
    TestCase.assertTrue(testTodo2.equals(testTodo3));
    TestCase.assertTrue(testTodo.equals(testTodo3));
  }

  @Test
  public void testEqualsDifferentText() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("laundry")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertFalse(testTodo.equals(testTodo2));
  }

  @Test
  public void testEqualsDifferentPriority() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(1)
        .category("Housework")
        .build();
    TestCase.assertFalse(testTodo.equals(testTodo2));
  }

  @Test
  public void testEqualsDifferentDueDate() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertFalse(testTodo.equals(testTodo2));
  }

  @Test
  public void testEqualsDifferentCategory() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(2)
        .category("Misc.")
        .build();
    TestCase.assertFalse(testTodo.equals(testTodo2));
  }

  @Test
  public void testEqualsDifferentID() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(3)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertFalse(testTodo.equals(testTodo2));
  }

  @Test
  public void testEqualsDifferentCompletionStatus() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(true)
        .dueDate(LocalDate.of(2020, 4, 6))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertFalse(testTodo.equals(testTodo2));
  }

  @Test
  public void testEqualsNull() {
    TestCase.assertFalse(testTodo.equals(null));
  }

  @Test
  public void testEqualsDifferentObject() {
    Integer testInt = 7;
    TestCase.assertFalse(testTodo.equals(testInt));
  }

  @Test
  public void testHashCodeReflexive() {
    TestCase.assertTrue(testTodo.hashCode() == testTodo.hashCode());
  }

  @Test
  public void testHashCodeConsistentWithEquals() {
    ToDo testTodo2 = new ToDo.ToDoBuilder().id(1)
        .text("Wash dishes")
        .completed(false)
        .dueDate(LocalDate.of(2020, 4, 7))
        .priority(2)
        .category("Housework")
        .build();
    TestCase.assertFalse(testTodo.hashCode() == testTodo2.hashCode());
  }

  @Test
  public void testToString() {
    String expected = "ToDo ID: " + testTodo.getID() +
        " Text: " + testTodo.getText() +
        " Completed: " + testTodo.isCompleted() +
        " Due Date: " + testTodo.getDueDate().toString() +
        " Priority: " + testTodo.getPriority() +
        " Category: " + testTodo.getCategory();
  }
}