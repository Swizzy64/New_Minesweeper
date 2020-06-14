import java.awt.BorderLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Minesweeper extends JFrame
{
    private static final long serialVersionUID = 1L;
    private int width, height;
    private int difficulty;
    private Cell[][] cells;
    private Board board;
    private JButton reset;
    private boolean finished;

    /**
     * Registers user action
     * @param x
     * @param y
     */
    public void select(int x, int y)
    {
        if(cells[x][y].isFlagged())
            return;
        cells[x][y].reveal();
        resetMarks();
        refresh();

        if(cells[x][y].isMine())
        {
            lose();
        }
        else if(won())
        {
            win();
        }
    }

    /**
     * Add hazard level to cells
     */
    private void setNumbers()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                int count = 0;

                if (i > 0 &&  j > 0 && cells[i - 1][j - 1].isMine())
                    count++;

                if (j > 0 && cells[i][j - 1].isMine())
                    count++;

                if (i < width - 1 && j > 0 && cells[i + 1][j - 1].isMine())
                    count++;

                if (i > 0 && cells[i - 1][j].isMine())
                    count++;

                if (i < width - 1 && cells[i + 1][j].isMine())
                    count++;

                if (i > 0 && j < height - 1 && cells[i - 1][j + 1].isMine())
                    count++;

                if (j < height - 1 && cells[i] [j + 1].isMine())
                    count++;

                if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].isMine())
                    count++;

                cells[i][j].setNumber(count);

                if (cells[i][j].isMine())
                    cells[i][j].setNumber(-1);

                if (cells[i][j].getNumber() == 0)
                    cells[i][j].reveal();
            }
        }
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (i > 0 && j > 0 && cells[i - 1][j - 1].getNumber() == 0)
                    cells[i][j].reveal();

                if (j > 0 && cells[i][j - 1].getNumber() == 0)
                    cells[i][j].reveal();

                if (i < width - 1 && j > 0 && cells[i + 1][j - 1].getNumber() == 0)
                    cells[i][j].reveal();

                if (i > 0 && cells[i - 1][j].getNumber() == 0)
                    cells[i][j].reveal();

                if (i < width - 1 && cells[i + 1][j].getNumber() == 0)
                    cells[i][j].reveal();

                if (i > 0 && j < height - 1 && cells[i - 1][j + 1].getNumber() == 0)
                    cells[i][j].reveal();

                if (j < height - 1 && cells[i][j + 1].getNumber() == 0)
                    cells[i][j].reveal();

                if (i < width - 1 && j < height - 1 && cells[i + 1][j + 1].getNumber() == 0)
                    cells[i][j].reveal();
            }
        }
    }

    /**
     * Marks a cell with a flag
     * @param x
     * @param y
     */
    public void mark(int x, int y)
    {
        if(cells[x][y].isFlagged())
            cells[x][y].unflag();
        else if(cells[x][y].isCovered())
            cells[x][y].flag();

        resetMarks();
    }

    /**
     * Self-explanatory
     */
    private void resetMarks()
    {
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(!cells[i][j].isCovered())
                    cells[i][j].unflag();
            }
        }
    }

    /**
     * Self-explanatory
     */
    public void reset()
    {
        Random random = new Random();
        finished = false;

        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                Cell c = new Cell();
                cells[i][j] = c;
                int r = random.nextInt(100);

                if(r < difficulty)
                    cells[i][j].setMine();
            }
        }
        setNumbers();
    }

    public int getx()
    {
        return width;
    }

    public int gety()
    {
        return height;
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public void refresh()
    {
        board.repaint();
    }

    /**
     * Winning conditions and announcement
     */
    private void win()
    {
        finished = true;
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                cells[i][j].reveal();

                if(!cells[i][j].isMine())
                    cells[i][j].unflag();
            }
        }
        refresh();
        JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
        reset();
    }

    /**
     * Losing conditions and announcement
     */
    private void lose()
    {
        finished = true;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (!cells[i][j].isCovered())
                    cells[i][j].unflag();

                cells[i][j].reveal();
            }
        }
        refresh();
        JOptionPane.showMessageDialog(null, "GAME OVER!");
        reset();
    }

    /**
     * Checks if wining conditions are met
     * @return
     */
    private boolean won()
    {
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                if(cells[i][j].isCovered() && !cells[i][j].isMine())
                    return false;
            }
        }
        return true;
    }

    public boolean isFinished()
    {
        return finished;
    }

    /**
     * Fun part - adds option to customize height, width and difficulty
     * makes board, places it on screen, allows it to react to clicks
     * A very good thing, essential tbh
     * @param w
     * @param h
     * @param d
     */
    public Minesweeper(int w, int h, int d)
    {
        width = w; //Width of the board
        height = h; //Height of the board
        difficulty = d; //Percentage of mines in the board
        cells = new Cell[width][height]; //add user-specified number of cells

        reset(); //Set mines on the board

        board = new Board(this); //Create new board
        reset = new JButton("Reset"); //Reset button

        add(board, BorderLayout.CENTER); //Put board in the center
        add(reset, BorderLayout.NORTH); //IT'S A BUTTON! AND IT WORKS!

        reset.addActionListener(new Actions(this)); //ActionListener to watch for mouse actions

        setTitle("New_Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); //constant size
        pack();
        setVisible(true);
    }
}
