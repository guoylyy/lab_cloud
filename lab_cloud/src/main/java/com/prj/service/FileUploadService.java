package com.prj.service;

import org.springframework.web.multipart.MultipartFile;

import com.prj.entity.Account;
import com.prj.util.AccountCharacter;
import com.prj.util.DataWrapper;

public interface FileUploadService {
	DataWrapper<String> saveFile(String path, MultipartFile file, String fileName);
	
	DataWrapper<String> saveFileById(String path, MultipartFile file, int id);

	DataWrapper<String> saveIcon(String path, MultipartFile file, String iconName);
}
