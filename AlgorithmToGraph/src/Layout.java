import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layout extends JFrame {

	private JButton sort;
	public static JButton reset;
	private JPanel buttonsHome;
	private ShowAlgorithm showAlgorithm = new ShowAlgorithm();

	public Layout() {
		super("QuickSort");
		sort = new JButton("Sort");
		reset = new JButton("reset");
		buttonsHome = new JPanel();
		buttonsHome.setLayout(new GridLayout(0, 2));
		buttonsHome.add(sort);
		buttonsHome.add(reset);
		showAlgorithm.generateRandomNum();

		sort.addActionListener(e -> {

			System.out.println("Finish");
			// 這裡要讓Thread開始執行
			// DO HERE
			sort.setEnabled(false);
			Thread thread = new Thread(showAlgorithm);
			reset.setEnabled(false);
			thread.start();
//			reset.setEnabled(true);
			System.out.println("start");
//					showAlgorithm.run();
		});

		reset.addActionListener(e -> {
			showAlgorithm.generateRandomNum();
			sort.setEnabled(true);
		});

		add(buttonsHome, BorderLayout.SOUTH);
		add(showAlgorithm, BorderLayout.CENTER);
	}

}