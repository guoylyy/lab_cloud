package com.prj.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prj.service.AccountService;
import com.prj.service.FileUploadService;
import com.prj.util.DataWrapper;
import com.prj.util.DateUtils;
import com.prj.util.ErrorCodeEnum;

@Service("FileUploadServiceImpl")
public class FileUploadServiceImpl implements FileUploadService {
//	@Resource(name = "AccountServiceImpl")
//	AccountService as;
	
	
	@Override
	public DataWrapper<String> saveFileById(String path, MultipartFile file, int id) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(path);
		strBuffer.append('/');
		strBuffer.append(id);
		strBuffer.append('/');
		strBuffer.append(DateUtils.getCurrentDateString());
		strBuffer.append('/');
		path = strBuffer.toString();
		return saveFile(path, file, null);
	}

	@Override
	public DataWrapper<String> saveFile(String path, MultipartFile file, String fileName) {

		DataWrapper<String> ret = new DataWrapper<String>();
		try {
			
			if (!new File(path).isDirectory()) {
				new File(path).mkdirs();
			}
			if (fileName == null)
				fileName = file.getOriginalFilename();
			FileOutputStream fos = new FileOutputStream(path + "/" + fileName);
			System.out.println("Add File: "+ path + "/" + file.getOriginalFilename());
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024 * 1024];
			int byteread = 0;
			while ((byteread = is.read(buffer)) != -1) {
				fos.write(buffer, 0, byteread);
				fos.flush();
			}
			fos.close();
			is.close();
			ret.setData(path + "/" + fileName);
			return ret;

		} catch (Exception e) {
			e.printStackTrace();
			ret.setErrorCode(ErrorCodeEnum.File_Creation_Error);
			return ret;
		}
	}

	@Override
	public DataWrapper<String> saveIcon(String path, MultipartFile file, String iconName) {
		int i = file.getOriginalFilename().lastIndexOf(".");
		String fileName = null;
		if (i != -1) {
			fileName = iconName + file.getOriginalFilename().substring(i);
		}		
		return saveFile(path, file, fileName);
	}
}
