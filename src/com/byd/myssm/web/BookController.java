package com.byd.myssm.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.byd.myssm.dto.AppointExecution;
import com.byd.myssm.dto.Result;
import com.byd.myssm.entity.Book;
import com.byd.myssm.service.BookService;

@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分
public class BookController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(Model model) {
		logger.info("---->BookController list");
		return "book/list";
	}

	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	@ResponseBody
	private String detail(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
//		Book book = bookService.getById(bookId);
//		if (book == null) {
//			return "forward:/book/list";
//		}
//		model.addAttribute("book", book);
		return "detail";
	}

	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {"application/json; charset=utf-8" })
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @Param("studentId") Long studentId) {
		if (studentId == null || studentId.equals("")) {
			return new Result<>(false, "学号不能为空");
		}
		//AppointExecution execution = bookService.appoint(bookId, studentId);
		//return new Result<AppointExecution>(true, execution);
		return null;
	}
	
	@RequestMapping(value = "/getBooKList", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String getBooKList(@Param("sort") String sort,@Param("order") String order,@Param("offset") int offset,@Param("limit") int limit) throws IOException{				
		//sort = new String(sort.getBytes("ISO-8859-1"), "UTF-8");
		//order = new String(order.getBytes("ISO-8859-1"), "UTF-8");
		//offset = Integer.parseInt(new String(offset.getBytes("ISO-8859-1"), "UTF-8"));
		//limit = new String(limit.getBytes("ISO-8859-1"), "UTF-8");
		
		logger.info("---->BookController::getBooKList sort = " + sort + ";order = " + order + ";offset = " + offset + ";limit = " + limit);
		List<Book> list = new ArrayList<Book>();
		list = bookService.getList(sort,order,offset,limit);
		int total = bookService.getTotalCount();
		return new Result<List<Book>>(true,list,total).toJsonString();
	}
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String updateBook(
			@Param("bookId") Long bookId, 
			@Param("name") String name, 
			@Param("price") String price){
		logger.info("---->BookController::updateBook bookId = " + bookId);
		Book book = new Book();
		book.setBookId(bookId);
		book.setName(name);
		book.setPrice(price);
		bookService.updateBook(book);
		return "";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	private String test(@Param("bookId") String bookId) throws IOException{				
		List<Book> list = new ArrayList<Book>();
		list = bookService.getList("desc","name",0,15);
		bookId = new String(bookId.getBytes("ISO-8859-1"), "UTF-8");
		logger.info("---->BookController::test bookId =  " + bookId);
		return new Result<List<Book>>(true,list,bookId + "操作成功！").toString();
		//return new Result<List<Book>>(false,bookId + "操作失败！").toString();
	}

}
