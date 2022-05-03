import java.awt.Color;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WinBookInfo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtisbn;
	private JTextField txttitle;
	private JTextField txtauthor;
	private JTextField txtpublisher;
	private JTextField txtmDate;
	private JTextField txtprice;
	private String strTitle = "";
	JLabel lblpicURL;
	JSpinner spinner_amount;
	JTextArea txtcontent;
	JLabel lblpic;
	String sISBN = "";
	
	
	public WinBookInfo(String strISBN, String Title) throws Exception {
		strTitle = Title;
		sISBN = strISBN;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBookInfo.class.getResource("/image/icons8-trash-64.png")));
		setTitle("도서삭제정보");
		setBounds(100, 100, 558, 564);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 542, 525);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblpic = new JLabel("New label");
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBookInfo.class.getResource("/image/icons8-trash-64.png")));
		setTitle("도서삭제정보");
		setBounds(100, 100, 558, 564);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 542, 525);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		lblpic = new JLabel("New label");
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
				if(KeyEvent.VK_ENTER == e.getKeyCode()) {
					String strDate = txtmDate.getText().trim();
					int length = strDate.length();
					String temp = "";
					if(length == 8) {
						temp = strDate.substring(0,4)+"-"+strDate.substring(4,6)+"-"+strDate.substring(6); 
						txtmDate.setText(temp);
					}else {
						JOptionPane.showMessageDialog(null, "날짜 형식 오류");
						txtmDate.setSelectionStart(0);
						txtmDate.setSelectionEnd(length);
						txtmDate.setSelectedTextColor(Color.RED);
					}
				}
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(91, 303, 401, 189);
		contentPanel.add(scrollPane);
		
		txtcontent = new JTextArea();
		txtcontent.setLineWrap(true);
		scrollPane.setViewportView(txtcontent);
		
		JButton btnbookinsert = new JButton("도서삭제");
		btnbookinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DeleteBook();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnbookinsert.setBounds(420, 208, 97, 23);
		contentPanel.add(btnbookinsert);
		
		spinner_amount = new JSpinner();
		spinner_amount.setBounds(251, 219, 144, 22);
		contentPanel.add(spinner_amount);
		
		lblpicURL = new JLabel("그림 파일의 경로");
		lblpicURL.setBounds(25, 260, 467, 15);
		contentPanel.add(lblpicURL);
		
		JButton btnNewButton_2 = new JButton("취소");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			WinBookDelete wbd;
			try {
				setVisible(false);
				wbd = new WinBookDelete(strTitle);
				wbd.setModal(true);
				wbd.setVisible(true);
			} catch(ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			}
		});
		btnNewButton_2.setBounds(420, 167, 97, 23);
		contentPanel.add(btnNewButton_2);
		
		String sql = "select * from bookTBL where isbn = " + sISBN;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(temp);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			txtisbn.setText(rs.getString("isbn"));
			txttitle.setText(rs.getString("title"));
			txtauthor.setText(rs.getString("author"));
			txtpublisher.setText(rs.getString("publisher"));
			txtmDate.setText(rs.getString("mDate"));
			txtprice.setText(rs.getString("price"));
			spinner_amount.setValue(rs.getInt("amount"));
			txtcontent.setText(rs.getString("description"));
			lblpic.setText("<html><img src='" + rs.getString("picPath") + "' + width=137 height=206></html>");
		}
	}
	protected void DeleteBook() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
		Connection con = DriverManager.getConnection(temp);
		Statement stmt = con.createStatement();
		String sql = "delete from bookTBL where isbn=" + txtisbn.getText();
		if(stmt.executeUpdate(sql) >= 1) {
			JOptionPane.showMessageDialog(null, "도서 삭제 완료!!!");	
			try {
				setVisible(false);
				WinBookDelete wbd = new WinBookDelete(strTitle);
				wbd.setModal(true);
				wbd.setVisible(true);
			
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
	}
		else
			JOptionPane.showMessageDialog(null, "도서 삭제 오류");
	
	}
		
		
}
