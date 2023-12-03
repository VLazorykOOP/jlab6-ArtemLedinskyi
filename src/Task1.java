import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Task1 extends JFrame {
    private static final  int FrameWidth=500;
    private static final  int FrameHeight=300;
    private static final int StringWidth=100;
    private static final int StringHeight = 20;

    private JLabel movingString;
    private Timer timer;
    private Color[] colors ={Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
    private int colorIndex=0;





    public Task1(){
        setTitle("Moving String");
        setSize(FrameWidth,FrameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        movingString = new JLabel("Moving String");
        movingString.setBounds(0,FrameHeight/2-StringHeight/2,StringWidth,StringHeight);
        movingString.setHorizontalAlignment(SwingConstants.CENTER);
        add(movingString);

        String[] colorName = {"Red","Green","BLue","Yellow"};
        JComboBox<String> colorComboBox = new JComboBox<>(colorName);
        colorComboBox.setBounds(10,20,80,20);
        add(colorComboBox);

        timer= new Timer(10, new ActionListener() {
            int direction=1;
            @Override
            public void actionPerformed(ActionEvent e) {
                int newX = movingString.getX()+direction;
                if(newX <0||newX+StringWidth>FrameWidth){

                    direction*=-1;
                    colorIndex = (colorIndex+1)%colors.length;
                    movingString.setForeground(colors[colorIndex]);
                    colorComboBox.setSelectedIndex(colorIndex);
                }
                movingString.setLocation(newX,movingString.getY());
            }
        });

        colorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorIndex=colorComboBox.getSelectedIndex();
                movingString.setForeground(colors[colorIndex]);
            }
        });

        timer.start();

    }

}
