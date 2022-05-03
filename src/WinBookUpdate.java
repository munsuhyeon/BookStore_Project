import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WinBookUpdate extends JDialog {

	private final JPanel contentPanel = new JPanel();

	
	public WinBookUpdate() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(WinBookUpdate.class.getResource("/image/icons8-books-64.png")));
		setTitle("도서갱신");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

}
