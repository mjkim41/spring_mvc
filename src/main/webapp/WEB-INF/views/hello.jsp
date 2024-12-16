<!-- 설정(톱니바퀴) -> 코드 조각 -> 새 코드 조각 파일 -> 
 html.json -> 아래 코드 복사 -> html 파일로 돌아가서 !jsp 탭탭 -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>Hello Spring JSP!!</h1>

  <script>
    const $h1 = document.querySelector('h1');

    fetch('/products/1')
      .then(res => res.json())
      .then(data => {
        console.log(data);
        $h1.textContent = data.name;
      });
  </script>
</body>

</html>