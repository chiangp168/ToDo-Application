package todoapplication.view;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import todoapplication.model.ToDo;

public class DisplayTest {
  Display<ToDo> testDisplay;
  ToDo testTodo1;
  ToDo testTodo2;
  List<ToDo> toDosToDisplay;

  @Before
  public void setUp() throws Exception {
    testTodo1 = new ToDo.ToDoBuilder().id(1)
                                      .text("Wash dishes")
                                      .completed(false)
                                      .dueDate(LocalDate.of(2020, 4, 6))
                                      .priority(2)
                                      .category("Housework")
                                      .build();
    testTodo2 = new ToDo.ToDoBuilder().id(2)
                                      .text("Java homework 9")
                                      .completed(false)
                                      .dueDate(LocalDate.of(2020, 4, 8))
                                      .priority(1)
                                      .category("Homework")
                                      .build();
    toDosToDisplay = new ArrayList<>();
    toDosToDisplay.add(testTodo1);
    toDosToDisplay.add(testTodo2);
    testDisplay = new Display<>(toDosToDisplay);
  }

  @Test (expected = NothingToDisplayException.class)
  public void testDisplay() throws NothingToDisplayException {
    List<ToDo> toDosToDisplayNothing = new ArrayList<>();
    Display<ToDo> testDisplayEmptyList = new Display<>(toDosToDisplayNothing);
    testDisplayEmptyList.display();
  }

  @Test
  public void testToString() {
    String expected = "Report: \n" +
        toDosToDisplay.toString();
    TestCase.assertEquals(expected, testDisplay.toString());
  }

  @Test
  public void testEqualsReflexive() {
    TestCase.assertTrue(testDisplay.equals(testDisplay));
  }

  @Test
  public void testEqualsConsistent() {
    TestCase.assertTrue(testDisplay.equals(testDisplay));
    TestCase.assertTrue(testDisplay.equals(testDisplay));
    TestCase.assertTrue(testDisplay.equals(testDisplay));
  }

  @Test
  public void testEqualsSymmetric() {
    Display<ToDo> testDisplay2 = new Display<>(toDosToDisplay);
    TestCase.assertTrue(testDisplay.equals(testDisplay2));
    TestCase.assertTrue(testDisplay2.equals(testDisplay));
  }

  @Test
  public void testEqualsTransitive() {
    Display<ToDo> testDisplay2 = new Display<>(toDosToDisplay);
    Display<ToDo> testDisplay3 = new Display<>(toDosToDisplay);
    TestCase.assertTrue(testDisplay.equals(testDisplay2));
    TestCase.assertTrue(testDisplay2.equals(testDisplay3));
    TestCase.assertTrue(testDisplay.equals(testDisplay3));
  }

  @Test
  public void testEqualsDifferentData() {
    List<ToDo> toDosToDisplay2 = new ArrayList<>();
    toDosToDisplay2.add(testTodo1);
    Display<ToDo> testDisplay2 = new Display<>(toDosToDisplay2);
    TestCase.assertFalse(testDisplay.equals(testDisplay2));
  }

  @Test
  public void testEqualsNull() {
    TestCase.assertFalse(testDisplay.equals(null));
  }

  @Test
  public void testEqualsDifferentObjectType() {
    Integer testInteger = 4;
    TestCase.assertFalse(testDisplay.equals(testInteger));
  }

  @Test
  public void testHashCodeReflexive() {
    TestCase.assertTrue(testDisplay.hashCode() == testDisplay.hashCode());
  }

  @Test
  public void testHashCodeConsistentWithEqualDisplayObjects() {
    Display<ToDo> testDisplay2 = new Display<>(toDosToDisplay);
    TestCase.assertTrue(testDisplay.hashCode() == testDisplay2.hashCode());
  }

  @Test
  public void testHashCodeConsistentWithUnEqualDisplayObjects() {
    List<ToDo> toDosToDisplay2 = new ArrayList<>();
    toDosToDisplay2.add(testTodo1);
    Display<ToDo> testDisplay2 = new Display<>(toDosToDisplay2);
    TestCase.assertFalse(testDisplay.hashCode() == testDisplay2.hashCode());
  }
}