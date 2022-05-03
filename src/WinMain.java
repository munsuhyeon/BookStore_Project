import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class WinMain extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblresult = new JLabel("준비중...");
	

	public WinMain() {
		setTitle("(주)RYAN 책 대여점");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 544);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnubook = new JMenu("도서관리");
		menuBar.add(mnubook);
		
		JMenuItem mnubookinsert = new JMenuItem("도서 등록");
		mnubookinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookInsert wbi = new WinBookInsert();
				wbi.setModal(true);
				wbi.setVisible(true);
				lblresult.setText("책을 추가하고 있습니다");
			}
		});
		mnubook.add(mnubookinsert);
		
		JMenuItem mnubookupdate = new JMenuItem("도서 갱신");
		mnubookupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookUpdate wbu = new WinBookUpdate();
				wbu.setModal(true);
				wbu.setVisible(true);
			}
		});
		mnubook.add(mnubookupdate);
		
		JMenuItem mnubookdelete = new JMenuItem("도서 삭제");
		mnubookdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = JOptionPane.showInputDialog("삭제할 책 제목을 입력하시오");
				WinBookDelete wbd = null;
				try {
					wbd = new WinBookDelete(title);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				wbd.setModal(true);
				wbd.setVisible(true);
			}
		});
		mnubook.add(mnubookdelete);
		
		JSeparator separator = new JSeparator();
		mnubook.add(separator);
		
		JMenuItem mnubookselect = new JMenuItem("도서 조회");
		mnubookselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				WinBookSelect wbs = new WinBookSelect();
				wbs.setModal(true);
				wbs.setVisible(true);
			}
		});
		mnubook.add(mnubookselect);
		
		JMenu mnumember = new JMenu("회원관리");
		menuBar.add(mnumember);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("회원 가입");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberInsert wmi = new WinMemberInsert();
				wmi.setModal(true);
				wmi.setVisible(true);
			}
		});
		mnumember.add(mntmNewMenuItem);
		
		JMenuItem mnumemberupdate = new JMenuItem("회원 갱신");
		mnumemberupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberUpdate wmu = new WinMemberUpdate();
				wmu.setModal(true);
				wmu.setVisible(true);
			}
		});
		mnumember.add(mnumemberupdate);
		
		JMenuItem mnumemberdelete = new JMenuItem("회원 삭제");
		mnumemberdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sName = JOptionPane.showInputDialog("삭제할 회원의 이름을 입력하시오");
				WinMemberDelete wmd;
				try {
					wmd = new WinMemberDelete(sName);
					wmd.setModal(true);
					wmd.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			}
		});
		mnumember.add(mnumemberdelete);
		
		JSeparator separator_1 = new JSeparator();
		mnumember.add(separator_1);
		
		JMenuItem mnumemberselect = new JMenuItem("회원 조회");
		mnumemberselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberSelect wms = new WinMemberSelect();
				wms.setModal(true);
				wms.setVisible(true);
			}
		});
		mnumember.add(mnumemberselect);
		
		JMenu mnuborrow = new JMenu("대여리스트");
		menuBar.add(mnuborrow);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnbookinsert = new JButton("");
		btnbookinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookInsert wbi = new WinBookInsert();
				wbi.setModal(true);
				wbi.setVisible(true);
				
			}
		});
		btnbookinsert.setIcon(new ImageIcon(WinMain.class.getResource("/image/add50.png")));
		toolBar.add(btnbookinsert);
		
		JButton btnbookdelete = new JButton("");
		btnbookdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = JOptionPane.showInputDialog("삭제할 책 제목을 입력하시오");
				WinBookDelete wbd = null;
				try {
					wbd = new WinBookDelete(title);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				wbd.setModal(true);
				wbd.setVisible(true);
			}
		});
		btnbookdelete.setIcon(new ImageIcon(WinMain.class.getResource("/image/delete50.png")));
		toolBar.add(btnbookdelete);
		
		JButton btnbookupdate = new JButton("");
		btnbookupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookSelect wbs = new WinBookSelect();
				wbs.setModal(true);
				wbs.setVisible(true);
			}
		});
		btnbookupdate.setIcon(new ImageIcon(WinMain.class.getResource("/image/booksearch50.png")));
		toolBar.add(btnbookupdate);
		
		JButton btnbookselect = new JButton("");
		btnbookselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinBookUpdate wbu = new WinBookUpdate();
				wbu.setModal(true);
				wbu.setVisible(true);
			}
		});
		btnbookselect.setIcon(new ImageIcon(WinMain.class.getResource("/image/accept50.png")));
		toolBar.add(btnbookselect);
		
		JSeparator separator_2 = new JSeparator();
		toolBar.add(separator_2);
		
		textField = new JTextField();
		textField.setText("전화번호4자리");
		toolBar.add(textField);
		textField.setColumns(10);
		
		JSeparator separator_3 = new JSeparator();
		toolBar.add(separator_3);
		
		JButton btnmemberinsert = new JButton("");
		btnmemberinsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberInsert wmi = new WinMemberInsert();
				wmi.setModal(true);
				wmi.setVisible(true);
			}
		});
		btnmemberinsert.setIcon(new ImageIcon(WinMain.class.getResource("/image/plus50.png")));
		toolBar.add(btnmemberinsert);
		
		JButton btnmemberdelete = new JButton("");
		btnmemberdelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sName = JOptionPane.showInputDialog("삭제할 회원의 이름을 입력하시오");
				WinMemberDelete wmd;
				try {
					wmd = new WinMemberDelete(sName);
					wmd.setModal(true);
					wmd.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			}
		});
		btnmemberdelete.setIcon(new ImageIcon(WinMain.class.getResource("/image/cancel50.png")));
		toolBar.add(btnmemberdelete);
		
		JButton btnmemberselect = new JButton("");
		btnmemberselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberSelect wms = new WinMemberSelect();
				wms.setModal(true);
				wms.setVisible(true);
			}
		});
		btnmemberselect.setIcon(new ImageIcon(WinMain.class.getResource("/image/group50.png")));
		toolBar.add(btnmemberselect);
		
		JButton btnmemberupdate = new JButton("");
		btnmemberupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinMemberUpdate wmu = new WinMemberUpdate();
				wmu.setModal(true);
				wmu.setVisible(true);
			}
		});
		btnmemberupdate.setIcon(new ImageIcon(WinMain.class.getResource("/image/check50.png")));
		toolBar.add(btnmemberupdate);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		
		panel.add(lblresult);
	}

}
