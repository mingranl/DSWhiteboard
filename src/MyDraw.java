import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class MyDraw extends JFrame {

	private JFrame frame;
	MyPanel mp = null;
	Graphics2D ggg;
	JButton line_btn;
	JButton circle_btn;
	JButton oval_btn;
	JButton rect_btn;
	JButton erase_btn;
	JButton free_btn;
	JButton clean_btn;
	JButton open;
	JButton save;
	JButton save_as;
	JComboBox jcb_color;
	JComboBox jcb_stroke;
	
	private int px = 0;
	private int py = 0;
	private int rx = 0;
	private int ry = 0;
	private int color=0;
	private int stroke=0;
	private int flag=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyDraw window = new MyDraw();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyDraw() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("My Draw");
		frame.setBounds(30, 50, 1800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		mp = new MyPanel();
		mp.setBackground(Color.WHITE);
		mp.setBounds(210, 100, 1300, 500);
		mp.setLayout(null);
		//mp.setOpaque(false);
		frame.getContentPane().add(mp);
		
		String strColor[] = { "black", "blue", "cyan", "dark gray", "gray", "light gray", "green", "magenta", "orange",
				"pink", "red", "white", "yellow" };
		jcb_color = new JComboBox(strColor);
		jcb_color.setBounds(1550, 150, 100, 30);
		jcb_color.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index=jcb_color.getSelectedIndex();
				System.out.println(index);
				color=index;
			}
		});
		frame.getContentPane().add(jcb_color);
		
		String strStroke[]={"1","2","3","4","5"};
		jcb_stroke=new JComboBox(strStroke);
		jcb_stroke.setBounds(1550, 400, 100, 30);
		jcb_stroke.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int index=jcb_stroke.getSelectedIndex();
				System.out.println(index);
				stroke=index;
			}
		});
		frame.getContentPane().add(jcb_stroke);
		
		//open button
		open=new JButton("Open");
		open.setBounds(10,10,100,30);
		frame.getContentPane().add(open);
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.Image("C:\\123.jpg");
				mp.repaint();
			}
			
		});
		
		//save button
		save=new JButton("Save");
		save.setBounds(160,10,100,30);
		frame.getContentPane().add(save);
		
		//save as button
		save_as=new JButton("Save As...");
		save_as.setBounds(310,10,100,30);
		frame.getContentPane().add(save_as);
		
		//free draw button
		free_btn=new JButton("free draw");
		free_btn.setBounds(50, 100, 100, 30);
		frame.getContentPane().add(free_btn);
		free_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("clicked free draw button\n");
				MouseListener[] listeners = mp.getMouseListeners();
				for (MouseListener listener : listeners) {
					mp.removeMouseListener(listener);
				}
				MouseMotionListener[] mmls=mp.getMouseMotionListeners();
				for(MouseMotionListener mml:mmls){
					mp.removeMouseMotionListener(mml);
				}
				mp.addMouseMotionListener(new MouseAdapter(){
					public void mouseDragged(MouseEvent e){
						px=e.getX();
						py=e.getY();
						mp.freeDraw(px, py, color, stroke);
						mp.repaint();
					}
				});
			}
		});
			
		//draw line button
		line_btn=new JButton("Draw Line");
		line_btn.setBounds(50, 150, 150, 30);
		frame.getContentPane().add(line_btn);
		line_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("clicked draw line button\n");
				MouseListener[] listeners = mp.getMouseListeners();
				for (MouseListener listener : listeners) {
					mp.removeMouseListener(listener);
				}
				MouseMotionListener[] mmls=mp.getMouseMotionListeners();
				for(MouseMotionListener mml:mmls){
					mp.removeMouseMotionListener(mml);
				}
				mp.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						px = e.getX();
						py = e.getY();
						System.out.println("mouse pressed at point (" + px + "," + py + ")\n");
					}
					public void mouseReleased(MouseEvent e) {
						rx = e.getX();
						ry = e.getY();
						System.out.println("mouse released at point (" + rx + "," + ry + ")\n");
						mp.drawLine(px, py, rx, ry,color,stroke);
						mp.repaint();
					}
				});
			}
		});
		
		//draw circle button
		circle_btn = new JButton("Draw Circle");
		circle_btn.setBounds(50, 200, 150, 30);
		frame.getContentPane().add(circle_btn);
		circle_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("clicked draw oval button\n");
				MouseListener[] listeners = mp.getMouseListeners();
				for (MouseListener listener : listeners) {
					mp.removeMouseListener(listener);
				}
				MouseMotionListener[] mmls=mp.getMouseMotionListeners();
				for(MouseMotionListener mml:mmls){
					mp.removeMouseMotionListener(mml);
				}
				mp.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						px = e.getX();
						py = e.getY();
						System.out.println("mouse pressed at point (" + px + "," + py + ")\n");
					}
					public void mouseReleased(MouseEvent e) {
						rx = e.getX();
						ry = e.getY();
						System.out.println("mouse released at point (" + rx + "," + ry + ")\n");
						mp.drawCircle(px, py, rx, ry,color,stroke);
						mp.repaint();
					}
				});
			}
		});

		//draw oval button
		oval_btn = new JButton("Draw Oval");
		oval_btn.setBounds(50, 250, 150, 30);
		frame.getContentPane().add(oval_btn);
		oval_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("clicked draw oval button\n");
				MouseListener[] listeners = mp.getMouseListeners();
				for (MouseListener listener : listeners) {
					mp.removeMouseListener(listener);
				}
				MouseMotionListener[] mmls=mp.getMouseMotionListeners();
				for(MouseMotionListener mml:mmls){
					mp.removeMouseMotionListener(mml);
				}
				mp.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						px = e.getX();
						py = e.getY();
						System.out.println("mouse pressed at point (" + px + "," + py + ")\n");
					}
					public void mouseReleased(MouseEvent e) {
						rx = e.getX();
						ry = e.getY();
						System.out.println("mouse released at point (" + rx + "," + ry + ")\n");
						mp.drawOval(px, py, rx, ry,color,stroke);
						mp.repaint();
					}
				});
			}
		});

		//draw rectangle button
		rect_btn = new JButton("Draw rectangle");
		rect_btn.setBounds(50, 300, 150, 30);
		frame.getContentPane().add(rect_btn);
		rect_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("clicked draw rectangle button\n");
				MouseListener[] listeners = mp.getMouseListeners();
				for (MouseListener listener : listeners) {
					mp.removeMouseListener(listener);
				}
				MouseMotionListener[] mmls=mp.getMouseMotionListeners();
				for(MouseMotionListener mml:mmls){
					mp.removeMouseMotionListener(mml);
				}
				mp.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						px = e.getX();
						py = e.getY();
						System.out.println("mouse pressed at point (" + px + "," + py + ")\n");
					}
					public void mouseReleased(MouseEvent e) {
						rx = e.getX();
						ry = e.getY();
						System.out.println("mouse released at point (" + rx + "," + ry + ")\n");
						mp.drawRect(px, py, rx, ry,color,stroke);
						mp.repaint();
					}
				});
			}
		});
		
		//eraser button
		erase_btn=new JButton("Eraser");
		erase_btn.setBounds(50, 350, 80, 30);
		frame.getContentPane().add(erase_btn);
		erase_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.print("clicked Eraser button\n");
				MouseListener[] listeners = mp.getMouseListeners();
				for (MouseListener listener : listeners) {
					mp.removeMouseListener(listener);
				}
				MouseMotionListener[] mmls=mp.getMouseMotionListeners();
				for(MouseMotionListener mml:mmls){
					mp.removeMouseMotionListener(mml);
				}
				mp.addMouseMotionListener(new MouseAdapter(){
					public void mouseDragged(MouseEvent e){
						px=e.getX();
						py=e.getY();
						mp.Eraser(px, py, stroke);
						mp.repaint();
					}
				});
			}
		});
		
		//clean button
		clean_btn=new JButton("Clean");
		clean_btn.setBounds(50, 400, 80, 30);
		frame.getContentPane().add(clean_btn);
		clean_btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mp.Eraser(0, 0, 1000);
				mp.repaint();
			}
			
		});
	}
}
