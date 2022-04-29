<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/settings.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<h2 class="mb-5">게시판</h2>
				<form method="post" action="${root}/board_modify.do">
					<h3>제목</h3>
					<textarea id="title" name="title" class="col-md-12 my-3 p-3" style="border: 1px solid black"
						required="required">${title}</textarea>
					<h3>내용</h3>
					<textarea id="content" name="content" class="col-md-12 my-3 p-3"
						style="border: 1px solid black; height: 300px" required="required">${content}</textarea>
					<input type="hidden" id="memberId" name="memberId" value="${memberId}">
					<input type="hidden" id="no" name="no" value="${no}">
					<div>
						<button type="submit" class="btn btn-primary">수정완료</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/footer.jsp"></jsp:include>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<!-- <script src="js/scripts.js"></script> -->
</body>
</html>
