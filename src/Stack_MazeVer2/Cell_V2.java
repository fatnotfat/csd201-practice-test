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
public class Cell_V2 {
    int row, col; 
    char value; 
    boolean blocked = false; //checking wall
    boolean visited = false; // cell can visited only one time
    Cell_V2 previous = null; //getting path
    
    //initial a cell 

    public Cell_V2(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }
    
    //blocking the cell - this cell is in the wall
    public void setBlock() {
        this.blocked = true; 
    }
    
    //testing whether the cell can be visited
    public boolean canBeVisited(){
        return !blocked && !visited;
    }

    @Override
    public String toString() {
        return "("+row+", "+col+", "+value+", "+")";
    }
}
