import java.awt.Color;

import javax.swing.JFrame;

public class FlashBG extends Thread{
	
	private Color temporaryColor;
	private JFrame temporaryJFrame;

	public FlashBG(Color c, JFrame f)
	{
		temporaryJFrame = f;
		temporaryColor = c;
	}
	
	@Override
	public void run() 
	{
		temporaryJFrame.setBackground(temporaryColor);
		temporaryJFrame.revalidate();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		temporaryJFrame.setBackground(Csts.NEUTRE);
		temporaryJFrame.revalidate();
		interrupt();
	}

}
