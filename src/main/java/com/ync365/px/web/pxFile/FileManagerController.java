package com.ync365.px.web.pxFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ync365.px.entity.PxFile;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.service.file.PxFileService;
import com.ync365.px.shiro.util.ShiroUserUtils;

@Controller
@RequestMapping("/fileManager")
public class FileManagerController {	
	
	@RequestMapping(value = "/toFileUpload")
	public String my(Model model) {
		//此页面仅仅用于测试
		return "fileUpload/newFile";
	}
	@Autowired
	private PxFileService pxFileService;
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/uploadFile")  
    public void uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		Map<String, Object> map = new HashMap<String, Object>();
		response.setContentType("application/json");     
        //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){                
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){                
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){                	
              		String rootPath = "/opt/pxpic"; 
              		File dir = new File(rootPath);
              		if(!dir.exists()){
              			dir.mkdir();
              		}
              		//文件保存路径  
                    String fileSavePath = rootPath + File.separator+file.getOriginalFilename();  
                    File localFile = new File(fileSavePath);
                    PxFile pxFile = new PxFile();
                    pxFile.setName(file.getOriginalFilename());
                    ShiroUser currentUser = ShiroUserUtils.getCurrentUser();
                    pxFile.setUserId(currentUser.id);
                    pxFile.setUserName(currentUser.getName());
                    pxFile.setSavePath(fileSavePath);
                    pxFile.setUploadTime(new Date());                    
                    long fileSize = file.getSize();
                    
                    if(fileSize<1024){//如果文件小于1k
                    	/*BigDecimal bd = new BigDecimal(fileSize);                       	
                    	BigDecimal bd2 = bd.divide(new BigDecimal(1024));
                    	bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);                   	
                    	pxFile.setSize(Long.parseLong(bd2.toString())); */
                    	
                    	//当文件小于1k时默认存储到数据库的大小都设置为1k                    	
                    	pxFile.setSize(1l);
                    }else if(fileSize>104857600){//如果文件大于100M
                    	map.put("fileSize", "请上传小于100M的文件");
                    	return;
                    }else{
                    	 pxFile.setSize(fileSize/1024);
                    }          
                    
                    //上传文件到本地服务器	
                    file.transferTo(localFile);  
                    map.put("isUpload", "OK");                    
                    //上传成功后将文件信息保存到数据库中                   
                    pxFileService.addFile(pxFile);
                }
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();       
 		response.getWriter().write(objectMapper.writeValueAsString(map));
 		response.flushBuffer(); 
    }  
	
	/**
	 * 下载文件
	 * @param fileId
	 * @param response
	 * @throws IOException
	 */
    @RequestMapping("/download/{fileId}")  
    public void download(@PathVariable String fileId,HttpServletResponse response) throws IOException {  
        OutputStream os = response.getOutputStream();         
        PxFile pxFile = pxFileService.getPxFileById(Long.parseLong(fileId));
        try {           
            response.reset(); 
            String fileName = new String(pxFile.getName().getBytes("UTF-8"),"iso-8859-1");
            response.setHeader("Content-Disposition", "attachment;fileName="+fileName);  
            response.setContentType("application/octet-stream; charset=utf-8");  
            os.write(FileUtils.readFileToByteArray(new File(pxFile.getSavePath())));  
            os.flush();  
        }catch(IOException e){
        	e.printStackTrace();
        }finally {  
            if (os != null) {  
                os.close();  
            }  
        }          
    }  
    @RequestMapping("/del/{fileId}")  
    public void delete(@PathVariable Long fileId,HttpServletResponse response) throws IOException {  
        PxFile pxFile = new PxFile();
        pxFile.setId(fileId);
        pxFileService.deleteFile(pxFile);
    }  

}
