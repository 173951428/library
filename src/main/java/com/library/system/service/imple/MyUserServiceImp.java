package com.library.system.service.imple;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.library.system.entity.MyUser;
import com.library.system.service.IMyUserService;
import com.library.system.utils.SqlClient;
import com.library.system.utils.UUIDGenerator;

@Service("userService")
public class MyUserServiceImp implements IMyUserService {
	 @Autowired
     public SqlClient sqlClient;
	 MyUser user;
	 
	/**
	 * 检查用户名和密码
	 */

	@Override
	
	/**
	 * 传播级别:isolation,隔离机制:propagation
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE,propagation=Propagation.SUPPORTS)
	public MyUser CheckUser(MyUser myuser) {
		user=sqlClient.queryForObject("mapper.MyUserMapper.checkUserExit", myuser);
		return sqlClient.queryForObject("mapper.MyUserMapper.checkUserExit", myuser);
	}
	
	public void checkDemo(Integer id){
		 if(null!=user){
			 System.out.println("得到的id:"+id);
		 }
	}
	
	@Override
	public MyUser QueryById(String id) {
		
		return sqlClient.queryForObject("mapper.MyUserMapper.queryById",id);
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public int SaveOrUpdate(MyUser user) {
		int result=0;
	     String id=user.getId();
	     if(id==null||id.equals("")){  //ID为空,则为新增加
	    	   String newId=UUIDGenerator.generate20UUID(); //主键生成
	    	   user.setId(newId);
	    	 sqlClient.insert("mapper.MyUserMapper.insertMyUser", user);  
	    	 result=1;
	     }else if(id!=null||!id.equals("")){ //ID不为空,则修改
	    	 sqlClient.update("mapper.MyUserMapper.updateMyUser", user);
	    	 result=2;
	     }
		return result;
	}
	
	/**
	 *根据ID删除数据 
	 */
	@Override
	public int deleteById(String id) {
		
		return sqlClient.delete("mapper.MyUserMapper.deleteById",id);
	}
	@Override
	public List<Map<String, Object>> slectByMap() {
		return sqlClient.queryForList("mapper.MyUserMapper.selectByMap");
	}

	     
	    
	    
	@Override
	public List<Map<String, String>> getMapBycardNumber(List<String> cardNumbers) {
		return sqlClient.queryForList("mapper.MyUserMapper.getMapBycardNumber", cardNumbers) ;
	}
	//批量删除 
	@Override
	public int deleteMyUserByIds(List<String> ids) {
		
		return sqlClient.delete("mapper.MyUserMapper.deleteIds",ids);
	}
	
	//批量 插入数据库
	public int insertMyUserByExcel(List<MyUser> users){
		return sqlClient.insert("mapper.MyUserMapper.insertMyUserByExcel", users);
		
		
		
	}
	@Override
	public int checkUser(String userName) {
		
		return sqlClient.queryForObject("mapper.MyUserMapper.checkUser", userName) ;
	}
	@Override
	public int checkUserAndEmail(Map<String, String> param) {
		 return sqlClient.queryForObject("mapper.MyUserMapper.checkUserAndEmail", param);
	}
	@Override
	public Integer changePassword(Map<String, Object> param) {
		return sqlClient.update("mapper.MyUserMapper.changePassword", param);
	}




}
