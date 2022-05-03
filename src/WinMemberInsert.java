
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WinMemberInsert extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtaddress;
	private JTextField txtmDate;
	private String path = "";	

	public WinMemberInsert() {
		setTitle("도서 대여점 회원가입");
		setBounds(100, 100, 450, 253);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 261);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblpic = new JLabel("");
		lblpic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) { //더블클릭
					JFileChooser chooser = new JFileChooser("C:/moon/JavaTest/image/");
					FileNameExtensionFilter filter = new FileNameExtensionFilter("그림파일","png","gif","jpg","svg");
					chooser.setFileFilter(filter);
					chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
					int ret = chooser.showOpenDialog(null);
					if(ret == JFileChooser.APPROVE_OPTION) { //APPROVE_OPTION=확인버튼을 누르면
						path = chooser.getSelectedFile().getPath(); //전체경로
						
						//이미지 크기조절
						ImageIcon image = new ImageIcon(path);
						Image pic = image.getImage();
						pic = pic.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						ImageIcon imageCon = new ImageIcon(pic);
						lblpic.setIcon(imageCon);
						
						path = path.replaceAll("\\\\","\\\\\\\\"); //역슬래쉬1개짜리를 2개짜리로 변경하기
					}
				}
			}
		});
		lblpic.setBackground(Color.YELLOW);
		lblpic.setOpaque(true);
		lblpic.setBounds(12, 10, 100, 100);
		contentPanel.add(lblpic);
		
		JLabel lblid = new JLabel("일련번호");
		lblid.setBounds(124, 10, 57, 15);
		contentPanel.add(lblid);
		
		txtid = new JTextField();
		txtid.setHorizontalAlignment(SwingConstants.CENTER);
		txtid.setEditable(false);
		txtid.setBounds(193, 7, 116, 21);
		contentPanel.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblid_1 = new JLabel("회원명");
		lblid_1.setBounds(124, 53, 57, 15);
		contentPanel.add(lblid_1);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(193, 50, 116, 21);
		contentPanel.add(txtname);
		
		JLabel lblid_2 = new JLabel("전화번호");
		lblid_2.setBounds(124, 92, 57, 15);
		contentPanel.add(lblid_2);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(193, 89, 116, 21);
		contentPanel.add(txtphone);
		
		JLabel lblid_2_1 = new JLabel("주소");
		lblid_2_1.setBounds(124, 132, 57, 15);
		contentPanel.add(lblid_2_1);
		
		txtaddress = new JTextField();
		txtaddress.setColumns(10);
		txtaddress.setBounds(193, 129, 229, 21);
		contentPanel.add(txtaddress);
		
		JLabel lblid_2_1_1 = new JLabel("가입일");
		lblid_2_1_1.setBounds(124, 172, 57, 15);
		contentPanel.add(lblid_2_1_1);
		
		txtmDate = new JTextField();
		txtmDate.setColumns(10);
		txtmDate.setBounds(193, 169, 116, 21);
		contentPanel.add(txtmDate);
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH)+1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		txtmDate.setHorizontalAlignment(SwingConstants.CENTER);
		txtmDate.setText(year + "-" + month + "-" + day);
		
		JButton btnNewButton = new JButton("중복확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CheckMember(txtname.getText(), txtphone.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			private void CheckMember(String name, String phone) throws Exception {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String temp = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
				Connection con = DriverManager.getConnection(temp);
				Statement stmt = con.createStatement();
				String sql = "select * from memberTBL where name='" + name;
				sql = sql + "'and phone='" + phone + "'";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()) {
					JOptionPane.showMessageDialog(null,"이미 존재합니다");
					txtname.setText("");
					txtphone.setText("");
					txtname.requestFocus();
				}else {
						JOptionPane.showMessageDialog(null,"등록 가능합니다");
						txtaddress.requestFocus();
				}
			}
		});
		btnNewButton.setBounds(325, 49, 97, 61);
		contentPanel.add(btnNewButton);
		
		JButton btninsert = new JButton("회원 등록");
		btninsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String strcon = "jdbc:mysql://localhost/sampledb?user=root&password=1234";
				Connection con = null;
				try {
					con = DriverManager.getConnection(strcon);
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
				
				String sql = "INSERT INTO membertbl VALUES(null,'";
				sql = sql + txtid.getText() + "','" + txtphone.getText() + "','";
				sql = sql + txtaddress.getText() + "','" + txtmDate.getText() + "','";
				sql = sql + path + "','새내기')";
				try {
					if(stmt.executeUpdate(sql) > 0)
						JOptionPane.showMessageDialog(null, "회원 등록 완료");
					else
						JOptionPane.showMessageDialog(null, "회원 등록 오류");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btninsert.setBounds(325, 168, 97, 23);
		contentPanel.add(btninsert);
	}

}
