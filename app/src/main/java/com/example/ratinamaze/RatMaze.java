package com.example.ratinamaze;

/* Java program to solve Rat in
a Maze problem using backtracking */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class RatMaze {

    Context context;
    // Size of the maze
    int N;

    public RatMaze(Context context, int n){
        this.context = context;
        N = n;
    }



    /* A utility function to print
    solution matrix sol[N][N] */
    void printSolution(int[][] sol)
    {
        TextView t;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
            {

                int id = Integer.parseInt(String.valueOf(i+1)+String.valueOf(j+1));
                t = ((Activity)context).findViewById(id);

                if (sol[i][j]==0){
                    t.setBackgroundColor(Color.GRAY);
                }
                else
                {
                    t.setBackgroundColor(Color.WHITE);
                }
            }
        }
    }

    /* A utility function to check
        if x, y is valid index for N*N maze */
    boolean isSafe(
            int[][] maze, int x, int y)
    {
        // if (x, y outside maze) return false
        return (x >= 0 && x < N && y >= 0
                && y < N && maze[x][y] == 1);
    }

    /* This function solves the Maze problem using
    Backtracking. It mainly uses solveMazeUtil()
    to solve the problem. It returns false if no
    path is possible, otherwise return true and
    prints the path in the form of 1s. Please note
    that there may be more than one solutions, this
    function prints one of the feasible solutions.*/
    boolean solveMaze(int[][] maze)
    {
        int[][] sol = new int[N][N];

        if (!solveMazeUtil(maze, 0, 0, sol)) {
            Toast.makeText(context, "Solution doesn't exist", Toast.LENGTH_SHORT).show();
            return false;
        }

        printSolution(sol);
        return true;
    }

    /* A recursive utility function to solve Maze
    problem */
    boolean solveMazeUtil(int[][] maze, int x, int y,
                          int[][] sol)
    {
        // if (x, y is goal) return true
        if (x == N - 1 && y == N - 1
                && maze[x][y] == 1) {
            sol[x][y] = 1;
            return true;
        }

        // Check if maze[x][y] is valid
        if (isSafe(maze, x, y)) {
            // Check if the current block is already part of solution path.
            if (sol[x][y] == 1)
                return false;

            // mark x, y as part of solution path
            sol[x][y] = 1;

            /* Move forward in x direction */
            if (solveMazeUtil(maze, x + 1, y, sol))
                return true;

			/* If moving in x direction doesn't give
			solution then Move down in y direction */
            if (solveMazeUtil(maze, x, y + 1, sol))
                return true;

			/* If moving in y direction doesn't give
			solution then Move backwards in x direction */
            if (solveMazeUtil(maze, x - 1, y, sol))
                return true;

			/* If moving backwards in x direction doesn't give
			solution then Move upwards in y direction */
            if (solveMazeUtil(maze, x, y - 1, sol))
                return true;

			/* If none of the above movements works then
			BACKTRACK: unmark x, y as part of solution
			path */
            sol[x][y] = 0;
            return false;
        }

        return false;
    }

    public void printmaze(int[][] maze) {
        TextView t;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
            {
                int id = Integer.parseInt(String.valueOf(i+1)+String.valueOf(j+1));
                t = ((Activity)context).findViewById(id);
                if (maze[i][j]==0)
                {
                    t.setBackgroundColor(Color.GRAY);
                }
                else{
                    t.setBackgroundColor(Color.WHITE);
                }
            }
        }
    }
}
