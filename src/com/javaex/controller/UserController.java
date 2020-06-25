package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //post로 왔을때 한글 깨지는거!
		String action = request.getParameter("action"); //action을 주는 이유는 회원가입 요청이들어와서
		
		if("joinForm".equals(action)) { //joinForm 화면을 보여주기 위해서 만들었어 회원가입폼
			System.out.println("joinForm"); //주소체계는 mysite2/user?action=joinForm 화면에치는링크 요청을 받았다면 콘솔에joinForm뜸!
		
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinForm.jsp"); //포워드하기 user joinform을 화면에 보여줄거야
			//화면이 깨진 이유는 css image가 없어서~
		}else if("join".equals(action)) { //회원가입 (14) //joinForm으로가 user에 링크를 걸거야! join user맨 아래에 <input type="text" name="action" value = "join">을줬어 (15)보고 그후는 동영상보기! (16)동영상
			//name값에 값을 넣어줘!!!! 그래야만 입력할수있어 아래 String id = request.getParameter("id"); 이거야! 그후 index.jsp에 꼭 링크넣어줘!
			System.out.println("join");
			
			String id = request.getParameter("id"); //(17) get으로 했기때문에 링크에 id pw가 뜰거야 그걸 이 파라미터에 옮기는거야 파라미터꺼내기!!! joinForm.jsp에다 준 name을 비교하는거야!!!! 둘이 틀렸나 안틀렸나 잘봐
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo vo = new UserVo(id, password, name, gender);
			System.out.println(vo.toString()); // ('17)db에 던지기 전에 vo로 테스트하기 no는 null이 맞아 db에 안들어갔으니까 상황 던지기직전~
			
			
			UserDao userDao = new UserDao(); //(18)db에 들어가있나 확인하기!
			userDao.insert(vo); //user에 joinOk만들자! join html을 잘 붙여서 링크달아줘!
			
			WebUtil.forword(request, response, "/WEB-INF/views/user/joinOk.jsp"); //joinOk페이지 잘나온느지 해보자 회원가입축하합니다 뜰거야 링크오타조심혀 loginform만들거야! user에 loginform 폼 만들고 아까랑 독같아 html,링크바꿔주기
		
		}else if("loginForm".equals(action)) { //로그인폼!
			System.out.println("loginForm");
			
			WebUtil.forword(request, response, "/WEB-INF/views/user/loginForm.jsp"); //로그인폼 뜨게하기 <li><a href="/mysite2/user?action=login">로그인</a></li> index.jsp에 꼬옥 넣어주기
				
		}else if("login".equals(action)) { ////(20)loginForm <input type="text" name="action" value = "login">을 주자 name=id, pw 주자!
			System.out.println("login");
			
			String id = request.getParameter("id"); //(21) 파라미터 꼭 주자~! loginForm애 name pw에다 담자~~ 원래는 UserVo생성자를 만들어야하지만 ! 생성자 안만들면 set으로 하면돼!! 그냥 생성자 안만들고가보기
			String password = request.getParameter("password");
			
			UserDao userDao = new UserDao();
			UserVo authVo = userDao.getUser(id, password); //생성자 안만들고 이렇게 해보고 싶어 로그인 사용자를 알고싶다 UserDao로가자 (22)
		
			//System.out.println(authVo.toString()); //(24)hi하고 1234의 정보를 넣은 사람이 올거야!
			//아래 "로그인실패" 에 오류주석이 뜨는 이유는 값이 null인데 toString하라고 해서 그런거니까 실험해보고 닫아!
			
			
			if(authVo == null) {
				System.out.println("로그인실패");
				WebUtil.redirect(request, response, "/mysite2/user?action=loginForm&result=fail"); //(33) result=fail을 써준 이유는 로그인 실패한앤지 여부를 알기위해서 loginForm으로가자(34)
			}else {
			
			//로그인 성공일때
			//세션영역에 값을 추가 (27)
				HttpSession session = request.getSession();
				session.setAttribute("authUser", authVo); //꺼내야하니까 ""에는 별명 넣어주기 authVo에는 no,name이있어! db select no name말하는거야 메인 컨트롤러로 가자(28)
			
			
			
				WebUtil.redirect(request, response, "/mysite2/main"); //(25)메인을 다시 불러오는거니까 메인 사이트는 이미 만들어져있지? 내부에서 불러오는 거니까 리다이렉트! 지금은 로그인이 되든 안되든 메인이 뜰거야 (26)동영상!
			}
			//tomcat이 얘가 ok요청을 받았던앤지 몰라 로그인 후에는 정보를 계속 갖고있어야하고 로그인이 되어있다고 화면에 떠야해 그래서 세션을 써야해(27)
		
		}else if("logout".equals(action)) { //로그아웃일때(36)
				HttpSession session = request.getSession();
				session.removeAttribute("authUser");
				session.invalidate(); //로그인한 정보가 사라졌을거야 
				
				WebUtil.redirect(request, response, "/mysite2/main"); //index.jsp로 돌아가서 <li><a href="/mysite2/user?action=logout">로그아웃</a></li> 써주자
				
				
			}
			
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
