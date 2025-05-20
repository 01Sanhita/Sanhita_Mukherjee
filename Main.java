package Tic_Tac_Toe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Main {
    private static char turn='X';
    private static JLabel title2;
    private static ArrayList<JLabel> labels=new ArrayList<JLabel>();
    private static String winner=" ";
    private static char[][] chars=new char[3][3];
    private static boolean enabled =true;
	
    public static void main(String[] args) {
		JFrame frame=new JFrame("Tic Tac Toe");
		frame.setSize(340,470);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setBackground(Color.white);
        
        JLabel title=new JLabel("Tic Tac Toe");
        title.setBackground(Color.white);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        title.setFont(new Font("Tahoma",Font.BOLD,30));
        frame.add(title,BorderLayout.NORTH);
        
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3,3,7,7));
        panel.setBackground(Color.black);
        title2=new JLabel("Player 1, it's your turn");
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setBackground(Color.white);
        title2.setOpaque(true);
        title2.setFont(new Font("Tahoma",Font.BOLD,25));
        title2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for(int i=0;i<3;i++) {
        	for(int j=0;j<3;j++) {
        		JLabel label=new JLabel(" ");
        		label.setOpaque(true);
        		label.setBackground(Color.white);
        		label.setFont(new Font("Tahoma",Font.BOLD,30));
        		label.setHorizontalAlignment(SwingConstants.CENTER);
        		int m=i,n=j;
        		label.addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent e) {
						if(enabled) {
							if(label.getText().toString().equals(" ")) {
								label.setText(String.valueOf(turn));
								chars[m][n]=turn;
								if(turn=='X') {
									label.setForeground(Color.blue);
									turn='O';
									title2.setText("Player 2, it's your turn");
								}
								else {
									label.setForeground(Color.red);
									turn='X';
									title2.setText("Player 1, it's your turn");
								}
							}
							else {
								title2.setText("Position already taken!!");
							}
							if(checkWinner() && winner.equals("X")) {
								title2.setText("Player 1 wins!!");
								title2.setForeground(Color.blue);
								enabled=false;
							}
							else if(checkWinner() && winner.equals("O")) {
								title2.setText("Player 2 wins!!");
								title2.setForeground(Color.red);
								enabled=false;
							}
							else if(!draw()) {
								title2.setText("Draw!!");
								title2.setForeground(Color.green);
								enabled=false;
							}
						}
					}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {}
        			
        		});
        		labels.add(label);
        		panel.add(label);
        	}
        }
        frame.add(panel,BorderLayout.CENTER);
        
        JPanel p=new JPanel(new BorderLayout());
        p.setBackground(Color.white);
        
        p.add(title2, BorderLayout.CENTER);
        
        JButton b=new JButton("New Game");
        b.setBackground(Color.black);
        b.setForeground(Color.yellow);
        b.setFont(new Font("Tahoma",Font.BOLD,18));
        b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reboot();
			}
        });
        p.add(b, BorderLayout.SOUTH);
        frame.add(p,BorderLayout.SOUTH);
        frame.setVisible(true);       
	}

    private static boolean checkWinner() {
    	boolean win=false;
    	if(chars[0][0]==chars[0][1] && chars[0][0]==chars[0][2] && chars[0][0]!=0) {
    		win=true;
    		winner=String.valueOf(chars[0][0]);
    	}
    	else if(chars[1][0]==chars[1][1] && chars[1][0]==chars[1][2] && chars[1][0]!=0) {
    		win=true;
    		winner=String.valueOf(chars[1][0]);
    	}
    	else if(chars[2][0]==chars[2][1] && chars[2][0]==chars[2][2] && chars[2][0]!=0) {
    		win=true;
    		winner=String.valueOf(chars[2][0]);
    	}
    	else if(chars[0][0]==chars[1][0] && chars[0][0]==chars[2][0] && chars[0][0]!=0) {
    		win=true;
    		winner=String.valueOf(chars[0][0]);
    	}
    	else if(chars[0][1]==chars[1][1] && chars[0][1]==chars[2][1] && chars[0][1]!=0) {
    		win=true;
    		winner=String.valueOf(chars[0][1]);
    	}
    	else if(chars[0][2]==chars[1][2] && chars[0][2]==chars[2][2] && chars[0][2]!=0) {
    		win=true;
    		winner=String.valueOf(chars[0][2]);
    	}
    	else if(chars[0][0]==chars[1][1] && chars[0][0]==chars[2][2] && chars[0][0]!=0) {
    		win=true;
    		winner=String.valueOf(chars[0][0]);
    	}
    	else if(chars[2][0]==chars[1][1] && chars[2][0]==chars[0][2] && chars[2][0]!=0) {
    		win=true;
    		winner=String.valueOf(chars[2][0]);
    	}
    	return win;
    }
    
    private static void reboot() {
    	chars=new char[3][3];
    	winner=" ";
    	title2.setText("Player 1, it's your turn");
    	title2.setForeground(Color.black);
    	turn='X';
    	enabled=true;
    	for(JLabel label:labels) {
    		label.setText(" ");
    	}
    }
    private static boolean draw() {
    	boolean draw=false;
    	for(char a[]:chars) {
    		for(char c:a) {
    			if(c==0) {
    				draw=true;
    				break;
    			}
    		}
    	}
    	return draw;
    }
}
