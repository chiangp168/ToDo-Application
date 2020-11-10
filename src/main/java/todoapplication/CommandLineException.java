package todoapplication;

public class CommandLineException extends Exception {

  private static String usageHelp;
  private static String[] flags;
  private static String[] explanations;
  private static String[] examples;

  CommandLineException(String message) {
    super(message + "\n" + usageHelp);
  }
    static {
      flags = new String[]{
          "--add-todo",
          "--display",
          "--completed",
          "--csv-file",
          "--todo-text",
          "--due",
          "--priority",
          "--category",
          "--complete-todo",
          "--show-incomplete",
          "--show-category",
          "--sort-by-date",
          "--sort-by-priority",
      };

      explanations = new String[]{
          "Add a new todo. If provided, --todo-text is also required.",
          "Display todo. If none of --show / -- sort is provided, all todos will be displayed.",
          "Set the new todo complete status to true",
          "The CSV file to process. This option is required.",
          "If add a new todo is provided, a description of todo is also required.",
          "The due date of the new todo.",
          "Set the priority of new todo between 1, 2 and 3.",
          "Set the category of a new todo. Input should be a string.",
          "Mark the todo with the provided ID to completed.",
          "Display all incomplete todos.",
          "Display all todo by category.",
          "Sort list of todo by date.",
          "Sort list of todo by priority."
      };

      examples = new String[]{
          "--csv-file hello.csv --add-todo --todo-text \"Need to finish hw\" --completed",
          "--csv-file hello.csv --display"
      };
    }
    static {
      usageHelp = "Usage:";
      for (int i = 0; i < flags.length; i++) {
        usageHelp += flags[i] + " " + explanations[i] + "\n";
      }
      for (String example : examples) {
        usageHelp += example + "\n";
      }
    }
}
