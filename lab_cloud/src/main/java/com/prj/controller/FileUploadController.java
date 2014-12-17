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

import com.prj.service.AccountService;
import com.prj.service.FileUploadService;
import com.prj.service.StudentService;
import com.prj.util.AccountAccess;
import com.prj.util.AuthorityException;
import com.prj.util.DataWrapper;
import com.prj.util.ErrorCodeEnum;

@Controller
@RequestMapping("/Upload")
public class FileUploadController {
	@Resource(name = "AccountServiceImpl")
	AccountService as;
	@Resource(name = "StudentServiceImpl")
	StudentService ss;
	@Resource(name = "FileUploadServiceImpl")
	FileUploadService fs;
	
	@SuppressWarnings("rawtypes")
	@AccountAccess
	@RequestMapping(value = "/file/{id}", method = RequestMethod.POST)
	@ResponseBody
	public DataWrapper uploadFile(DataWrapper<MultipartFile> wrapper, @PathVariable int id, HttpServletRequest request) {
		DataWrapper ret = ss.getStudentById(id);
		if (!ret.getErrorCode().equals(ErrorCodeEnum.No_Error))
			return ret;
		
		MultipartFile file = wrapper.getData();
		String path = request.getSession().getServletContext().getRealPath("/files");
		return fs.saveFileById(path, file, id);
	}
	
//	@SuppressWarnings("rawtypes")
//	@RequestMapping(value = "/test", method = RequestMethod.POST)
//	@ResponseBody
//	public DataWrapper uploadFile(HttpServletRequest request) {
//		System.out.println("HERE");
////		System.out.println(request.getParameter("test"));
////		System.out.println(test);
//			try {
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
//		return new DataWrapper();
//	}
	
	@ExceptionHandler(AuthorityException.class)
	@ResponseBody
	public DataWrapper<Void> handleAuthorityException(AuthorityException ex) {
		System.out.println(ex.getErrorCode().getLabel());
		DataWrapper<Void> ret = new DataWrapper<Void>();
		ret.setErrorCode(ex.getErrorCode());
		return ret;
	}
}
