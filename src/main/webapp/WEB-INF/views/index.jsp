<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>스프링 기초 학습 중</h1>
  <ul>
      <li>
          <a href="score/page">성적 관리</a>
      </li>
      <li>
          <a href="/board/list">게시판 관리</a>
      </li>
  </ul>

  <script>
    fetch('/api/v1/boards')
    .then(res => res.json())
    .then(data => {
      console.log(data);
    }) 
  </script>
</body>
</html>
