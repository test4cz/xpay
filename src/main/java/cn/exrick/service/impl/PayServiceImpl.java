package cn.exrick.service.impl;

import cn.exrick.bean.Pay;
import cn.exrick.bean.PayRecord;
import cn.exrick.dao.PayDao;
import cn.exrick.service.PayService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Exrickx
 */
@Service
public class PayServiceImpl implements PayService {

    private static final Logger log = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private PayDao payDao;

    @Override
    public List<Map<String,Object>> getPayList(Integer state) {

        List<Map<String,Object>> list= payDao.getByStateIs(state);
//        for(Pay pay:list){
//            //屏蔽隐私数据
//            pay.setId("");
//            pay.setEmail("");
//            pay.setTestEmail("");
//            pay.setUsername("");
//        }
        return list;
    }

    @Override
    public List<Pay> getNotPayList() {

        List<Pay> list=payDao.getByStateIsNotAndStateIsNot(0,1);
        for(Pay pay:list){
            //屏蔽隐私数据
            pay.setId("");
            pay.setEmail("");
            pay.setTestEmail("");
            pay.setUsername("");
        }
        return list;
    }

    @Override
    public Pay getPay(String id) {

//        Pay pay=payDao.findOne(id);
//        pay.setTime(StringUtils.getTimeStamp(pay.getCreateTime()));
//        return pay;
        return  null;
    }

    @Override
    public int addPay(Pay pay) {

        pay.setId(UUID.randomUUID().toString());
        pay.setCreateTime(new Date());
        pay.setState(0);
//        payDao.save(pay);
        return 1;
    }

    @Override
    public int updatePay(Pay pay) {

        pay.setUpdateTime(new Date());
//        payDao.saveAndFlush(pay);
        return 1;
    }

    @Override
    public int changePayState(String id, Integer state) {

        Pay pay=getPay(id);
        pay.setState(state);
        pay.setUpdateTime(new Date());
//        payDao.saveAndFlush(pay);
        return 1;
    }

    @Override
    public int delPay(String id) {

//        payDao.delete(id);
        return 1;
    }

    @Override
    public String getRandomPayQRImg(Integer type, String account,String amount,String channel) {
        List<Map<String,Object>> result=payDao.findAccountByType(type);
        int max=result.size()-1,min=0;
        int randNumber =max<=0?0:new Random().nextInt(max - min + 1) + min;
        Map<String,Object> map= result.get(randNumber);

        PayRecord record=new PayRecord();
        try {
            BeanUtils.populate(record,map);
            record.setPayAccount(account);
            record.setChannel(channel);
            record.setCreateTime(new Date());
            record.setState(0);
            record.setAmount(new BigDecimal(amount));
            payDao.savePayRecord(record);
             return record.getReceiveQrImg();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }



        return null;
    }

}
