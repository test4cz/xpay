package cn.exrick.dao.impl;

import cn.exrick.bean.Pay;
import cn.exrick.bean.PayRecord;
import cn.exrick.dao.PayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class PayDaoImpl implements PayDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Map<String,Object>> getByStateIs(Integer state) {
        String sql="select id,nick_name,info,money,email,state,pay_type,create_time,update_time,test_email,username from payRecord where state=?";
        List<Map<String,Object>> list=  jdbcTemplate.queryForList(sql,new Object[]{state});


        return list;
    }

    @Override
    public List<Pay> getByStateIsNotAndStateIsNot(Integer state1, Integer state2) {
        return null;
    }

    @Override
    public List<Map<String, Object>> findAccountByType(Integer type) {
        String sql= "select  a.id as receiveAccountId,a.type as receiveAccountType, q.id as qrId,a.account as receiveAccount,q.qr_img as receiveQrImg,q.amount from receiveaccount a join receiveaccount_qr q on a.id=q.accountId where a.`status`=0 and q.`status`=0 and a.type=?";
        return jdbcTemplate.queryForList(sql,new Object[]{type});
    }

    @Override
    public Integer savePayRecord(PayRecord record) {
        String sql="INSERT INTO `payrecord` (`receiveAccountId`, `receiveAccount`, `receiveAccountType`, `payAccount`, `state`, `createTime`, `channel`, `receiveQrImg`) VALUES (?,?,?,?,?,?,?,?);";
        return jdbcTemplate.update(sql,record.getReceiveAccountId(),record.getReceiveAccount(),record.getReceiveAccountType(),record.getPayAccount(),record.getState(),record.getCreateTime(),record.getChannel(),record.getReceiveQrImg());
    }
}
