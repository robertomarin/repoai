package ai.liga.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ResizeImageTest {

	@DataPoint
	public static InputStream FLOWERS = new ResizeImageTest().getClass().getResourceAsStream("flowers.jpg");

	@DataPoint
	public static InputStream DF = new ResizeImageTest().getClass().getResourceAsStream("DSC01698.JPG");

	@Theory
	public void testaResizeImage(InputStream file) throws IOException {

		BufferedImage image = ImageIO.read(file);
		image = resizeImage(image, 800);

		String nameFile = file.toString() + ".jpg";
		File fileOut = new File(nameFile);
		OutputStream tmp = new FileOutputStream(fileOut);

		ImageIO.write(image, "jpg", fileOut);
		tmp.close();

		// Assert.assertTrue(image.getWidth() == image.getHeight());
	}

	public BufferedImage resizeImage(final BufferedImage image, final int target) {

		double percentage = 0;
		if (image.getWidth() > image.getHeight()) {
			percentage = (double) target / image.getWidth();
		} else {
			percentage = (double) target / image.getHeight();
		}

		return new ImageTransform().makeResize(image, (int) (image.getWidth() * percentage),
				(int) (image.getHeight() * percentage));
	}

}
