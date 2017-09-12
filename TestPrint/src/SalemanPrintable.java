import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;

public class SalemanPrintable implements Printable {
	
	ArrayList<Saleman> list;
	public SalemanPrintable(ArrayList<Saleman> xlist)
	{
		list = xlist;
	}
	
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
		if (page > 0 || list == null ||list.size() == 0) {
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		g.drawString("Hello World!", 100, 100);
		
		int x=50;
		int y=200;
		for(int i=0;i<list.size();i++)
		{
			y = 700-list.get(i).totalSale;
			g2d.fillRect(x, y, 20, list.get(i).totalSale);
			
			g2d.translate(x, 700+10);
			g2d.rotate(Math.PI/3);
			g2d.drawString(list.get(i).name, 0, 0);
			g2d.rotate(-Math.PI/3);
			g2d.translate(-x, -(700+10));
			x += 22;
		}
		
		
		return PAGE_EXISTS;
	}
}
