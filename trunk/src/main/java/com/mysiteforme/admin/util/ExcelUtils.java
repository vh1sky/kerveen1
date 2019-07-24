package com.mysiteforme.admin.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Iwen
 * @date 2019/6/25 10:16
 * @Version 1.0
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    private static File hasFile;

    /**
     * 同步操作，防止并发。
     *
     * @param map
     * @return
     * @throws IOException
     * @throws RowsExceededException
     * @throws WriteException
     */
    public synchronized static String[] write(Map map)
            throws IOException, RowsExceededException, WriteException {

        // 文件路径
        // 判断文件是否存在，如果存在就不创建，追加，如果不存在则创建文件并追加。
        WritableWorkbook book = Workbook.createWorkbook(getHasFile());
        book.setProtected(true);
        // -- 第一个参数是Sheet名，第二个参数是Sheet下标
        // -- 下标是整数，只起标识作用，建立的时候会以create顺序建立，本例生成的EXCEL文件第一个Sheet是sheet1
        WritableSheet sheet = book.createSheet("第一页", 1);
        sheet.setColumnView(0, 20);
        sheet.setColumnView(1, 20);
        sheet.getSettings().setProtected(true);
        //sheet.getSettings().setPassword("xxxx");//设置密码
        String[] title = {"时间", "内容"};
        for (int i = 0; i < title.length; i++) {
            Label lable = new Label(i, 0, title[i]);
            sheet.addCell(lable);
        }
        // 初次创建，写入一行。
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            Map.Entry<String, String> entry = it.next();
            Label keyLable = new Label(0, index, entry.getKey());
            sheet.addCell(keyLable);
            Label valueLable = new Label(1, index, entry.getValue());
            sheet.addCell(valueLable);
        }

        // 每次写入数据时，写到最后一行。
        book.write();
        book.close();
        System.out.println("写入成功");
        return null;
    }

    /**
     * 追加excel
     *
     * @param map
     * @throws IOException
     * @throws BiffException
     * @throws WriteException
     * @throws RowsExceededException
     */
    public static void addExcel(File file, Map map) throws BiffException,
            IOException, RowsExceededException, WriteException {
        Workbook book = Workbook.getWorkbook(file);
        Sheet sheet = book.getSheet(0);
        // 获取行
        int length = sheet.getRows();
        WritableWorkbook wbook = Workbook.createWorkbook(file, book); // 根据book创建一个操作对象
        WritableSheet sh = wbook.getSheet(0);// 得到一个工作对象

        /*if (length == 0) {
            String[] title = {"时间", "内容"};
            for (int i = 0; i < title.length; i++) {
                Label lable = new Label(i, 0, title[i]);
                sh.addCell(lable);
            }
        }*/

        // 从最后一行开始加
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            Label keyLable = new Label(0, length, entry.getKey());
            sh.addCell(keyLable);
            Label valueLable = new Label(1, length, entry.getValue());
            sh.addCell(valueLable);
        }

        wbook.write();
        wbook.close();
    }

    /**
     * 判断文件是否已经写入
     *
     * @param filename
     * @return
     */
    public static boolean filecheck(String filename) {
        boolean flag = false;
        File file = new File(filename);
        if (file.exists()) {
            flag = true;
        }
        setHasFile(file);
        return flag;
    }

    /**
     * @return the hasFile
     */
    public static File getHasFile() {
        return hasFile;
    }

    /**
     * @param hasFile the hasFile to set
     */
    public static void setHasFile(File hasFile) {
        ExcelUtils.hasFile = hasFile;
    }
}
