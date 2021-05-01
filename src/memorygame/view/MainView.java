package memorygame.view;

import memorygame.model.ShowScore;
import memorygame.model.User;

import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainView {
    
    private final JFrame mainframe;
    private Container mainContentPane;
    private ImageIcon cardIcon[];
    
    public MainView() {
       
        this.mainframe = new JFrame("Jogo da Memória");
        this.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainframe.setSize(800, 900);
        this.mainframe.setLocationRelativeTo(null);
        this.mainContentPane = this.mainframe.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));

        // teste do contador de cartas
        // this.mainframe.add(new ShowScore());
        // this.mainframe.pack();
        // this.mainframe.setVisible(true);





    }

    public void newGame(){
        this.mainframe.setVisible(true);
    }
    
}
