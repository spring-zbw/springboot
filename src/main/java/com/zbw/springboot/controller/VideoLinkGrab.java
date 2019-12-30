package com.zbw.springboot.controller;

import com.zbw.springboot.util.UUIDUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 郑博文 on 2019/12/30.
 */
public class VideoLinkGrab {
    private final static Executor executor = Executors.newCachedThreadPool();
    /***73074  9540
     * 请求的网址url常量
     */
    public static final String REQUEST_URL = "https://www.fie0.com/tupian/";
    /****
     * 图片保存路径
     */
    public static final String IMAGE_SAVE_PATH = "D:\\rob";

    /***
     *  获取img标签正则表达式
     */
    public static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    /****
     * 获取src路径的正则
     */
    public static final String IMGSRC_REG = "(http|https):\"?(.*?)(\"|>|\\s+)";


    public static String[] IMAGE_TYPE_SUFFIX = new String[]{"=png","=jpg","=jpeg",".png",".jpg","jpeg"};
    /****
     * 生成图片的名称默认从1开始递增
     */
    public static Integer imageIndex = 1;

    public static void main(String[] args) {
    for(int i=73074;i>=9540;i--){
                String myUrl="https://www.fie0.com/tupian/"+ String.valueOf(i)+".html";
                String htmlContent = parseContext(myUrl);
                List<String> imageUrlList = getImageSrc(htmlContent);
                for(String imageUrl:imageUrlList){
                    try {
                        download(imageUrl, IMAGE_SAVE_PATH);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

        }

        //通过正则表达式匹配，取出data-src的图片链接存放到list数组中
        //<img class="" data-ratio="0.5993031358885017" data-src="https://mmbiz.qpic.cn/mmbiz_png/dkwuWwLoRK8POMmicDvKwHwYrqrG7KyiaCGBdaib7rOlRlCSfLqaecaXeJvyRGwZZyvmvL9YGiaicNlLs6jlLKaia1icA/640?wx_fmt=png" data-type="png" data-w="861" height="516" style="margin: auto;max-width: 80%;box-sizing: inherit;-webkit-tap-highlight-color: transparent;border-width: initial;border-style: none;border-color: initial;" width="861"  />

//
//        for(String imageUrl:imageUrlList){
//            try {
//                download(imageUrl, IMAGE_SAVE_PATH);
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//        }

//        System.out.println("从【"+REQUEST_URL+"】网站，共抓取【"+(imageIndex-1)+"】张图片。");
    }





    /***
     * 解析图片url路径，保存到对应目录下
     * @param oldUrl 图片链接url
     * @param savePath 图片报错路径
     * @throws Exception
     */
    public static void download(String oldUrl,String savePath) throws Exception {

        String imageType = "";
        boolean flag = false;
        for(String suffix:IMAGE_TYPE_SUFFIX){
            if(oldUrl.lastIndexOf(suffix) > -1 || oldUrl.lastIndexOf(suffix.toUpperCase()) > -1){
                flag = true;
                imageType = suffix.replace("=", ".");
                break;
            }
        }
        //图片类型存在
        if(flag){
            String filename = UUIDUtil.getUUID() + imageType;
            download(oldUrl, filename, savePath);
            imageIndex ++ ;
        }
    }

    /*****
     * 根据图片url路径，下载到对应目录下
     * @param urlString 图片url路径
     * @param filename  文件名称
     * @param savePath  文件报错路径
     * @throws Exception
     */
    public static void download(String urlString, String filename, String savePath) throws Exception {

        if(StringUtils.isEmpty(urlString) || StringUtils.isEmpty(filename) || StringUtils.isEmpty(savePath)){
            throw new IllegalArgumentException("方法入参不能为空！");
        }
        //目录如果不存在，则新增
        File dir = new File(savePath);
        if(!dir.exists() && dir.isDirectory()){
            dir.mkdirs();
        }
        // 构造URL
        URL url = new URL(urlString);

        URLConnection con = url.openConnection();
        //模拟用户访问下载
        con.setRequestProperty("User-agent","Mozilla/4.0");
        // 设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }



    /****
     * 通过httpclient，读取url中的响应内容并返回
     * @param url 请求的url路径
     * @return
     */
    public static String  parseContext(String url) {

        if(StringUtils.isEmpty(url)){
            throw new IllegalArgumentException("访问地址url不能为空");
        }

        String html = null;
        // 创建httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();



        try {
            String userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36";
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("User-Agent",userAgent);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            System.out.println("这是返回的数值"+response);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    html = EntityUtils.toString(entity);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return html;
    }



    /***
     * 获取ImageUrl地址
     *
     * @param htmlContext
     * @return
     */
    private static List<String> getImageUrl(String htmlContext) {

        if(StringUtils.isEmpty(htmlContext)){
            throw new IllegalArgumentException("html请求内容不能为空.");
        }

        List<String> listImgUrl = new ArrayList<String>();

        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(htmlContext);

        while (matcher.find()) {
            listImgUrl.add(matcher.group().replaceAll("'", ""));
        }

        return listImgUrl;
    }

    /***
     * 获取ImageSrc地址
     *
     * @param htmlContext
     * @return
     */
    public static  List<String> getImageSrc(String htmlContext) {

        if(StringUtils.isEmpty(htmlContext)){
            throw new IllegalArgumentException("html请求内容不能为空.");
        }
        List<String> listImageUrl = getImageUrl(htmlContext);

        List<String> listImgSrc = new ArrayList<String>();

        for (String imageContext : listImageUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(imageContext);
            while (matcher.find()) {
                listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1));
            }
        }
        return listImgSrc;
    }


}
