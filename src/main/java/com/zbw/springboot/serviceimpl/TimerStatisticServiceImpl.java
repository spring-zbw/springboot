package com.zbw.springboot.serviceimpl;

import com.zbw.springboot.dao.TimerStatisticDao;
import com.zbw.springboot.pojo.CustomerCnt;
import com.zbw.springboot.service.TimerStatisticService;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author 郑博文
 * @date 2019/10/11
 */
@Service
public class TimerStatisticServiceImpl implements TimerStatisticService {

     @Autowired
     private TimerStatisticDao timerStatisticDao;


     public  void generatingStatisticalGraph(long customerId,int fansCnt,int thumbCnt,int mutualFans) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("粉丝数"+fansCnt, new Double(fansCnt));
        dataset.setValue("关注数"+thumbCnt, new Double(thumbCnt));
        dataset.setValue("互粉数"+mutualFans, new Double(mutualFans));

        JFreeChart chart = ChartFactory.createPieChart("",
                dataset,
                true,
                true, false);
        setChart(chart);

        PiePlot pieplot = (PiePlot) chart.getPlot();
        pieplot.setSectionPaint("粉丝数"+fansCnt, Color.decode("#84D0E0"));
        pieplot.setSectionPaint("关注数"+thumbCnt, Color.decode("#FFDD65"));
        pieplot.setSectionPaint("互粉数"+mutualFans, Color.decode("#FC935E"));

        PiePlot piePlot= (PiePlot) chart.getPlot();
        piePlot.setLabelFont(new Font("PingFangSC-Regular",Font.PLAIN,15));
        piePlot.setLabelPaint(Color.decode("#666666"));
        pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{2}", NumberFormat.getNumberInstance(),
                new DecimalFormat("0.00%")));
        chart.getLegend().setItemFont(new Font("PingFang_简细",Font.PLAIN,15));
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        try {
           ChartUtilities.saveChartAsPNG(new File("/opt/apache-tomcat-8.5.16/webapps/yuejian-mg/data/"+str+"/"+getRandomFileName(customerId)+".jpg"), chart, 375, 215);
            //ChartUtilities.saveChartAsPNG(new File("d:\\myjava\\"+str+"\\"+getRandomFileName(customerId)+".png"), chart, 375, 215);
        } catch (Exception e) {
            System.err.println("创建图形时出错");
        }
    }
    @Override
    public void timerStatistics(){
        List<CustomerCnt> customerCntAll = timerStatisticDao.findCustomerCntAll();
        int threadSize = 500;
        int remainder = customerCntAll.size()%threadSize;
        int threadNum  = 0;
        if(remainder == 0){
            threadNum  = customerCntAll.size()/threadSize;
        } else {
            threadNum  = customerCntAll.size()/threadSize + 1;
        }
        ExecutorService eService = Executors.newFixedThreadPool(threadNum);
        List<Callable<String>> cList = new ArrayList<>();
        Callable<String> task = null;
        List<CustomerCnt> sList = null;

        for(int i=0;i<threadNum;i++){
            if(i == threadNum - 1){
                sList = customerCntAll.subList(i*threadSize, customerCntAll.size());
            } else {
                sList = customerCntAll.subList(i*threadSize, (i+1)*threadSize);
            }
            final List<CustomerCnt> nowList = sList;
            task = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    for(int j=0;j<nowList.size();j++){
                        CustomerCnt customerCnt= nowList.get(j);
                        Integer customer_id = customerCnt.getCustomer_id().intValue();
                        int mutualFans = timerStatisticDao.getMutualFans(customer_id);
                        generatingStatisticalGraph(customerCnt.getCustomer_id(),customerCnt.getFans_cnt(),customerCnt.getThumb_cnt(),mutualFans);

                    }
                    return "";
                }
            };
            cList.add(task);
        }
        try {
            eService.invokeAll(cList);
            eService.shutdown();
        }catch (Exception e){
            System.out.println("线程异常");
        }
    }

    /**
     * 生成随机文件名：当前年月日时分秒+五位随机数
     * @return
     */
    public static String getRandomFileName(long id) {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        return str+id;
    }
    public static void setChart(JFreeChart chart) {
        chart.setTextAntiAlias(true);
        PiePlot pieplot = (PiePlot) chart.getPlot();
        // 设置图表背景颜色
        pieplot.setBackgroundPaint(ChartColor.WHITE);
        pieplot.setLabelBackgroundPaint(null);
        pieplot.setLabelOutlinePaint(null);
        pieplot.setLabelShadowPaint(null);
        pieplot.setOutlinePaint(null);
        pieplot.setShadowPaint(null);
        pieplot.setSectionOutlinesVisible(false);
        pieplot.setNoDataMessage("没有可供使用的数据！");
    }
}
