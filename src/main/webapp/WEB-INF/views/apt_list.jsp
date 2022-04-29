<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/settings.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/header.jsp"></jsp:include>

	<!-- Main Content-->
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<form class="" style="display: flex; justify-content: center">
					<select name="" id="" style="margin-right: 5px; width: 200px">
						<option value="">도/광역시</option>
						<option value="경기도" selected="selected">경기도</option>
					</select> <select name="" id="" style="margin-right: 5px; width: 200px">
						<option value="">시/구/군</option>
						<option value="김포시" selected="selected">김포시</option>
					</select> <select name="" id="" style="margin-right: 5px; width: 200px">
						<option value="">동</option>
						<option value="걸포동" selected="selected">걸포동</option>
					</select>
				</form>
			</div>
		</div>
	</div>



	<div class="container px-4 px-lg-5 mt-3 mb-3">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-4">
				<div>
					<form method="post" action="${root }/apt_list.do">
						동 코드 입력 : <input type="text" id="dongcode" name="dongcode" /> <br />
						<input type="submit" value="제출" />
					</form>
				</div>

				<ul>
					<c:forEach var="houseInfo" items="${houseInfoList }">
						<li><a
							href="${root }/apt_detail.do?aptcode=${houseInfo.aptCode }">${houseInfo.aptName }</a></li>
					</c:forEach>
				</ul>
			</div>


			<!-- 카카오맵 -->
			<div class="col-md-8">
				<div id="map" style="width: 100%; height: 500px">
					<script type="text/javascript"
						src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3ad87771f028439dcd62f33a2627643b"></script>
					<script>
						var container = document.getElementById('map');
						var options = {
							center : new kakao.maps.LatLng(37.06262829792641,
									127.59690310051316),
							level : 12,
						};
						var map = new kakao.maps.Map(container, options);

						// houseInfoList 가져와서 markerPosition들을 배열에 담고, 중심 좌표 계산

						// houseInfoList가 존재하면
						if ( ${not empty(houseInfoList) }) {
							// 집 위치를 받아와서 마커 위치를 생성, 모든 마커들의 중심 좌표 계산하기 위해 sum 값도 계산
							var sumLat = 0, sumLng = 0, cnt = 0;
							<c:forEach items="${houseInfoList}" var="houseInfo">
							cnt++;
							var lat = Number("${houseInfo.lat}");
							var lng = Number("${houseInfo.lng}");
							var markerPosition = new kakao.maps.LatLng(lat, lng);

							sumLat += lat;
							sumLng += lng;

							// 마커 위치에 마커를 찍기
							var marker = new kakao.maps.Marker({
								position : markerPosition
							});

							marker.setMap(map);
	
							</c:forEach>

							
							// 지도 중심과 레벨 변경
							var centerLat = sumLat / cnt;
							var centerLng = sumLng / cnt;

							var center = new kakao.maps.LatLng(centerLat, centerLng);
							map.setCenter(center);
							map.setLevel(5);

						}

						// 맵 중심, 레벨 이동 이벤트
						kakao.maps.event.addListener(map, 'center_changed',
								function() {
									var level = map.getLevel();

									var latlng = map.getCenter();

									var message = '<p>지도 레벨은 ' + level
											+ ' 이고</p>';
									message += '<p>중심 좌표는 위도 '
											+ latlng.getLat() + ', 경도 '
											+ latlng.getLng() + '입니다</p>';

									console.log(message);
								});

						// 맵 설정
					</script>
				</div>
			</div>
		</div>
	</div>
	<!-- 카카오맵 end -->
	<!-- Footer-->
	<jsp:include page="/footer.jsp"></jsp:include>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<!-- <script src="js/scripts.js"></script> -->
</body>
</html>
