package com.library.system.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.library.system.entity.BookManageEntity;
import com.library.system.entity.BookStatus;
import com.library.system.entity.BooksTypeEntity;
import com.library.system.service.IBookStatus;
import com.library.system.service.IBooksService;
import com.library.system.service.IBooksTypeServer;


// 图书控制类
@Controller
@RequestMapping(value = "/books")
public class BooksController {
	@Autowired
	IBooksService bookServer;
	@Autowired
	IBooksTypeServer booksTypeServer;
    
	@Autowired
	IBookStatus bookStatus;
	
	//查询图书类型
	@RequestMapping(value="selectAllBooksType")
	@ResponseBody
	public List<BooksTypeEntity> selectAllBooksType(){	 
		  List<BooksTypeEntity> booksList=booksTypeServer.selectAll();
		  return booksList;
	}
	
	//查询图书状态
     @RequestMapping(value="selectAllBooksStautus")
     @ResponseBody
	public List<BookStatus> selectAllStatus(){
		  return  bookStatus.selectAll();
	  }
	 
	@RequestMapping(value="selectBooksTypeById")
	@ResponseBody
	public String selectBooksTypeById(@RequestBody Map<Object, Object> param){	 
		Integer id=Integer.valueOf(param.get("id").toString()) ;
		  return booksTypeServer.selectById(id);
		
	}
	
	
	//新书推荐,查询新书前10条
	@RequestMapping(value="NewbooksRecommendation")
	@ResponseBody
	public List<Map<String, Object>> NewbooksRecommendation(){	 
		  List<Map<String, Object>> booksList=bookServer.selectNewTopSeven();
		  return booksList;
	}
	
	
	//根据借阅次数,现实前8个图书
		@RequestMapping(value="selectToEight")
		@ResponseBody
		public List<Map<String, Object>> selectToEight(){	 
			  List<Map<String, Object>> booksList=bookServer.selectToEight();
			  return booksList;
		}
	
	//根据id查询图书
	@RequestMapping(value="/selectById",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> selectBookById(@RequestBody Map<Object, Object> param){
	
		Integer id=Integer.valueOf(param.get("id").toString()) ;
		return bookServer.selectById(id);
		
	}
	//增加或者修改
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Integer insertOrUpdateBook(@RequestBody  BookManageEntity bookManageEntity){	 
		   return bookServer.saveOrUpdate(bookManageEntity);
		  
	}
	

	
	//根据id删除
		@RequestMapping(value = "/deleteById", method = RequestMethod.POST)
		@ResponseBody
		public Integer deleteById(@RequestBody Map<String, Object> param,HttpServletRequest request){	 
			Integer id=Integer.valueOf((String) param.get("id")) ;
			//删除书籍的时候,同时需要删除服务器对应的书籍图片
			Map<String, Object> result=bookServer.selectById(id);
			// 获取要删除图片的服务器完整保存路径
			String path=  request.getSession().getServletContext().getRealPath("/")+(String) result.get("bookImgUrl");
			 this.deleteFile(path);
			 return  bookServer.deleteById(id);
			
			  
		}
	//上传封面图,返回map集合
	@RequestMapping(value="uploadImg")
	@ResponseBody
	public Map<String,Object> uploadify(HttpServletRequest request,@RequestParam(value = "uploadFiles", required = false) MultipartFile[] file ){
	      
		   Map<String,Object>  result=new HashMap<String,Object>();
	      String savePath=request.getParameter("savePath");    // 获取前台uploadify带过来的参数
	      String filePath="";
	      ServletContext application = request.getSession().getServletContext();
	      String filePathStr = application.getRealPath("/") + "uploadFiles/";  // 获取系统路径加上个自定义保存文件的路径
	     if(savePath.equals("")||null==savePath){ //如果用户是第一次上传文件
	              if (file != null && file.length > 0 && file[0].getSize()>0) {
	                  filePath=this.uploadFile(file, filePathStr);
	                  filePath="uploadFiles/"+ filePath;   
	                  result.put("status",true);
	                  result.put("urlImg",filePath);	                           
	              }
	     }else if(!savePath.equals("")){  //如果用户多次操作上传文件功能,避免服务器资源占用过多,需要删除用户上一次上传保存到服务器的文件,在进行 保存
	         String deletePath = application.getRealPath("/") + savePath;
	         this.deleteFile(deletePath);
	         if (file != null && file.length > 0 && file[0].getSize()>0) {
	              filePath=this.uploadFile(file, filePathStr);
	              filePath="uploadFiles/"+ filePath; 	          
	              result.put("status",true);
                  result.put("urlImg",filePath);          
	          }
	     }
	         
	        return  result;
	    }
	
	
	
	 /** 
     * @author lisujie
    * 删除单个文件 
    * @param   sPath    被删除文件的文件名 
    * @return 单个文件删除成功返回true，否则返回false 
    */  

   private boolean deleteFile(String sPath) {  
       Boolean flag = false;  
       File file = new File(sPath);  
       // 路径为文件且不为空则进行删除  
       if (file.isFile() && file.exists()) {  
           file.delete();  
           flag = true;  
       }  
       return flag;  
   }  
   
   /**
    * 文件上传
    * 
    * @param files
    *            要上传的文件
    * @param fileUrl
    *            文件上传路径
    * @author lisujie           
    */
   public String uploadFile(MultipartFile[] files, String imageUrl) {
      
       String imageName = null;
       
       for (int i = 0; i < files.length; i++) {
          
           if (!files[i].isEmpty()) {
               try {
                   // 检验文件夹是否存在，不存在 就创建
                 //  FileUtil.makeDir(SystemConstant.BASE_IMAGE_ADDRESS + imageUrl);
                   // 文件后缀名
                   String suffix = files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".") + 1, files[i].getOriginalFilename().length());
                   // 拿到输出流，同时重命名上传的文件
//                   String imageName = new Date().getTime() + files[i].getOriginalFilename();
                   imageName = UUID.randomUUID().toString() + "." + suffix;
                   FileOutputStream os = new FileOutputStream(imageUrl + imageName);

                   // 拿到上传文件的输入流
                   InputStream in = files[i].getInputStream();
                   // 以写字节的方式写文件
                   int len = 0;
                   byte[] bb = new byte[4096];
                   while ((len = in.read(bb)) != -1) {
                       os.write(bb, 0, len);
                   }
                   os.flush();
                   os.close();
                   in.close();
           
               } catch (Exception e) {
                   e.printStackTrace();
                  
               }
           }
       }
       return imageName;
   }
   
   
      
}
