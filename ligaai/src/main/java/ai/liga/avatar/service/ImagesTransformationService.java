package ai.liga.avatar.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ai.liga.image.ImageTransform;

@Service
public class ImagesTransformationService {

	private static final String PATH_IMAGE = "/var/www/html/ligaai/ligaai/avatar/";

	private static final String PATH_IMAGE_PREVIEW = PATH_IMAGE + "original/";

	private static Logger logger = Logger.getLogger(ImagesTransformationService.class);

	@Autowired
	private ImageTransform transform;

	public BufferedImage makeTransfomations(int resizeX, int resizeY, MultipartFile mpf) {

		BufferedImage bi;
		try {
			bi = ImageIO.read(mpf.getInputStream());
		} catch (IOException e) {
			logger.error("Erro ao ler a imagem a partir do inputStream", e);
			return null;
		}

		return transform.makeResize(transform.makeSquareCrop(bi), resizeX, resizeY);

	}

	private BufferedImage makResize(int resizeX, int resizeY, MultipartFile mpf) {

		BufferedImage bi;
		try {
			bi = ImageIO.read(mpf.getInputStream());
		} catch (IOException e) {
			logger.error("Erro ao ler a imagem a partir do inputStream", e);
			return null;
		}

		return transform.makeResize(bi, resizeX, resizeY);

	}

	public boolean saveImageResized(int resizeX, int resizeY, MultipartFile mpf, final Long idUser) {
		return saveImage(makResize(resizeX, resizeY, mpf), idUser);

	}

	public boolean saveImage(MultipartFile mpf, final Long idUser) {
		BufferedImage bi;
		try {
			bi = ImageIO.read(mpf.getInputStream());
		} catch (IOException e) {
			logger.error("Erro ao ler a imagem a partir do inputStream", e);
			return false;
		}
		return saveImage(bi, idUser);
	}

	private boolean saveImage(final BufferedImage image, final Long idUser) {
		try {
			ImageIO.write(image, "jpg", new File(PATH_IMAGE_PREVIEW + idUser + ".jpg"));
		} catch (IOException e) {
			logger.error("Erro ao gravar a imagem", e);
			return false;
		}

		return true;

	}

	public boolean cropAndResizeImage(Long idUser, int x, int y, int w, int h) {
		BufferedImage bi;
		try {
			bi = ImageIO.read(new File(PATH_IMAGE_PREVIEW + idUser + ".jpg"));
		} catch (IOException e) {
			logger.error("Erro ao ler a imagem", e);
			return false;
		}

		bi = transform.makeSquareCrop(bi, x, y, h, w);

		if (bi != null) {
			try {
				ImageIO.write(bi, "jpg", new File(PATH_IMAGE + idUser + ".jpg"));
			} catch (Exception e) {
				logger.error("Erro ao gravar a imagem", e);
				return false;
			}

			return true;
		}

		return false;
	}

}
