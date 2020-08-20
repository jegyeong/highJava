package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.prod.vo.LprodVO;
import kr.or.ddit.prod.vo.ProdVO;

public interface IProdDao {
	/**
	 * Lprod테이블의 전체 내용을 List에 담아 반환하는 메서드
	 * @return LprodVO객체가 저장된 List
	 */
	public List<LprodVO> getAllLprod(); 
	
	/**
	 * prodLgu값을 매개변수로 받아서 해당 상품 분류에 해당하는 상품 목록을 List로 반환하는 메서드
	 * @param prodLgu 검색할 상품 분류 코드
	 * @return 검색된 상품 목록 List
	 */
	public List<ProdVO> getProdList(String prodLgu);
	
	/**
	 * 상품 코드를 매개변수로 받아 해당 상품을 검색해서 List로 반환하는 메서드
	 * @param prodId 검색할 상품 코드
	 * @return 검색된 상품 목록 List
	 */
	public List<ProdVO> getProd(String prodId);
}
