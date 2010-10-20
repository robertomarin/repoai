package ai.liga.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class ImageTransformTest {

	@DataPoint
	public static InputStream CARRO = new ImageTransformTest().getClass()
			.getResourceAsStream("car.jpg");
	@DataPoint
	public static InputStream CASA = new ImageTransformTest().getClass()
			.getResourceAsStream("house.jpg");

	@DataPoint
	public static InputStream CASA_RETRATO = new ImageTransformTest().getClass()
			.getResourceAsStream("house_portrait.jpg");

	@DataPoint
	public static InputStream DOG_JPG = new ImageTransformTest().getClass()
			.getResourceAsStream("dog_jpg.jpg");

	@DataPoint
	public static InputStream DOG = new ImageTransformTest().getClass()
			.getResourceAsStream("dog.gif");

	@DataPoint
	public static InputStream SUPER_MARIO = new ImageTransformTest().getClass()
			.getResourceAsStream("supermario.png");
	@DataPoint
	public static InputStream MARIO = new ImageTransformTest().getClass()
			.getResourceAsStream("mario.png");

	@Theory
	public void testaResizeImage(InputStream file) throws IOException {

		BufferedImage image = ImageIO.read(file);
		image = new ImageTransform().resize(image, 200, 200);

		Assert.assertTrue(image.getWidth() == image.getHeight());
		//
		// String nameFile = "file" + new Date().getTime() + ".jpg";
		// File fileOut = new File(nameFile);
		// OutputStream tmp = new FileOutputStream(fileOut);
		//
		// ImageIO.write(image, "jpg", fileOut);
		// tmp.close();

		// URL url = this.getClass().getResource("carro.jpg");
		//
		// System.out.println("url: " + url + "type: "
		// + new MimetypesFileTypeMap().getContentType(url.getPath()));

	}

}
