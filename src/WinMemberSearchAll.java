import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class WinMemberSearchAll extends JDialog {

	private final JPanel contentPanel = new JPanel();
	String strName, strPhone;
	String Members[][] = new String[100][7];

	public WinMemberSearchAll(String strName,String strPhone) throws Exception {
		this.strName = strName;
		this.strPhone = strPhone;
		
		setTitle("검색한 회원 정보 보기");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			
			int count = SearchMember();
			Member member[] = new Member[count]; 
			for(int i=0; i<count; i++) {
				member[i] = new Member(Members[i][0],Members[i][1],Members[i][2],Members[i][3],
						Members[i][4],Members[i][5],Members[i][6]);
										
				tabbedPane.addTab(Members[i][1], member[i]);
			}
		}
	}

	private int SearchMember() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(temp);
		Statement stmt = con.createStatement();
		
		String sql;
		if(strName.trim().length()==0 && strPhone.trim().length()==0)// 이름 입력x 전화번호 입력x
			sql = "select * from memberTBL";
		else if(strName.trim().length()==0 && strPhone.trim().length()!=0) // 이름 입력x 전화번호 입력o
			sql = "select * from memberTBL where phone='" + strPhone + "'";
		else if(strName.trim().length()!=0 && strPhone.trim().length()==0)// 이름 입력o 전화번호 입력x
			sql = "select * from memberTBL where name='" + strName + "'";	
		else {
			sql = "select * from memberTBL where name='" + strName + "' and phone='";
			sql = sql + strPhone + "'";
		}

		ResultSet rs = stmt.executeQuery(sql);
		int count = 0;
		while(rs.next()) {
			Members[count][0] = rs.getString("id");
			Members[count][1] = rs.getString("name");
			Members[count][2] = rs.getString("phone");
			Members[count][3] = rs.getString("address");
			Members[count][4] = rs.getString("mDate");
			Members[count][5] = rs.getString("picPath");
			Members[count][6] = rs.getString("grade");
			count++;
		}
		return count;
	}

}
