import java.util.*;
public class TypingSpeedTester
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Typing Speed Tester!");
        
        String easy = "Once a small boy found a lost puppy on the road. He took it home, gave it food, and they became best friends.";

        String medium = "One day a young boy decided to learn typing to improve his computer skills. At first he typed very slowly and made many mistakes. But he practiced every day with patience and focus.";

        String hard = "Typing is an important skill in the modern digital world. People who type faster and more accurately can complete their work efficiently and save a lot of time in their daily tasks.";
        String currentparagraph = easy;

        boolean running = true;
        while (running) 
        {
            if(currentparagraph.equals(easy))
            {
                startLevel("Level 1:- Easy", currentparagraph, sc);
            }
            else if(currentparagraph.equals(medium))
            {
                startLevel("Level 2:- Medium", currentparagraph, sc);
            }
            else
            {
                startLevel("Level 3:- Hard", currentparagraph, sc);
            }

            if(currentparagraph.equals(hard))
            {
                System.out.println("1. Retry");
                System.out.println("2. Exit");
            }
            else
            {
                System.out.println("1. Next Level");
                System.out.println("2. Retry");
                System.out.println("3. Exit");
            }
            
            if(currentparagraph.equals(hard))
            {
                int option = sc.nextInt();
                sc.nextLine();

                if(option == 1)
                {
                    continue; // retry hard
                }
                else if(option == 2)
                {
                    System.out.println("Thank you for using Typing Speed Tester!");
                    break;
                }
            }
            else
            {
                int option = sc.nextInt();
                sc.nextLine();

                if(option == 1)
                {
                    if(currentparagraph.equals(easy))
                    {
                        currentparagraph = medium;
                    }
                    else if(currentparagraph.equals(medium))
                    {
                        currentparagraph = hard;
                    }
                }
                else if(option == 2)
                {
                    continue;
                }
                else if(option == 3)
                {
                    System.out.println("Thank you for using Typing Speed Tester!");
                    break;
                }
            }
        }
        
    }
    static void startLevel(String levelname, String paragraph, Scanner sc)
    {
        System.out.println(levelname);
        System.out.println("Type the following paragraph:");
        System.out.println(paragraph);
        System.out.println("------------------------------------------");
        long startTime = System.currentTimeMillis();

        String userInput = sc.nextLine();

        long endTime = System.currentTimeMillis();

        long timeTaken = (endTime - startTime)/1000;

        System.out.println("Time taken: " + timeTaken + " seconds");

        int wordstyped = userInput.split(" ").length;
        
        if(timeTaken == 0)
        {
            timeTaken = 1;
        }
        double wpm = (wordstyped / (double)timeTaken)*60;
        System.out.println("Typing Speed: " + wpm + " WPM");

        
        int correctchars = 0;

        for(int i=0;i<paragraph.length() && i<userInput.length();i++)
        {
            if(paragraph.charAt(i) == userInput.charAt(i))
            {
                correctchars++;
            }
        }
        double accuracy = (correctchars / (double) paragraph.length())*100;
        System.out.println("Accuracy: " + accuracy + "%");

        System.out.println();

        if(accuracy >= 90)
        {
            System.out.println("Excellent Typing!");
        }
        else if( accuracy >= 70)
        {
            System.out.println("Good accuracy but try to reduce mistakes.");
        }
        else
        {
            System.out.println("Focus more on accuracy while typing.");
        }
        System.out.println();
        if(wpm >= 60)
        {
            System.out.println("Rating: Fast Typer!");
        }
        else if(wpm >= 40)
        {
            System.out.println("Rating: Average Typer.");
        }
        else
        {
            System.out.println("Rating: Beginner - Practice more.");
        }
        System.out.println();
        

        double score = (wpm * accuracy) / 100;
        System.out.println("Your Score: " + score);
    }
    
}