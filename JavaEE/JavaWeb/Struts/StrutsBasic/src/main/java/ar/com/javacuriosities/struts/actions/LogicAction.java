package ar.com.javacuriosities.struts.actions;

import ar.com.javacuriosities.struts.model.User;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class LogicAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String> messageList = new ArrayList<>();

        messageList.add("Mensaje A");
        messageList.add("Mensaje B");
        messageList.add("Mensaje C");
        messageList.add("Mensaje D");
        request.setAttribute("simpleList", messageList);

        List<User> userList = new ArrayList<User>();

        userList.add(new User("Usuario 1", "http://javacuriosities.blogspot.com/"));
        userList.add(new User("Usuario 2", "http://javacuriosities.blogspot.com/"));
        userList.add(new User("Usuario 3", "http://javacuriosities.blogspot.com/"));
        userList.add(new User("Usuario 4", "http://javacuriosities.blogspot.com/"));

        request.setAttribute("userList", userList);

        User user = new User();
        user.setEmail("luisdebello.cursos@gmail.com");

        request.setAttribute("user", user);
        request.setAttribute("number", 100);
        request.setAttribute("email", "luisdebello.cursos@gmail.com");

        return mapping.findForward("logic");
    }
}
