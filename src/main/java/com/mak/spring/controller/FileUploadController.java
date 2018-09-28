package com.mak.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
	
	
	@RequestMapping(value="/upload", method = RequestMethod.GET)
	public String upload() {
		return "upload";
	}
	
	@RequestMapping(value="uploadm", method=RequestMethod.GET)
	public String uploadMultiple() {
		return "uploadMultiple";
	}

	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadFileHandler(@RequestParam("name") String name,
			@RequestParam("file") MultipartFile file) {
		logger.info("INFO::::1");
		logger.debug("DEBUG::::1");
		logger.info("INFO::::1");
		logger.debug("DEBUG::::2");
		logger.info("INFO::::3");
		logger.debug("DEBUG::::3");
		logger.info("INFO::::4");
		logger.debug("DEBUG::::4");
		logger.info("INFO::::5");
		logger.debug("DEBUG::::5");
		logger.info("INFO::::6");
		logger.debug("DEBUG::::6");
		logger.info("INFO::::7");
		logger.debug("DEBUG::::7");
		logger.info("INFO::::8");
		logger.debug("DEBUG::::8");
		
		if(!file.getContentType().equalsIgnoreCase("text/plain")) {
			logger.info("INFO:::::Wrong Format entered");
			logger.debug("DEBUG::::"+"Wrong Format entered");
			return "Wrong Format entered";
		}

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());
				logger.info("INFO:::::You successfully uploaded file=" + name);
				logger.debug("DEBUG::::"+"You successfully uploaded file=" + name);
				return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				logger.info("INFO::::You failed to upload " + name + " => " + e.getMessage());
				logger.debug("DEBUG::::"+"You failed to upload " + name + " => " + e.getMessage());
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			logger.info("INFO:::::You failed to upload " + name+ " because the file was empty.");
			logger.debug("DEBUG::::"+"You failed to upload " + name+ " because the file was empty.");
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}

	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
}
