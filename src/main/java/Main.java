import java.util.Scanner;

public class Main
{
    private static final int min_value = 1;
    private static final int max_width = 60;
    private static final int max_height = 30;
    private static final int max_difficulty = 100;

    static int intErrorCatch (int x, int y) //it kind of works?
    {
        int max, min;

        if (x < y)
        {
            min = x;
            max = y;
        }
        else
        {
            min = y;
            max = x;
        }

        int input;
        boolean loopEnd;
        String wordcheck;

        do {
            System.out.println("Wprowadź int od " + min + " do " + max + ".");
            Scanner userInput = new Scanner(System.in);

            try
            {
                input = userInput.nextInt();

                if(input > max)
                {
                    loopEnd = false;
                    System.out.println("Invalid input.");
                    return -1;
                }
                else if(input < min)
                {
                    loopEnd = false;
                    System.out.println("Invalid input.");
                    return -1;
                }
                else
                {
                    loopEnd = true;
                    System.out.println(input + " is valid.");
                    return input;
                }
            }
            catch (Exception e)
            {
                loopEnd = false;
                wordcheck = userInput.next();
                System.out.println("Invalid input.");
                return 0;
            }
        }while (!loopEnd);
    }

    public static void main(String[] args)
    {
        System.out.println("Enter width");
        int w = intErrorCatch(min_value, max_width);

        System.out.println("Enter height");
        int h = intErrorCatch(min_value, max_height);

        System.out.println("Enter difficulty");
        int d = intErrorCatch(min_value, max_difficulty);

        new Minesweeper(w, h, d);
    }
}
