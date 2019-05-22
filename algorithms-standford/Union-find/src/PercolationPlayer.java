import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author bone1289
 */
public class PercolationPlayer {
    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
        int N = Integer.parseInt(br.readLine());
        StdOut.println(N);

        // turn on animation mode
        StdDraw.enableDoubleBuffering();

        Percolation perc = new Percolation(N);
        PercolationVisualizer.draw(perc, N);
        StdDraw.show();
        StdDraw.pause(DELAY);
        new ControllFrame(N, perc, br).setVisible(true);
    }

    private static class ControllFrame extends JFrame {

        ControllFrame(final int N, final Percolation perc, final BufferedReader br) {
            super("Controlls");

            final JTextField textField = new JTextField("1");
            textField.setPreferredSize(new Dimension(50, 25));

            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> {
                int stepsToGo = Integer.parseInt(textField.getText());
                String currentLine;
                for (int step = 0; step < stepsToGo; step++) {
                    try {
                        currentLine = br.readLine();
                        StdOut.println(currentLine);
                        if (currentLine == null) break;
                        String[] values = currentLine.trim().split(" ");

                        if (values[0].trim().equals("")) {
                            continue;
                        }

                        int i = Integer.parseInt(values[0].trim());
                        int j = Integer.parseInt(values[1].trim());
                        perc.open(i, j);

                        // draw N-by-N percolation system
                        StdDraw.show();
                        StdDraw.pause(DELAY);
                        PercolationVisualizer.draw(perc, N);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }

                }
            });

            setLayout(new FlowLayout());
            add(textField);
            add(playButton);
            setSize(300, 150);
        }
    }

}
