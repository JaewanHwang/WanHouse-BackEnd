<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/views/settings.jsp"></jsp:include>
<script>
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
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
	<!-- Main Content-->
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="jumbotron">

					<h2 class="post-title">
						<a href="#!">SSAFY 고지서 신청하고 포인트 받으세요!</a>
					</h2>

					<div class="post-meta">
						<ul>
							<li>#100만원 당첨 기회</li>
							<li>#재산세 #주민세</li>
						</ul>
					</div>
				</div>
				<!-- Divider-->
				<hr class="my-4" />
				<!-- Post preview-->
				<div class="post-preview">
					<h2 class="post-title">
						<a href="#!">지혜롭게 내집 마련하기</a>
					</h2>
					<div class="post-meta">
						<ul>
							<li><a href="#!">가용자금 확인 및 대출 계획</a></li>
							<li><a href="#!">집 종류 및 지역 선택</a></li>
							<li><a href="#!">정보 수집 , 시세파악</a></li>
							<li><a href="#!">부동산 방문 , 집구경</a></li>
							<li><a href="#!">계약 및 잔금 치르기</a></li>
							<li><a href="#!">소유권 이전등기</a></li>
							<li><a href="#!">인테리어 공사</a></li>
						</ul>
					</div>
				</div>
				<!-- Divider-->
				<hr class="my-4" />
				<!-- Post preview-->
				<div class="post-preview">
					<h2 class="post-title">오늘의 뉴스</h2>
					<ul>
						<li><a href="#!">서울 입주 2년차 아파트 전셋값 1억 400만원 올라</a>한겨레</li>
						<li><a href="#!">1216 거래한파... 매수자가 사라졌다</a>아시아 경제</li>
						<li><a href="#!">재건축 대안 뜨는 수직증축 리모델링...추진 속..</a>동아일보</li>
						<li><a href="#!">고가기준 9억, 서울 아파트 중간값 됐다</a>동아일보</li>
					</ul>
				</div>
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
