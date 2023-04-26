<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@ include file="meta.jsp" %>
    <title>게시글 보기</title>
</head>
<body>
<%@ include file="header.jsp" %>
    <div class="container">
        <h2>게시판</h2>
            <h3>${board.title}</h3>
            <p>${board.content}</p>
            <p>${board.author}</p>
            <p>${board.created_at}</p>
        <c:if test="${sessionScope.username!=null}">
            <a href="edit.jsp?id=${board.id}>" class="btn btn-primary">수정</a>
            <button class="btn btn-danger" onClick="deleteBoard()">삭제</button>
        </c:if>
    </div>
    <script>
        function deleteBoard() {
            if(confirm("삭제하시겠습니까?")) {
                location.href = "delete.jsp?id=${board.id}";
            }
        }
    </script>
<%@ include file="footer.jsp" %>
</body>
</html>

