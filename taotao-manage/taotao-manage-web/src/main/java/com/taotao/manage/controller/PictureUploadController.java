package com.taotao.manage.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manage.controller.bean.PictureUploadResult;
import com.taotao.manage.service.PropertieService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 图片上传
 */
@RestController
@RequestMapping("/pic")
public class PictureUploadController {
	private static final Logger logger = LoggerFactory.getLogger(PictureUploadController.class);

	@Autowired
	private PropertieService propertieService;
	
	private static final ObjectMapper mapper = new ObjectMapper();

	// 允许上传的格式
	private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

	@PostMapping(value = "/upload")
	public String upload(@RequestParam("uploadFile") MultipartFile uploadFile , HttpServletResponse response) throws Exception {
		//
		String originalFilename = uploadFile.getOriginalFilename();

		// 校验图片格式
		boolean isLegal = false;
		for (String type : IMAGE_TYPE) {
			if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
				isLegal = true;
				break;
			}
		}

		// 封装Result对象，并且将文件的byte数组放置到result对象中
		PictureUploadResult fileUploadResult = new PictureUploadResult();

		// 状态
		fileUploadResult.setError(isLegal ? 0 : 1);

		// 文件新路径
		String filePath = getFilePath(originalFilename);

		if (logger.isDebugEnabled()) {
			logger.debug("Pic file upload .[{}] to [{}] .", originalFilename, filePath);
		}

		// 生成图片的绝对引用地址
		String picUrl = StringUtils.replace(StringUtils.substringAfter(filePath, propertieService.IMAGE_STORE_PATH), "\\", "/");
		fileUploadResult.setUrl(propertieService.IMAGE_BASE_URL + picUrl);

		File newFile = new File(filePath);

		// 写文件到磁盘
		uploadFile.transferTo(newFile);

		// 校验图片是否合法
		isLegal = false;
		try {
			BufferedImage image = ImageIO.read(newFile);
			if (image != null) {
				fileUploadResult.setWidth(image.getWidth() + "");
				fileUploadResult.setHeight(image.getHeight() + "");
				isLegal = true;
			}
		} catch (IOException e) {
		}

		// 状态
		fileUploadResult.setError(isLegal ? 0 : 1);

		if (!isLegal) {
			// 不合法，将磁盘上的文件删除
			newFile.delete();
		}

		response.setContentType(MediaType.TEXT_HTML_VALUE);
		return mapper.writeValueAsString(fileUploadResult);
	}

	private String getFilePath(String sourceFileName) {
		String baseFolder = propertieService.IMAGE_STORE_PATH + File.separator + "images";
		Date nowDate = new Date();
		// yyyy/MM/dd
		String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy") + File.separator + new DateTime(nowDate).toString("MM") + File.separator
				+ new DateTime(nowDate).toString("dd");
		File file = new File(fileFolder);
		if (!file.isDirectory()) {
			// 如果目录不存在，则创建目录
			file.mkdirs();
		}
		// 生成新的文件名
		String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS") + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
		return fileFolder + File.separator + fileName;
	}

}
