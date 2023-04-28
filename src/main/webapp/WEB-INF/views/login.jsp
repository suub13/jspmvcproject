<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
        rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
        crossorigin="anonymous">
</head>
<body>
<%@ include file="header.jsp"%>
    <div class="container">
        <h1>로그인</h1>
        <form action="login" method="post">
            <label for="username">아이디</label>
            <input type="text" id="username" name="username" placeholder="아이디를 입력하세요." required> <br>
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required> <br>
            <button type="submit" class="btn btn-primary">로그인</button>
        </form>
        <c:choose>
            <c:when test="${param.error==1}">
                <div class="alert alert-danger" role="alert">
                    사용자를 찾을 수 없습니다. 아이디와 비밀번호를 다시 입력하세요.
                </div>
            </c:when>
            <c:when test="${param.error==2}">
                <div class="alert alert-danger" role="alert">
                    비밀번호가 틀렸습니다. 아이디와 비밀번호를 다시 입력하세요.
                </div>
            </c:when>
        </c:choose>
    </div>
<%@ include file="footer.jsp" %>
</body>
</html>