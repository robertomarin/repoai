package ai.liga.avatar.service;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ai.liga.image.ImageTransform;

@Service
public class ImagesTransformationService {

	private static Logger logger = Logger
			.getLogger(ImagesTransformationService.class);

	@Autowired
	private ImageTransform transform;

	public BufferedImage makeTransfomations(int resizeX, int resizeY,
			MultipartFile mpf) {

		BufferedImage bi;
		try {
			bi = ImageIO.read(mpf.getInputStream());
		} catch (IOException e) {
			logger.error("Erro ao ler a imagem a partir do inputStream", e);
			return null;
		}

		return transform.makeResize(transform.makeSquareCrop(bi), resizeX,
				resizeY);

	}

}
