package code.jtduan.util;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by jintaoduan on 17/5/5.
 */
public class StarUtil {
    /**
     * 刷榜：点赞(无需登录，需要代理)
     */
    public static void testStar(Proxy proxy) {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .proxy(proxy)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .hostnameVerifier((a, b) -> true)
                .build();

        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "5889");
        params.put("type", "1");
        String url = "http://highlights.paper.edu.cn/index/pointZan/";

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), buildParams(params));
        Request request = new Request.Builder()
                .url(url).post(body)
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            String res = CharsetDetector.decode(response.body().bytes());
            System.out.println(res);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 刷榜：点赞2(无需登录)
     */
    public static String testStar2(Proxy proxy) {
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .proxy(proxy)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .hostnameVerifier((a, b) -> true)
                .build();

        Request request1 = new Request.Builder()
                .url("http://www.paper.edu.cn/youxiu_new").get()
                .build();

        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", "233");
        params.put("type", "1");
        String url = "http://www.paper.edu.cn/youxiu_new/pointZan/";

        RequestBody body = RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), buildParams(params));
        Request request = new Request.Builder()
                .url(url).post(body)
                .build();

        String res;
        try (Response response1 = okHttpClient.newCall(request1).execute()) {
            if (!response1.isSuccessful()) {
                response1.close();
                return "-2";
            }
        } catch (Exception e) {
            return "-3";
        }
        try (Response response = okHttpClient.newCall(request).execute()) {
            res = CharsetDetector.decode(response.body().bytes());
            if ("2".equals(res)) {
                System.out.println("2:" + proxy.toString());
            }
            response.close();
            return res;
        } catch (IOException e) {
            return "-3";
        }
    }

    public static String buildParams(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            sb.append(str).append("=").append(map.get(str)).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void testGet() {

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .hostnameVerifier((a, b) -> true)
                .build();
//        for(int i=0;i<10;i++) {
//        OKHttpUtil.sendGet("http://highlights.paper.edu.cn/index/paper_detail/5889", "");
        downLoadFile("http://highlights.paper.edu.cn/index/paper_view?id=5889", "/Users/jtduan/Downloads");
    }

    /**
     * 下载文件
     *
     * @param fileUrl     文件url
     * @param destFileDir 存储目标目录
     */
    public static void downLoadFile(String fileUrl, final String destFileDir) {

        OkHttpClient mOkHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .hostnameVerifier((a, b) -> true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Accept-Encoding", "gzip, deflate")
                                .addHeader("Connection", "keep-alive")
                                .addHeader("Host", "highlights.paper.edu.cn")
                                .addHeader("Upgrade-Insecure-Requests", "1")
                                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36")
                                .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();

        final String fileName = String.valueOf(new Random().nextInt(10000)) + ".pdf";
        final File file = new File(destFileDir, fileName);
        if (file.exists()) {
            return;
        }
        final Request request = new Request.Builder().url(fileUrl).build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    long total = response.body().contentLength();
                    System.out.println("total------>" + total);
                    long current = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
//                        current += len;
//                        fos.write(buf, 0, len);
//                        System.out.println("current------>" + current);
                    }
//                    fos.flush();
                    System.out.println("finished!");
                } catch (IOException e) {
                    System.out.println(e.toString());
                } finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                        System.out.println(e.toString());
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
        StarUtil.testGet();
        System.out.println("finished");
    }
}
