<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/views/settings.jsp"></jsp:include>
    <link rel="stylesheet" href="/css/apt.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
<body>
<div class="container">
    <section id="index_section">
        <div class="card col-sm-12 mt-1" style="min-height: 850px;">
            <div class="card-body">
                <div class="form-group form-inline justify-content-center">
                    <label class="mr-2" for="sido">시도 : </label>
                    <select class="form-select form-control" id="sido">
                        <option value="0">선택</option>
                    </select>
                    <label class="mr-2 ml-3" for="gugun">구군 : </label>
                    <select class="form-select form-control" id="gugun">
                        <option value="0">선택</option>
                    </select>
                    <label class="mr-2 ml-3" for="dong">읍면동 : </label>
                    <select class="form-select form-control" id="dong">
                        <option value="0">선택</option>
                    </select>

                    <label class="mr-2 ml-3" for="dong">정렬: </label>
                    <select class="form-select form-control" id="order">
                        <option value="aptName">아파트이름순</option>
                        <option value="dealAmount">거래금액순</option>
                        <option value="area">전용면적순</option>
                        <option value="buildYear">건축년도순</option>
                    </select>

                </div>
                <a data-toggle="collapse" href="#searchApt">상세 검색</a>
                <div class="collapse form-group form-inline justify-content-center" id="searchApt">

                    <label class="mr-2" for="area">전용 면적 : </label>
                    <select class="form-select form-control" id="area">
                        <c:forEach var="i" begin="0" end="200" step="10">
                            <option value="${i}">${i}&#13217 이상</option>
                        </c:forEach>
                    </select>
                    <label class="mr-2 ml-3" for="dealAmount">거래 금액 : </label>
                    <select class="form-select form-control" id="dealAmount">
                        <c:forEach var="i" begin="0" end="20" step="1">
                            <option value="${i}">${i}억원 이상</option>
                        </c:forEach>
                    </select>
                    <label class="mr-2 ml-3" for="buildYear">건축 년도: </label>
                    <select class="form-select form-control mr-5" id="buildYear">
                        <c:forEach var="i" begin="1990" end="2022" step="1">
                            <option value="${i}">${i}년 이상</option>
                        </c:forEach>
                    </select>
                    <button type="button" id="aptSearchBtn">해당 조건으로 검색</button>
                </div>

                <table class="table mt-2">
                    <colgroup>
                        <col width="100">
                        <col width="200">
                        <col width="*">
                        <col width="120">
                        <col width="100">
                        <col width="130">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>번호</th>
                        <th>아파트이름</th>
                        <th class="text-center">주소</th>
                        <th>건축연도</th>
                        <th>전용면적</th>
                        <th>최근거래금액</th>
                    </tr>
                    </thead>
                    <tbody id="searchResult"></tbody>
                </table>
                <div id="map" style="width:100%;height:500px;"></div>
                <script type="text/javascript" src="/js/map.js"></script>
                <script type="text/javascript">
                    let colorArr = ['table-primary', 'table-success', 'table-danger'];
                    $(document).ready(function () {
                        $.get(root + "/house/sido"
                            , function (data, status) {
                                $.each(data, function (index, vo) {
                                    $("#sido").append("<option value='" + vo.sidoCode + "'>" + vo.sidoName + "</option>");
                                });
                            }
                            , "json"
                        );
                    });
                    $(document).on("change", "#sido", function () {
                        $.get(root + "/house/gugun"
                            , {sido: $("#sido").val()}
                            , function (data, status) {
                                $("#gugun").empty();
                                $("#gugun").append('<option value="0">선택</option>');
                                $.each(data, function (index, vo) {
                                    $("#gugun").append("<option value='" + vo.gugunCode + "'>" + vo.gugunName + "</option>");
                                });
                            }
                            , "json"
                        );
                    });
                    $(document).on("change", "#gugun", function () {
                        $.get(root + "/house/dong"
                            , {gugun: $("#gugun").val()}
                            , function (data, status) {
                                $("#dong").empty();
                                $("#dong").append('<option value="0">선택</option>');
                                $.each(data, function (index, vo) {
                                    $("#dong").append("<option value='" + vo.dongCode + "'>" + vo.dongName + "</option>");
                                });
                            }
                            , "json"
                        );
                    });
                    $(document).on("change", "#dong", function () {
                        $.get(root + "/house/apt"
                            , {dong: $("#dong").val(), order: $("#order").val()}
                            , function (data, status) {
                                $("tbody").empty();
                                $.each(data, function (index, vo) {
                                    let str = `
										<tr class="${'${colorArr[index%3]}'}">
										<td>${'${vo.aptCode}'}</td>
										<td>${'${vo.aptName}'}</td>
										<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
										<td>${'${vo.buildYear}'}</td>
										<td>${'${vo.area}'}&#13217</td>
										<td>${'${vo.recentPrice}'}만원</td>
									`;
                                    $("tbody").append(str);
                                });
                                displayMarkers(data);
                            }
                            , "json"
                        );
                    });
                    $(document).on("change", "#order", function() {
                        $.get(root + "/house/apt"
                            , {dong: $("#dong").val(), order: $("#order").val()}
                            , function (data, status) {
                                $("tbody").empty();
                                $.each(data, function (index, vo) {
                                    let str = `
										<tr class="${'${colorArr[index%3]}'}">
										<td>${'${vo.aptCode}'}</td>
										<td>${'${vo.aptName}'}</td>
										<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
										<td>${'${vo.buildYear}'}</td>
										<td>${'${vo.area}'}&#13217</td>
										<td>${'${vo.recentPrice}'}만원</td>
									`;
                                    $("tbody").append(str);
                                });
                                displayMarkers(data);
                            }
                            , "json"
                        );
                    })
                    $("tbody").on("click", "tr", function () {
                        location.href = root + "/house/apt/" + $(this).children().first().text()
                    });
                    $(document).on("click", "#aptSearchBtn", function() {
                        if ($("#dong").val() === "0") {
                            alert("주소를 모두 입력해 주세요!!!")
                        } else {
                            $.get(root + "/house/apt"
                                , {dong: $("#dong").val(), order: $("#order").val(), area:$("#area").val(), dealAmount:$("#dealAmount").val(), buildYear:$("#buildYear").val()}
                                , function (data, status) {
                                    $("tbody").empty();
                                    $.each(data, function (index, vo) {
                                        let str = `
										<tr class="${'${colorArr[index%3]}'}">
										<td>${'${vo.aptCode}'}</td>
										<td>${'${vo.aptName}'}</td>
										<td>${'${vo.sidoName} ${vo.gugunName} ${vo.dongName} ${vo.jibun}'}</td>
										<td>${'${vo.buildYear}'}</td>
										<td>${'${vo.area}'}&#13217</td>
										<td>${'${vo.recentPrice}'}만원</td>
									`;
                                        $("tbody").append(str);
                                    });
                                    displayMarkers(data);
                                }
                                , "json"
                            );
                        }
                    })
                </script>
            </div>
        </div>
    </section>
</div>

<!-- 카카오맵 end -->
<!-- Footer-->
<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include>
<!-- Bootstrap core JS-->
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<!-- <script src="js/scripts.js"></script> -->
</body>
</html>
