import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Member extends JPanel {
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtaddress;
	private JTextField txtmDate;
	private JTextField txtgrade;
	
	public Member(String id, String name, String address, String phone, String mDate, String picPath,String grade) {
		setLayout(null);
		
		JLabel lblpic = new JLabel("");
		lblpic.setBounds(12, 10, 100, 100);
		add(lblpic);
		
		JLabel lblid = new JLabel("ID");
		lblid.setBounds(142, 32, 57, 15);
		add(lblid);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setBounds(211, 29, 137, 21);
		add(txtid);
		txtid.setColumns(10);
		
		JLabel lblname = new JLabel("이름");
		lblname.setBounds(142, 65, 57, 15);
		add(lblname);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(211, 62, 137, 21);
		add(txtname);
		
		JLabel lblphone = new JLabel("전화번호");
		lblphone.setBounds(142, 103, 57, 15);
		add(lblphone);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(211, 100, 137, 21);
		add(txtphone);
		
		JLabel lbladdress = new JLabel("주소");
		lbladdress.setBounds(142, 140, 57, 15);
		add(lbladdress);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(211, 137, 227, 21);
		add(txtaddress);
		
		JLabel lblmDate = new JLabel("가입일");
		lblmDate.setBounds(142, 173, 57, 15);
		add(lblmDate);
		
		txtmDate = new JTextField();
		txtmDate.setColumns(10);
		txtmDate.setBounds(211, 170, 137, 21);
		add(txtmDate);
		
		JLabel lblgrade = new JLabel("등급");
		lblgrade.setBounds(142, 211, 57, 15);
		add(lblgrade);
		
		txtgrade = new JTextField();
		txtgrade.setColumns(10);
		txtgrade.setBounds(211, 208, 137, 21);
		add(txtgrade);
		
		JButton btnupdate = new JButton("변경");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
					Connection con = DriverManager.getConnection(temp);
					Statement stmt = con.createStatement();
					String sql ="update memberTBL set name='" + txtname.getText();
					sql = sql + "', phone='" + txtphone.getText().trim() + "', address='";
					sql = sql + txtaddress.getText() + "'";
					sql = sql + " where id=" + txtid.getText().trim();
					if(stmt.executeUpdate(sql)>0)
						JOptionPane.showMessageDialog(null, "변경완료");
				}catch(ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch(SQLException e1){
					e1.printStackTrace();
				}
			}
		});
		btnupdate.setBounds(231, 250, 97, 23);
		add(btnupdate);
		
		txtid.setText(id);
		txtname.setText(name);
		txtphone.setText(phone);
		txtaddress.setText(address);
		txtmDate.setText(mDate);
		txtgrade.setText(grade);
		
		ImageIcon pic = new ImageIcon(picPath);
		Image image = pic.getImage();
		image = image.getScaledInstance(100,100,Image.SCALE_SMOOTH);
		ImageIcon pic1 = new ImageIcon(image);
		lblpic.setIcon(null);
	}

}
