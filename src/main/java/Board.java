import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Board extends JPanel
{
    private static final long serialVersionUID = 1L; //ensures consistency across different java compilers
    public static final int size = 20;

    private Minesweeper mine;
    private Cell[][] cells;

    /**
     * Draws and updates visible gamespace
     * @param g
     */
    public void paintComponent(Graphics g)
    {
        cells = mine.getCells();

        for(int i = 0; i < mine.getx(); i++)
        {
            for(int j = 0; j < mine.gety(); j++)
            {
                Cell current = cells[i][j];

                if(current.isFlagged())
                {
                    if(current.isMine() && mine.isFinished())
                    {
                        g.setColor(Color.ORANGE);
                        g.fillRect(i * size, j * size, i* size + size, j * size + size);
                        g.setColor(Color.BLACK);

                        g.drawLine(i * size, j * size, i * size + size, j * size + size);
                        g.drawLine(i * size, j * size + size, i * size + size, j * size);
                    }
                    else if (mine.isFinished())
                    {
                        g.setColor(Color.YELLOW);
                        g.fillRect(i * size, j * size, i * size + size, j * size + size);
                        g.setColor(Color.BLACK);
                    }
                    else
                    {
                        g.setColor(Color.GREEN);
                        g.fillRect(i * size, j * size, i * size + size, j * size + size);
                        g.setColor(Color.BLACK);
                    }
                }
                else if(current.isCovered())
                {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(i * size, j * size, i * size + size, j * size + size);
                    g.setColor(Color.BLACK);
                }
                else if (current.isMine())
                {
                    g.setColor(Color.RED);
                    g.fillRect(i * size, j * size, i * size + size, j * size + size);
                    g.setColor(Color.BLACK);
                    g.drawLine(i * size, j * size, i * size + size, j * size + size);
                    g.drawLine(i * size, j * size + size, i * size + size, j * size);
                }
                else
                {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(i * size,j * size, i * size + size, j * size + size);
                    g.setColor(Color.BLACK);
                }
                /*
                Numbers represent a "line" position on clock like style
                  2
                1   3
                  4
                5   6
                  7
                 Just like this
                 */
                if (!current.isCovered())
                {
                    if (current.getNumber() == 1)
                    {
                        g.drawLine(i * size + 13, j * size + 5, i * size + 13, j * size + 9);    //3
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                    }
                    else if (current.getNumber() == 2)
                    {
                        g.drawLine(i * size + 8, j * size + 4, i * size + 12, j * size + 4);    //2
                        g.drawLine(i * size + 13, j * size + 5, i * size + 13, j * size + 9);    //3
                        g.drawLine(i * size + 8, j * size + 10, i * size + 12, j * size + 10);    //4
                        g.drawLine(i * size + 7, j * size + 11, i * size + 7, j * size + 15);    //5
                        g.drawLine(i * size + 8, j * size + 16, i * size + 12, j * size + 16);    //7
                    }
                    else if (current.getNumber() == 3)
                    {
                        g.drawLine(i * size + 8, j * size + 4, i * size + 12, j * size + 4);    //2
                        g.drawLine(i * size + 13, j * size + 5, i * size + 13, j * size + 9);    //3
                        g.drawLine(i * size + 8, j * size + 10, i * size + 12, j * size + 10);    //4
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                        g.drawLine(i * size + 8, j * size + 16, i * size + 12, j * size + 16);    //7
                    }
                    else if (current.getNumber() == 4)
                    {
                        g.drawLine(i * size + 7, j * size + 5, i * size + 7, j * size + 9);        //1
                        g.drawLine(i * size + 13, j * size + 5, i * size + 13, j * size + 9);    //3
                        g.drawLine(i * size + 8, j * size + 10, i * size + 12, j * size + 10);    //4
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                    }
                    else if (current.getNumber() == 5)
                    {
                        g.drawLine(i * size + 7, j * size + 5, i * size + 7, j * size + 9);        //1
                        g.drawLine(i * size + 8, j * size + 4, i * size + 12, j * size + 4);    //2
                        g.drawLine(i * size + 8, j * size + 10, i * size + 12, j * size + 10);    //4
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                        g.drawLine(i * size + 8, j * size + 16, i * size + 12, j * size + 16);    //7
                    }
                    else if (current.getNumber() == 6)
                    {
                        g.drawLine(i * size + 7, j * size + 5, i * size + 7, j * size + 9);        //1
                        g.drawLine(i * size + 8, j * size + 4, i * size + 12, j * size + 4);    //2
                        g.drawLine(i * size + 8, j * size + 10, i * size + 12, j * size + 10);    //4
                        g.drawLine(i * size + 7, j * size + 11, i * size + 7, j * size + 15);    //5
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                        g.drawLine(i * size + 8, j * size + 16, i * size + 12, j * size + 16);    //7
                    }
                    else if (current.getNumber() == 7)
                    {
                        g.drawLine(i * size + 8, j * size + 4, i * size + 12, j * size + 4);    //2
                        g.drawLine(i * size + 13, j * size + 5, i * size + 13, j * size + 9);    //3
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                    }
                    else if (current.getNumber() == 8)
                    {
                        g.drawLine(i * size + 7, j * size + 5, i * size + 7, j * size + 9);        //1
                        g.drawLine(i * size + 8, j * size + 4, i * size + 12, j * size + 4);    //2
                        g.drawLine(i * size + 13, j * size + 5, i * size + 13, j * size + 9);    //3
                        g.drawLine(i * size + 8, j * size + 10, i * size + 12, j * size + 10);    //4
                        g.drawLine(i * size + 7, j * size + 11, i * size + 7, j * size + 15);    //5
                        g.drawLine(i * size + 13, j * size + 11, i * size + 13, j * size + 15);    //6
                        g.drawLine(i * size + 8, j * size + 16, i * size + 12, j * size + 16);    //7
                    }
                }
                g.setColor(Color.BLACK);
                g.drawRect(i * size, j * size, i * size + size, j * size + size);
            }
        }
    }

    public Board(Minesweeper m)
    {
        mine = m;
        cells = mine.getCells();

        addMouseListener(new Actions(mine));

        setPreferredSize(new Dimension(mine.getx() * size, mine.gety() * size));
    }
}
