import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WinBookInsert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtisbn;
	private JTextField txttitle;
	private JTextField txtauthor;
	private JTextField txtpublisher;
	private JTextField txtmDate;
	private JTextField txtprice;
	JLabel lblpicURL;
	JSpinner spinner_amount;
	
	public WinBookInsert() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBookInsert.class.getResource("/image/icons8-book-64.png")));
		setTitle("도서등록");
		setBounds(100, 100, 558, 564);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 542, 525);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblpic = new JLabel("New label");
		lblpic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) { //더블클릭
					String picUrl = JOptionPane.showInputDialog("책 그림의 URL을 입력하세요");;
					lblpicURL.setText(picUrl); //입력한 url 표시
					//lblpic.setText("<html><img src='" + picUrl + "'></html>"); //입력한 url의 이미지로 바뀜
							lblpic.setText("<html><img src='" + picUrl + "' + width=137 height=206></html>"); 
				}
			}
		});
		lblpic.setHorizontalAlignment(SwingConstants.CENTER);
		lblpic.setBounds(12, 10, 150, 220);
		contentPanel.add(lblpic);
		lblpic.setText("<html><img src='https://bookthumb-phinf.pstatic.net/cover/121/433/12143302.jpg?type=m5'></html>");
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(201, 13, 57, 15);
		contentPanel.add(lblNewLabel);
		
		txtisbn = new JTextField();
		txtisbn.setBounds(251, 10, 144, 21);
		contentPanel.add(txtisbn);
		txtisbn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("제목");
		lblNewLabel_1.setBounds(201, 48, 57, 15);
		contentPanel.add(lblNewLabel_1);
		
		txttitle = new JTextField();
		txttitle.setColumns(10);
		txttitle.setBounds(251, 45, 144, 21);
		contentPanel.add(txttitle);
		
		JLabel lblNewLabel_2 = new JLabel("저자");
		lblNewLabel_2.setBounds(201, 82, 57, 15);
		contentPanel.add(lblNewLabel_2);
		
		txtauthor = new JTextField();
		txtauthor.setColumns(10);
		txtauthor.setBounds(251, 79, 144, 21);
		contentPanel.add(txtauthor);
		
		JLabel lblNewLabel_3 = new JLabel("출판사");
		lblNewLabel_3.setBounds(201, 113, 57, 15);
		contentPanel.add(lblNewLabel_3);
		
		txtpublisher = new JTextField();
		txtpublisher.setColumns(10);
		txtpublisher.setBounds(251, 110, 144, 21);
		contentPanel.add(txtpublisher);
		
		JLabel lblNewLabel_4 = new JLabel("출판일");
		lblNewLabel_4.setBounds(201, 151, 57, 15);
		contentPanel.add(lblNewLabel_4);
		
		txtmDate = new JTextField();
		txtmDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String strDate = txtmDate.getText().trim();
				int length = strDate.length();
				String temp = "";
				if(length == 8) {
					temp = strDate.substring(0,4) + "-" + strDate.substring(4,6) + "-" + strDate.substring(6);
							txtmDate.setText(temp);
				}else {
					JOptionPane.showMessageDialog(null, "날짜 형식 오류");
					txtmDate.setSelectionStart(0);
					txtmDate.setSelectionEnd(length);
					txtmDate.setSelectedTextColor(Color.RED);
				}
			}
		});
		txtmDate.setColumns(10);
		txtmDate.setBounds(251, 148, 144, 21);
		contentPanel.add(txtmDate);
		
		JLabel lblNewLabel_5 = new JLabel("가격");
		lblNewLabel_5.setBounds(201, 187, 57, 15);
		contentPanel.add(lblNewLabel_5);
		
		txtprice = new JTextField();
		txtprice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//F2=0000, F3=000, F4=00
				 String money = txtprice.getText().replaceAll(",", "").trim();
				   if(e.getKeyCode()==KeyEvent.VK_F2) {
					  money = money + "0000";
					  money = money.replaceAll("\\B(?=(\\d{3})+(?!\\d))",",");//1000단위로 ,표시하기
					  txtprice.setText(money);
					  //spinner_amount.requestFocus();
				   } else if(e.getKeyCode()==KeyEvent.VK_F3) {
						  money = money + "000";
						  money = money.replaceAll("\\B(?=(\\d{3})+(?!\\d))",",");
				   		  txtprice.setText(money);
				   		  //spinner_amount.requestFocus();
				   } else if(e.getKeyCode()==KeyEvent.VK_F4) 
						  money = money + "00";
				   money = money.replaceAll("\\B(?=(\\d{3})+(?!\\d))",",");
				   		  txtprice.setText(money);
				   		  //spinner_amount.requestFocus();
			}
		});
		txtprice.setColumns(10);
		txtprice.setBounds(251, 184, 144, 21);
		contentPanel.add(txtprice);
		
		JLabel lblNewLabel_5_1 = new JLabel("수량");
		lblNewLabel_5_1.setBounds(201, 222, 57, 15);
		contentPanel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("책 소개");
		lblNewLabel_5_2.setBounds(12, 303, 57, 15);
		contentPanel.add(lblNewLabel_5_2);
		
		JButton btncheck = new JButton("중복체크");
		btncheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CheckISBN(txtisbn.getText());
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}

			private void CheckISBN(String isbn) throws ClassNotFoundException, SQLException {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
				Connection con = DriverManager.getConnection(temp);
				Statement stmt = con.createStatement();
				String sql = "select * from bookTBL where isbn=" + isbn;
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					JOptionPane.showMessageDialog(null,isbn+"이 존재합니다");
					txtisbn.setText("");
					txtisbn.requestFocus();
				}else {
						JOptionPane.showMessageDialog(null,isbn+"이 없습니다");
						txttitle.requestFocus();
				}
			}
		});
		btncheck.setBounds(420, 23, 97, 23);
		contentPanel.add(btncheck);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(91, 303, 401, 189);
		contentPanel.add(scrollPane);
		
		JTextArea txtcontent = new JTextArea();
		scrollPane.setViewportView(txtcontent);
		
		JButton btnNewButton_1 = new JButton("도서등록");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
				Connection con = null;
				try {
					con = DriverManager.getConnection(temp);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Statement stmt = null;
				try {
					stmt = con.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String sql = "INSERT INTO bookTBL VALUES(" + txtisbn.getText() + ",'";
				sql = sql + txttitle.getText() + "','" + txtauthor.getText() + "','";
				sql = sql + txtpublisher.getText() + "','" + txtmDate.getText() + "',";
				sql = sql + txtprice.getText().replaceAll(",", "") + "," + spinner_amount.getValue() + ",'";
				sql = sql + txtcontent.getText() + "','" + lblpicURL.getText() + "');";
				System.out.println(sql);
				try {
					if(stmt.executeUpdate(sql) >= 1) {
						JOptionPane.showMessageDialog(null, "도서 추가 완료");
						ClearAll();
					}else
						JOptionPane.showMessageDialog(null, "도서 추가 미완료");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			private void ClearAll() {
				txtisbn.setText("");
				txttitle.setText("");
				txtauthor.setText("");
				txtpublisher.setText("");
				txtmDate.setText("");
				txtprice.setText("");
				spinner_amount.setValue(1);
				txtcontent.setText("");
				lblpicURL.setText("");
				txtisbn.requestFocus();
			}
		});
		btnNewButton_1.setBounds(420, 208, 97, 23);
		contentPanel.add(btnNewButton_1);
		
		spinner_amount = new JSpinner();
		spinner_amount.setBounds(251, 219, 144, 22);
		contentPanel.add(spinner_amount);
		
		lblpicURL = new JLabel("그림 파일의 경로");
		lblpicURL.setBounds(25, 260, 467, 15);
		contentPanel.add(lblpicURL);
		
		JButton btnCalendar = new JButton("달력");
		btnCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinCalendar cal = new WinCalendar();
				cal.setModal(true);
				cal.setVisible(true);
				txtmDate.setText(cal.getMDate());
			}
		});
		btnCalendar.setBounds(420, 147, 97, 23);
		contentPanel.add(btnCalendar);
	}
}
