import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

public class ShowAlgorithm extends JPanel implements Runnable {


	private int[] randNums = new int[200];

	private void drawPoints(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(Color.orange);

		for (int i = 0; i < randNums.length; i++) {
			g2d.fillOval(100 + i * 3, 100 + randNums[i] * 3 - 3, 6, 6);
//			System.out.println("i: " + i + ", randNums[i]:" + randNums[i]);
		}
	}

	public void generateRandomNum() {
		for (int i = 0; i < randNums.length; i++) {
			randNums[i] = i + 1;
		}
		Random rand = new Random();

		for (int i = 0; i < randNums.length; i++) {
			int randomIndexToSwap = rand.nextInt(randNums.length);
			int temp = randNums[randomIndexToSwap];
			randNums[randomIndexToSwap] = randNums[i];
			randNums[i] = temp;
//			System.out.println("randNums[i]: " + randNums[i]);
		}
		repaint();
	}

	public void drawScaleX(Graphics g) {
		g.setColor(Color.black);
		g.drawString("0", 100, 712);
		g.drawString("199", 691, 712);
		for (int i = 1; i < 10; i++) {
			g.setColor(Color.lightGray);
			g.drawLine(100 + i * 30, 100, 100 + i * 30, 700);
			g.setColor(Color.black);
			g.drawString(String.valueOf((i) * 10), 100 + i * 30 - 6, 712);
		}
		for (int i = 10; i <= 20; i++) {
			g.setColor(Color.lightGray);
			g.drawLine(100 + i * 30, 100, 100 + i * 30, 700);
			g.setColor(Color.black);
			if (i < 20) {
				g.drawString(String.valueOf((i) * 10), 100 + i * 30 - 9, 712);
			}
		}
	}

	public void drawScaleY(Graphics g) {
		g.setColor(Color.black);
		g.drawString("200", 80, 105);
		g.drawString("1", 90, 700);
		for (int i = 1; i < 10; i++) {
			g.drawString(String.valueOf((i) * 10 + 1), 85, 705 - i * 30);
			g.setColor(Color.lightGray);
			g.drawLine(100, 700 - i * 30, 700, 700 - i * 30);
			g.setColor(Color.black);
		}
		for (int i = 10; i <= 20; i++) {
			if (i < 20) {
				g.drawString(String.valueOf((i) * 10 + 1), 80, 705 - i * 30);
			}
			g.setColor(Color.lightGray);
			g.drawLine(100, 700 - i * 30, 700, 700 - i * 30);
			g.setColor(Color.black);
		}
	}

	private void clear(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(100, 100, 600, 600);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		clear(g);
		// the drawings occur here
		g.setColor(Color.black);
		g.drawLine(100, 100, 100, 700);
		g.drawLine(100, 700, 700, 700);
		drawScaleX(g);
		drawScaleY(g);
		drawPoints(g);
	}

	@Override
	public void run() {
		Layout.reset.setEnabled(false);
		quick(randNums);
		System.out.println("Success!");
		Layout.reset.setEnabled(true);
		
		/*這裡是利用Sort Class 的寫法*/
//		Sort sort = new Sort(randNums, ()->{
//			SwingUtilities.invokeLater(()->{
//				repaint();
//			});
//		});
//		Thread thread = new Thread(sort);
//		thread.start();
	}

	// QuickSort 寫法 (Sort Class 的內容)
	public void quick(int[] number) {
		sort(number, 0, number.length - 1);
	}

	private void sort(int[] number, int left, int right) {
		if (left < right) {
			int q = partition(number, left, right);
			sort(number, left, q - 1);
			sort(number, q + 1, right);
		}

	}

	private int partition(int number[], int left, int right) {
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (number[j] >= number[right]) {
				i++;
				swap(number, i, j);
			}
			repaint();// 交換完後立即重畫
			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		swap(number, i + 1, right);
		repaint();// 最後交換完後重畫
		return i + 1;
	}

	private void swap(int[] number, int i, int j) {
		int t = number[i];
		number[i] = number[j];
		number[j] = t;
	}

}