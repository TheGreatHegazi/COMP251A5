import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class islands {
	public static int islandsCount = 0;

	islands(char[][] input, int[] size) {

		DisjointSets body = new DisjointSets(size[0] * size[1]);
		for (int i = 0; i < size[0]; i++) {
			for (int j = 0; j < size[0]; j++) {
				if (input[i][j+1] == input[i][j]){
					body.union(body.find(i*j),body.find( i*(j+1)));
				}
				
				if (input[i+1][j] == input[i][j]){
					body.union(body.find((i+1)*j), body.find(i*(j)));
				}
			}
		}
	}


		
	
	public int getIslands() {
		return islandsCount;
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String file = args[0];
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
	

					char[][] inputland = new char[problemSizes[0]][problemSizes[1]];
					for (int i = 0; i < problemSizes[0]; i++) {
						String buffer = input.nextLine();
						char[] row = new char[buffer.length()];
						for (int j = 0; j < buffer.length(); j++) {
							row[j] = buffer.charAt(j);
						}
						inputland[i] = row;
					}
					islands problem = new islands(inputland, problemSizes);

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

	public class DisjointSets {

		private int[] par;
		private int[] rank;

		/* contructor: creates a partition of n elements. */
		/* Each element is in a separate disjoint set */
		DisjointSets(int n) {
			if (n > 0) {
				par = new int[n];
				rank = new int[n];
				for (int i = 0; i < this.par.length; i++) {
					par[i] = i;
				}
			}
		}

		public String toString() {
			int pari, countsets = 0;
			String output = "";
			String[] setstrings = new String[this.par.length];
			/* build string for each set */
			for (int i = 0; i < this.par.length; i++) {
				pari = find(i);
				if (setstrings[pari] == null) {
					setstrings[pari] = String.valueOf(i);
					countsets += 1;
				} else {
					setstrings[pari] += "," + i;
				}
			}
			/* print strings */
			output = countsets + " set(s):\n";
			for (int i = 0; i < this.par.length; i++) {
				if (setstrings[i] != null) {
					output += i + " : " + setstrings[i] + "\n";
				}
			}
			return output;
		}

		/* find resentative of element i */
		public int find(int i) {

			if (par[i] == i) { // if parent of i is the same as i then we return
								// i
				return i;
			} else { // else call the function the parent of i
				par[i] = find(par[i]);
				return par[i];
			}

		}

		/* merge sets containing elements i and j */
		public int union(int i, int j) {
			if (find(i) != find(j)) {
				if (rank[find(i)] > rank[find(j)]) {
					par[find(j)] = find(i);
				}

				else if (rank[find(i)] == rank[find(j)]) {
					par[find(i)] = find(j);
					rank[find(j)]++;
				} else {

					par[find(i)] = find(j);
				}
			}

			return find(i);

		}

	}

}
