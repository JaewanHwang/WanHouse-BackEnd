<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/settings.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="text-center">
					<div class="form-signin">
						<form style="margin-top: 30px" method="post" action="${root }/user/register">
							<svg style="margin-bottom: 20px"
								xmlns="http://www.w3.org/2000/svg" width="50" height="50"
								fill="currentColor" class="bi bi-person-circle"
								viewBox="0 0 16 16">
                  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                  <path fill-rule="evenodd"
									d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
                </svg>

							<h1 class="h3 mb-3 fw-normal">Please Sign Up</h1>

							<div class="form-floating">
								<input type="text" class="form-control" id="id" name="id"
									placeholder="ID" required="required" /> <label for="id">ID</label>
							</div>
							<div class="form-floating">
								<input type="password" class="form-control" id="pw" name="pw"
									placeholder="Password" required="required" /> <label for="pw">Password</label>
							</div>
							<div class="form-floating">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="Name" required="required" /> <label for="name">Name</label>
							</div>
							<div class="form-floating">
								<input type="text" class="form-control" id="addr" name="addr"
									placeholder="Address" required="required" /> <label for="addr">Address</label>
							</div>
							<div class="form-floating">
								<input type="text" class="form-control" id="phone" name="phone"
									placeholder="Phone" required="required" /> <label for="phone">Phone</label>
							</div>

							<div class="checkbox mb-3"></div>
							<button class="w-100 btn btn-lg btn-primary"
								style="margin-bottom: 20px" type="submit" id="signUp"
								name="signUp">회원가입</button>
						</form>

						<div>
							<c:if test="${errorMsg != null}">
								<div style="color: red">에러내용 : ${errorMsg}</div>
							</c:if>
						</div>
					</div>
				</div>
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
