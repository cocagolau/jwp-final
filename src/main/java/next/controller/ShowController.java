package next.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import core.mvc.Controller;
import core.mvc.ModalAndView;

public class ShowController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long questionId = Long.parseLong(request.getParameter("questionId"));
		
		QuestionDao questionDao = new QuestionDao();
		AnswerDao answerDao = new AnswerDao();
		Question question;
		List<Answer> answers;
		
		question = (Question) questionDao.findById(questionId);
		answers = (List<Answer>) answerDao.findAllByQuestionId(questionId);
		request.setAttribute("question", question);
		request.setAttribute("answers", answers);
		return "show.jsp";
	}
	
}
