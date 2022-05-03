import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

public class WinSearchShowAll extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTabbedPane tabbedPane;
	String strTitle;
	String strAuthor;
	String Books[][] = new String[100][8];// 검색된 책들의 정보를 저장할 배열
	
	public WinSearchShowAll(String strTitle, String strAuthor) throws Exception {
		this.strTitle = strTitle;
		this.strAuthor = strAuthor;
		setTitle("검색된 책들");
		setBounds(100, 100, 631, 474);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT); // 생략
			
			int count = SelectBook();// 검색된 책의 수, 배열객체에 정보 저장
			Book book[] = new Book[count];
			for(int i=0;i<count;i++) {
				book[i] = new Book(Books[i][0],Books[i][1],Books[i][2],
						Books[i][3],Books[i][4],Books[i][5],Books[i][6],Books[i][7]);				
				tabbedPane.addTab(Books[i][1], book[i]);
			}
			contentPanel.add(tabbedPane, BorderLayout.CENTER);
			
		}
	}

	private int SelectBook() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(temp);
		Statement stmt = con.createStatement();
		String sql;
		if(strTitle.length()==0 && strAuthor.length()==0) // 제목 x 저자 x
			sql = "select * from bookTBL ";
		else if(strTitle.length()==0 && strAuthor.length()!=0) // 제목x 저자 o
			sql = "select * from bookTBL where author='" + strAuthor + "'";
		else if(strTitle.length()!=0 && strAuthor.length()==0)// 제목o 저자 x
			sql = "select * from bookTBL where title='" + strTitle + "'";
		else {
			sql = "select * from bookTBL where author='" + strAuthor + "' and ";
		sql = sql + "title'" + strTitle + "'";
	}
		ResultSet rs = stmt.executeQuery(sql);
		int count = 0;
		while(rs.next()) {
			Books[count][0] = rs.getString("isbn");
			Books[count][1] = rs.getString("title");
			Books[count][2] = rs.getString("author");
			Books[count][3] = rs.getString("publisher");
			Books[count][4] = rs.getString("mDate");
			Books[count][5] = rs.getString("price");
			Books[count][6] = rs.getString("amount");
			Books[count][7] = rs.getString("picPath");
			count++;
		}
		return count;
	}

}
