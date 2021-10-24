package schulte_table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class SchulteTable extends JFrame implements ActionListener {
    final int SIZE_EASY = 25;
    final int SIZE_MEDIUM = 100;
    final int SIZE_HARD = 400;
    int size=SIZE_EASY;
    public JButton[] playingField = new JButton[size];
    public static Random rand = new Random();
    public int[] array = new int[size];

    JMenuBar menuBar;
    JMenu fileMenu, levelMenu;
    JMenuItem newGame, exit, easy, medium, hard;

    Font font_start = new Font("Eras Bold ITC", Font.BOLD, 25);

    long tBegin, tEnd;
    int count = 0;

    public SchulteTable() throws HeadlessException {
        if (size==SIZE_EASY){
        setBounds(150, 150, 350, 500);}
        if (size==SIZE_MEDIUM){
            setBounds(150, 150, 500, 500);}
        if (size==SIZE_HARD){
            setBounds(150, 150, 1000, 500);}
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Schulte Table");

        setLayout(new GridLayout((int) Math.sqrt(size), (int) Math.sqrt(size)));

        init();


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        newGame = new JMenuItem("New Game");
        exit = new JMenuItem("Exit");
        fileMenu.add(newGame);
        newGame.addActionListener(this);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        exit.addActionListener(this);


        levelMenu = new JMenu("Level");
        menuBar.add(levelMenu);
        easy = new JMenuItem("Easy");
        medium = new JMenuItem("Medium");
        hard = new JMenuItem("Hard");
        levelMenu.add(easy);
        levelMenu.add(medium);
        levelMenu.add(hard);
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        setVisible(true);
    }

    private void init() {

        String text;

        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        System.out.println(Arrays.toString(array));
        shuffle(array);

        System.out.println(Arrays.toString(array));
        for (int i = 0; i < size; i++) {

            text = String.valueOf(array[i]);
            playingField[i] = new JButton();
            playingField[i].setText(text);
            playingField[i].setFont(font_start);
            playingField[i].addActionListener(this);
            add(playingField[i]);

        }
    }

    private void shuffle(int[] arr) {
        for (int i = size - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit) System.exit(0);
        if(e.getSource()==easy)size=SIZE_EASY;
        if(e.getSource()==medium)size=SIZE_MEDIUM;
        if(e.getSource()==hard)size=SIZE_HARD;
        if (e.getSource() == newGame) {
            for (int i = 0; i < size; i++) {
                array[i] = i + 1;
            }
            System.out.println(Arrays.toString(array));
            shuffle(array);

            System.out.println(Arrays.toString(array));
            for (int i = 0; i < size; i++) {
                playingField[i].setEnabled(true);
                String text = String.valueOf(array[i]);
                playingField[i].setText(text);
                playingField[i].setFont(font_start);
                playingField[i].setForeground(Color.BLACK);
            }
            count=0;
        }


        for (int i = 0; i < size; i++) {
            if (e.getSource() == playingField[i] && playingField[i].getText().equals("1")) {
                tBegin = System.currentTimeMillis();
                playingField[i].setForeground(Color.GREEN);
                playingField[i].setEnabled(false);
                count++;
                System.out.print(count + " ");
            } else if (e.getSource() == playingField[i]) {
                if (playingField[i].getText().equals(String.valueOf(count + 1)) && !playingField[i].getText().equals(String.valueOf(size))) {
                    playingField[i].setForeground(Color.GREEN);
                    count++;
                } else if ((count == size - 1) && e.getSource() == playingField[i] && playingField[i].getText().equals(String.valueOf(size))) {
                    tEnd = System.currentTimeMillis();
                    playingField[i].setForeground(Color.BLUE);
                    int timeGame = (int) ((tEnd - tBegin) / 1000);
                    JOptionPane.showMessageDialog(null, "Ваше время: " + timeGame + " секунд");
                }
                System.out.print(count + " ");
            }

        }

    }
}
