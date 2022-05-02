<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/settings.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<h2 class="mb-5">게시판</h2>
				<div>
					<h3>제목</h3>
					<div class="my-3 p-3" style="border: 1px solid black">${board.title}</div>
					<h3>내용</h3>
					<div class="my-3 p-3"
						style="border: 1px solid black; height: 300px">${board.content}</div>
					<h3>작성자</h3>
					<div class="my-3 p-3" style="border: 1px solid black">${board.memberId}</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<c:if test="${member.id eq board.memberId }">
					<span>
						<button type="button" class="btn btn-primary"
							onclick="move('/board_modify_form?no=${board.no }&title=${board.title}&content=${board.content}&memberId=${board.memberId}')">수정</button>
						<button type="button" class="btn btn-primary"
							onclick="move('/board_delete?no=${board.no }')">삭제</button>
					</span>
				</c:if>
				<span>
					<button type="button" style="float: right" class="btn btn-primary"
						onclick="move('/board_list')">이전</button>
				</span>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<!-- <script src="js/scripts.js"></script> -->
</body>
</html>
