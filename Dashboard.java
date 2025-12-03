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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Dashboard {

	private JFrame frmLibmanage;
	private JTable table;

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
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new MatteBorder(0, 0, 0, 3, (Color) Color.DARK_GRAY));
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(0, 0, 150, 1062);
		frmLibmanage.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(null);
		btnNewButton.setBounds(10, 106, 130, 85);
		panel.add(btnNewButton);
		
		JButton btnLibrary = new JButton("Library");
		btnLibrary.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLibrary.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLibrary.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnLibrary.setBorder(null);
		btnLibrary.setBackground(Color.WHITE);
		btnLibrary.setBounds(10, 300, 130, 85);
		panel.add(btnLibrary);
		
		JButton btnTransaction = new JButton("Transaction");
		btnTransaction.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnTransaction.setVerticalAlignment(SwingConstants.BOTTOM);
		btnTransaction.setFont(new Font("Century Gothic", Font.BOLD, 18));
		btnTransaction.setBorder(null);
		btnTransaction.setBackground(Color.WHITE);
		btnTransaction.setBounds(10, 500, 130, 85);
		panel.add(btnTransaction);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.DARK_GRAY));
		panel_1.setBounds(101, 0, 1823, 150);
		frmLibmanage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Recent Transactions");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblNewLabel.setBounds(170, 161, 411, 65);
		frmLibmanage.getContentPane().add(lblNewLabel);
		
		String[][] data = null;
		String[] column = null;

		try {
		    // 1. Get the MongoDB collection
		    MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

		    // 2. Determine columns (field names)
		    // We'll take the first document as reference
		    Document firstDoc = collection.find().first();
		    if (firstDoc != null) {
		        String[] allColumns = firstDoc.keySet().toArray(new String[0]);
		        // Skip the first column
		        column = new String[allColumns.length - 1];
		        System.arraycopy(allColumns, 1, column, 0, allColumns.length - 1);
		    }

		    // 3. Count number of documents
		    long rowsCount = collection.countDocuments();
		    int rows = (int) rowsCount;

		    // 4. Prepare data array
		    int cols = column.length;
		    data = new String[rows][cols];

		    // 5. Iterate over documents and fill data
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
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
		scrollPane.setBounds(170, 234, 1114, 362);  // explicit size and position
		frmLibmanage.getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1447, 0, 477, 1062);
		frmLibmanage.getContentPane().add(panel_2);
		
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
