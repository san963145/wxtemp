package questionnaire;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
        ServletContext application=(ServletContext) request.getServletContext();
        Map<String,String> xmlMap=ReceiveParser.parse(request, response);
        String toUserName=xmlMap.get("toUserName");
        String fromUserName=xmlMap.get("fromUserName");
        String content=xmlMap.get("content");
        String paperID=(String)application.getAttribute("paperID");
        if(paperID==null){
        	List<String> userIDList=new ArrayList<String>();   //////////
        	if(userIDList.contains(content)){
        		String title="Login";
             	String url="";
             	String replyContent="";
             	String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
             	response.getWriter().write(xml);
             	return ;
        	}else{
        		String replyContent="No papers online now!";
                String xml=ReplyContent.generateXML(fromUserName, toUserName,replyContent);
                response.getWriter().write(xml);
                return ;
        	}        	
        }else{
        	List<String> finishedOpenIDList=new ArrayList<String>();  ////////
        	if(finishedOpenIDList.contains(fromUserName)){
              String title="check your paper";
           	  String url="";
           	  String replyContent="";
           	  String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
           	  response.getWriter().write(xml);
           	  return ;
        	}else{
        		String title="sign your paper";
                String url="";
             	String replyContent="";
             	String xml=ReplyContent.generateXML(fromUserName, toUserName, title, replyContent, url);
             	response.getWriter().write(xml);
             	return ;
        	}
        }
	}

}
