/*
 * sam woodburn
 * csc111 project 2
 * 10/12/23
 * recursive method to read a maze file them traverse through it and find the exit
 * 
 */

//import scanner, file, and file not found exception
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Maze {

	//instance variables
    private static final char CLEAR = ' ';
    private static final char PATH = '.';

    public static void main(String[] args) {

    	//try to read the file, catch the error if file is not readable
        try {

            // Initialize variables
            String line = " ";
            Scanner scanner = new Scanner(new File("mazeS.txt"));

            // Read maze dimensions and start/finish coordinates
            int numCols = scanner.nextInt();
            int numRows = scanner.nextInt();
            int startx = scanner.nextInt();
            int starty = scanner.nextInt();
            int finishx = scanner.nextInt();
            int finishy = scanner.nextInt();
            // Consume the newline character after reading the integers
            line = scanner.nextLine();

            // Initialize a 2D array to store the maze
            char maze[][] = new char[numRows][numCols];

            // Read the maze layout line by line and save it to maze array
            for (int row = 0; row < numRows; row++) {
                line = scanner.nextLine();
                for (int col = 0; col < numCols; col++) {
                    maze[row][col] = line.charAt(col);
                }
            }

            // Close the scanner when done
            scanner.close();


            // print the mazes info  			
            System.out.println("Rows: " + numRows + "\tColumns: " + numCols + "\nStarting Indices: (" + startx + ", " + starty + ")\nFinishing Indices: (" + finishx + ", "  + finishy +")");

            //display the maze unsolved
            System.out.println("\nMaze:");
            display(maze);
            
            // solve the maze
            if (solveMaze(maze,startx,starty,finishx,finishy)) {
            	//display the solved maze
            	System.out.println("\nMaze solved:");
                display(maze);
            } else {
            	//the maze is not solvable
                System.out.println("No solution found.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not readable");
        }
    }

    //method to solve the maze
    private static boolean solveMaze(char[][] maze, int x, int y, int finishx, int finishy) {
        // Base case: If we reach the finish cell, the maze is solved and return true
        if (x == finishx && y == finishy+1) {
            return true;
        }

        // Check if the current cell is valid and not a wall or visited
        if (isValidCell(maze, x, y)) {
            // Mark the current cell as visited
            maze[y][x] = PATH;

            // Try to move in all possible directions (up, down, left, right)
            if (solveMaze(maze, x - 1, y, finishx, finishy) || solveMaze(maze, x + 1, y, finishx, finishy) || solveMaze(maze, x, y - 1, finishx, finishy) || solveMaze(maze, x, y + 1, finishx, finishy)) {
                //return true if you were able to move in that direction
            	return true;
            }

            // If none of the directions lead to the finish, mark the current cell as unvisited and backtrack
            maze[y][x] = CLEAR;
        }

        return false;
    }

    //method to check if the current cell is open
    private static boolean isValidCell(char[][] maze, int x, int y) {
    	//check that it is within bounds and not a wall or visited path
        return (x >= 0 && x < maze[0].length && y >= 0 && y < maze.length && maze[y][x] == CLEAR);
    }
    
    //method to print the maze array
    private static void display(char[][]maze) {
    		//loop through and print each character
    		for(int x = 0; x < maze.length; x++) {
    			for(int y = 0; y < maze[x].length; y++) {
   					System.out.print(maze[x][y] + " ");
    			}
    			System.out.println();
    		}
    }
    
}






