package liga.ai.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import junit.framework.Assert;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import ai.liga.image.Transform;

@RunWith(Theories.class)
public class TestaCropImage {

	@DataPoint
	public static InputStream CARRO = new TestaCropImage().getClass()
			.getResourceAsStream("carro.jpg");
	@DataPoint
	public static InputStream CASA = new TestaCropImage().getClass()
			.getResourceAsStream("casa.jpg");
	
	@DataPoint
	public static InputStream DOG = new TestaCropImage().getClass()
			.getResourceAsStream("logoCachorroMutley.gif");
	
	@DataPoint
	public static InputStream CASA_RETRATO = new TestaCropImage().getClass()
			.getResourceAsStream("casaR.jpg");
	

	@Theory
	public void testaCropSquereImage(InputStream file) throws IOException {

		BufferedImage image = ImageIO.read(file);
		image = new Transform().makeSquareCrop(image);
		
		Assert.assertTrue(image.getWidth()==image.getHeight());
		
//		String nameFile = "file"+new Date().getTime()+".jpg";
//		File fileOut = new File(nameFile);
//		OutputStream tmp = new FileOutputStream(fileOut);
//
//		ImageIO.write(image, "jpg", fileOut);
//		tmp.close();

	}
	
	@Theory
	public void testaResizeImage(InputStream file) throws IOException {

		BufferedImage image = ImageIO.read(file);
		image = new Transform().makeResize(image, 200, 200);
		
//		Assert.assertTrue(image.getWidth()==image.getHeight());
		
		String nameFile = "file"+new Date().getTime()+".gif";
		File fileOut = new File(nameFile);
		OutputStream tmp = new FileOutputStream(fileOut);

		ImageIO.write(image, "gif", fileOut);
		tmp.close();

	}


}
