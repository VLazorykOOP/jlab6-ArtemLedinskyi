import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter task : ");
        int task = in.nextInt();
        switch (task){
            case 1:
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Task1 task1= new Task1();
                        task1.setVisible(true);
                    }
                });
                break;
            case 2:
                Task2 task2 = new Task2();
                break;
            default:
                System.out.println("Invalid task.");
                break;
        }

    }
}

