package code.jtduan.crawler.proxypool;

/**
 * Created by jintaoduan on 17/4/21.
 */
public class IP {
    String ip;
    String port;

    public IP(String ip, String port) {
        this.ip=ip;
        this.port=port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        if(port.isEmpty()) return -1;
        return Integer.parseInt(port);
    }

    public void setPort(String port) {
        this.port = port;
    }
}
