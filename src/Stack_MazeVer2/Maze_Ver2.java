/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stack_MazeVer2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ADMIN
 */
public class Maze_Ver2 {
    char entryChar = 'E', destChar = 'M';
    char emptyChar = '0', blockedChar = '1';
    int rows = 0, cols = 0;
    Cell_V2[][] cells = null; //create map of the maze; 
    Cell_V2 entryCell = null; //entry pos
    Cell_V2 destCell = null; //exit pos or dest 
    boolean completed = false; 
    boolean suceeded = false; 
    
    //constructor 

    public Maze_Ver2() {
    }
    
    public Maze_Ver2(char entryChar, char destChar, char emptyChar, char blockedChar){
        this.entryChar = entryChar;
        this.destChar = destChar; 
        this.emptyChar = emptyChar;
        this.blockedChar = blockedChar;
    }
    
    //print maze
    public void print(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(cells[i][j].value);
            }
            System.out.println();
        }
    }
    
    //test maze is valid or not
    private boolean isValid(int row, int col){
        return row >= 0 && row < rows && col >= 0 && col < cols &&
                cells[row][col].canBeVisited();
    }
    
    //getting 4 adjancency cells of the current cell 
    private ArrayList<Cell_V2> getAdjs(Cell_V2 curCell){
        ArrayList<Cell_V2> adjs = new ArrayList();
        int row = curCell.row; 
        int col = curCell.col;
        
        if(isValid(row - 1, col)){
            cells[row-1][col].previous = curCell;
            adjs.add(cells[row-1][col]);
        }
        
        if(isValid(row + 1, col)){
            cells[row+1][col].previous = curCell;
            adjs.add(cells[row+1][col]);
        }
        
        if(isValid(row, col-1)){
            cells[row][col-1].previous = curCell;
            adjs.add(cells[row][col-1]);
        }
        
        if(isValid(row, col+1)){
            cells[row][col+1].previous = curCell;
            adjs.add(cells[row][col+1]);
        }
        return adjs;
    }
    
    //load a maze from a text file 
    public boolean loadFromFile(String filename){
        File f = new File(filename);
        if(!f.exists()){ //check file existence
            System.out.println("This file: "+filename+" doesn's existed");
            System.exit(0);
    }
        try{
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            ArrayList<String> list = new ArrayList<>();
            String line; 
            
            //load all lines in the file to list
            while((line = bf.readLine()) != null ){
                line = line.trim();
                if(line.length() > 0 ) list.add(line.toUpperCase());
            }
            bf.close();
            fr.close();
            
            //creat matrix from the list 
            this.rows = list.size(); //number of rows
            this.cols = list.get(0).length(); //number of cols
            this.cells = new Cell_V2[rows][cols];  //allocating memory from matrix
            
            for(int i = 0 ; i < list.size() ; i++ ){
                line = list.get(i);
                for(int j = 0 ; j < cols ; j++ ){
                    char ch = line.charAt(j);
                    cells[i][j] = new Cell_V2(i, j, ch);
                    if(ch == blockedChar )cells[i][j].setBlock();
                    //determine the entry and dest
                    else if( ch == entryChar ) this.entryCell = cells[i][j];
                    else if( ch == destChar ) this.destCell = cells[i][j];
                }
            }
        } catch(Exception e ){
            System.out.println(e);
            System.exit(0);
        }
        return true; 
    }
    
    public boolean solve(){
        LinkedList<Cell_V2> stack = new LinkedList();
        Cell_V2 curCell = this.entryCell;
        while(!completed){ //finding a solution
            curCell.visited = true; //marking curCell as visited
            if(curCell == this.destCell) //suceeded
                completed = suceeded = true; 
            else { 
                ArrayList<Cell_V2> adjs = getAdjs(curCell);
                //if ther are adjancency cells to move to 
                if(adjs.size()> 0 ){
                    curCell = adjs.get(0); //move to next cell 
                    for(int i = 1 ; i < adjs.size(); i++)
                        stack.addFirst(adjs.get(i));
                }
                
                else if (!stack.isEmpty()) //if having a cell can be examined
                    curCell = stack.removeFirst(); //popping a cell from stack
                
                else{ // stack is empty -> failed
                    completed = true; 
                    suceeded = false; 
                }
            }
        }
        return completed; 
    }
    
    public LinkedList<Cell_V2> getPath(){
        if(!suceeded) return null; 
        LinkedList<Cell_V2> path = new LinkedList<>();
        Cell_V2 cell = this.destCell; 
        while(cell!= null ){
            path.addFirst(cell);
            cell = cell.previous; 
        }
        return path;
    }
}