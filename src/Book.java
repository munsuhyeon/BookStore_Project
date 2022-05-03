

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Book extends JPanel {
	private JTextField txtisbn;
	private JTextField txttitle;
	private JTextField txtauthor;
	private JTextField txtpublisher;
	private JTextField txtmDate;
	private JTextField txtprice;
	private JTextField txtamount;

	/**
	 * Create the panel.
	 */
	public Book(String isbn, String title, String author, String publisher, String mDate,
				String price, String amount, String picPath) {
		setLayout(null);
		
		JLabel lblpic = new JLabel("");
		lblpic.setBackground(Color.ORANGE);
		lblpic.setOpaque(isOpaque());
		lblpic.setBounds(12, 21, 150, 220);
		add(lblpic);
		
		JLabel lblisbn = new JLabel("ISBN");
		lblisbn.setBounds(182, 24, 57, 15);
		add(lblisbn);
		
		txtisbn = new JTextField();
		txtisbn.setEditable(false);
		txtisbn.setBounds(251, 21, 128, 21);
		add(txtisbn);
		txtisbn.setColumns(10);
		
		JLabel lbltitle = new JLabel("제목");
		lbltitle.setBounds(182, 59, 57, 15);
		add(lbltitle);
		
		txttitle = new JTextField();
		txttitle.setEditable(false);
		txttitle.setColumns(10);
		txttitle.setBounds(251, 56, 221, 21);
		add(txttitle);
		
		JLabel lblauthor = new JLabel("저자");
		lblauthor.setBounds(182, 99, 57, 15);
		add(lblauthor);
		
		txtauthor = new JTextField();
		txtauthor.setEditable(false);
		txtauthor.setColumns(10);
		txtauthor.setBounds(251, 96, 221, 21);
		add(txtauthor);
		
		JButton btnupdate = new JButton("변경");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
					Connection con = DriverManager.getConnection(temp);
					Statement stmt = con.createStatement();
					String sql = "update bookTBL set amount=" + txtamount.getText();
					sql = sql + "where isbn=" + txtisbn.getText().trim();
					if(stmt.executeUpdate(sql)>0);
					JOptionPane.showMessageDialog(null, "변경 완료");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnupdate.setBounds(268, 313, 97, 41);
		add(btnupdate);
		
		JLabel lblauthor_1 = new JLabel("출판사");
		lblauthor_1.setBounds(182, 140, 57, 15);
		add(lblauthor_1);
		
		txtpublisher = new JTextField();
		txtpublisher.setEditable(false);
		txtpublisher.setColumns(10);
		txtpublisher.setBounds(251, 137, 128, 21);
		add(txtpublisher);
		
		JLabel lblauthor_2 = new JLabel("출판일자");
		lblauthor_2.setBounds(182, 181, 57, 15);
		add(lblauthor_2);
		
		txtmDate = new JTextField();
		txtmDate.setEditable(false);
		txtmDate.setColumns(10);
		txtmDate.setBounds(251, 178, 128, 21);
		add(txtmDate);
		
		JLabel lblauthor_3 = new JLabel("가격");
		lblauthor_3.setBounds(182, 222, 57, 15);
		add(lblauthor_3);
		
		txtprice = new JTextField();
		txtprice.setEditable(false);
		txtprice.setColumns(10);
		txtprice.setBounds(251, 219, 128, 21);
		add(txtprice);
		
		JLabel lblauthor_4 = new JLabel("수량");
		lblauthor_4.setBounds(182, 260, 57, 15);
		add(lblauthor_4);
		
		txtamount = new JTextField();
		txtamount.setColumns(10);
		txtamount.setBounds(251, 257, 128, 21);
		add(txtamount);
		
		txtisbn.setText(isbn);
		txttitle.setText(title);
		txtauthor.setText(author);
		txtpublisher.setText(publisher);
		txtmDate.setText(mDate);
		txtprice.setText(price);
		txtamount.setText(amount);
		if(picPath.trim().length()!=0)
			lblpic.setText("<html><img src='" + picPath + "' + width=137 height=206></html>"); 
		else
			lblpic.setText("<html><img src='https://bookthumb-phinf.pstatic.net/cover/223/538/22353804.jpg?udate=20220503' + width=137 height=206></html>");

	}
}
