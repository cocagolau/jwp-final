package next.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;
import core.mvc.FrontController;
import core.mvc.ModalAndView;

public class ApiListController implements Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiListController.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		QuestionDao questionDao = new QuestionDao();
		List<Question> questions = (List<Question>) questionDao.findAll();
		request.setAttribute("questions", questions);
		
		return "api";
	}
	

}
