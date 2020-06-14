import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Actions implements ActionListener, MouseListener
{
    private Minesweeper mine;

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public Actions(Minesweeper m)
    {
        mine = m;
    }

    public void actionPerformed(ActionEvent e)
    {
        mine.reset();
        mine.refresh();
    }

    public void mouseClicked(MouseEvent e)
    {
        if (e.getButton() == 1)//left click
        {
            int x = e.getX() / Board.size;
            int y = e.getY() / Board.size;

            mine.select(x, y);
        }
        if (e.getButton() == 3)//right click
        {
            int x = e.getX() / Board.size;
            int y = e.getY() / Board.size;

            mine.mark(x, y);
        }
        mine.refresh();
    }
}
