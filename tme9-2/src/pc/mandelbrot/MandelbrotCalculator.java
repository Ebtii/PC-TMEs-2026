package pc.mandelbrot;

import java.awt.Color;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MandelbrotCalculator {
	public static void compute(BoundingBox boundingBox, int maxIterations, int[] imageBuffer) {
		long start = System.currentTimeMillis();
		// Iterate over each pixel
		for (int py = 0; py < boundingBox.height; py++) {
			for (int px = 0; px < boundingBox.width; px++) {
				int color = computePixelColor(boundingBox, maxIterations, px, py);

				// Set the pixel in the image buffer
				imageBuffer[py * boundingBox.width + px] = color;
			}
		}
		System.out.println("Rendered image in " + (System.currentTimeMillis() - start) + " ms");
	}

	public static void parCompute(BoundingBox boundingBox, int maxIterations, int[] imageBuffer) {
		long start = System.currentTimeMillis();
    	MandelbrotTask tache = new MandelbrotTask( boundingBox, maxIterations, imageBuffer, 0, boundingBox.height - 1 );
    	ForkJoinPool.commonPool().invoke(tache);
		// compute(boundingBox, maxIterations, imageBuffer);
		System.out.println("Rendered image in " + (System.currentTimeMillis() - start) + " ms");
	}

	public static int computePixelColor(BoundingBox boundingBox, int maxIterations, int px, int py) {
		int width = boundingBox.width;
		int height = boundingBox.height;

		// Map pixel to complex plane with inverted y-axis
		double x0 = boundingBox.xmin + px * (boundingBox.xmax - boundingBox.xmin) / width;
		double y0 = boundingBox.ymax - py * (boundingBox.ymax - boundingBox.ymin) / height;

		double x = 0.0;
		double y = 0.0;
		int iteration = 0;

		while (x * x + y * y <= 4 && iteration < maxIterations) {
			double xtemp = x * x - y * y + x0;
			y = 2 * x * y + y0;
			x = xtemp;
			iteration++;
		}

		if (iteration < maxIterations) {
			// Compute smooth iteration count
			double logZn = Math.log(x * x + y * y) / 2;
			double nu = Math.log(logZn / Math.log(2)) / Math.log(2);
			double smoothIteration = iteration + 1 - nu;

			// Normalize smooth iteration
			float hue = (float) (0.95f + 10 * smoothIteration / maxIterations);
			float saturation = 0.6f;
			float brightness = 1.0f;

			// Wrap hue to [0,1] to prevent overflow
			hue = hue - (float) Math.floor(hue);

			return Color.HSBtoRGB(hue, saturation, brightness);
		} else {
			return Color.BLACK.getRGB();
		}
	}
	static class MandelbrotTask extends RecursiveAction {

		private static final int THRESHOLD = 5000;

		private final BoundingBox boundingBox;
		private final int maxIterations;
		private final int[] imageBuffer;
		private final int ligneDebut;
		private final int ligneFin;

		MandelbrotTask(BoundingBox boundingBox, int maxIterations,
					int[] imageBuffer, int ligneDebut, int ligneFin) {
			this.boundingBox   = boundingBox;
			this.maxIterations = maxIterations;
			this.imageBuffer   = imageBuffer;
			this.ligneDebut    = ligneDebut;
			this.ligneFin      = ligneFin;
    	}

		@Override
		protected void compute() {
			int nbPixels = (ligneFin - ligneDebut + 1) * boundingBox.width;

			if (nbPixels <= THRESHOLD) {
				// Assez petit → séquentiel
				for (int py = ligneDebut; py <= ligneFin; py++) {
					for (int px = 0; px < boundingBox.width; px++) {
						imageBuffer[py * boundingBox.width + px] =
							computePixelColor(boundingBox, maxIterations, px, py);
					}
				}
			} else {
				// Trop grand → on coupe en deux
				int milieu = ligneDebut + (ligneFin - ligneDebut) / 2;

				MandelbrotTask haut = new MandelbrotTask(
					boundingBox, maxIterations, imageBuffer, ligneDebut, milieu);
				MandelbrotTask bas  = new MandelbrotTask(
					boundingBox, maxIterations, imageBuffer, milieu + 1, ligneFin);

				haut.fork();
				bas.compute();
				haut.join();
			}
		}
	}

}
