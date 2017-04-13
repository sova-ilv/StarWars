package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.SystemColor;

public class SpaceInvaders extends JFrame implements Config{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtLifeLeft;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpaceInvaders frame = new SpaceInvaders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpaceInvaders() {
		
		initComponents();
		createEvents();
		
		
	}

	/*
	 * This method all code for creating and initializing components
	 * */
	private void initComponents() {
		setTitle("Space Invader");
		
		Space space = new Space();
		space.setBackground(SystemColor.desktop);
		getContentPane().add(space);
		
		

        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        
		//setBounds(100, 100, 579, 539);
		//JPanel contentPane = new JPanel();
		//contentPane.setBackground(Color.WHITE);
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
	}

	/*
	 * This method all code for creating events
	 * */
	private void createEvents() {
		// TODO Auto-generated method stub
		
	}

}
