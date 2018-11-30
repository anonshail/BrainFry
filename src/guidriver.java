    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;  
    public class guidriver implements ActionListener{  
        JTextField tf1,tf2,tf3, tf4;  
        JButton b1,b2;  
        JLabel l1;
        guidriver(){
            JFrame f= new JFrame("BrainFry Interpreter");
            l1=new JLabel("BRAINFRY");  
            l1.setBounds(50,50, 100,30);  
            tf1=new JTextField("STDIN");  
            tf1.setBounds(50,100,150,20);  
            tf2=new JTextField("CODE");  
            tf2.setBounds(50,150,150,20);  
            tf3=new JTextField("Output");  
            tf3.setBounds(50,200,150,20);  
            tf3.setEditable(false);
            tf4=new JTextField("Memory Status");
            tf4.setBounds(50,250,150,20);  
            tf4.setEditable(false);
            b1=new JButton("Run");  
            b1.setBounds(75,300,100,50);    
            b1.addActionListener(this);  
            f.add(tf1);f.add(tf2);f.add(tf3);f.add(b1);f.add(tf4);f.add(l1);  
            f.setSize(250,400);  
            f.setLayout(null);  
            f.setVisible(true);  
        }         
        public void actionPerformed(ActionEvent e) {  
            String rawip=tf1.getText();  
            String code=tf2.getText();  
            
            int finalip[];
            int temp[] = new int[1];
            temp[0]=0;
                     
            if(rawip.equals("STDIN")) {
            	finalip=temp;
            	
            }
            else {
	        	String ip[] = rawip.split(" ");
	            finalip = new int[ip.length];
	            for(int i=0;i<ip.length; i++) {
	            	finalip[i] = Integer.parseInt(ip[i]); 
	            }
            }
            interpreter inter;
            
            if(e.getSource()==b1){  
            	inter = new interpreter(finalip);
            
            	inter.interpret(code);
            
	            String memstat="";
	            for(int i=0; i<10; i++) {
	            	memstat = memstat + " " + inter.cells[i];
	            }
	            
	            tf3.setText(inter.fop);
	            tf4.setText(memstat); 
            
            }
            
        }  
    public static void main(String[] args) {  
        new guidriver();  
    } }  