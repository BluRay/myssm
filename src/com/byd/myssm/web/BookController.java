package com.byd.myssm.web;

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
		//List<Book> list = bookService.getList();
		//model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		
		bookService.getList();
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
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	private String test(Model model){		
		Book book = new Book();
		book.setBookId(1);
		book.setName("TESTBOOK");
		//return new Result<Book>(true,book);
		logger.info(new Result<Book>(true,book).toString());
		model.addAttribute("book", book);
		//return new Result<Book>(true,book).toString();
		//return new Result<>(false, "学号不能为空");
		return "{\"employees\": [{ \"firstName\":\"Bill\" , \"lastName\":\"Gates\" }]}";
	}

}
