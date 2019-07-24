package com.mysiteforme.admin.onenet;

import com.mysiteforme.admin.mqtt.OneNetConfig;
import com.mysiteforme.admin.util.CloneUtils;
import com.mysiteforme.admin.util.DateUtils;
import com.mysiteforme.admin.util.ExcelUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author Iwen
 * @date 2019/6/25 14:25
 * @Version 1.0
 */
public class DataAnalyseRunner implements Runnable {

    @Autowired
    private OneNetConfig config;

    public void dataAnalyse() throws Exception {
        ArrayList<String> contents = new ArrayList(OnenetConstant.CONTENT.values());

        if ("true".equals(config.getExcel())) {
            OnenetConstant.EXCEL_CONTENT.putAll(CloneUtils.clone(OnenetConstant.CONTENT));
            /**导出到excel*/
            if (OnenetConstant.EXCEL_CONTENT.size() > 100) {
                String path = config.getExcelPath() + DateUtils.getDateDay() + ".xlsx";
                if (!ExcelUtils.filecheck(path)) {
                    // 如果存在
                    ExcelUtils.write(OnenetConstant.EXCEL_CONTENT);
                } else {
                    ExcelUtils.addExcel(ExcelUtils.getHasFile(), OnenetConstant.EXCEL_CONTENT);
                }
                File file = new File(path);
                ExcelUtils.addExcel(file, OnenetConstant.EXCEL_CONTENT);
            }
            OnenetConstant.EXCEL_CONTENT.clear();
        }
        OnenetConstant.CONTENT.clear();


        for (String content : contents) {

            List<String> contentList = Arrays.asList(content.split(","));

            for (String data : contentList) {
                JSONObject jsonMsg = new JSONObject(data);
                long ldate = Long.parseLong(jsonMsg.getString("at"));
                Date date = new Date(ldate);
                String oneNetId = jsonMsg.getString("dev_id");
                String hexString = jsonMsg.getString("value");
                HexAnalyse.analyse(oneNetId, date, hexString);
            }

        }
    }

    @Override
    public void run() {
        try {
            dataAnalyse();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}
