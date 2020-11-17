package controller;

import java.util.List;
import java.util.Scanner;

import service.IJdbcBoardService;
import service.JdbcBoardServiceImpl;
import vo.JdbcBoardVO;

public class JdbcBoardController {
	private IJdbcBoardService boardService;
	private Scanner scan;

	public JdbcBoardController() {
		// 인스턴스 사용
		boardService = JdbcBoardServiceImpl.getInstance();
		scan = new Scanner(System.in);
	}

	////////////////////////////////////////
	// 컨트롤러는 일종의 보여주고 입력 출력을 받는 느낌의 클래스
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}

	// 메소드시작
	public void boardStart() {
		String jBoardTitle = null;

		int choice = -1;

		while (true) {
			// 해당 메소드에서 값을 리턴 받음.
			// if 해주어야 3번 검색후 오류 출력 방지됨.
			if (choice != 3) {
				jBoardTitle = null;
			}
			choice = displayMenu(jBoardTitle);

			switch (choice) {
			case 1: // 새글 작성
				insertBoard();
				break;
			case 2: // 게시글 보기
				viewBoard();
				break;
			case 3: // 게시글 검색
				jBoardTitle = searchBoard();
				break;
			case 0: // 작업 끝
				System.out.println("프로그램 종료...");
				System.exit(0);
				return;
			default:
				System.out.println("번호를 잘못 입력. 다시입력");
			}
		}
	}

	// 게시판 목록 출력. 입력 디스플레이 출력
	public int displayMenu(String jBoardTitle) {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("  No	    제 목            작성자     	조회수");
		System.out.println("---------------------------------------------");

		// 리스트 생성
		List<JdbcBoardVO> boardList = boardService.getSearchBoardList(jBoardTitle);

		// 리스트 내용 확인
		if (boardList == null || boardList.size() == 0) {
			System.out.println(" 출력할 게시글이 없습니다.");
		} else {
			for (JdbcBoardVO jboardVo : boardList) {
				System.out.println(jboardVo.getBoard_no() + "	" + jboardVo.getBoard_title() + "	"
						+ jboardVo.getBoard_writer() + "	" + jboardVo.getBoard_cnt());
			}
		}
		System.out.println("---------");
		System.out.println("메뉴 : 1. 새글작성 2. 게시글보기 3. 검색 0. 작업끝");
		System.out.print("작업선택>>> ");
		while (!scan.hasNextInt()) {
			scan.nextInt();// 잘못된 입력 초기화
			System.out.println("잘못된 입력. 해당 작업번호를 입력해주세요.");
		}
		int num = scan.nextInt();
		return num;
	}

	// 새글 작성
	public void insertBoard() {
		System.out.println();

		// 스캔 값 초기화
		scan.nextLine();

		System.out.println("새글 작성하기");
		System.out.println("---------");
		System.out.print("새 제목 입력 : ");
		String boardTitle = scan.nextLine();

		// 스캔 값 초기화
		scan.nextLine();

		System.out.print("새 작성자 입력 : ");
		String boardWriter = scan.next();

		// 스캔 값 초기화
		scan.nextLine();

		System.out.print("새 내용 입력 : ");
		String boardContent = scan.nextLine();

		// 해당 데이터 클래스에 지정(일종의 저장)할 클래스 호출
		JdbcBoardVO jBoardVo = new JdbcBoardVO();

		// 해당 칼럼값 지정
		jBoardVo.setBoard_title(boardTitle);
		jBoardVo.setBoard_writer(boardWriter);
		jBoardVo.setBoard_content(boardContent);

		// 저장된 값 service에서 쿼리문 수행
		int cnt = boardService.insertBoard(jBoardVo);
		// 수행시 1 미수행시 0
		if (cnt > 0) {
			System.out.println("새글이 추가되었습니다.");
		} else {
			System.out.println("새글 추가 실패!!");
		}
	}

	// 게시글보기
	public void viewBoard() {
		System.out.println();
		System.out.print("볼 게시물 번호 입력 >> ");
		int boardNo = scan.nextInt();

		// 스캔 값 초기화
		scan.nextLine();

		// 해당 게시글 번호값의 내용 호출
		JdbcBoardVO jBoardVo = boardService.getBoard(boardNo);

		if (jBoardVo == null) {
			System.out.println(boardNo + "번의 게시글이 존재하지 않습니다.");
			return;
		}

		System.out.println();
		System.out.println(boardNo + "번글 내용");
		System.out.println("----------");
		System.out.println("제목 : " + jBoardVo.getBoard_title());
		System.out.println("작성자 : " + jBoardVo.getBoard_writer());
		System.out.println("내용 : " + jBoardVo.getBoard_content());
		System.out.println("작성일 : " + jBoardVo.getBoard_date());
		System.out.println("조회수 : " + jBoardVo.getBoard_cnt());
		System.out.println("----------");
		System.out.println("메뉴 : 1.수정 2.삭제 3.리스트로");
		System.out.print("작업선택>>>");
		int choice = scan.nextInt();

		switch (choice) {
		case 1: // 게시글 수정
			updateBoard(boardNo);
			break;
		case 2: // 게시글 삭제
			deleteBoard(boardNo);
			break;
		case 3: // 리스트로
			return;
		}
	}

	// 게시글 검색
	public String searchBoard() {
		System.out.println();

		// 스캔 초기화
		scan.nextLine();

		System.out.print("검색할 제목 입력 : ");
		String jBoardTitle = scan.nextLine();

		return jBoardTitle;
	}

	// 게시글 수정
	public void updateBoard(int boardNo) {
		System.out.println();

		// 스캔 초기화
		scan.nextLine();
		System.out.print("제목 : ");
		String jBoardTitle = scan.nextLine();

		System.out.print("내용 : ");
		String jBoardContent = scan.nextLine();

		JdbcBoardVO jBoardVo = new JdbcBoardVO();
		jBoardVo.setBoard_title(jBoardTitle);
		jBoardVo.setBoard_content(jBoardContent);
		jBoardVo.setBoard_no(boardNo);

		int cnt = boardService.updateBoard(jBoardVo);
		if (cnt > 0) {
			System.out.println(boardNo + "번글이 수정되었습니다.");
		} else {
			System.out.println(boardNo + "번글 수정 실패!!");
		}
	}

	// 게시글 삭제
	public void deleteBoard(int boardNo) {
		int cnt = boardService.deleteBoard(boardNo);
		if (cnt > 0) {
			System.out.println(boardNo + "번글이 삭제되었습니다.");
		} else {
			System.out.println(boardNo + "번글이 삭제 실패!!");
		}
	}
}
