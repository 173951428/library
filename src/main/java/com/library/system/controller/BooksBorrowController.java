package com.library.system.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.system.entity.BookManageEntity;
import com.library.system.entity.BooksBorrow;
import com.library.system.entity.BooksReturnEntity;
import com.library.system.service.IBooksBorrowServer;
import com.library.system.service.IBooksReturnServer;
import com.library.system.service.IBooksService;
// 图书借阅controller 
@Controller
@RequestMapping(value = "/booksBorrow")
public class BooksBorrowController {
    @Autowired
    IBooksBorrowServer booksBorrowServer;
    @Autowired
	IBooksService bookServer; 
    
    @Autowired
    IBooksReturnServer booksReturnServer; 
    /**
     * 图书借阅方法 ,根据图书的id借阅书籍,逻辑为在图书借阅表添加一条数据,同时,该id的书籍减少一
     * @return
     */
  	@RequestMapping(value="borrowById")
  	@ResponseBody
    public Integer BooksBorrowByBooksId(@RequestBody Map<Object, Object> param,HttpServletRequest request){
  		  Integer result;
  		  Integer booksId=Integer.valueOf(param.get("booksId").toString());
  		  String userId=(String) request.getSession().getAttribute("userId");
  		  // String userId="402881e6754b8a740000";
  		  // 借书之前先判断,该用户是否存在5本以上,未归还的数据,如果有,就不能继续 在借书
  		   Integer count=booksBorrowServer.selectCountByStatus(userId);
  		   if(count>=5){
  			  result=2;
  		  }else{ 
  			 Map<String,Object>  bookMap=  bookServer.selectById(booksId);
  			  if((Integer.valueOf( bookMap.get("bookCount").toString())>0)){   //有库存
  				 /******************借书表增加数据******************************/
  				  BooksBorrow b=new BooksBorrow();
  				  b.setUserBorrowId(userId);
  				  b.setBookId(booksId);
  				  b.setBorrowDate(new Date());
  				  b.setBookStatusId(1); //默认设置为未归还状态 
  				  b.setBookTypeId(Integer.valueOf( bookMap.get("bookTypeId").toString()));
  				  Calendar c = Calendar.getInstance();  
  				  c.add(Calendar.DAY_OF_MONTH, 60);
  				  b.setReturnTime(c.getTime());// 借阅一本书,默认归还时间在当前时间上加60天 .
  				  booksBorrowServer.saveOrUpdate(b);  //借书表增加一条数据 
  				  
  				  Integer borrowId=b.getId();
  				
  				  /*********************还书表增加数据 ***************************/

  				  
  				  BooksReturnEntity bre=new BooksReturnEntity();
  				  bre.setBookBorrowId(borrowId);
  				  bre.setUserBorrowId(userId);
  				  bre.setBookId(booksId);
  				  bre.setBorrowDate(new Date());
  				  bre.setBookStatusId(1);
  				  bre.setBookTypeId(Integer.valueOf( bookMap.get("bookTypeId").toString()));
  				  booksReturnServer.saveOrUpdate(bre);
  				 /*********************修改图书个数 ***************************/
  				  Integer newBookCount=Integer.valueOf( bookMap.get("bookCount").toString())-1; //同时库存数量减1
  				  Integer newBookOutSum=Integer.valueOf( bookMap.get("bookOutSum").toString())+1;//同时借出数量加1
  				  BookManageEntity bookManageEntity=new BookManageEntity();
  				  bookManageEntity.setId(booksId);
  				  bookManageEntity.setBookCount(newBookCount);
  				  bookManageEntity.setBookOutSum(newBookOutSum);
  				  bookServer.saveOrUpdate(bookManageEntity);
  				  result=1;
  			  }else{
  				  result=0; //没库存 
  			  }
  		  }
  		
  		   return result;
  		  
		
    }
  	
  	 /**
  	  * 续借 
  	  * @param param
  	  * @return
  	  * @throws ParseException
  	  */
	@RequestMapping(value="xujie")
  	@ResponseBody
    public Integer xujie(@RequestBody Map<Object, Object> param) throws ParseException{
		  Integer result=0;
		  Integer borrowId=Integer.valueOf(param.get("id").toString()); // 借书表的id 
		String returnDateParam= param.get("returnTime").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date returnDate=sdf.parse(returnDateParam);
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(returnDate);
		 calendar.add(Calendar.DATE, 30); // 追加30天 
		 Date updateDate=calendar.getTime();  // 得到新的天数 
		 
		 try {
			 BooksBorrow b=new BooksBorrow(); // 借书对象
			 b.setReturnTime(updateDate);
			 b.setBookStatusId(3);
			 b.setId(borrowId);
			 booksBorrowServer.saveOrUpdate(b); 
			 
			 BooksReturnEntity booksReturnEntity=	 booksReturnServer.selectByBorrowId(borrowId);
		     //更改还书对象 
			 booksReturnEntity.setBookStatusId(3); // 把还书对象改为续借
			 booksReturnServer.saveOrUpdate(booksReturnEntity);
			  
			  result=1;
		} catch (Exception e) {
			  
			e.printStackTrace();
		}
		 
		
		return result;
	}
	  
	/**
	 * 判断是否逾期 
	 * @return
	 */
	@RequestMapping(value="selectReturnTime")
  	@ResponseBody
	public Integer selectReturnTime( @RequestBody Map<Object, Object> param){
		  Integer result=0;
		  Integer borrowId=Integer.valueOf(param.get("id").toString()); // 借书表的id 
		  Date returnTime=  booksBorrowServer.selectReturnTime(borrowId);
		   
		   if(new Date().before(returnTime)){
			   // 如果当前时间,小于应该归还时间,说明没有逾期  
			     //System.out.println("没有逾期 ");
			   result=1;
		   }else{
			   result=2;
		   }

		return result;
	}
	
	
	@RequestMapping(value="returnBook")
  	@ResponseBody
	public Integer returnBook( @RequestBody Map<Object, Object> param){
		  Integer result=0;
		  Integer borrowId=Integer.valueOf(param.get("borrowId").toString()); // 借书表的id 
		  Integer commitStatusId=Integer.valueOf(param.get("commitStatusId").toString()); // 还书状态的id 
		try {
			Map<String,Object> borrowMap=new HashMap<String, Object>();
			borrowMap.put("id", borrowId);
			borrowMap.put("bookStatusId", commitStatusId);
			booksBorrowServer.updateStatusById(borrowMap);

			Map<String,Object> returnMap=new HashMap<String, Object>();
			returnMap.put("bookStatusId", commitStatusId);
			returnMap.put("bookBorrowId", borrowId);
			returnMap.put("returnDateInfo", new Date());
			booksReturnServer.updateStatusByBorrowId(returnMap);
			
			/*最后把书籍加1*/
		   BooksBorrow bw=	booksBorrowServer.selectBookId(borrowId);
		   Integer bookId=bw.getBookId();
		   Map<String,Object>  bookMap=  bookServer.selectById(bookId);
		   Integer newBookCount=Integer.valueOf( bookMap.get("bookCount").toString())+1; //同时库存数量加1
		   Integer newBookOutSum=Integer.valueOf( bookMap.get("bookOutSum").toString())-1;//同时借出数量减1
		   BookManageEntity bookManageEntity=new BookManageEntity();
		   bookManageEntity.setId(bookId);
		   bookManageEntity.setBookCount(newBookCount);
		   bookManageEntity.setBookOutSum(newBookOutSum);
		   bookServer.saveOrUpdate(bookManageEntity);
			result=1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
}
