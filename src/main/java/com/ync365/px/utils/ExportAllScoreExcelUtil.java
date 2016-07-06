package com.ync365.px.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;

 

 

public class ExportAllScoreExcelUtil {
	  public static String objListToExcel(Map data) {
	        Map<String, String> headlines = (Map<String, String>) data.get("headlines");
	        List exportAllScoreModels = (List) data.get("exportAllScoreModels");
	        Object[] keys = headlines.keySet().toArray();
	        String[] headlinesKeys = new String[keys.length];
	        for (int k = 0; k < keys.length; k++) {
	        	int co11 = 0;
	            String temp = keys[k].toString(); 
	             if(k>=0 && k<=9) { 
	            	 co11 = Integer.valueOf(temp.substring(0, 1));//列号
	  	            headlinesKeys[co11] = temp.substring(1);
	            }else{
	            	 co11 = Integer.valueOf(temp.substring(0, 2));//列号
	 	            headlinesKeys[co11] = temp.substring(2);
	            }
	        }
	        
	        
	        FileOutputStream fos = null;
	        
	        try {
	        	File file = new File("/opt/pxpic/data/export.xls");  
	            
					if (!file.exists()) {
						file.createNewFile();
					} 
	            HSSFWorkbook wb = new HSSFWorkbook();
	            HSSFSheet sheet = wb.createSheet("new sheet");
	      
	            for (int i = 0; i < exportAllScoreModels.size(); i++) {
	                HSSFRow row = sheet.createRow(i);
	                Object obj = exportAllScoreModels.get(i);
	                for (int j = 0; j < headlinesKeys.length; j++) {
	                    HSSFCell cell = row.createCell(j);
	                    if (i == 0) { 
	                        sheet.setColumnWidth(j, 6000);
	                        cell.setCellValue(new HSSFRichTextString(headlines.get(j
	                        + headlinesKeys[j])));
	                    } else {
	                        String ziDuanName = (String) headlinesKeys[j];
	                      // System.out.println(ziDuanName);
	                        ziDuanName = ziDuanName.replaceFirst(ziDuanName
	                        .substring(0, 1), ziDuanName.substring(0, 1)
	                        .toUpperCase());
	                        ziDuanName = "get" + ziDuanName;
	                        Class clazz = Class.forName(obj.getClass().getName());
	                        Method[] methods = clazz.getMethods();
	                        Pattern pattern = Pattern.compile(ziDuanName);
	                        Matcher mat = null;
	                        for (Method m : methods) {
	                            mat = pattern.matcher(m.getName());
	                            if (mat.find()) {
	                            Object shuXing = m.invoke(obj, null);
	                            if (shuXing != null) {
	                                cell.setCellValue(shuXing.toString());//这里可以做数据格式处理
	                            } else {
	                                cell.setCellValue("");
	                            }
	                            break;
	                        }
	                    }
	                }
	            }
	                
	        }
	            fos = new FileOutputStream(file);
	        wb.write(fos);// 写入File
	       
	        fos.flush();
	       
	        return file.getAbsolutePath();
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }finally {
				if (null != fos) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	    }
	 
/*	    public static void main(String args[]) {
	        try {
	            List listData = new ArrayList();
	            for (int i = 0; i < 6; i++) {
	                TreeNode treeNode = new TreeNode();//要导出的数据类
	                treeNode.setId("hehe" + i);
	                treeNode.setChildren(true);
	                treeNode.setText("adf");
	                listData.add(treeNode);
	            }
	            Map<String, String> headlines = new HashMap<String, String>();
	            headlines.put("0BasicInfo", "基本信息");//属性前边的数字代表字段的先后顺序。
	            headlines.put("1Scores", "积分");//最好将源码中判别顺序的格式改为"序号-字段"。
	            headlines.put("2ScoreSumYear", "年度汇总");
	            Map data = new HashMap();
	            data.put("listData", listData);
	            data.put("headlines", headlines);
	            List2Excel.objListToExcel("测试", data);
	            } catch (Exception e) {
	                e.printStackTrace();
	        }
	    }*/
}
