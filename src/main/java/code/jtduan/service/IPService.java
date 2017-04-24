package code.jtduan.service;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jintaoduan on 17/4/24.
 */
@Service
public class IPService {

    private static IPRepo ipRepo;

    @Autowired
    IPRepo ipRepo_temp;

    @PostConstruct
    public void  postConstruct(){
        this.ipRepo = ipRepo_temp;
    }

    public void add(IP ip){
        ipRepo.save(ip);
    }

    public static void add(String ip, int port, String source,String city) {
        if(ipRepo.findByIpAndSource(ip+":"+port,source)==null) {
            IP entity = new IP(ip + ":" + port, source,city);
            entity.setCreate_time(new Date());
            ipRepo.save(entity);
        }
        else{
            System.out.println("重复");
        }
    }

    public List<IP> selectIpByCity(String city) {
        return ipRepo.findByCity(city);
    }
}
