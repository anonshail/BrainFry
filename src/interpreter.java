import java.util.*;
import java.lang.*;

public class interpreter {
	
	//data members
	byte cells[];	//30k cells, which can hold 127 ASCII values
	int ptr;	//this variable holds the current active cell
	int no_of_cells;	//no of cells in memory
	private static Scanner in = new Scanner(System.in);	//input stream
	
	//member functions
	interpreter(){
		//default constructor		
		ptr = 0;	//the pointer is set to point to the first cell
		no_of_cells = 30000;
		this.init();	//clears the cells
	}
	
	interpreter(int length){
		//param constructor
		ptr = 0;	//the pointer is set to point to the first cell
		no_of_cells = length;
		this.init();	//clears the cells
	}
	
	public void init() {
		//this function initializes the cells and sets them all back to zero
		cells = new byte[no_of_cells];
		for(int i=0;i<no_of_cells; i++) {
			cells[i] = 0;
		}
	}
	
	public void interpret(String code) {
		//This is the main interpreter function
		//The variable code is the BrainFuck code passed to the function
		
		int c=0;	//variable to balance the loops and stuff I guess
		
		//checking for the 8 characters
		for(int i=0; i<code.length(); i++) {
			
			//if the char is '>', the pointer moves right
			if(code.charAt(i)=='>') {
				if(ptr == no_of_cells-1) {
					System.out.println("Memory full, shifting to cell 0");
					ptr=0;	//resetting pointer to 0
				}
				else
					ptr++;	//next cell
			}
			
			//if the char is '<', the pointer moves left
			else if(code.charAt(i)=='<') {
				if(ptr == 0) {
					System.out.println("Shifting to cell no " + no_of_cells);
					ptr=0;	//resetting pointer to 0
				}
				else
					ptr--;	//prev cell
			}
			
			//if the char is '+', the value of the current cell is incremented
			else if(code.charAt(i)=='+') {
					cells[i]++;
			}
			
			//if the char is '-', the value of the current cell is decremented
			else if(code.charAt(i)=='-') {
					cells[i]--;
			}
		
			//if the char is '.', it'll display the ASCII char at the current cell
			else if(code.charAt(i)=='.'){
				System.out.print((char)(cells[ptr]));
			}
			
			//if the char is ',', an ASCII char is taken as an input, and stored in the current cell
			else if(code.charAt(i)==','){
				cells[ptr] = (byte)(in.next().charAt(0));
			}
			
			// [ jumps past the matching ] if the cell  
            // under the pointer is 0 
            else if (code.charAt(i) == '[') 
            { 
                if (cells[ptr] == 0) 
                { 
                    i++; 
                    while (c > 0 || code.charAt(i) != ']') 
                    { 
                        if (code.charAt(i) == '[') 
                            c++; 
                        else if (code.charAt(i) == ']') 
                            c--; 
                        i++; 
                    } 
                } 
            } 
  
            // ] jumps back to the matching [ if the 
            // cell under the pointer is nonzero 
            else if (code.charAt(i) == ']') 
            { 
                if (cells[ptr] != 0) 
                { 
                    i--; 
                    while (c > 0 || code.charAt(i) != '[') 
                    { 
                        if (code.charAt(i) == ']') 
                            c++; 
                        else if (code.charAt(i) == '[') 
                            c--; 
                        i--; 
                    } 
                    i--; 
                } 
            }
			
            //if another char is inputted, the interpreter simply ignores it
			//this means that you can write comments, leave blanks spaces without breaking the code
		
		}//end of for loop
		
		//displaying the contents of the memory
		
	}
	
	public static void main(String args[]){
		interpreter i = new interpreter();
		System.out.println("Enter the brainfuck code: ");
		String code = in.next();
		i.interpret(code);
		System.out.println("Exitting. . . ");
	}
}
