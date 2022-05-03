

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WinMemberDelete extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	String strName;
	private Vector data = null;

	public WinMemberDelete(String sName) throws ClassNotFoundException, SQLException {
		strName = sName;
		setTitle("회원탈퇴");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		contentPanel.add(scrollPane, BorderLayout.CENTER);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("이름");
		columnNames.add("전화번호");
		
		String sql = "select * from memberTBL where name like '%" + strName + "%'";
		SearchName(sql);
		
		DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int Column = table.getSelectedColumn();
				String sID = table.getValueAt(row, 0).toString();
				
				setVisible(false); // 표 있는 대화상자는 안보이게
				
				WinMemberInfo dialog;
				try {
					dialog = new WinMemberInfo(sID);
					dialog.setModal(true);
					dialog.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
	}
		public void SearchName(String sql) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
			Connection con = DriverManager.getConnection(strCon);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			data = new Vector<>();
			while(rs.next()) {
				Vector <String>row = new Vector<String>();
				
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getString("phone"));	
				
				data.add(row);
			}
			
			stmt.close();
			con.close();
		}

	}

