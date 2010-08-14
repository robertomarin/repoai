package ai.liga.image;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class Transform {

	public BufferedImage makeSquareCrop(BufferedImage image) {

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
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image
				.getType();
		BufferedImage bi = new BufferedImage(width, height, type);
		Graphics2D g = bi.createGraphics();

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();

		return bi;
	}

}
