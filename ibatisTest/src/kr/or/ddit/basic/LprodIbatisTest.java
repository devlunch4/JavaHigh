package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LprodIbatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// 1. iBatis의 환경 설정 파일 ()sqlMapConfig.xml)을 읽어와서 실행한다.

		try {
			// 1-1. 문자 인코딩 캐릭터 셋 설정하기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);

			// 1-2. 환경 설정 파일을 읽어온다.
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");

			// 1-3. 위에서 읽어온 reader 객체를 이용하여 실제 환경 설정을 완성한후
			/////// SQL문을 호출해서 실행할 수 있는 객체를 생성한다.

			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);

			rd.close(); // 객체 닫기

			// 환경설정 끝.~~~~~~~~~~

			// --------------------------------------------------------------------

			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.

			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			// 2.1 insert 연습
			// System.out.println("INSERT 작업 시작~~");
			// System.out.print("lprod_id 입력 : ");
			// int lprodId = scan.nextInt();
			//
			// System.out.print("lprod_gu 입력 : ");
			// String lprodGu = scan.next();
			//
			// System.out.print("lprod_nm 입력 : ");
			// String lprodNm = scan.next();
			//
			// // 1) Insert 할 데이터를 VO 객체에 담는다.
			// LprodVO lprodVo = new LprodVO();
			// lprodVo.setLprod_id(lprodId);
			// lprodVo.setLprod_gu(lprodGu);
			// lprodVo.setLprod_nm(lprodNm);
			//
			// // 2) sqlMapClient객체 변수를 이용해서 처리할 쿼리문을 호출하여 실행한다
			// ////// 형식) sqlMapClient객체 변수.insert("namespace값.id속성값", 파라미터클래스);
			// ///////// 반환 값 : INSERT 성공 : null, INSERT 실패 : 오류 객체;
			//
			// Object obj = smc.insert("lprod.insertLprod", lprodVo);
			// if (obj == null) {
			// System.out.println("INSERT 성공~~");
			// } else {
			// System.out.println("INSERT 실패~~");
			// }
			// System.out.println("-------------------------------------");
			//
			// INSERT 연습
			// 끝//////////////////////////////////////////////////////////////////////////////////////////////////////////

			//////////////////////////////////////////////////////////////////////////////////////////////////////////
			// 2-2 UPDATE 연습

			System.out.println("UPDATE 작업 시작~~");

			System.out.print("수정할 lprod_gu 입력 : ");
			String lprodGu = scan.next();

			System.out.print("새로운 lprod_id 입력 : ");
			int lprodId = scan.nextInt();

			System.out.println("새로운 lprod_nm 입력 : ");
			String lprodNm = scan.next();

			// 1) 수정할 데이터를 VO객체에 담는다.
			LprodVO lprodVo2 = new LprodVO();
			lprodVo2.setLprod_gu(lprodGu);
			lprodVo2.setLprod_id(lprodId);
			lprodVo2.setLprod_nm(lprodNm);

			// 2) sqlMapClient객체변수.update("namespace값.id속성값", 파라미터클래스)
			///// ==> 반환값 : 작업에 성공한 레코드 수
			int cnt = smc.update("lprod.updateLprod", lprodVo2);

			if (cnt > 0) {
				System.out.println("UPDATE 작업 성공~~");
			} else {
				System.out.println("UPDATE 작업 실패~~");
			}
			System.out.println("-------------------------------------");

			// UPDATE 연습 끝
			// ///////////////////////////////////////////////////////////////////////////////

			////////////////////////////////////////////////////////////
			// 2-3 DELETE 연습
			// System.out.println("DELETE 작업시작..");
			//
			// System.out.print("삭제할 Lprod_gu 입력 : ");
			// String lprodGu = scan.next();
			//
			// 1) sqlMapClient객체변수.delete("namespace값.id속성값", 파라미터 클래스);
			//////// 반환값 : 작업에 성공한 레코드 수
			//
			// int cnt2 = smc.delete("lprod.deleteLprod", lprodGu);
			//
			// if (cnt2 > 0) {
			// System.out.println("DELETE 작업 성공~~");
			// } else {
			// System.out.println("DELETE 작업 실패~~");
			// }
			// System.out.println("-------------------------------------");
			// DELETE
			// 연습끝/////////////////////////////////////////////////////////////////////////////

			//
			//
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
