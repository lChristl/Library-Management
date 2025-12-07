package mongoJava;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class Dashboard {

	private JFrame frmLibmanage;
	private JTable table;
	private JTable table1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private DefaultTableModel tableModel;
	private String[][] originalData;
	private int borrowedCount = 0;
	private int returnedCount = 0;
	private int overdueCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frmLibmanage.setVisible(true);
					window.frmLibmanage.setState(JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibmanage = new JFrame();
		frmLibmanage.setIconImage(Toolkit.getDefaultToolkit().getImage(Dashboard.class.getResource("/mongoJava/photos/Libris_logo (2) (2).png")));
		frmLibmanage.getContentPane().setBackground(new Color(255, 255, 255));
		frmLibmanage.setTitle("LibManage");
		frmLibmanage.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmLibmanage.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmLibmanage.setResizable(false);
		frmLibmanage.setPreferredSize(new Dimension(1920, 1080));
		frmLibmanage.setAutoRequestFocus(false);
		frmLibmanage.setBounds(0, 0, 1940, 1080);
		frmLibmanage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibmanage.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 150, 1062);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(28, 28, 28)));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frmLibmanage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Home");
		lblNewLabel_3.setForeground(new Color(28, 28, 28));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(30, 275, 87, 23);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("\r");
		lblNewLabel_4.setForeground(new Color(28, 28, 28));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Dashboard.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/home (2) (1).png")));
		lblNewLabel_4.setBounds(30, 200, 87, 87);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("\r\n");
		lblNewLabel_4_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Book.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		lblNewLabel_4_1.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/book (1).png")));
		lblNewLabel_4_1.setBounds(30, 400, 87, 87);
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_3_1 = new JLabel("Library");
		lblNewLabel_3_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_3_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(30, 468, 87, 23);
		panel.add(lblNewLabel_3_1);
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Book.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		
		JLabel lblNewLabel_4_1_1 = new JLabel("\r\n");
		lblNewLabel_4_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Transaction.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		lblNewLabel_4_1_1.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/file (1) (1).png")));
		lblNewLabel_4_1_1.setBounds(26, 600, 87, 87);
		panel.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Transaction");
		lblNewLabel_3_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_3_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(12, 670, 128, 23);
		panel.add(lblNewLabel_3_1_1);
		lblNewLabel_3_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Transaction.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		
		JLabel lblNewLabel_4_1_2 = new JLabel("\r\n");
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Setting.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		lblNewLabel_4_1_2.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/settings (2).png")));
		lblNewLabel_4_1_2.setBounds(12, 922, 130, 87);
		panel.add(lblNewLabel_4_1_2);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Setting");
		lblNewLabel_3_1_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_3_1_1_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1.setBounds(26, 998, 101, 23);
		panel.add(lblNewLabel_3_1_1_1);
		lblNewLabel_3_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Setting.main(new String[]{});
				frmLibmanage.dispose();
			}
		});
		
		JLabel lblNewLabel_4_2 = new JLabel("\r");
		lblNewLabel_4_2.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/Libris_logo (2) (3).png")));
		lblNewLabel_4_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_2.setBounds(22, 37, 101, 97);
		panel.add(lblNewLabel_4_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(101, 0, 1823, 150);
		panel_1.setBackground(new Color(103, 0, 0));
		panel_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(28, 28, 28)));
		frmLibmanage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("LIBRIS");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Montserrat Black", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(71, 45, 270, 65);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Recent Transactions");
		lblNewLabel.setForeground(new Color(28, 28, 28));
		lblNewLabel.setBounds(170, 161, 411, 65);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		frmLibmanage.getContentPane().add(lblNewLabel);
		
		String[][] data = null;
		String[] column = null;

		try {
		    MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

		    Document firstDoc = collection.find().first();
		    if (firstDoc != null) {
		        String[] allColumns = firstDoc.keySet().toArray(new String[0]);

		        int newLength = allColumns.length - 2;
		        column = new String[newLength];

		        System.arraycopy(allColumns, 1, column, 0, newLength);
		    }

		    int rows = (int) collection.countDocuments();

		    int cols = column.length;
		    data = new String[rows][cols];

		    MongoCursor<Document> cursor = collection.find().iterator();
		    int count = 0;
		    while (cursor.hasNext()) {
		        Document doc = cursor.next();

		        for (int i = 0; i < cols; i++) {
		            Object value = doc.get(column[i]);
		            data[count][i] = value != null ? value.toString() : "";
		        }
		        count++;
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		table = new JTable(data,column);
		table.setBackground(new Color(255, 255, 255));
		//sizes
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setMinWidth(80);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(1).setMinWidth(350);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setMinWidth(200);
		
		//column header color
		table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
		table.getTableHeader().setBackground(new Color(128, 0, 0));
		table.getTableHeader().setForeground(Color.WHITE);
		
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(25);
		table.setFont(new Font("Century Gothic", Font.BOLD, 14));
		table.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(180, 668, 1114, 362);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(28, 28, 28)));
		frmLibmanage.getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setFocusable(false);
		panel_2.setDoubleBuffered(false);
		panel_2.setBounds(1308, 83, 616, 544);
		frmLibmanage.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(148, 194, 408, 27);
		panel_2.add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

			    // Get entered Book ID
			    String bookId = textField.getText();

			    // Find book in MongoDB
			    Document book = collection.find(Filters.eq("ID", bookId)).first();

			    if (book != null) {
			        String bookTitle = book.getString("Title");
			        textField_1.setText(bookTitle);
			    } else {
			        textField_1.setText("");  // Clear if no match
			    }
			}
		});
		
		JLabel lblNewLabel_5 = new JLabel("Transaction Form");
		lblNewLabel_5.setForeground(new Color(28, 28, 28));
		lblNewLabel_5.setFocusable(false);
		lblNewLabel_5.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblNewLabel_5.setBounds(24, 91, 348, 27);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Book ID :");
		lblNewLabel_6.setForeground(new Color(28, 28, 28));
		lblNewLabel_6.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(35, 196, 103, 21);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Title :");
		lblNewLabel_6_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_1.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_1.setBounds(70, 240, 68, 21);
		panel_2.add(lblNewLabel_6_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(148, 238, 408, 27);
		panel_2.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(148, 281, 408, 27);
		panel_2.add(textField_2);
		
		JLabel lblNewLabel_6_2 = new JLabel("Status :");
		lblNewLabel_6_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_2.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_2.setBounds(52, 156, 86, 21);
		panel_2.add(lblNewLabel_6_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(148, 156, 164, 27);
		panel_2.add(textField_4);
		
		JLabel lblNewLabel_6_1_2 = new JLabel("Name :");
		lblNewLabel_6_1_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_1_2.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_1_2.setBounds(52, 283, 86, 21);
		panel_2.add(lblNewLabel_6_1_2);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_4.setText("");
		        textField.setText("");
		        textField_1.setText("");
		        textField_2.setText("");
			}
		});
		btnNewButton.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(28, 28, 28));
		btnNewButton.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		btnNewButton.setBounds(148, 337, 95, 27);
		panel_2.add(btnNewButton);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				        String status = textField_4.getText();
				        String bookId = textField.getText();
				        String bookTitle = textField_1.getText();
				        String studentName = textField_2.getText();
				   
				        int issue = TransactionDB.save(status, bookId, bookTitle, studentName);

				        if (issue > 0) {
				        	JOptionPane.showMessageDialog(null, "Book Issued Successfully!");
				            Dashboard.main(new String[]{});
				            frmLibmanage.dispose();
				        } else {
				            JOptionPane.showMessageDialog(null, "Sorry, unable to issue!");
				        }

				    }
				});

		btnDone.setForeground(new Color(28, 28, 28));
		btnDone.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		btnDone.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnDone.setBackground(Color.WHITE);
		btnDone.setBounds(461, 337, 95, 27);
		panel_2.add(btnDone);
		
		JLabel lblNewLabel_1 = new JLabel("Book List");
		lblNewLabel_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_1.setBounds(170, 604, 400, 53);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 32));
		frmLibmanage.getContentPane().add(lblNewLabel_1);
		
		String[][] data1 = null;
		String[] column1 = { "Status", "Book ID", "Book Title", "Student" };

		try {
			MongoCollection<Document> collection = DB.getDatabase().getCollection("Transaction");

			int rows = (int) collection.countDocuments();
			int cols = column1.length;
			data1 = new String[rows][cols];
			originalData = new String[rows][cols];

			MongoCursor<Document> cursor = collection.find().iterator();
			int count1 = 0;
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				String status = doc.getString("status") != null ? doc.getString("status") : "";
				data1[count1][0] = status;
				originalData[count1][0] = status;
				data1[count1][1] = doc.getString("book_id") != null ? doc.getString("book_id") : "";
				originalData[count1][1] = data1[count1][1];
				data1[count1][2] = doc.getString("title") != null ? doc.getString("title") : "";
				originalData[count1][2] = data1[count1][2];
				data1[count1][3] = doc.getString("student") != null ? doc.getString("student") : "";
				originalData[count1][3] = data1[count1][3];

				// Count statuses
				if ("Borrowed".equalsIgnoreCase(status)) {
					borrowedCount++;
				} else if ("Returned".equalsIgnoreCase(status)) {
					returnedCount++;
				} else if ("Overdue".equalsIgnoreCase(status)) {
					overdueCount++;
				}
				count1++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableModel = new DefaultTableModel(data1, column1);
		table1 = new JTable(tableModel);
		table1.setBackground(new Color(255, 255, 255));
		// sizes
		table1.getColumnModel().getColumn(0).setResizable(false);
		table1.getColumnModel().getColumn(0).setPreferredWidth(100);
		table1.getColumnModel().getColumn(0).setMinWidth(100);
		table1.getColumnModel().getColumn(1).setResizable(false);
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table1.getColumnModel().getColumn(1).setMinWidth(100);
		table1.getColumnModel().getColumn(2).setResizable(false);
		table1.getColumnModel().getColumn(2).setPreferredWidth(300);
		table1.getColumnModel().getColumn(2).setMinWidth(300);
		table1.getColumnModel().getColumn(3).setResizable(false);
		table1.getColumnModel().getColumn(3).setPreferredWidth(200);
		table1.getColumnModel().getColumn(3).setMinWidth(200);

		// column header color
		table1.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
		table1.getTableHeader().setBackground(new Color(128, 0, 0));
		table1.getTableHeader().setForeground(Color.WHITE);

		table1.setSelectionBackground(Color.LIGHT_GRAY);
		table1.setRowSelectionAllowed(false);
		table1.setRowHeight(25);
		table1.setFont(new Font("Century Gothic", Font.BOLD, 14));
		table1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(180, 231, 1114, 362);
		scrollPane1.setForeground(Color.BLACK);
		scrollPane1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane1.setBackground(new Color(128, 0, 0));
		scrollPane1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(28, 28, 28)));
		frmLibmanage.getContentPane().add(scrollPane1);

		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(1308, 648, 616, 414);
		frmLibmanage.getContentPane().add(panel_2_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("Book Summary");
		lblNewLabel_5_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_5_1.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblNewLabel_5_1.setFocusable(false);
		lblNewLabel_5_1.setBounds(21, 22, 348, 27);
		panel_2_1.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_7 = new JLabel("Returned : " + returnedCount);
		lblNewLabel_7.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(103, 0, 0)));
		lblNewLabel_7.setForeground(new Color(28, 28, 28));
		lblNewLabel_7.setBackground(new Color(255, 255, 255));
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(21, 74, 262, 129);
		panel_2_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Borrowed : " + borrowedCount);
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_7_1.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		lblNewLabel_7_1.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(103, 0, 0)));
		lblNewLabel_7_1.setBackground(Color.WHITE);
		lblNewLabel_7_1.setBounds(307, 74, 262, 129);
		panel_2_1.add(lblNewLabel_7_1);
		
		JLabel lblNewLabel_7_2 = new JLabel("Overdue : " + overdueCount);
		lblNewLabel_7_2.setOpaque(true);
		lblNewLabel_7_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_7_2.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		lblNewLabel_7_2.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(103, 0, 0)));
		lblNewLabel_7_2.setBackground(Color.WHITE);
		lblNewLabel_7_2.setBounds(21, 254, 262, 129);
		panel_2_1.add(lblNewLabel_7_2);
		
		MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");
		long total = collection.countDocuments();
		
		JLabel lblNewLabel_7_3 = new JLabel("Total Books: " + total);
		lblNewLabel_7_3.setOpaque(true);
		lblNewLabel_7_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_7_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_3.setForeground(new Color(28, 28, 28));
		lblNewLabel_7_3.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		lblNewLabel_7_3.setBorder(new MatteBorder(10, 10, 10, 10, (Color) new Color(103, 0, 0)));
		lblNewLabel_7_3.setBackground(Color.WHITE);
		lblNewLabel_7_3.setBounds(307, 254, 262, 129);
		panel_2_1.add(lblNewLabel_7_3);
		
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		    @Override
		    public java.awt.Component getTableCellRendererComponent(
		            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        if (!isSelected) {
		            setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240));
		        }
		        return this;
		    }
		});
		
		
	}
}
