import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinBookSelect extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txttitle;
	private JTextField txtauthor;

	public WinBookSelect() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBookSelect.class.getResource("/image/icons8-search-64.png")));
		setTitle("도서조회");
		setBounds(100, 100, 450, 160);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbltitle = new JLabel("제목");
		lbltitle.setBounds(23, 35, 57, 15);
		contentPanel.add(lbltitle);
		
		txttitle = new JTextField();
		txttitle.setBounds(92, 32, 214, 21);
		contentPanel.add(txttitle);
		txttitle.setColumns(10);
		
		JLabel lblauthor = new JLabel("저자");
		lblauthor.setBounds(23, 76, 57, 15);
		contentPanel.add(lblauthor);
		
		txtauthor = new JTextField();
		txtauthor.setColumns(10);
		txtauthor.setBounds(92, 73, 214, 21);
		contentPanel.add(txtauthor);
		
		JButton btnsearch = new JButton("검색");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String strTitle = txttitle.getText().trim();
				String strAuthor = txtauthor.getText().trim();
				
				WinSearchShowAll dlg;
				try {
					dlg = new WinSearchShowAll(strTitle, strAuthor);
					dlg.setModal(true);
					dlg.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnsearch.setBounds(325, 31, 97, 60);
		contentPanel.add(btnsearch);
	}
	

}
