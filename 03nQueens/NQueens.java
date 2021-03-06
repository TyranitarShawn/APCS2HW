import java.util.*;
import java.io.*;

public class NQueens{

    final static String clear =  "\033[2J";
    final static String hide =  "\033[?25l";
    final static String show =  "\033[?25h";
    public char[][]board;
    
    public String go(int x,int y){
	return ("\033[" + x + ";" + y + "H");
    }
 
    public void wait(int millis){
	try {
	    Thread.sleep(millis);
	}
	catch (InterruptedException e) {
	}
    }

    public String name(){
	return "li.shawn";
    }

    public String toString(){
	String ans = "\n";
	int row = 0; 
	while (row < board.length){
	    int col = 0; 
	    while (col < board[0].length){
		ans += " " + board[row][col];
		col += 1;
	    }
	    ans += "\n";
	    row += 1;
	}
	return hide + clear + go(0,0) + ans + "\n" + show;}

    public NQueens(int size){
	board = new char[size][size];
	int row = 0; 
	while (row < size){
	    int col = 0; 
	    while (col < size){
		board[row][col] = '_';
		col+=1;
	    }
	    row += 1;
	}
    }
    
    public boolean solve(){
	return solve(0, board.length);
    }
    
    public boolean solve(int col){
	if (col < 0 || col > board.length){
	    return false;
	}
	board[0][col] = 'Q';
	return solve(1, board.length - 1);}

    public boolean solve(int row, int n){
	if (n == 0){
	    return true;
	}
	int col = 0;
	while (col < board.length){
	    if (check(col, row)){
	        board[row][col] = 'Q';
		if (solve(row + 1, n - 1)){
		    return true;
		}
		board[row][col] = '_';
	    }
	    col += 1;
	}
	return false;
    }

    public boolean check(int counter, int checkRow){
	int row = checkRow;
        while (row >= 0){
	    if (board[row][counter] == 'Q'){
		return false;
	    }
	    row-=1;
	}
	int col = counter;
	row = checkRow;
	while(col >= 0 && row >= 0){
	    if (board[row][col] == 'Q'){
		return false;
	    }
	    col--;
	    row--;
	}
        while(counter < board.length && checkRow >= 0){
	    if (board[checkRow][counter] == 'Q'){
		return false;
	    }
	    counter++;
	    row--;
	}
	return true;
    }

}
