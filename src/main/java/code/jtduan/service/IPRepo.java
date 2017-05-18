package code.jtduan.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IPRepo extends CrudRepository<IP,Long> {

    public IP findByIpAndSource(String ip, String source);

    public IP findByIp(String ip);

    @Query(value = "select ip from IP ip where ip.city like %:city%")
    public List<IP> findByCity(@Param("city") String city);
}
