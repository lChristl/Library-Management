package mongoJava;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Book {

	JFrame frmLibmanage;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book window = new Book();
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
	public Book() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibmanage = new JFrame();
		frmLibmanage.setIconImage(Toolkit.getDefaultToolkit().getImage(Book.class.getResource("/mongoJava/photos/Libris_logo (2) (3).png")));
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
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(103, 0, 0)));
		desktopPane_1.setBackground(new Color(255, 255, 255));
		desktopPane_1.setBounds(367, 282, 452, 150);
		desktopPane_1.setVisible(false);
		frmLibmanage.getContentPane().add(desktopPane_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ID :");
		lblNewLabel_1_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_1_2.setFont(new Font("Montserrat Black", Font.PLAIN, 23));
		lblNewLabel_1_2.setBounds(39, 57, 70, 25);
		desktopPane_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("\r");
		lblNewLabel_4_2.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/Libris_logo (2) (3).png")));
		lblNewLabel_4_2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_2.setBounds(22, 37, 101, 97);
		panel.add(lblNewLabel_4_2);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField_5.setColumns(10);
		textField_5.setBounds(111, 55, 286, 34);
		desktopPane_1.add(textField_5);
		
		JLabel lblDeleteBookForm = new JLabel("Delete Book Form");
		lblDeleteBookForm.setFont(new Font("Monospaced", Font.BOLD, 30));
		lblDeleteBookForm.setBounds(10, 11, 411, 34);
		desktopPane_1.add(lblDeleteBookForm);
		
		JButton btnDone_1 = new JButton("Done");
		btnDone_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField_5.getText();
				
				int issue = BookDB.delete(id);

		        if (issue > 0) {
		        	JOptionPane.showMessageDialog(null, "Book Deleted Successfully!");
		            Book.main(new String[]{});
		            frmLibmanage.dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Sorry, unable to delete!");
		        }

			}
		});
		btnDone_1.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		btnDone_1.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnDone_1.setBackground(Color.WHITE);
		btnDone_1.setBounds(353, 100, 89, 34);
		desktopPane_1.add(btnDone_1);
		
		JButton btnNewButton_11_1 = new JButton("x");
		btnNewButton_11_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane_1.setVisible(false);
			}
		});
		btnNewButton_11_1.setFont(new Font("Montserrat Black", Font.PLAIN, 11));
		btnNewButton_11_1.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_11_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_11_1.setBounds(403, 11, 39, 23);
		desktopPane_1.add(btnNewButton_11_1);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(103, 0, 0)));
		desktopPane.setBackground(new Color(255, 255, 255));
		desktopPane.setBounds(172, 285, 693, 370);
		desktopPane.setVisible(false);
		frmLibmanage.getContentPane().add(desktopPane);
		
		JLabel lblNewLabel_1 = new JLabel("ID :");
		lblNewLabel_1.setFont(new Font("Montserrat Black", Font.PLAIN, 23));
		lblNewLabel_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_1.setBounds(127, 77, 70, 25);
		desktopPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField.setBounds(188, 72, 462, 34);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Title :");
		lblNewLabel_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_1_1.setFont(new Font("Montserrat Black", Font.PLAIN, 23));
		lblNewLabel_1_1.setBounds(97, 123, 122, 25);
		desktopPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Author :");
		lblNewLabel_1_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_1_1_1.setFont(new Font("Montserrat Black", Font.PLAIN, 23));
		lblNewLabel_1_1_1.setBounds(65, 167, 122, 25);
		desktopPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Year :");
		lblNewLabel_1_1_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_1_1_1_1.setFont(new Font("Montserrat Black", Font.PLAIN, 23));
		lblNewLabel_1_1_1_1.setBounds(97, 213, 122, 25);
		desktopPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Available :");
		lblNewLabel_1_1_1_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_1_1_1_1_1.setFont(new Font("Montserrat Black", Font.PLAIN, 23));
		lblNewLabel_1_1_1_1_1.setBounds(33, 262, 164, 25);
		desktopPane.add(lblNewLabel_1_1_1_1_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(188, 119, 462, 34);
		desktopPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(188, 162, 462, 34);
		desktopPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(188, 208, 462, 34);
		desktopPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(188, 257, 462, 34);
		desktopPane.add(textField_4);
		
		JLabel lblAddBook = new JLabel("Add Book Form");
		lblAddBook.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblAddBook.setBounds(23, 11, 411, 50);
		desktopPane.add(lblAddBook);
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 	String id = textField.getText();
			        String title = textField_1.getText();
			        String author = textField_2.getText();
			        int year = Integer.parseInt(textField_3.getText());
			        int avail = Integer.parseInt(textField_4.getText());
			        int transact = 0;
			   
			        int issue = BookDB.save(id, title, author, year, avail, transact);

			        if (issue > 0) {
			        	JOptionPane.showMessageDialog(null, "Book Added Successfully!");
			            Book.main(new String[]{});
			            frmLibmanage.dispose();
			        } else {
			            JOptionPane.showMessageDialog(null, "Sorry, unable to add!");
			        }

			    }
			});
			
	
		btnDone.setFont(new Font("Montserrat Black", Font.PLAIN, 18));
		btnDone.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnDone.setBackground(Color.WHITE);
		btnDone.setBounds(561, 312, 89, 34);
		desktopPane.add(btnDone);
		
		JButton btnNewButton_11 = new JButton("x");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.setVisible(false);
			}
		});
		btnNewButton_11.setFont(new Font("Montserrat Black", Font.PLAIN, 11));
		btnNewButton_11.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton_11.setBackground(Color.LIGHT_GRAY);
		btnNewButton_11.setBounds(644, 11, 39, 23);
		desktopPane.add(btnNewButton_11);
		
		
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
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.DARK_GRAY));
		panel_1.setBounds(101, 0, 1823, 150);
		frmLibmanage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("LIBRIS");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Montserrat Black", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(71, 45, 270, 65);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Book List");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblNewLabel.setBounds(172, 161, 411, 65);
		frmLibmanage.getContentPane().add(lblNewLabel);
		
		String[][] data = null;
		String[] column = null;

		try {
		    MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

		    Document firstDoc = collection.find().first();
		    if (firstDoc != null) {
		        String[] allColumns = firstDoc.keySet().toArray(new String[0]);

		        int newLength = allColumns.length - 2; // remove _id and maybe another column
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

		// ✅ Use DefaultTableModel explicitly
		DefaultTableModel model = new DefaultTableModel(data, column) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false; // disable editing
		    }
		};

		table = new JTable(model);

		// ✅ Row sorter MUST be attached after model creation
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		// ==================== UI Styling ======================
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(25);
		table.setFont(new Font("Century Gothic", Font.BOLD, 14));
		table.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

		table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
		table.getTableHeader().setBackground(new Color(128, 0, 0));
		table.getTableHeader().setForeground(Color.WHITE);

		// column width
		if (table.getColumnCount() > 0) {
		    table.getColumnModel().getColumn(0).setPreferredWidth(80);
		}
		if (table.getColumnCount() > 1) {
		    table.getColumnModel().getColumn(1).setPreferredWidth(300);
		}
		if (table.getColumnCount() > 2) {
		    table.getColumnModel().getColumn(2).setPreferredWidth(200);
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, Color.DARK_GRAY));
		scrollPane.setBounds(170, 285, 1722, 745);

		frmLibmanage.getContentPane().add(scrollPane);

		
		JButton btnAddBook = new JButton("+ Add Book");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane.setVisible(true);
			}
		});
		btnAddBook.setForeground(new Color(28, 28, 28));
		btnAddBook.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		btnAddBook.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnAddBook.setBackground(Color.WHITE);
		btnAddBook.setBounds(170, 223, 166, 51);
		frmLibmanage.getContentPane().add(btnAddBook);
		
		
		JButton btnDeleteBook = new JButton("Delete Book");
		btnDeleteBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktopPane_1.setVisible(true);
			}
		});
		btnDeleteBook.setForeground(new Color(28, 28, 28));
		btnDeleteBook.setFont(new Font("Montserrat Black", Font.PLAIN, 20));
		btnDeleteBook.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(103, 0, 0)));
		btnDeleteBook.setBackground(Color.WHITE);
		btnDeleteBook.setBounds(367, 223, 166, 51);
		frmLibmanage.getContentPane().add(btnDeleteBook);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		textField_6.setColumns(10);
		textField_6.setBounds(1465, 206, 427, 42);
		frmLibmanage.getContentPane().add(textField_6);
		textField_6.getDocument().addDocumentListener(new DocumentListener() {
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
				String text = textField_6.getText().toLowerCase();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + text, 0, 1, 2, 3)); // Search in Book Title
																									// and Student
																									// columns
				}
			}
		});

		
		JLabel lblNewLabel_5 = new JLabel("Search :");
		lblNewLabel_5.setFont(new Font("Montserrat Black", Font.PLAIN, 30));
		lblNewLabel_5.setBounds(1295, 206, 160, 47);
		frmLibmanage.getContentPane().add(lblNewLabel_5);
		
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
