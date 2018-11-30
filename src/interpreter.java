import java.util.*;
import java.lang.*;



class errorcheck{
	//this class handles checking of syntax errors in the code
	
	//data members
	boolean allownonchar;	//flag to allow other characters or not
	
	
	
	//member methods
	public errorcheck(){
		//default constructor
		allownonchar=true;
	}
	
	public errorcheck(boolean flag){
		//parameterised constructor
		allownonchar=flag;
	}
	
	public void loopcheck(String code){
		//this function checks if the loops are opened and closed correctly
		//it will display and error and exit if the loops aren't balanced
		
		int l=0;	//no of active loops
		//l must be 0 at the start and end of the code
		//if l dips below zero or is non zero at the end of the code, loops are unbalanced
		
		for(int i=0; i<code.length(); i++){
			if(code.charAt(i)=='[')
				l++;
			else if(code.charAt(i)==']')
				l--;
			
			if(l<0){
				System.out.println("Loops unbalanced. Check character no : " + i);
				System.out.println("Terminating. . .");
				System.exit(-1);
			}
			
		}
	}
	
	public void charcheck(String code){
		//this function checks if only legal characters or whitespaces are present
		if(allownonchar)
			;
		else {
			for(int i=0; i<code.length(); i++){
				switch(code.charAt(i)) {
					case ' ':
						break;
					case '\t':
						break;
					case '>':
						break;
					case '<':
						break;
					case '+':
						break;
					case '-':
						break;
					case '[':
						break;
					case ']':
						break;
					case '.':
						break;
					case ',':
						break;
					default:
						System.out.println("Illegal character found at position : " + i);
						System.out.println("Exitting. . . ");
						System.exit(-1);
						break;
				}
			}
		}
	}
}




public class interpreter extends errorcheck{
	
	//data members
	byte cells[];	//30k cells, which can hold 127 ASCII values
	int ptr;	//this variable holds the current active cell
	int no_of_cells;	//no of cells in memory
	private static Scanner in = new Scanner(System.in);	//input stream
	int inp[];
	int inpptr=0;
	String fop;
	
	
	//member functions
	interpreter(int input1[]){
		//default constructor		
		super();	//calling parent class constructor
		ptr = 0;	//the pointer is set to point to the first cell
		no_of_cells = 30000;
		this.init();	//clears the cells
		inp = input1;
		fop="";
	}
	
	/*interpreter(int length){
		//param constructor
		super();	//calling parent class constructor
		ptr = 0;	//the pointer is set to point to the first cell
		no_of_cells = length;
		this.init();	//clears the cells
	}*/
	
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
		
		int l=0;	//variable to balance the loops and stuff I guess
		
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
					ptr=no_of_cells-1;	//resetting pointer to 0
				}
				else
					ptr--;	//prev cell
			}
			
			//if the char is '+', the value of the current cell is incremented
			else if(code.charAt(i)=='+') {
					cells[ptr]++;
			}
			
			//if the char is '-', the value of the current cell is decremented
			else if(code.charAt(i)=='-') {
					cells[ptr]--;
			}
		
			//if the char is '.', it'll display the ASCII char at the current cell
			else if(code.charAt(i)=='.'){
				fop = fop + (char)(cells[ptr]);
			}
			
			//if the char is ',', an ASCII char is taken as an input, and stored in the current cell
			else if(code.charAt(i)==','){
				cells[ptr] = (byte)(inp[inpptr++]);
			} 
		
			else if(code.charAt(i) == '[') {
	            if(cells[ptr] == 0) {
	                i++;
	                while(l > 0 || code.charAt(i) != ']') {
	                    if(code.charAt(i) == '[') l++;
	                    if(code.charAt(i) == ']') l--;
	                    i++;
	                }
	            }
			} 
			else if(code.charAt(i) == ']') {
	            if(cells[ptr] != 0) {
	                i--;
	                while(l > 0 || code.charAt(i) != '[') {
	                    if(code.charAt(i) == ']') l++;
	                    if(code.charAt(i) == '[') l--;
	                    i--;
	                }
	                i--;
	            }
	        }
			
            //if another char is inputted, the interpreter simply ignores it
			//this means that you can write comments, leave blanks spaces without breaking the code
		
		}//end of for loop	
	}
	
	public static void main(String args[]){
		interpreter i = new interpreter(new int[10]);
		System.out.println("Enter the brainfuck code: ");
		String code = in.next();
		i.interpret(code);
		System.out.println(i.fop);
		System.out.println("Exitting. . . ");
	}
}


