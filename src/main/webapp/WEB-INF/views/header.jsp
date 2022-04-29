<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div class="px-3 py-2 bg-dark text-white">
		<div class="container">
			<div
				class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
				<a href="index.do"
					class="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none"
					style="font-size: 36px">Happy House <svg class="bi me-2"
						width="40" height="32" role="img" aria-label="Bootstrap">
                <use xlink:href="#bootstrap"></use>
              </svg>
				</a>
				<ul
					class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
					<li><a href="${root }/board_list.do"
						class="nav-link text-white"> 게시판 </a></li>
					<li><a href="${root }/apt_list.do"
						class="nav-link text-white"> 실거래가 조회 </a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="px-3 py-2 border-bottom mb-3">
		<div class="container d-flex flex-wrap justify-content-end">
			<div class="text-end">
				<div class="btn col-12 col-lg-auto mb-2 mb-lg-0 me-lg-auto">
					<input type="text" class="form-control" placeholder="Search..."
						id="search" aria-label="Search" onkeypress="" />
				</div>

				<c:choose>
					<c:when test="${empty(member)}">
						<!-- 로그인 안 한 경우 -->
						<span>
							<button type="button" class="btn btn-light text-dark me-2"
								onclick="move('/login_form.do')">Login</button>
							<button type="button" class="btn btn-primary me-2"
								onclick="move('/register_form.do')">회원가입</button>
						</span>
					</c:when>
					<c:otherwise>
						<!-- 로그인 한 경우 -->
						<span>
							<a>${member.name}님 반갑습니다.</a>
							<button type="button" class="btn btn-light text-dark me-2"
								onclick="move('/logout.do')">Logout</button>
							<button type="button" class="btn btn-primary me-2"
								onclick="move('/member_detail.do')">회원정보</button>
						</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</header>