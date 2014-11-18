package com.prj.service;

import org.springframework.web.multipart.MultipartFile;

import com.prj.util.DataWrapper;

public interface FileUploadService {
	@SuppressWarnings("rawtypes")
	DataWrapper saveFile(String path, int id, MultipartFile fileList);
}
