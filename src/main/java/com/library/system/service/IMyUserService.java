package com.library.system.service;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;
import com.library.system.entity.MyUser;
public interface IMyUserService {
	/**
	 * 检查用户
	 * @param myuser
	 * @return
	 */
       public MyUser CheckUser(MyUser myuser); 
       /**
        * 根据ID查询
        * @param id
        * @return
        */
       public MyUser QueryById(String id);
       /**
        * 增加或者修改
        * @param user
        * @return
        */
       public int SaveOrUpdate(@RequestBody MyUser user);
       
       public int deleteById(String id);
       
       
       public List<Map<String,Object>> slectByMap();
     
       /**
        * 根据身份证的集合,查询数据库中,id,cardnumber两个字段,并且以key,value的形式返回,
        * 其中,key值是返回对象的列名,value是返回的值
        * @param cardNumbers
        * @return
        */
       public List<Map<String,String>> getMapBycardNumber(List<String> cardNumbers);
       
       public int deleteMyUserByIds(List<String> ids);
       
       public int insertMyUserByExcel(List<MyUser> users);
       
       public int checkUser(String userName);
       /**
        * 根据用户名和密码,检是否匹配
        * @param userName
        * @param Email
        * @return
        */
       public int checkUserAndEmail(Map<String, String> param);
       /**
        * 根据用户名修改密码
        * @param param
        * @return
        */
       public Integer changePassword(Map<String, Object> param);
       
       public  void checkDemo(Integer id);
}
