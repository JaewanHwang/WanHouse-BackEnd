<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/settings.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="text-center">
					<div class="form-signin">
						<form style="margin-top: 30px" method="post" action="${root }/login.do">
							<svg style="margin-bottom: 20px"
								xmlns="http://www.w3.org/2000/svg" width="50" height="50"
								fill="currentColor" class="bi bi-person-circle"
								viewBox="0 0 16 16">
									<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
									<path fill-rule="evenodd"
									d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
								</svg>

							<h1 class="h3 mb-3 fw-normal">Please sign in</h1>


							<div class="form-floating">
								<input type="text" class="form-control" id="id" name="id"
									placeholder="ID" /> <label for="id">ID</label>
							</div>
							<div class="form-floating">
								<input type="password" class="form-control" id="pw"
									placeholder="Password" name="pw" /> <label for="pw">Password</label>
							</div>

							<div class="checkbox mb-3">
								<label> <input type="checkbox" value="remember-me" />
									Remember me
								</label>
							</div>


							<button class="w-100 btn btn-lg btn-primary"
								style="margin-bottom: 20px" type="submit" id="login">로그인</button>

							<div>
								<c:if test="${errorMsg != null}">
									<div style="color: red">에러내용 : ${errorMsg}</div>
								</c:if>
							</div>

						</form>
					</div>
				</div>
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
