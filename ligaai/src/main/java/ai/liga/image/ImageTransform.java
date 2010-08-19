package ai.liga.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Component;

@Component
public class ImageTransform {

	public BufferedImage makeSquareCrop(BufferedImage image) {

		if (image == null) {
			return null;
		}

		int width = image.getWidth();
		int height = image.getHeight();

		if (width > height) {
			int begin = (width - height) / 2;
			image = image.getSubimage(begin, 0, height, height);
		} else if (width < height) {
			int begin = (height - width) / 2;
			image = image.getSubimage(0, begin, width, width);
		}

		return image;
	}

	public BufferedImage makeResize(BufferedImage image, int width, int height) {
		if (image == null) {
			return null;
		}
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage dimg;

		dimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g.drawImage(image, 0, 0, width, height, 0, 0, w, h, null);
		g.dispose();

		return dimg;

	}

}
