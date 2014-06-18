package next.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import next.support.gson.GSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;

public class ApiListController implements Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiListController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		logger.debug("frame");
		QuestionDao questionDao = new QuestionDao();
		List<Question> questions = (List<Question>) questionDao.findAll();
	
		
		PrintWriter out = null;

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=UTF-8");

		try {
			out = response.getWriter();
			out.println(GSON.toJsonString(questions));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		finally {
			// out.close();
		}
		
		return "api";
	}
	

}
