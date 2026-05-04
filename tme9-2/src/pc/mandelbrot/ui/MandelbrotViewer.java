package pc.mandelbrot.ui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class MandelbrotViewer {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mandelbrot Viewer");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // Create the MandelbrotPanel
            MandelbrotPanel mandelbrotPanel = new MandelbrotPanel();

            // Create the SettingsPanel with mandelbrotPanel reference
            SettingsPanel settingsPanel = new SettingsPanel(mandelbrotPanel);

            // Use JSplitPane to place SettingsPanel to the right of MandelbrotPanel
            JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(mandelbrotPanel),
                    settingsPanel);
            splitPane.setDividerLocation(800); // Initial divider position

            frame.add(splitPane);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

/*  Q2 
Plus maxIterations est grand -> plus le temps augmente car chaque pixel fait plus de calculs.
Plus l'image est grande --> plus le temps augmente car il y a plus de pixels à calculer.
Les zones zoomées -> les zones avec beaucoup de noir sont plus rapides (l'itération s'arrête tôt), les zones colorées (bord de l'ensemble) sont plus lentes.
Séquentiel vs Parallèle :
seq
Rendered image in 1682 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 1856ms

parallele
Rendered image in 346 ms
Mandelbrot image saved as mandelbrot.png
Execution time: 429ms

le parallèle est presque 5x plus rapide (1682ms → 346ms) grâce à ForkJoin qui distribue le travail sur plusieurs threads.
*/