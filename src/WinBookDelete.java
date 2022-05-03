import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class WinBookDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private String strTitle="";
	private Vector data = null;
	
	public WinBookDelete(String Title) throws ClassNotFoundException, SQLException {
		strTitle = Title;
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBookDelete.class.getResource("/image/icons8-trash-64.png")));
		setTitle("도서삭제");
		setBounds(100, 100, 631, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ISBN");
		columnNames.add("제목");
		columnNames.add("저자");
		
		String sql = "select * from bookTBL where title like '%" + strTitle + "%'";
		SearchTitle(sql);
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String strISBN = table.getValueAt(table.getSelectedRow(), 0).toString();				
				WinBookInfo dlg;
				try {
					setVisible(false);
					dlg = new WinBookInfo(strISBN, strTitle);
					dlg.setModal(true);
					dlg.setVisible(true);					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
	}
	public void SearchTitle(String sql) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		data = new Vector<>();
		while(rs.next()) {
			Vector <String>row = new Vector<String>();
		
			row.add(rs.getString("isbn"));
			row.add(rs.getString("title"));	
			row.add(rs.getString("author"));
			
			data.add(row);
		}
		
		stmt.close();
		con.close();
	}

}
