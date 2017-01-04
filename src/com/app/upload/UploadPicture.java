package com.app.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Part;

/**
 * 
 * @author user-sqli date: 12/05/2016 17:30
 */
public class UploadPicture {
	private static final String EXTENSION = ".";
	public static final String SAVE_DIR = "uploadImage";
	private static UploadPicture uploadPicture = null;
	private static final Logger LOGGER = Logger.getLogger(UploadPicture.class
			.getName());

	private UploadPicture() {
	}

	public String TranseferPicture(Part part, String appPath) {
		String savePath = appPath + File.separator + SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		String nameImage = fileName + EXTENSION + getExtensionImage(part);
		try {
			part.write(savePath + File.separator + nameImage);
			LOGGER.log(Level.FINE, "Upload  Picture to {0} ", savePath
					+ File.separator + nameImage);

		} catch (IOException ex) {
			LOGGER.log(Level.SEVERE, ex.toString(), ex);
		}
		return nameImage;
	}

	private String getExtensionImage(Part part) {

		return part.getContentType().split("/")[1];
	}

	public static UploadPicture createNewInstance() {
		if (uploadPicture == null) {
			uploadPicture = new UploadPicture();
		}
		return uploadPicture;
	}
}
