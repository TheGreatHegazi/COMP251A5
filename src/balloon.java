import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class balloon {
public static int arrowCount = 0;
	balloon(int[] input) {
	
		int popped = 0;
		arrowCount = 0;
		for (int i = 0; i < input.length; i++) {
		
			if (popped == input.length) {
				break;
			}
			int arrow = 0;
			if (input[i] == 0) {
				continue;
			}
			arrowCount++;
			arrow = input[i];
			int j = i;
			while (arrow != 0 && j < input.length) {
				if (arrow == input[j]) {
					input[j] = 0;
					arrow--;
					popped++;
				}
				j++;
			}

		}
	}
	public int getArrow(){
		return arrowCount;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); // to see runtime
		String file = "testBalloons.java";
		try {
			Scanner input = new Scanner(new File(file));

			int numberOfProblems = Integer.parseInt(input.nextLine());

			String[] sizes = input.nextLine().split("\\s");
			int[] problemSizes = new int[numberOfProblems];
			for (int i = 0; i < numberOfProblems; i++) {
				problemSizes[i] = Integer.parseInt(sizes[0]);
			}

			String destination = file.replaceFirst(".txt", "_solution.txt");
			PrintWriter writer;
			try {
				writer = new PrintWriter(new FileWriter(destination));

				while (input.hasNext()) {
					String[] heights = input.nextLine().split("\\s");
					int[] inputHeights = new int[heights.length];
					for (int i = 0; i < inputHeights.length; i++) {
						inputHeights[i] = Integer.parseInt(heights[i]);
					}
					balloon problem = new balloon(inputHeights);
					String output = problem.getArrow() + "\n";
					System.out.print(output);
					writer.print(output);
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
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("Took " + (endTime - startTime) + " ms");
		}

	}

}
