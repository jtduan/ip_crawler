package code.jtduan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

public class FileDownload {

    public static final String LOCAL_PATH = "/Users/jtduan/Downloads";

    public static void main(String[] args) {
        //待下载文件地址
        String fileUrl = "http://highlights.paper.edu.cn/index/paper_view?id=5889";
        InputStream in = null;
        OutputStream out = null;
        HttpURLConnection conn = null;
        String fileName = null;
        try {
            //初始化连接
            URL url = new URL(fileUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //获取文件名
            String disposition = conn.getHeaderField("Content-Disposition");
            if (disposition != null && !"".equals(disposition)) {
                //从头中获取文件名
                fileName = disposition.split(";")[1].split("=")[1].replaceAll("\"", "");
            } else {
                //从地址中获取文件名
                fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            }

            if (fileName != null && !"".equals(fileName)) {
                //文件名解码
                fileName = URLDecoder.decode(fileName, "utf-8");
            } else {
                //如果无法获取文件名，则随机生成一个
                fileName = "file_" + (int) (Math.random() * 10);
            }

            //读取数据
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                byte[] buffer = new byte[2048];
                in = conn.getInputStream();
                out = new FileOutputStream(new File(LOCAL_PATH, fileName));
                int count = 0;
                int finished = 0;
                int size = conn.getContentLength();
                while ((count = in.read(buffer)) != -1) {
                    if (count != 0) {
                        out.write(buffer, 0, count);
                        finished += count;
                        System.out.printf("########################################---->%1$.2f%%\n", (double) finished / size * 100);
                    } else {
                        break;
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
