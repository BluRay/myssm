package com.byd.myssm.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.byd.myssm.entity.Book;

@Controller
@RequestMapping("/file")
public class FileController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
    private String fileUpload(Book book ,@RequestParam(value="file",required=false) MultipartFile file,
            HttpServletRequest request)throws Exception{
        //基本表单
		logger.info("---->upload:" + book.toString());
        
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!file.isEmpty()){
            //生成uuid作为文件名称
            //String uuid = UUID.randomUUID().toString().replaceAll("-","");
        	
        	Long bookid = book.getBookId();
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType=file.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            path="/upload/images/"+bookid+"."+imageName;
            file.transferTo(new File(pathRoot+path));
        }
        //System.out.println(path);
        request.setAttribute("imagesPath", path);
        //return "index";
        return "redirect:/index";  
    }
	
	@RequestMapping(value="/multiupload",method=RequestMethod.POST)
    private String fildUpload(Book book ,@RequestParam(value="file",required=false) MultipartFile[] file,
            HttpServletRequest request)throws Exception{
        //基本表单
		logger.info("---->multiupload:" + book.toString());
        
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        List<String> listImagePath=new ArrayList<String>();
        int file_count = 0;
        for (MultipartFile mf : file) {
            if(!mf.isEmpty()){
                //生成uuid作为文件名称
                //String uuid = UUID.randomUUID().toString().replaceAll("-","");
            	String bookid = book.getBookId() + "-" + String.valueOf(file_count);
                //获得文件类型（可以判断如果不是图片，禁止上传）
                String contentType=mf.getContentType();
                //获得文件后缀名称
                String imageName=contentType.substring(contentType.indexOf("/")+1);
                path="/upload/images/"+bookid+"."+imageName;
                mf.transferTo(new File(pathRoot+path));
                listImagePath.add(path);
            }
            file_count++;
        }
        
        request.setAttribute("imagesPathList", listImagePath);
        return "redirect:/index";
    }
	
    //因为我的JSP在WEB-INF目录下面，浏览器无法直接访问
    @RequestMapping(value="/forward")
    private String forward(){
        return "index";
    }

}
