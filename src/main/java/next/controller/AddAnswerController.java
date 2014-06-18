package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import core.mvc.Controller;
import core.mvc.ModalAndView;

public class AddAnswerController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AnswerDao answerDao = new AnswerDao();
		QuestionDao questionDao = new QuestionDao();
		long questionId = Long.parseLong(request.getParameter("questionId"));
		
		Answer answer = new Answer(
				request.getParameter("writer"),
				request.getParameter("contents"),
				questionId);
		
		questionDao.updateCountOfComment(questionId);
		answerDao.insert(answer);
		
		// TODO Auto-generated method stub
		return "api";
	}

}
