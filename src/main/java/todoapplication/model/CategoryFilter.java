package todoapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a filter that filters a Collection of ToDos by category.
 * Follows the Singleton design pattern ("lazy" allocation)
 */
public class CategoryFilter {
  private static CategoryFilter categoryFilter;
  private String categoryToFilterOn;

  private CategoryFilter() {
  }

  /**
   * Instance getter method
   * @return the instance of the category filter
   */
  public static synchronized CategoryFilter getCategoryFilter() {
    if (categoryFilter == null) {
      categoryFilter = new CategoryFilter();
    }
    return categoryFilter;
  }

  /**
   * Sets the category filter on flag
   * @param categoryToFilterOn - the category to filter on
   */
  public void setCategoryToFilterOn(String categoryToFilterOn) {
    this.categoryToFilterOn = categoryToFilterOn;
  }

  /**
   * Method to get the category filter flag status
   * @return the category the filter will filter on
   */
  public String getCategory() {
    return this.categoryToFilterOn;
  }

  /**
   * Filters the collection of ToDos by the given category, returns a list of all ToDos from
   * the collection with the given category.
   * @param toDos - the list of ToDos to filter
   * @return a list of all ToDos from the collection with the given category.
   */
  public List<ToDo> filter(List<ToDo> toDos) {
    List<ToDo> results = new ArrayList<>();
    for (ToDo todo : toDos) {
      if (todo.getCategory().equals(this.categoryToFilterOn)) {
        results.add(todo);
      }
    }
   return results;
  }

  /**
   * Returns a string representation of the Category Filter
   * @return a string representation of the Category Filter
   */
  @Override
  public String toString() {
    return "CategoryFilter: " +
        "categoryToFilterOn: '" + this.categoryToFilterOn;
  }
}

