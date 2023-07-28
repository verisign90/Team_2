package member.command;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.FindUserService;
import mvc.command.CommandHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/* findUser.do */
public class FindUserHandler implements CommandHandler  {
	
	FindUserService findUserService = new FindUserService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {// 요청방식이 get방식이면  FORM_VIEW
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {// 요청방식이 post방식이면 기능
			return processSubmit(request,response);  
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);//405
			return null;
		}
	
	}
	
	// 기능 
	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String tlno = request.getParameter("tlno");
		
		String result = findUserService.existTlnoCheck(tlno) ? "exist" : "not_exist";
        try {
        	String code = generateRandomNumber();
        	// JSON 형식으로 응답을 설정합니다.
        	JSONObject jsonResponse = new JSONObject();
        	jsonResponse.put("result", result);
        	jsonResponse.put("code", code);
        	
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	// JSON 형식으로 응답을 생성합니다.
        	response.getWriter().write(jsonResponse.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	// 폼 
	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return "";
	}
	
	
	
	public static String generateRandomNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            // 0부터 9까지의 난수를 생성하여 문자열에 추가합니다.
            int digit = random.nextInt(10);
            sb.append(digit);
        }

        return sb.toString();
    }
	
}



