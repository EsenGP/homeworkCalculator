import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panel extends JPanel {

    private JButton[] numbers = new JButton[10];
    private Font font = new Font("Calibry",Font.BOLD,20);
    private JTextField output = new JTextField();
    private static int firstNumb = 0, secondNumb = 0;
    private int whatDo = 0, count = 0, errorInspect = 0;

    private void error(){
        JOptionPane.showMessageDialog(null,"Ошибка, после каждого действия нажимайте на знак \'=\'" +
                ", сейчас закройте ошибку и нажмите \'C\' ");
    }

    private void what(){
        switch (whatDo) {
            case 0:
                break;
            case 1:
                doit();
                secondNumb = firstNumb + secondNumb;
                break;
            case 2: doit();
                secondNumb = firstNumb - secondNumb;
                break;
            case 3:   doit();
                secondNumb = firstNumb * secondNumb;
                break;
            case 4:   doit();
                secondNumb = firstNumb / secondNumb;
                break;

        }
        whatDo = 0;
        count++;
    }

    private void doit() {

            firstNumb = secondNumb;
            secondNumb = Integer.parseInt(output.getText());


        output.setText("");
    }

    private void out(int equ) {
        output.setText(Integer.toString(equ));
    }


    public Panel(){

        setLayout(null);
        setFocusable(true);
        grabFocus();


        JButton delete = new JButton("del");
        delete.setFont(new Font("Calibri",Font.BOLD, 10));
        delete.setBounds(10, 250, 50, 50);
        add(delete);

        JButton equally = new JButton("=");
        equally.setFont(font);
        equally.setBounds(130, 250, 50, 50);
        add(equally);

        JButton clear = new JButton("C");
        clear.setFont(font);
        clear.setBounds(190, 10, 50, 50);
        add(clear);

        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton multiply = new JButton("*");
        JButton divide = new JButton("/");
        plus.setBounds(190, 70, 50, 50);
        minus.setBounds(190, 130, 50, 50);
        multiply.setBounds(190, 190, 50, 50);
        divide.setBounds(190, 250, 50, 50);
        plus.setFont(font); minus.setFont(font); multiply.setFont(font); divide.setFont(font);
        add(plus);
        add(minus);
        add(multiply);
        add(divide);

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70,250,50,50);
        numbers[0].setFont(font);
        add(numbers[0]);

        for (int i = 0; i < 3; i++){
            for (int l =0; l < 3; l ++){
                numbers[i * 3 + l + 1] = new JButton((i * 3 + l + 1) + "");
                numbers[i * 3 + l + 1].setBounds(i * (50 + 10) + 10, l * (50 + 10) + 70, 50, 50);
                numbers[i * 3 + l + 1].setFont(font);
                add(numbers[i * 3 + l + 1]);
            }
        }


        output.setBounds(10, 10, 170, 50);
        output.setEditable(false);
        output.setFont(font);
        add(output);

        ActionListener l = (ActionEvent e) -> {
            JButton b = (JButton)e.getSource();
            output.setText(output.getText() + b.getText());
        };

        for(JButton b : numbers){
            b.addActionListener(l);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symbol = e.getKeyChar();

                if(!Character.isDigit(symbol))
                return;

                output.setText(output.getText() + symbol);
            }
        });

        clear.addActionListener(e -> {
            firstNumb = 0;
            secondNumb = 0;
            count = 0;
            whatDo = 0;
            errorInspect = 0;
            output.setText("");
        });

        delete.addActionListener(e ->
                output.setText(output.getText().substring(0,(output.getText().length()-1))));

        plus.addActionListener(e -> {

            if (errorInspect>0) error();
            else {

                doit();

                if (count == 1) secondNumb = firstNumb + secondNumb;

                whatDo = 1;
                count = 1;
                errorInspect++;
            }
        });

        minus.addActionListener(e -> {

            if (errorInspect>0) error();
            else {

                doit();

                if (count == 1) secondNumb = firstNumb - secondNumb;

                whatDo = 2;
                count = 1;
                errorInspect++;
            }

        });

        multiply.addActionListener(e -> {

            if (errorInspect>0) error();
            else {

                doit();

                if (count == 1) secondNumb = firstNumb * secondNumb;

                whatDo = 3;
                count = 1;
                errorInspect++;
            }

        });

        divide.addActionListener(e -> {

            if (errorInspect>0) error();
            else {

                doit();

                if (count == 1) secondNumb = firstNumb / secondNumb;

                whatDo = 4;
                count = 1;
                errorInspect++;
            }

        });

        equally.addActionListener(e -> {
            errorInspect = 0;

                what();
            count++;
            out(secondNumb);});

    }

}
