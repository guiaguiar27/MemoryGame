package memorygame.view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import memorygame.model.Card;

public class MainView implements ActionListener {

    private final JFrame mainframe;
    private final Container mainContentPane;
    private final ImageIcon cardIcon[]; //0-7 faces, 8 trás
    private final String ImageDirectory = "default";

    public MainView() {

        this.mainframe = new JFrame("Jogo da Memória");
        this.mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainframe.setSize(800, 900);
        this.mainframe.setLocationRelativeTo(null);
        this.mainContentPane = this.mainframe.getContentPane();
        this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS));

        JMenuBar menuBar = new JMenuBar();
        this.mainframe.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Opções");
        menuBar.add(gameMenu);

        newMenuItem("Novo Jogo", gameMenu, this);
        newMenuItem("Sair", gameMenu, this);

        JMenu aboutMenu = new JMenu("Sobre");
        menuBar.add(aboutMenu);
        newMenuItem("Ajuda", aboutMenu, this);
        newMenuItem("Sobre", aboutMenu, this);
        newMenuItem("Outros", aboutMenu, this);

        this.cardIcon = loadCardIcons();
    }

    private ImageIcon[] loadCardIcons() {
        ImageIcon[] icon = new ImageIcon[9];

        for (int i = 0; i < 9; i++) {
            String fileName = "images/" + ImageDirectory + "/card-" + i + ".png";
            icon[i] = new ImageIcon(fileName);

            if (icon[i].getImageLoadStatus() == MediaTracker.ERRORED) {
                JOptionPane.showMessageDialog(this.mainframe,
                        "The image " + fileName + " could not be loaded.",
                        "Matching Game Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }

        return icon;
    }

    public static void randomizeIntArray(int[] a) {
        Random randomizer = new Random();

        for (int i = 0; i < a.length; i++) {

            int d = randomizer.nextInt(a.length);

            int t = a[d];
            a[d] = a[i];
            a[i] = t;
        }
    }

    public JPanel makeCards() {

        JPanel panel = new JPanel(new GridLayout(4, 2));

        // define a face de trás
        ImageIcon backIcon = this.cardIcon[8];

        int cardsToAdd[] = new int[8];
        for (int i = 0; i < 4; i++) {
            cardsToAdd[2 * i] = i;
            cardsToAdd[2 * i + 1] = i;
        }

        randomizeIntArray(cardsToAdd);

        for (int i = 0; i < cardsToAdd.length; i++) {

            int num = cardsToAdd[i];

            Card newCard = new Card(this.cardIcon[num], backIcon, num);

            panel.add(newCard);
        }

        return panel;
    }

    private void newMenuItem(String string, JMenu gameMenu, ActionListener listener) {
        JMenuItem newItem = new JMenuItem(string);
        newItem.setActionCommand(string);
        newItem.addActionListener(listener);
        gameMenu.add(newItem);

    }

    public void newGame() {
        this.mainContentPane.removeAll();

        this.mainContentPane.add(makeCards());

        this.mainframe.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Novo Jogo")) {
            newGame();
        }
        if (ae.getActionCommand().equals("Sair")) {
            System.exit(0);
        }
    }
}
