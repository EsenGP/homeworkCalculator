import javax.swing.*;

public class Frame extends JFrame {


    Frame(){
        setTitle("Calculator");
        setLocationRelativeTo(null);
        add(new Panel());
        setSize(265,350);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
