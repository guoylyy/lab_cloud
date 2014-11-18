package com.prj.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.prj.service.AccountService;
import com.prj.service.FileUploadService;
import com.prj.util.AccountAccess;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;

@Controller
@RequestMapping("/Upload")
public class FileUploadController {
	@Resource(name = "AccountServiceImpl")
	AccountService as;
	@Resource(name = "FileUploadServiceImpl")
	FileUploadService fs;
	
	@SuppressWarnings("rawtypes")
	@AccountAccess
	@RequestMapping(value = "/file/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper uploadFile(DataWrapper<MultipartFile> wrapper, @PathVariable int id, HttpServletRequest request) {
		DataWrapper ret = as.getAccountById(id);
		if (ret.getErrorCode() != ErrorCodeEnum.No_Error)
			return ret;
		
		MultipartFile file = wrapper.getData();
		String path = request.getSession().getServletContext().getRealPath("/files");
		return fs.saveFile(path, id, file);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper uploadFile(MultipartHttpServletRequest request) {
//		try {
//			StringBuffer sb = new StringBuffer() ; 
//			InputStream is = request.getInputStream(); 
//			InputStreamReader isr = new InputStreamReader(is);   
//			BufferedReader br = new BufferedReader(isr); 
//			String s = "" ; 
//			while((s=br.readLine())!=null){ 
//			sb.append(s) ; 
//			} 
//			String str =sb.toString(); 
//			System.out.println(str);
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(request.getParameter("token"));
//		System.out.println(request.getFile("file"));
		//		System.out.println(token);
//		System.out.println(file.getOriginalFilename());
		
//		String token = request.getParameter("token");
//		System.out.println(wrapper.getToken());
//		System.out.println("Size: " + wrapper.getFile().getOriginalFilename());
		return new DataWrapper();
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(AuthorityException.class)
	@ResponseBody
	public DataWrapper handleAuthorityException(AuthorityException ex) {
		System.out.println(ex.getErrorCode().getLabel());
		DataWrapper ret = new DataWrapper();
		ret.setErrorCode(ex.getErrorCode());
		return ret;
	}
}
