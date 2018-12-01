import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;
public class balloon {

	balloon (int[] input){
	int arrowCount = 0;
		for ( int i = 0; i < input.length; i ++){
			if ( input.equals(new int[input.length])){ break;}
			int arrow = 0; 
			arrowCount++;
			arrow = input[i];
			int j = i;
			while (arrow != 0 && j < input.length){
				if (arrow == input[j]){
					input[j] =0;
					arrow--; 
				}
				j++;
			}
			
		
		}
	}

	 public static void main(String[] args){
			long startTime = System.nanoTime(); // to see runtime
			String file = args[0];
			try {
				Scanner input = new Scanner(new File(file));
				
				// first line tells how many balloon problems there are
				int numberOfProblems = Integer.parseInt(input.nextLine());
				
				// second line tells us how many balloons there are for each problem
				// not really used, just to get us through the second line
				String[] sizes = input.nextLine().split("\\s");
				int[] problemSizes = new int[numberOfProblems];
				for (int i = 0; i < numberOfProblems; i++){
					problemSizes[i] = Integer.parseInt(sizes[0]);
				}
				
				String destination = file.replaceFirst(".txt", "_solution.txt");
				PrintWriter writer;
				try {
					writer = new PrintWriter(new FileWriter(destination));
					
					// rest of the lines have the balloon heights for every problem
					while(input.hasNext()){
						String[] heights = input.nextLine().split("\\s");
						int[] inputHeights  = new int[heights.length];
						for (int i = 0; i < inputHeights.length; i++){
							inputHeights[i] = Integer.parseInt(heights[i]);
						}
						balloon problem = new balloon(inputHeights);
						//String output = problem.getArrows() + "\n";
					//	System.out.print(output);
						//writer.print(output);
					}
					writer.close();
				} catch (IOException e) {
					System.out.println("Could not create file");
					System.exit(1);
				}
				input.close();
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
				System.exit(1);
			}
			finally{
				long endTime = System.nanoTime();
				System.out.println("Took "+(endTime - startTime) + " ns");
			}
			
		}
 
 

}
