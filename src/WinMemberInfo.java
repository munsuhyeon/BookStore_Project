import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class WinMemberInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtaddress;
	private JTextField txtmDate;
	String strID;
	JLabel lblpic;

	public WinMemberInfo(String sID) throws Exception {
		setTitle("도서 대여점 회원 탈퇴");
		strID = sID;
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblpic = new JLabel("");
		lblpic.setOpaque(true);
		lblpic.setBackground(Color.YELLOW);
		lblpic.setBounds(12, 28, 100, 100);
		contentPanel.add(lblpic);
		
		JLabel lblid = new JLabel("일련번호");
		lblid.setBounds(124, 28, 57, 15);
		contentPanel.add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBounds(193, 25, 116, 21);
		contentPanel.add(txtid);
		
		JLabel lblid_1 = new JLabel("회원명");
		lblid_1.setBounds(124, 71, 57, 15);
		contentPanel.add(lblid_1);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(193, 68, 116, 21);
		contentPanel.add(txtname);
		
		JLabel lblid_2 = new JLabel("전화번호");
		lblid_2.setBounds(124, 110, 57, 15);
		contentPanel.add(lblid_2);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(193, 107, 116, 21);
		contentPanel.add(txtphone);
		
		JLabel lblid_2_1 = new JLabel("주소");
		lblid_2_1.setBounds(124, 150, 57, 15);
		contentPanel.add(lblid_2_1);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(193, 147, 229, 21);
		contentPanel.add(txtaddress);
		
		JLabel lblid_2_1_1 = new JLabel("가입일");
		lblid_2_1_1.setBounds(124, 190, 57, 15);
		contentPanel.add(lblid_2_1_1);
		
		txtmDate = new JTextField();
		txtmDate.setText("2022-5-3");
		txtmDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtmDate.setColumns(10);
		txtmDate.setBounds(193, 187, 116, 21);
		contentPanel.add(txtmDate);
		
		JButton btndelete = new JButton("회원삭제");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteMember();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		btndelete.setBounds(325, 67, 97, 61);
		contentPanel.add(btndelete);
		
		txtid.setText(sID);
		String sql = "select * from memberTBL where id=" + strID;
		
		ShowMember(sql);
	}

	protected void DeleteMember() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		String sql = "delete from memberTBL where id=" + strID;
		stmt.executeUpdate(sql);
		
	}

	private void ShowMember(String sql) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String strCon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(strCon);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			txtname.setText(rs.getString("name"));
			txtphone.setText(rs.getString("phone"));
			txtaddress.setText(rs.getString("address"));
			txtmDate.setText(rs.getString("mDate"));
			
			ImageIcon pic = new ImageIcon(rs.getString("picPath"));			
			Image image = pic.getImage();
			
			image = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon imageCon = new ImageIcon(image);
			lblpic.setIcon(imageCon);
		}
		
	}
}
