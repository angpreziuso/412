package graphs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Main program for a PA that reads a csv file of distances between
 * cities and forms a weighted graph from it. Then it uses Kruskal's
 * and Prim's algorithms to generate spanning trees, whose edges it
 * prints. Finally, it checks whether the spanning trees are identical.
 * 
 * @author C. Fox
 * @version 10/19
 */
public class VASpanner {
  private static final String SOURCE_FILE = "VAdistances.csv";
  private static String[] cities;  // record city names

  /**
   * Run a program that reads 
   * @param args
   */
  public static void main(String[] args) {
    // load the data for distances between cities in VA
    WeightedGraph cityDistances =
        loadDistancesGraph(args.length == 1 ? args[0] : SOURCE_FILE);
    
    // use Kruskal's algorithm to find a minimum spanning tree and print
    WeightedGraph tree1 = WeightedGraphs.kruskal(cityDistances);
    System.out.println("Spanning tree edges found by Kruskal's algorithm.");
    WeightedGraphs.printEdges(tree1, cities);
    System.out.println();
    
    // use Prim's algorithm to find a minimum spanning tree and print
    WeightedGraph tree2 = WeightedGraphs.prim(cityDistances);
    System.out.println("Spanning tree edges found by Prims's algorithm.");
    WeightedGraphs.printEdges(tree2, cities);
    System.out.println();
    
    // check that the spanning trees are the same (true in this case)
    System.out.println("Prim's and Kruskal's found the same tree: "+
                           WeightedGraphs.isEqual(tree1,tree2));
  }
  
  /**
   * Load a csv file with weighted graph information.
   * The file must have a specific format, as follows.
   * - the first line must have a blank first field and the
   *   rest of the fields must be vertex names
   * - the remaining lines must have a vertex name as a
   *   first field and the remaining fields must be edge
   *   weights
   * Only the lower triangle of the array of values is processed.
   * If there is a problem reading the file a message is generated
   * and we exit. Number format exceptions generate an error report
   * but no program exit.
   * 
   * @param fileName path name to the csv file
   * @return a weighted graph with the file data; cities is set
   *         as a side effect
   */
  private static WeightedGraph loadDistancesGraph(String fileName) {
    WeightedGraph g = null;
    
    try (BufferedReader input = new BufferedReader(new FileReader(fileName)))
    {
      // process the header line from the csv file
      String line = input.readLine();
      String[] headers = line.split(",");
      cities = new String[headers.length-1];
      g = new ArrayWeightedGraph(cities.length);
      for (int i = 1; i < headers.length; i++)
        cities[i-1] = headers[i];
      
      // skip the first line because we process only the lower triangle
      line = input.readLine();
      
      // read each line until the diagonal and add edges
      int i = 1;
      line = input.readLine();
      while (line != null) {
        String[] fields = line.split(",");
        for (int j = 0; j < i; j++) {
          try {
            double weight = Double.parseDouble(fields[j+1]);
            g.addEdge(i,j,weight);
          }
          catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("Bad line: "+line);
          }
        }
        i++;
        line = input.readLine();
      }
    }
    catch (FileNotFoundException e) {
      System.err.println(e);
      System.exit(1);
    }
    catch (IOException e) {
      System.err.println(e);
      System.exit(1);
    }
    return g;

  } // LoadDistancesGraph
  
} // VASpanner
