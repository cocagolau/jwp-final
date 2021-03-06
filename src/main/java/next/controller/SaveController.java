package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;
import core.mvc.Controller;
import core.mvc.ModalAndView;

public class SaveController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		
		Question question = new Question(request.getParameter("writer"), request.getParameter("title"), request.getParameter("contents"));
		questionDao.insert(question);
		
		return "redirect:/list.next";
	}
	
}
