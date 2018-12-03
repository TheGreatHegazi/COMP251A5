import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class mancala {

	public static int numPebbles = 0;

	ArrayList<int[]> reference = new ArrayList<int[]>();

	mancala(int[] board) {

		numPebbles = Integer.MAX_VALUE;

		if (!noMoves(board)) {
			board = solve(board);
			int[] answer = new int[reference.size()];
			for (int j = 0; j < reference.size(); j++) {
				for (int i = 0; i < reference.get(j).length; i++) {
					if (reference.get(j)[i] == 1) {
						answer[j]++;
					}
				}

			}
			for (int i = 0; i < answer.length; i++) {
				if (numPebbles > answer[i]) {
					numPebbles = answer[i];
				}
			}
		} else {
			numPebbles = 0;
			for (int i = 0; i < board.length; i++) {
				if (board[i] == 1) {
					numPebbles++;
				}
			}
		}
	}

	public boolean noMoves(int[] board) {
		for (int i = 0; i < board.length; i++) {
			if (isMoveable(board, i) != 0) {
				return false;
			}
		}
		return true;
	}

	public int[] solve(int[] board) {

		ArrayList<int[]> possible = possibleMoves(board);

		for (int i = 0; i < possible.size(); i++) {
			if (!noMoves(possible.get(i))) {
				solve(possible.get(i));
			} else {
				reference.add(possible.get(i));
			}
		}

		return board;
	}

	public int getPeb() {
		return numPebbles;
	}

	public ArrayList<int[]> possibleMoves(int[] board) {
		ArrayList<int[]> possible = new ArrayList<int[]>();
		int possibleCount = 0;
		for (int i = 0; i < board.length; i++) {
			if (isMoveable(board, i) == -1) {
				possible.add(board.clone());
				moveLeft(possible.get(possibleCount), i);
				possibleCount++;
			} else if (isMoveable(board, i) == 1) {
				possible.add(board.clone());
				moveRight(possible.get(possibleCount), i);
				possibleCount++;
			}
		}
		return possible;

	}

	public int isMoveable(int[] board, int index) {
		if (board[index] == 1) {
			if (index > 1) {
				if (board[index - 1] == 1 && board[index - 2] == 0) {
					return -1;
				}
			}

			if (index < board.length - 2) {
				if (board[index + 1] == 1 && board[index + 2] == 0) {
					return 1;

				}
			}
		}
		return 0;
	}

	public int[] moveLeft(int[] array, int index) {
		array[index] = 0;
		array[index - 1] = 0;
		array[index - 2] = 1;
		return array;
	}

	public int[] moveRight(int[] array, int index) {
		array[index] = 0;
		array[index + 1] = 0;
		array[index + 2] = 1;
		return array;
	}

	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		String file = args[0];
		try {
			Scanner input = new Scanner(new File(file));

			int numberOfProblems = Integer.parseInt(input.nextLine());

			String destination = file.replaceFirst(".txt", "_solution.txt");
			PrintWriter writer;
			try {
				writer = new PrintWriter(new FileWriter(destination));

				while (input.hasNext()) {
					String[] heights = input.nextLine().split("\\s");
					int[] board = new int[heights.length];
					for (int i = 0; i < board.length; i++) {
						board[i] = Integer.parseInt(heights[i]);
					}
					mancala problem = new mancala(board);
					String output = problem.getPeb() + "\n";
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
