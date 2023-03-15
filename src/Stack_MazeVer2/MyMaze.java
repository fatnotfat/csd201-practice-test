/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack_MazeVer2;

/**
 *
 * @author ADMIN
 */
public class MyMaze {
    public static void main(String[] args) {
        String filename = "maze01.txt";
        Maze_Ver2 maze = new Maze_Ver2('E', 'M', '0', '1');
        maze.loadFromFile(filename);
        maze.print();
        maze.solve(); 
        if(maze.suceeded){
            System.out.println("Result Path: ");
            System.out.println(maze.getPath());
        }else 
            System.out.println("Failed");
    }
}
