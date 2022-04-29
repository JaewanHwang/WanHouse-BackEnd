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

	<!-- 구글맵 -->
	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-4">
				<h3>실거래가 상세 조회</h3>
				<h5>${aptName }</h5>
				
				<div>
					<c:forEach var="houseDeal" items="${houseDealList }">
						<ul>
							<li>거래금액: ${houseDeal.dealAmount }만원</li>
							<li>거래년도: ${houseDeal.dealYear }년</li>
							<li>전용면적: ${houseDeal.area }제곱미터</li>
							<li>층: ${houseDeal.floor }층</li>
						</ul>
						<br />
					</c:forEach>
				</div>
			</div>

			<div class="col-md-8">
				<div id="mapDetail" style="width: 100%; height: 500px">
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3ad87771f028439dcd62f33a2627643b"></script>
					<script>
						var container = document.getElementById('mapDetail');
						
						var lat = "<c:out value='${lat}' />"
						var lng = "<c:out value='${lng}' />"
						
						var options = {
							center : new kakao.maps.LatLng(lat, lng),
							level : 3
						};
						var map = new kakao.maps.Map(container, options);
						
						var markerPosition  = new kakao.maps.LatLng(lat, lng);
						
						var marker = new kakao.maps.Marker({
						    position: markerPosition
						});
						
						marker.setMap(map);
						
					</script>
				</div>
			</div>
		</div>
	</div>
	<!-- 구글맵 end -->

	<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<!-- <script src="js/scripts.js"></script> -->
</body>
</html>
