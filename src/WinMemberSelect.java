

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinMemberSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtname;
	private JTextField txtphone;

	public WinMemberSelect() {
		setTitle("회원조회");
		setBounds(100, 100, 450, 145);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblname = new JLabel("이름");
		lblname.setBounds(12, 26, 57, 15);
		contentPanel.add(lblname);
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(81, 23, 214, 21);
		contentPanel.add(txtname);
		
		JLabel lblphone = new JLabel("전화번호");
		lblphone.setBounds(12, 67, 57, 15);
		contentPanel.add(lblphone);
		
		txtphone = new JTextField();
		txtphone.setColumns(10);
		txtphone.setBounds(81, 64, 214, 21);
		contentPanel.add(txtphone);
		
		JButton btnsearch = new JButton("검색");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strName = txtname.getText().trim();
				String strPhone = txtphone.getText().trim();
				try {
					WinMemberSearchAll dlg = new WinMemberSearchAll(strName,strPhone);
					setModal(true);
					setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnsearch.setBounds(314, 22, 97, 60);
		contentPanel.add(btnsearch);
	}

}
