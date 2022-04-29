package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.*;
import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.model.service.HouseDealService;
import com.ssafy.happyhouse.model.service.HouseInfoService;
import com.ssafy.happyhouse.model.service.MemberService;


@WebServlet(value = "*.do", loadOnStartup = 1)
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberService memberService = new MemberService();
	BoardService boardService = new BoardService();
	HouseInfoService houseInfoService = new HouseInfoService();
	HouseDealService houseDealService = new HouseDealService();

	@Override
	public void init() throws ServletException {
		getServletContext().setAttribute("root", getServletContext().getContextPath());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String url = request.getServletPath();
			PageInfo pageInfo = null;

			 if (url.equals("/member_modify.do")) {
				pageInfo = memberModify(request, response);
			} else if (url.equals("/member_delete.do")) {
				pageInfo = memberDelete(request, response);
			} else if (url.equals("/register.do")) {
				pageInfo = register(request, response);
//	board
			} else if (url.equals("/board_list.do")) {
				pageInfo = boardList(request, response);
			} else if (url.equals("/board_detail.do")) {
				pageInfo = boardDetail(request, response);
			} else if (url.equals("/board_modify.do")) {
				pageInfo = boardModify(request, response);
			} else if (url.equals("/board_modify_form.do")) {
				pageInfo = boardModifyForm(request, response);
			} else if (url.equals("/board_delete.do")) {
				pageInfo = boardDelete(request, response);
			} else if (url.equals("/board_insert.do")) {
				pageInfo = boardInsert(request, response);
			} else if (url.equals("/board_form.do")) {
				pageInfo = boardForm(request, response);
			} else if (url.equals("/apt_list.do")) {
				pageInfo = aptList(request, response);
			} else if (url.equals("/apt_detail.do")) {
				pageInfo = aptDetail(request, response);
			}

			if (pageInfo.isForward()) {
				request.getRequestDispatcher(pageInfo.getUrl()).forward(request, response);
				return;
			} else {
				response.sendRedirect(request.getContextPath() + pageInfo.getUrl());
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

	}

	private PageInfo loginForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false, "/login_form.jsp");
	}

	private PageInfo login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 서비스 호출해서 로그인 구현
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		try {
			Member member = memberService.login(id, pw);
			if (member != null) {
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				return new PageInfo(false, "/index.jsp");
			}

			else {
				request.setAttribute("errorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
				return new PageInfo(true, "/login_form.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", "로그인 실행 중 문제가 발생하였습니다.");
			throw e;
		}
	}

	private PageInfo logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return new PageInfo(false, "/index.jsp");
	}

	private PageInfo memberDetail(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false, "/member_detail.jsp");
	}

	private PageInfo registerForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false, "/register_form.jsp");
	}

	private PageInfo register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 서비스 호출해서 DB에 유저 정보 저장
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");

		try {
			boolean check = memberService.register(new Member(id, pw, name, addr, phone));
			if (check) {
				return new PageInfo(false, "/login_form.jsp");
			} else {
				request.setAttribute("errorMsg", "이미 등록된 id입니다.");
				return new PageInfo(true, "/register_form.jsp");
			}
		} catch (Exception e) {
			request.setAttribute("errorMsg", "회원가입 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}

		// 예외 처리
	}

	private PageInfo memberModify(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");

		Member m = new Member(id, pw, name, addr, phone);
		try {
			memberService.update(m);
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			return new PageInfo(false, "/index.jsp");
		} catch (Exception e) {
			request.setAttribute("errorMsg", "회원정보 수정 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}

	}

	private PageInfo memberDelete(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			memberService.delete(id);
			request.getSession().invalidate();
			return new PageInfo(false, "/index.jsp");
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "회원탈퇴 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}

	}

	private PageInfo index(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false, "/index.jsp");
	}

	private PageInfo boardList(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Board> boardList = boardService.selectBoardList();
			request.setAttribute("boardList", boardList);
			return new PageInfo(true, "/board_list.jsp");
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "게시판 목록 조회 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}
	}

	private PageInfo boardDetail(HttpServletRequest request, HttpServletResponse response) {

		int no = Integer.parseInt(request.getParameter("no"));
		try {
			Board board = boardService.selectBoard(no);
			request.setAttribute("board", board);
			return new PageInfo(true, "/board_detail.jsp");

		} catch (SQLException e) {
			request.setAttribute("errorMsg", "게시판 상세 조회 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}
	}

	private PageInfo boardForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		// 세션에 로그인 됨?
		if (member == null) {
			return new PageInfo(false, "/login_form.jsp");
		} else {
			return new PageInfo(false, "/board_form.jsp");
		}
	}

	private PageInfo boardInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");

		String id = member.getId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		try {
			boardService.insertBoard(new Board(title, content, id));
			return new PageInfo(false, "/board_list.do");
		} catch (Exception e) {
			request.setAttribute("errorMsg", "글 작성 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}

	}

	private PageInfo boardDelete(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));

		try {
			boardService.deleteBoard(no);
			return new PageInfo(false, "/board_list.do");
		} catch (Exception e) {
			request.setAttribute("errorMsg", "글 삭제 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}
	}

	private PageInfo boardModifyForm(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String memberId = request.getParameter("memberId");

		request.setAttribute("no", no);
		request.setAttribute("title", title);
		request.setAttribute("content", content);
		request.setAttribute("memberId", memberId);

		return new PageInfo(true, "/board_modify_form.jsp");
	}

	private PageInfo boardModify(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String memberId = request.getParameter("memberId");

		try {
			boardService.updateBoard(new Board(no, title, content, memberId));
			return new PageInfo(false, "/board_list.do");
		} catch (Exception e) {
			request.setAttribute("errorMsg", "글 수정 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}

	}

	private PageInfo aptList(HttpServletRequest request, HttpServletResponse response) {
		String dongcode = request.getParameter("dongcode");

		try {
			List<HouseInfo> houseInfoList = houseInfoService.getHouseInfoListByDongCode(dongcode);
			request.setAttribute("houseInfoList", houseInfoList);
			return new PageInfo(true, "/apt_list.jsp");
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "아파트 정보 조회 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}
	}

	private PageInfo aptDetail(HttpServletRequest request, HttpServletResponse response) {
		int aptCode = Integer.parseInt(request.getParameter("aptcode"));
		
		try {
			HouseInfo houseInfo = houseInfoService.getHouseInfoByAptCode(aptCode);
			List<HouseDeal> houseDealList = houseDealService.getHouseDealList(aptCode);
			request.setAttribute("aptName", houseInfo.getAptName());
			request.setAttribute("lat", houseInfo.getLat());
			request.setAttribute("lng", houseInfo.getLng());
			request.setAttribute("houseDealList", houseDealList);
			return new PageInfo(true, "/apt_detail.jsp");
		} catch (SQLException e) {
			request.setAttribute("errorMsg", "아파트 정보 상세 조회 중 문제가 발생하였습니다.");
			return new PageInfo(true, "/error.jsp");
		}
	}
}