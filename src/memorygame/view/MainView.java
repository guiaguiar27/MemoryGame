package memorygame.view;

import java.awt.Container;
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
    }
    
    public void newGame(){
        this.mainframe.setVisible(true);
    }
    
}
