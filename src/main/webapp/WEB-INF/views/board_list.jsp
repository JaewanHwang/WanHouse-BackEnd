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

	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('assets/img/home-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="site-heading">
						<h1>Happy House</h1>
						<span class="subheading">Kim Wansang / Hwang Jaewan</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<h3 class="mb-5">게시판</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>작성자</th>
							<th>제목</th>
							<th>작성날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${boardList}" var="board">
							<tr onClick="location.href='${root }/board/board_detail?no=${board.no }'">
								<td>${board.memberId}</td>
								<td>${board.title}</td>
								<td>${board.lastModified}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<button type="button" class="btn btn-primary"
					onclick="move('/board/board_form')">등록</button>
			</div>
		</div>
	</div>
	<!-- Footer-->
	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<!-- <script src="js/scripts.js"></script> -->
</body>
</html>
