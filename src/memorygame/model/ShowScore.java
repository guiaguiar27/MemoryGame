package memorygame.model;
import java.awt.*;        // Using AWT's layouts
import java.awt.event.*;  // Using AWT's event classes and listener interfaces
import javax.swing.*;

public class ShowScore extends JFrame {
    JLabel label;
    int count;
    // integrar
    // ao inves de tempo sera cartas coletadas
    User UserThis = new User();
    Container cp = getContentPane();
    private JButton btnAdd = new JButton("Add more cards");

    public ShowScore() {
        label = new JLabel("...");
        setLayout(new GridBagLayout());
        add(label);
        count = 1 ;

        while(count < 10000) {
                    label.setText(Integer.toString(count));
            count++;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
