package comp_net;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.*;

class enc extends JFrame{
	 	JLabel l1, l2;
	    JTextField t1;
	    JComboBox<String> c1;
	    JButton b1;

	    enc() {
	        setLayout(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);

	        l1 = new JLabel("Line Coding Simulator");
	        l1.setBounds(250, 10, 300, 50);
	        add(l1);

	        l2 = new JLabel("Digital Sequence:");
	        l2.setBounds(200, 50, 300, 30);
	        add(l2);

	        t1 = new JTextField();
	        t1.setBounds(300, 50, 150, 25);
	        add(t1);

	        String state[] = {"Uni-Polar NRZ", "Bi-Polar NRZ-L", "Bi-Polar NRZ-I", "Polar RZ", "Manchester", "Differential Manchester"};
	        c1 = new JComboBox<>(state);
	        c1.setBounds(250, 80, 150, 25);
	        add(c1);

	        b1 = new JButton("Draw Graph");
	        b1.setBounds(100, 150, 400, 50);
	        add(b1);

	        b1.addActionListener((ActionListener) new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {

	                String lent = t1.getText();
	                String[] digits = String.valueOf(lent).split("");
	                String type = (String) c1.getSelectedItem();
	                int len = digits.length;
	                int[] input1  = new int[len];
	                int[] input  = new int[2*len];
	                int temp,last;
	                //dispose();
	                switch (type){
	                case "Uni-Polar NRZ" : {
	                    for(int i=0;i<len;i++){
	                        input1[i]=Integer.parseInt(digits[i]);
	                    }
	                    LineEncoding en = new LineEncoding(input1,1);
	                    break;
	                 }
	                case "Bi-Polar NRZ-L" : {               
	                    for(int i=0;i<len;i++){
	                        temp=Integer.parseInt(digits[i]);
	                        if (temp ==0){
	                            input1[i] = 1;
	                        } else if (temp == 1) {
	                            input1[i]=-1;
	                        }
	                    }
	                    LineEncoding enc = new LineEncoding(input1,1);
	                    break;
	                 }
	                case  "Bi-Polar NRZ-I" : {
	                    last = 1;
	                    for(int i=0;i<len;i++){
	                        temp =Integer.parseInt(digits[i]);
	                        if (temp ==0){
	                            input1[i] = last;
	                        } else if (temp == 1) {
	                            if(last==1){
	                                input1[i]=-1;
	                                last= -1;
	                            } else if (last ==-1) {
	                                input1[i]=1;
	                                last = 1;
	                            }
	                        }
	                    }
	                    LineEncoding ena = new LineEncoding(input1,1);
	                    break;
	                 }
	                case "Polar RZ" : {
	                    for(int i=0;i<len;i++){
	                        temp=Integer.parseInt(digits[i]);
	                        if (temp ==0){
	                            input[2*i] = -1;
	                        } else if (temp == 1) {
	                            input[2*i] = 1;
	                        }
	                        input[2*i+1]= 0;
	                    }
	                    LineEncoding enb = new LineEncoding(input, 0.5);
	                    break;
	                 }
	                case "Manchester" : {
	                    for(int i=0;i<len;i++){
	                        temp=Integer.parseInt(digits[i]);
	                        if (temp == 0){
	                            input[2*i]=1;
	                            input[2*i+1]=-1;
	                        }else {
	                            input[2*i]=-1;
	                            input[2*i+1]=1;
	                        }
	                    }
	                    LineEncoding end = new LineEncoding(input, 0.5);
	                    break;
	                 }
	                case "Differential Manchester" : {
	                    last = 1;
	                    for(int i=0;i<len;i++){
	                        temp=Integer.parseInt(digits[i]);
	                        if ((temp == 0 && last ==-1)||(temp==1 && last == 1)){
	                            input[2*i]=1;
	                            input[2*i+1]=-1;
	                            last = -1;
	                        }else {
	                            input[2*i]=-1;
	                            input[2*i+1]=1;
	                            last= 1;
	                        }
	                    }
	                    LineEncoding ene = new LineEncoding(input,0.5);
	                    break;
	                 }
	           
	                }}
	        });

	}
}

	public class encode {
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            enc s = new enc();
	            s.setBounds(400, 400, 600, 300);
	            s.setVisible(true);
	        });
	    }
	}
    