import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 extends JFrame {
    private JTextField sizeField;
    private JTextArea result;

    public Task2() {
        setTitle("Завдання №2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel sizelabel = new JLabel("Введіть розмірність матриці (не більше 15):");
        sizeField = new JTextField(10);

        JButton calculateButton = new JButton("Обчислити");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Додано можливість вибору введення матриці або зчитування з файлу
                int choice = JOptionPane.showOptionDialog(null,
                        "Як ви хочете ввести матрицю?",
                        "Вибір",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Ввести вручну", "Зчитати з файлу"},
                        "Ввести вручну");

                if (choice == JOptionPane.YES_OPTION) {
                    // Введення матриці вручну
                    calculateManually();
                } else {
                    // Зчитування матриці з файлу
                    calculateFromFile();
                }
            }
        });
        result = new JTextArea(10, 30);
        result.setEditable(false);
        panel.add(sizelabel);
        panel.add(sizeField);
        panel.add(calculateButton);
        panel.add(new JScrollPane(result));

        add(panel);
        setVisible(true);
    }

    private void calculateManually() {
        try {
            int n = Integer.parseInt(sizeField.getText());
            int[][] A;
            int[][] B;

            if (n <= 15) {
                A = new int[n][n];
                B = new int[n][n];
            } else {
                JOptionPane.showMessageDialog(null, "Помилка: розмірність матриць перевищує максимально допустиму (15).");
                return;
            }

            // Отримання значень для матриці A
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String inputValue = JOptionPane.showInputDialog("Введіть значення для матриці A[" + i + "][" + j + "]:");
                    A[i][j] = Integer.parseInt(inputValue);
                }
            }

            // Отримання значень для матриці B
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    String inputValue = JOptionPane.showInputDialog("Введіть значення для матриці B[" + i + "][" + j + "]:");
                    B[i][j] = Integer.parseInt(inputValue);
                }
            }

            performCalculation(A, B, n);
        } catch (NumberFormatException e) {
            result.setText("Помилка: введіть коректну розмірність матриць.");
        } catch (Exception ex) {
            result.setText("Сталась невідома помилка.");
        }
    }

    private void calculateFromFile() {
        try {
            int n = Integer.parseInt(sizeField.getText());
            int[][] A;
            int[][] B;

            if (n <= 15) {
                A = new int[n][n];
                B = new int[n][n];
            } else {
                JOptionPane.showMessageDialog(null, "Помилка: розмірність матриць перевищує максимально допустиму (15).");
                return;
            }

            // Зчитування матриці A з файлу
            String filePathA = askForFilePath("A");
            if (filePathA == null) {
                return;
            }
            readMatrixFromFile(filePathA, A, n);

            // Зчитування матриці B з файлу
            String filePathB = askForFilePath("B");
            if (filePathB == null) {
                return;
            }
            readMatrixFromFile(filePathB, B, n);

            performCalculation(A, B, n);
        } catch (NumberFormatException e) {
            result.setText("Помилка: введіть коректну розмірність матриць.");
        } catch (Exception ex) {
            result.setText("Сталась невідома помилка.");
        }
    }

    private String askForFilePath(String matrixName) {
        return JOptionPane.showInputDialog("Введіть шлях до файлу для матриці " + matrixName + ":");
    }

    private void readMatrixFromFile(String filePath, int[][] matrix, int n) throws FileNotFoundException {
        File file = new File(filePath);

        if (file.exists()) {
            Scanner fileScanner = new Scanner(file);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (fileScanner.hasNextInt()) {
                        matrix[i][j] = fileScanner.nextInt();
                    } else {
                        throw new RuntimeException("Недостатньо елементів у файлі.");
                    }
                }
            }
            fileScanner.close();
        } else {
            throw new FileNotFoundException("Файл не знайдено: " + filePath);
        }
    }

    private void performCalculation(int[][] A, int[][] B, int n) {
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = 1;
            for (int j = 0; j < n; j++) {
                if (A[i][j] != B[j][i]) {
                    x[i] = 0;
                    break;
                }
            }
        }

        StringBuilder resultBuilder = new StringBuilder("Результат : \n");
        for (int num : x) {
            resultBuilder.append(num).append("\n");
        }
        result.setText(resultBuilder.toString());
    }
}
