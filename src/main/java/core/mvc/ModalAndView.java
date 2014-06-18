package core.mvc;

public class ModalAndView {
	
	Object modal;
	String view;
	
	public ModalAndView(String view) {
		this(view, null);
	}
	
	public ModalAndView(String view, Object modal) {
		this.view = view;
		this.modal = modal;
	}
	
	public Object getModal() {
		return modal;
	}
	public void setModal(Object modal) {
		this.modal = modal;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
	
	
}
