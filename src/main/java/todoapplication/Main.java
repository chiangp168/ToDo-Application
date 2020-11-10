package todoapplication;

import java.util.HashMap;
import todoapplication.model.FileErrorException;
import todoapplication.model.IDNotFoundException;
import todoapplication.view.NothingToDisplayException;
import java.util.Scanner;
import java.io.*;
public class Main {

  public static void main(String[] args)
      throws CommandLineException, IDNotFoundException,
      FileErrorException, NothingToDisplayException {
    // Create a command line parser
    // return a hash map of data called commandLineDate
   // File file=new File("test_csv.txt");
    //System.out.println(file.exists());
   // System.out.println(new File(".").getAbsoluteFile());
    CmdLineProcessor cmdLineProcessor = new CmdLineProcessor();
    HashMap<String, String> commandLineData = cmdLineProcessor.processArguments(args);
    System.out.println(commandLineData);
    Controller.getController().run(commandLineData);
  }

}
