package com.ync365.px.service.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ync365.px.entity.PxFile;
import com.ync365.px.repository.PxFileDao;
import com.ync365.px.service.account.ShiroDbRealm.ShiroUser;
import com.ync365.px.shiro.util.ShiroUserUtils;

@Component
@Transactional
public class PxFileService {
	@Autowired
	private PxFileDao pxFileDao;
	public void addFile(PxFile pxFile){
		pxFileDao.save(pxFile);
	}
	public void deleteFile(PxFile pxFile){
		pxFileDao.delete(pxFile);
	}
	public List<PxFile> getPxFileList(){
		return (List<PxFile>) pxFileDao.findAll();
	}
	public PxFile getPxFileById(Long fileId) {		
		return pxFileDao.findOne(fileId);
	}
	public List<PxFile> getPxFileByTypeFK(Integer type ,Integer foreignKey) {
	    return pxFileDao.findByTypeAndForeignKey(type, foreignKey);
	}
	public PxFile uploadFile(MultipartFile file , Integer type ,Integer foreignKey) {
	    String rootPath = "/opt/pxpic";
        File dir = new File(rootPath);
        if(!dir.exists()){
            dir.mkdir();
        }
        //文件保存路径  
        String fileSavePath = rootPath + File.separator+file.getOriginalFilename()+System.currentTimeMillis();  
        File localFile = new File(fileSavePath);
        PxFile pxFile = new PxFile();
        pxFile.setName(file.getOriginalFilename());
        ShiroUser currentUser = ShiroUserUtils.getCurrentUser();
        pxFile.setUserId(currentUser.id);
        pxFile.setUserName(currentUser.getName());
        pxFile.setSavePath(fileSavePath);
        pxFile.setUploadTime(new Date());      
        pxFile.setForeignKey(foreignKey);
        pxFile.setType(type);
        long fileSize = file.getSize();
        
        if(fileSize<1024){//如果文件小于1k
            /*BigDecimal bd = new BigDecimal(fileSize);                         
            BigDecimal bd2 = bd.divide(new BigDecimal(1024));
            bd2 = bd2.setScale(2, BigDecimal.ROUND_HALF_UP);                    
            pxFile.setSize(Long.parseLong(bd2.toString())); */
            
            //当文件小于1k时默认存储到数据库的大小都设置为1k                     
            pxFile.setSize(1l);
        }else if(fileSize>104857600){//如果文件大于100M
            return null;
        }else{
             pxFile.setSize(fileSize/1024);
        }          
        
        //上传文件到本地服务器    
        try {
            file.transferTo(localFile);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  
        //上传成功后将文件信息保存到数据库中      
       addFile(pxFile);
       return pxFile;
	}
}
