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
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		JLabel lblNewLabel_3 = new JLabel("Home");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblNewLabel_3.setBounds(30, 275, 87, 23);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\r");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/home (2) (1).png")));
		lblNewLabel_4.setBounds(30, 200, 87, 87);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("\r\n");
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
		lblNewLabel_3_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(30, 468, 87, 23);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("\r\n");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_1_1.setIcon(new ImageIcon(Dashboard.class.getResource("/mongoJava/photos/file (1) (1).png")));
		lblNewLabel_4_1_1.setBounds(26, 600, 87, 87);
		panel.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Transaction");
		lblNewLabel_3_1_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setFont(new Font("Century Gothic", Font.BOLD, 18));
		lblNewLabel_3_1_1.setBounds(26, 670, 101, 23);
		panel.add(lblNewLabel_3_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 0, 0));
		panel_1.setBorder(new MatteBorder(0, 0, 3, 0, (Color) Color.DARK_GRAY));
		panel_1.setBounds(101, 0, 1823, 150);
		frmLibmanage.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("LibManage");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Century Gothic", Font.BOLD, 45));
		lblNewLabel_2.setBounds(71, 45, 270, 65);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Recent Transactions");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblNewLabel.setBounds(170, 161, 411, 65);
		frmLibmanage.getContentPane().add(lblNewLabel);
		
		String[][] data = null;
		String[] column = null;

		try {
		    MongoCollection<Document> collection = DB.getDatabase().getCollection("BookList");

		    Document firstDoc = collection.find().first();
		    if (firstDoc != null) {
		        String[] allColumns = firstDoc.keySet().toArray(new String[0]);
		        column = new String[allColumns.length - 1];
		        System.arraycopy(allColumns, 1, column, 0, allColumns.length - 1);
		    }
		    
		    long rowsCount = collection.countDocuments();
		    int rows = (int) rowsCount;

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
		scrollPane.setForeground(Color.BLACK);
		scrollPane.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane.setBackground(Color.LIGHT_GRAY);
		scrollPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
		scrollPane.setBounds(170, 668, 1114, 362);  // explicit size and position
		frmLibmanage.getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1308, 0, 616, 1062);
		frmLibmanage.getContentPane().add(panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Book List");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 32));
		lblNewLabel_1.setBounds(170, 604, 400, 53);
		frmLibmanage.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);
		scrollPane_1.setForeground(Color.BLACK);
		scrollPane_1.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		scrollPane_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.DARK_GRAY));
		scrollPane_1.setBackground(Color.LIGHT_GRAY);
		scrollPane_1.setBounds(170, 231, 1114, 362);
		frmLibmanage.getContentPane().add(scrollPane_1);
		
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
