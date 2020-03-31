package glg203.sessions;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@Controller
@SessionAttributes({"dateAttribute"})
public class DemoSessionAttributeController {

	@GetMapping("/sessions/attr")
	@ResponseBody
	public String getDateSession(DateAttribute dateAttribute) {
		return dateAttribute.toString();
	}


	@GetMapping("/sessions/attr/clear")
	@ResponseBody
	public String clear(SessionStatus status) {
		status.setComplete();
		return "session terminée";
	}

	@GetMapping("/sessions/show")
		@ResponseBody
		public String show(HttpSession session) {
		StringBuilder build= new StringBuilder();
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements()) {
			build.append(names.nextElement());
			build.append(" ");
		}
		return build.toString();
	}
}
