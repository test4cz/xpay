package cn.exrick.dao;

import cn.exrick.bean.Pay;
import cn.exrick.bean.PayRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Exrickx
 */
public interface PayDao {

    List<Map<String,Object>> getByStateIs(Integer state);

    List<Pay> getByStateIsNotAndStateIsNot(Integer state1,Integer state2);

//    @Query(value = "select a.id as accountId,q.id as qrId,a.account,q.qr_img,q.amount from receiveaccount a join receiveaccount_qr q on a.id=q.accountId where a.`status`=0 and q.`status`=0 and a.type=:type ",nativeQuery=true)
    List<Map<String,Object>> findAccountByType(Integer type);
    Integer savePayRecord(PayRecord record);
}
