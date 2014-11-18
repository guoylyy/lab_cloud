package com.prj.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.prj.service.FileUploadService;
import com.prj.util.DataWrapper;
import com.prj.util.DateUtils;
import com.prj.util.ErrorCodeEnum;

@Service("FileUploadServiceImpl")
public class FileUploadServiceImpl implements FileUploadService {

	@SuppressWarnings("rawtypes")
	@Override
	public DataWrapper saveFile(String path, int id, MultipartFile file) {
		try {
			StringBuffer strBuffer = new StringBuffer();
			strBuffer.append(path);
			strBuffer.append('/');
			strBuffer.append(id);
			strBuffer.append('/');
			strBuffer.append(DateUtils.getCurrentDateString());
			strBuffer.append('/');
			path = strBuffer.toString();
//			System.out.println(path);
			if (!new File(path).isDirectory()) {
				new File(path).mkdirs();
			}
			FileOutputStream fos = new FileOutputStream(path + "/" + file.getOriginalFilename());
			System.out.println("Add File: "+ path + "/" + file.getOriginalFilename());
			InputStream is = file.getInputStream();
			byte[] buffer = new byte[1024 * 1024];
//			int bytesum = 0;
			int byteread = 0;
			while ((byteread = is.read(buffer)) != -1) {
//				bytesum += byteread;
				fos.write(buffer, 0, byteread);
				fos.flush();
			}
			fos.close();
			is.close();
			return new DataWrapper();
		} catch (Exception e) {
			e.printStackTrace();
			DataWrapper ret = new DataWrapper();
			ret.setErrorCode(ErrorCodeEnum.File_Creation_Error);
			return ret;
		}
	}
}
