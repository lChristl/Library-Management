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
import javax.swing.JDesktopPane;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class Transaction {

	private JFrame frmLibmanage;
	private JTable table;
	private JTextField textField;
	private DefaultTableModel tableModel;
	private String[][] originalData;
	private int borrowedCount = 0;
	private int returnedCount = 0;
	private int overdueCount = 0;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField_3;
	private JDesktopPane desktopPane;
	private JTextField textField_11;
	private JTextField textField_31;
	private JTextField textField_41;
	private JTextField textField_21;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Transaction window = new Transaction();
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
	public Transaction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibmanage = new JFrame();
		frmLibmanage.setIconImage(Toolkit.getDefaultToolkit().getImage(Transaction.class.getResource("/mongoJava/photos/Libris_logo (2) (3).png")));
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
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(1256, 295, 612, 344);
		frmLibmanage.getContentPane().add(desktopPane_1);
		desktopPane_1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(103, 0, 0)));
		desktopPane_1.setBackground(new Color(255, 255, 255));
		desktopPane_1.setVisible(false);
		
		textField_11 = new JTextField();
		textField_11.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 12));
		textField_11.setBounds(156, 152, 408, 27);
		desktopPane_1.add(textField_11);
		textField_11.setColumns(10);
		textField_11.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

			    // Get entered Book ID
			    String bookId = textField_11.getText();

			    // Find book in MongoDB
			    Document book = collection.find(Filters.eq("ID", bookId)).first();

			    if (book != null) {
			        String bookTitle = book.getString("Title");
			        textField_31.setText(bookTitle);
			    } else {
			        textField_31.setText("");  // Clear if no match
			    }
			}
		});
		
		JLabel lblNewLabel_511 = new JLabel("Transaction Form");
		lblNewLabel_511.setForeground(new Color(28, 28, 28));
		lblNewLabel_511.setFocusable(false);
		lblNewLabel_511.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblNewLabel_511.setBounds(26, 21, 348, 27);
		desktopPane_1.add(lblNewLabel_511);
		
		
		JLabel lblNewLabel_61 = new JLabel("Book ID :");
		lblNewLabel_61.setForeground(new Color(28, 28, 28));
		lblNewLabel_61.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_61.setBounds(43, 154, 103, 21);
		desktopPane_1.add(lblNewLabel_61);
		
		JLabel lblNewLabel_6_11 = new JLabel("Title :");
		lblNewLabel_6_11.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_11.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_11.setBounds(78, 198, 68, 21);
		desktopPane_1.add(lblNewLabel_6_11);
		
		textField_31 = new JTextField();
		textField_31.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 12));
		textField_31.setColumns(10);
		textField_31.setBounds(156, 196, 408, 27);
		desktopPane_1.add(textField_31);
		
		textField_21 = new JTextField();
		textField_21.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 12));
		textField_21.setColumns(10);
		textField_21.setBounds(156, 239, 408, 27);
		desktopPane_1.add(textField_21);
		
		JLabel lblNewLabel_6_21 = new JLabel("Status :");
		lblNewLabel_6_21.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_21.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_21.setBounds(60, 114, 86, 21);
		desktopPane_1.add(lblNewLabel_6_21);
		
		textField_41 = new JTextField();
		textField_41.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 12));
		textField_41.setColumns(10);
		textField_41.setBounds(156, 114, 408, 27);
		desktopPane_1.add(textField_41);
		
		JLabel lblNewLabel_6_1_21 = new JLabel("Name :");
		lblNewLabel_6_1_21.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_1_21.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_1_21.setBounds(60, 241, 86, 21);
		desktopPane_1.add(lblNewLabel_6_1_21);
		
		JButton btnDone1 = new JButton("Update");
		btnDone1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						String id = textField_5.getText();
				        String status = textField_41.getText();
				        String bookId = textField_11.getText();
				        String bookTitle = textField_31.getText();
				        String studentName = textField_21.getText();
				   
				        int issue = TransactionDB.updatestatus(id, status, bookId, bookTitle, studentName) ;

				        if (issue > 0) {
				        	JOptionPane.showMessageDialog(null, "Book Updated Successfully!");
				            Transaction.main(new String[]{});
				            frmLibmanage.dispose();
				        } else {
				            JOptionPane.showMessageDialog(null, "Sorry, unable to issue!");
				        }

				    }
				});
		
				btnDone1.setForeground(new Color(28, 28, 28));
				btnDone1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
				btnDone1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
				btnDone1.setBackground(Color.WHITE);
				btnDone1.setBounds(469, 295, 95, 27);
				desktopPane_1.add(btnDone1);
				
				JButton btnNewButton_11 = new JButton("x");
				btnNewButton_11.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						desktopPane_1.setVisible(false);
					}
				});
				btnNewButton_11.setBackground(new Color(192, 192, 192));
				btnNewButton_11.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
				btnNewButton_11.setFont(new Font("Montserrat Black", Font.PLAIN, 11));
				btnNewButton_11.setBounds(563, 11, 39, 23);
				desktopPane_1.add(btnNewButton_11);
				
				JLabel lblNewLabel_6_21_1 = new JLabel("ID :");
				lblNewLabel_6_21_1.setForeground(new Color(28, 28, 28));
				lblNewLabel_6_21_1.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
				lblNewLabel_6_21_1.setBounds(97, 75, 49, 21);
				desktopPane_1.add(lblNewLabel_6_21_1);
				
				textField_5 = new JTextField();
				textField_5.setEditable(false);
				textField_5.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 12));
				textField_5.setColumns(10);
				textField_5.setBounds(156, 76, 408, 27);
				desktopPane_1.add(textField_5);
		
		
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 255, 255));
		desktopPane.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(103, 0, 0)));
		desktopPane.setBounds(1256, 295, 612, 309);
		frmLibmanage.getContentPane().add(desktopPane);
		desktopPane.setVisible(false);
		desktopPane.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(139, 116, 408, 27);
		desktopPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

			    // Get entered Book ID
			    String bookId = textField_1.getText();

			    // Find book in MongoDB
			    Document book = collection.find(Filters.eq("ID", bookId)).first();

			    if (book != null) {
			        String bookTitle = book.getString("Title");
			        textField_3.setText(bookTitle);
			    } else {
			        textField_3.setText("");  // Clear if no match
			    }
			}
		});
		
		JLabel lblNewLabel_51 = new JLabel("Transaction Form");
		lblNewLabel_51.setForeground(new Color(28, 28, 28));
		lblNewLabel_51.setFocusable(false);
		lblNewLabel_51.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblNewLabel_51.setBounds(26, 21, 348, 27);
		desktopPane.add(lblNewLabel_51);
		
		JLabel lblNewLabel_6 = new JLabel("Book ID :");
		lblNewLabel_6.setForeground(new Color(28, 28, 28));
		lblNewLabel_6.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(26, 118, 103, 21);
		desktopPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Title :");
		lblNewLabel_6_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_1.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_1.setBounds(61, 162, 68, 21);
		desktopPane.add(lblNewLabel_6_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(139, 160, 408, 27);
		desktopPane.add(textField_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(139, 203, 408, 27);
		desktopPane.add(textField_2);
		
		JLabel lblNewLabel_6_2 = new JLabel("Status :");
		lblNewLabel_6_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_2.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_2.setBounds(43, 78, 86, 21);
		desktopPane.add(lblNewLabel_6_2);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(139, 78, 164, 27);
		desktopPane.add(textField_4);
		
		JLabel lblNewLabel_6_1_2 = new JLabel("Name :");
		lblNewLabel_6_1_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_6_1_2.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		lblNewLabel_6_1_2.setBounds(43, 205, 86, 21);
		desktopPane.add(lblNewLabel_6_1_2);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				        String status = textField_4.getText();
				        String bookId = textField_1.getText();
				        String bookTitle = textField_3.getText();
				        String studentName = textField_2.getText();
				   
				        int issue = TransactionDB.save(status, bookId, bookTitle, studentName);

				        if (issue > 0) {
				        	JOptionPane.showMessageDialog(null, "Book Issued Successfully!");
				            Transaction.main(new String[]{});
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
		btnDone.setBounds(452, 259, 95, 27);
		desktopPane.add(btnDone);
		
		JButton btnNewButton_1 = new JButton("x");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.setVisible(false);
			}
		});
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_1.setFont(new Font("Montserrat Black", Font.PLAIN, 11));
		btnNewButton_1.setBounds(563, 11, 39, 23);
		desktopPane.add(btnNewButton_1);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 150, 1062);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(28, 28, 28)));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		frmLibmanage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_4_2 = new JLabel("\r");
		lblNewLabel_4_2.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/Libris_logo (2) (3).png")));
		lblNewLabel_4_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_2.setBounds(22, 37, 101, 97);
		panel.add(lblNewLabel_4_2);
		
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
		
		JLabel lblNewLabel = new JLabel("Transaction List");
		lblNewLabel.setForeground(new Color(28, 28, 28));
		lblNewLabel.setBounds(170, 161, 411, 65);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		frmLibmanage.getContentPane().add(lblNewLabel);

		String[][] data = null;
		String[] column = { "ID", "Status", "Book ID", "Book Title", "Student" };

		try {
			MongoCollection<Document> collection = DB.getDatabase().getCollection("Transaction");

			int rows = (int) collection.countDocuments();
			int cols = column.length;
			data = new String[rows][cols];
			originalData = new String[rows][cols];

			MongoCursor<Document> cursor = collection.find().iterator();
			int count = 0;
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				String id = doc.getObjectId("_id") != null ? doc.getObjectId("_id").toString() : "";
				data[count][0] = id;
				String status = doc.getString("status") != null ? doc.getString("status") : "";
				data[count][1] = status;
				originalData[count][1] = status;
				data[count][2] = doc.getString("book_id") != null ? doc.getString("book_id") : "";
				originalData[count][2] = data[count][2];
				data[count][3] = doc.getString("title") != null ? doc.getString("title") : "";
				originalData[count][3] = data[count][3];
				data[count][4] = doc.getString("student") != null ? doc.getString("student") : "";
				originalData[count][4] = data[count][4];

				// Count statuses
				if ("Borrowed".equalsIgnoreCase(status)) {
					borrowedCount++;
				} else if ("Returned".equalsIgnoreCase(status)) {
					returnedCount++;
				} else if ("Overdue".equalsIgnoreCase(status)) {
					overdueCount++;
				}
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		tableModel = new DefaultTableModel(data, column);
		table = new JTable(tableModel);
		table.setFocusTraversalKeysEnabled(false);
		table.setFocusable(false);
		table.setRowSelectionAllowed(false);
		table.setRequestFocusEnabled(false);
		table.setAutoscrolls(false);
		table.setBackground(new Color(255, 255, 255));
		// sizes
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setMinWidth(30);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setMinWidth(30);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setMinWidth(300);

		//table click
		table.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		    	desktopPane_1.setVisible(true);
		    	
		        int row = table.getSelectedRow();

		        textField_5.setText(table.getValueAt(row, 0).toString());
		        textField_41.setText(table.getValueAt(row, 1).toString());
		        textField_11.setText(table.getValueAt(row, 2).toString());
		        textField_31.setText(table.getValueAt(row, 3).toString());
		        textField_21.setText(table.getValueAt(row, 4).toString());
		       
		    }
		});
		
		// column header color
		table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
		table.getTableHeader().setBackground(new Color(128, 0, 0));
		table.getTableHeader().setForeground(Color.WHITE);

		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowHeight(25);
		table.setFont(new Font("Century Gothic", Font.BOLD, 14));
		table.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(198, 320, 1670, 583);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane.setBackground(new Color(128, 0, 0));
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(28, 28, 28)));
		frmLibmanage.getContentPane().add(scrollPane);

		// Add sorter for search
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(sorter);

		JLabel lblNewLabel_1 = new JLabel("Borrowed " + borrowedCount);
		lblNewLabel_1.setFont(new Font("Montserrat Black", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(264, 929, 204, 52);
		frmLibmanage.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		textField.setBounds(429, 233, 499, 47);
		frmLibmanage.getContentPane().add(textField);
		textField.setColumns(10);

		// Add document listener for search
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				search();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				search();
			}

			private void search() {
				String text = textField.getText().toLowerCase();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + text, 2, 3)); // Search in Book Title
																									// and Student
																									// columns
				}
			}
		});

		JLabel lblNewLabel_5 = new JLabel("Search :");
		lblNewLabel_5.setFont(new Font("Montserrat Black", Font.PLAIN, 30));
		lblNewLabel_5.setBounds(266, 233, 160, 47);
		frmLibmanage.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_1_1 = new JLabel("Returned " + returnedCount);
		lblNewLabel_1_1.setFont(new Font("Montserrat Black", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(618, 929, 204, 52);
		frmLibmanage.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Overdue " + overdueCount);
		lblNewLabel_1_2.setFont(new Font("Montserrat Black", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(953, 929, 204, 52);
		frmLibmanage.getContentPane().add(lblNewLabel_1_2);
		
		JButton btnAddTrnsaction = new JButton("+ Add Transaction");
		btnAddTrnsaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.setVisible(true);
			}
		});
		btnAddTrnsaction.setForeground(new Color(28, 28, 28));
		btnAddTrnsaction.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		btnAddTrnsaction.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnAddTrnsaction.setBackground(Color.WHITE);
		btnAddTrnsaction.setBounds(1608, 233, 260, 51);
		frmLibmanage.getContentPane().add(btnAddTrnsaction);
		
		
		
	}
}
