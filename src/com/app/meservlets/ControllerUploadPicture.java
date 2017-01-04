package com.app.meservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.app.upload.UploadPicture;

/**
 * 
 * @author user-sqli date: 12/05/2016 17:42
 */
@WebServlet(urlPatterns = "/upload", loadOnStartup = 1)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class ControllerUploadPicture extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String appPath = request.getServletContext().getRealPath("");

		Part part = request.getPart("file");
		UploadPicture.createNewInstance().TranseferPicture(part, appPath);

		getServletContext().getRequestDispatcher("/show.jsp").forward(request,
				response);
	}

}





