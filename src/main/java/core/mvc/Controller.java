package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
//	ModalAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
