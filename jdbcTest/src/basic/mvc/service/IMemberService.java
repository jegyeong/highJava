package basic.mvc.service;

import java.util.List;
import java.util.Map;

import basic.mvc.vo.MemberVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author SEM-PC
 *
 */
public interface IMemberService {

	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param memVo DB에 inert할 자료가 저장된 MemberVO객체
	 * @return DB작업 성공 : 1, 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업 성공 : 1, 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * 하나의 MemberVO자료를 이용하여 회원 정보를 update하는 메서드
	 * @param memVo 수정할 정보가 저장된 MemverVO객체
	 * @return 작업 성공 : 1, 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	/**
	 * Map의 정보를 이용하여 회원 정보를 수정하는 메서드
	 * @param paramMap 수정할 정보가 저장된 Map객체
	 * @return 작업 성공 : 1, 실패 : 0
	 */
	public int updateMember(Map<String, String> paramMap);
	
	/**
	 * 회원ID를 매개변수로 받아서 해당 회원의 인원수를 반환하는 메서드
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원 수
	 */
	public int getMemberCount(String memId);
	
	/**
	 * 전체 회원정보를 DB에서 가져와 각각의 정보는 MemberVO에 담고, 
	 * 이 MemberVO객체를 List에 넣어서 반환하는 메서드
	 * @return 전체 회원정보(MemberVO객체)가 저장된 List객체
	 */
	public List<MemberVO> getAllMember();
	
	/**
	 * 검색할 컬럼명과 검색할 단어가 저장된 Map을 매개변수로 받아서 해당 데이터를 검색하여 List로 반환하는 메서드
	 * @param searchMap 검색할 컬럼명과 검색할 단어가 저장된 Map객체
	 * @return 검색결과를 List에 담아서 반환한다.
	 */
	public List<MemberVO> getSearchMember(Map<String, String> searchMap);
}
