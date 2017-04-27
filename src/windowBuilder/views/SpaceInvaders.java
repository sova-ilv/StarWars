/*
 * SpaceInvader.java
 * - Version 4/13/2017 - compiles and actually run with basic features. (JV)
 * - Version 4/21/2017 - Added Menubar to contain upcoming features.
 *                     - Completed the game restart feature.
 * 
 * Here are the wish list on my initial requirements
 * 1. Restart button - Done 4-21-17 
 * 2. Stats status => how many lives left, how many enemies left
 * 3. colors are boring, please update
 * 4. Icons are boring, please update
 * 5. Config may not be enough.
 * 
 * */


package windowBuilder.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class SpaceInvaders extends JFrame implements Config{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Space pSpace;
    private JPanel pContentPane;
    static SpaceInvaders pframe;

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
					pframe = new SpaceInvaders();
					pframe.setVisible(true);
				    pframe.setupMenuBar();
					
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
		setTitle("Star Wars Game");
		        
		setBounds(100, 100, 579, 539);
		pContentPane = new JPanel();
		pContentPane.setBackground(Color.WHITE);
		pContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		pContentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(pContentPane);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(466, 450);
        setLocationRelativeTo(null);
        setResizable(true);
        
        pSpace = new Space();
        pContentPane.add(pSpace);
               
	}
	
	class MenuActionListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		    //System.out.println("Selected: " + e.getActionCommand());
		    JOptionPane.showMessageDialog(null, "New Game");
		    
		    resetSpaceParams();		    
		  }
		}
	
	public void setupMenuBar () {
		JMenuBar menuBar;
		JMenu menu; //, submenu;
		JMenuItem menuItem;
		
		 
		//Create the menu bar.
		menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);
		
		//a group of JMenuItems
		menuItem = new JMenuItem("start new game",  KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		//menuItem.getAccessibleContext().setAccessibleDescription(
		//        "This doesn't really do anything");
		menu.add(menuItem);
		menuItem.addActionListener(new MenuActionListener());
		
		
		pframe.setJMenuBar(menuBar);
		}
	
	public void resetSpaceParams() {
		pSpace.initSpace();
		
	}

	/*
	 * This method all code for creating events
	 * */
	private void createEvents() {
		// TODO Auto-generated method stub
		
	}

}
