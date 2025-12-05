package mongoJava;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Transaction {

	private JFrame frmLibmanage;
	private JTable table;
	private JTextField textField;
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
		frmLibmanage.setType(Type.UTILITY);
		frmLibmanage.getContentPane().setBackground(new Color(255, 255, 255));
		frmLibmanage.setTitle("LibManage");
		frmLibmanage.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frmLibmanage.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmLibmanage.setResizable(false);
		frmLibmanage.setPreferredSize(new Dimension(1920, 1080));
		frmLibmanage.setAutoRequestFocus(false);
		frmLibmanage.setAlwaysOnTop(true);
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

		JLabel lblNewLabel_4 = new JLabel("\r");
		lblNewLabel_4.setForeground(new Color(28, 28, 28));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
//		lblNewLabel_4.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/home (2) (1).png")));
		lblNewLabel_4.setBounds(30, 200, 87, 87);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("\r\n");
		lblNewLabel_4_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				Book.main(new String[] {});
//				frmLibmanage.dispose();
			}
		});
//		lblNewLabel_4_1.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/book (1).png")));
		lblNewLabel_4_1.setBounds(30, 400, 87, 87);
		panel.add(lblNewLabel_4_1);

		JLabel lblNewLabel_3_1 = new JLabel("Library");
		lblNewLabel_3_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_3_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(30, 468, 87, 23);
		panel.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("\r\n");
		lblNewLabel_4_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
//		lblNewLabel_4_1_1.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/file (1) (1).png")));
		lblNewLabel_4_1_1.setBounds(26, 600, 87, 87);
		panel.add(lblNewLabel_4_1_1);

		JLabel lblNewLabel_3_1_1 = new JLabel("Transaction");
		lblNewLabel_3_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_3_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3_1_1.setBounds(12, 670, 128, 23);
		panel.add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_4_1_2 = new JLabel("\r\n");
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_4_1_2.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/settings (2).png")));
		lblNewLabel_4_1_2.setBounds(12, 922, 128, 87);
		panel.add(lblNewLabel_4_1_2);

		JLabel lblNewLabel_3_1_1_1 = new JLabel("Setting");
		lblNewLabel_3_1_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1_1.setForeground(new Color(28, 28, 28));
		lblNewLabel_3_1_1_1.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		lblNewLabel_3_1_1_1.setBounds(26, 998, 101, 23);
		panel.add(lblNewLabel_3_1_1_1);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(144, 0, 1780, 150);
		panel_1.setBackground(new Color(103, 0, 0));
		panel_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) new Color(28, 28, 28)));
		frmLibmanage.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("LIBRIS");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Montserrat Black", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(71, 45, 270, 65);
		panel_1.add(lblNewLabel_2);

		String[][] data = null;
		String[] column = { "Status", "Book ID", "Book Title", "Student" };

		try {
			MongoCollection<Document> collection = DB.getDatabase().getCollection("Transactions");

			int rows = (int) collection.countDocuments();
			int cols = column.length;
			data = new String[rows][cols];
			originalData = new String[rows][cols];

			MongoCursor<Document> cursor = collection.find().iterator();
			int count = 0;
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				String status = doc.getString("status") != null ? doc.getString("status") : "";
				data[count][0] = status;
				originalData[count][0] = status;
				data[count][1] = doc.getString("book_id") != null ? doc.getString("book_id") : "";
				originalData[count][1] = data[count][1];
				data[count][2] = doc.getString("book_title") != null ? doc.getString("book_title") : "";
				originalData[count][2] = data[count][2];
				data[count][3] = doc.getString("student") != null ? doc.getString("student") : "";
				originalData[count][3] = data[count][3];

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
		table.setBackground(new Color(255, 255, 255));
		// sizes
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(0).setMinWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setMinWidth(200);

		// column header color
		table.getTableHeader().setFont(new Font("Monospaced", Font.BOLD, 18));
		table.getTableHeader().setBackground(new Color(128, 0, 0));
		table.getTableHeader().setForeground(Color.WHITE);

		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(25);
		table.setFont(new Font("Century Gothic", Font.BOLD, 14));
		table.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(28, 28, 28)));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(264, 375, 1103, 528);
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane.setBackground(new Color(128, 0, 0));
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(28, 28, 28)));
		frmLibmanage.getContentPane().add(scrollPane);

		// Add sorter for search
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
		table.setRowSorter(sorter);

		JLabel lblNewLabel = new JLabel("Transaction List");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(182, 174, 381, 52);
		frmLibmanage.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Borrowed " + borrowedCount);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(264, 929, 204, 52);
		frmLibmanage.getContentPane().add(lblNewLabel_1);

		textField = new JTextField();
		textField.setBounds(385, 285, 499, 47);
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

		JLabel lblNewLabel_5 = new JLabel("Search");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_5.setBounds(263, 285, 112, 47);
		frmLibmanage.getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_1_1 = new JLabel("Returned " + returnedCount);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(618, 929, 204, 52);
		frmLibmanage.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Overdue " + overdueCount);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(953, 929, 204, 52);
		frmLibmanage.getContentPane().add(lblNewLabel_1_2);

	}
}
