<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.javaex.vo.UserVo" %>

<%
	UserVo authUser = (UserVo)session.getAttribute("authUser"); //(30)임포트도 해줘! 그래도 오류가 뜰거야 (31)(UserVo)를 넣어주자
	//메인화면에 로그인 하면 로그인 한사람의 이름이 뜨게하고싶어!(32) 황일영님 안녕하세요로 가봐 authUser.getName()를 넣어줬어 지금은 님 안녕하세요^^야
	//userController로 로그인 실패로가자(33)
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css"> 
<link href="/mysite2/assets/css/main.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header">
			<h1><a href="">MySite</a></h1>
			
			<!-- 로그인 실패시 --> <%//(29) %>
			<%if(authUser == null){ %>
			 
			<ul>
				<li><a href="/mysite2/user?action=loginForm">로그인</a></li>
				<li><a href="/mysite2/user?action=joinForm">회원가입</a></li>
			</ul>
			<%}else { %>
			
			<!-- 로그인 성공시 -->
			<!--  -->
			<ul>
				<li><%=authUser.getName() %>님 안녕하세요^^</li>
				<li><a href="/mysite2/user?action=logout">로그아웃</a></li>
				<li><a href="">회원정보수정</a></li>
			</ul>
			<%} %>
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<!-- aside없음 -->

		<div id="full-content">
		
			<!-- content-head 없음 -->

			<div id="index"> 
			
				<img id="profile-img" src="/mysite2/assets/image/profile.jpg">
				
				<div id="greetings">
					<p class="text-xlarge">
						<span class="bold">안녕하세요!!<br>
						박지민의 MySite에 오신 것을 환영합니다.<br>
						<br>
						이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.<br>
						</span>
						<br>
						사이트 소개, 회원가입, 방명록, 게시판으로 구성되어 있으며<br>
						jsp&serlvet(모델2) 방식으로 제작되었습니다.<br>
						<br>
						자바 수업 + 데이터베이스 수업 + 웹프로그래밍 수업<br>
						배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.<br>
						<br>
						(자유롭게 꾸며보세요!!)<br>
						<br><br>
						<a class="" href="">[방명록에 글 남기기]</a>
					</p>	
				</div>
				<!-- //greetings -->
				
				<div class="clear"></div>
				
			</div>
			<!-- //index -->
			
		</div>
		<!-- //full-content -->
		<div class="clear"></div>
		
		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>
