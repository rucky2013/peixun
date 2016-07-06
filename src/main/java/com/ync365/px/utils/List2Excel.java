package com.ync365.px.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ync365.px.model.bo.TreeNode;

public class List2Excel {
    public static String[] objListToExcel(String name, Map data) {
        Map<String, String> ziDuan = (Map<String, String>) data.get("ziDuan");
        List listData = (List) data.get("listData");
        Object[] keys = ziDuan.keySet().toArray();
        String[] ziDuanKeys = new String[keys.length];
        for (int k = 0; k < keys.length; k++) {
            String temp = keys[k].toString();
            int xuHao = Integer.valueOf(temp.substring(0, 1));
            ziDuanKeys[xuHao] = temp.substring(1);
        }
        try {
            String path = "hehe.xls";
            File newFile = new File(path);
            newFile.createNewFile();
            System.out.println("创建文件成功：" + path);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();
      
            for (int i = 0; i < listData.size(); i++) {
                HSSFRow row = sheet.createRow(i);
                Object obj = listData.get(i);
                for (int j = 0; j < ziDuanKeys.length; j++) {
                    HSSFCell cell = row.createCell(j);
                    if (i == 0) {
                        sheet.setColumnWidth(j, 6000);
                        cell.setCellValue(new HSSFRichTextString(ziDuan.get(j
                        + ziDuanKeys[j])));
                    } else {
                        String ziDuanName = (String) ziDuanKeys[j];
                        System.out.println(ziDuanName);
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
        OutputStream out = new FileOutputStream(path);
        wb.write(out);// 写入File
        out.flush();
        out.close();
        return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String args[]) {
        try {
            List listData = new ArrayList();
            for (int i = 0; i < 6; i++) {
                TreeNode treeNode = new TreeNode();//要导出的数据类
                treeNode.setId("hehe" + i);
                treeNode.setChildren(true);
                treeNode.setText("adf");
                listData.add(treeNode);
            }
            Map<String, String> ziDuan = new HashMap<String, String>();
            ziDuan.put("0children", "孩子");//属性前边的数字代表字段的先后顺序。
            ziDuan.put("1Text", "内容");//最好将源码中判别顺序的格式改为"序号-字段"。
            ziDuan.put("2id", "编号");
            Map data = new HashMap();
            data.put("listData", listData);
            data.put("ziDuan", ziDuan);
            List2Excel.objListToExcel("测试", data);
            } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
