package kr.green.leather.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.leather.pagination.Criteria;
import kr.green.leather.vo.BasketVO;
import kr.green.leather.vo.ProductVO;

public interface ProductService {

	ArrayList<ProductVO> getProductList(Criteria cri, String product_state, String product_maincategory, String product_subcategory);

	int getTotalCount(Criteria cri, String product_state);

	ProductVO getProduct(String product_code);

	void registerProduct(ProductVO pVo);

	ArrayList<ProductVO> getProductList(Criteria cri, String product_state, String product_maincategory);

	ArrayList<ProductVO> getProductList(Criteria cri, String product_state);

	int getTotalCount(Criteria cri, String product_state, String product_maincategory, String product_subcategory);

	int getTotalCount(Criteria cri, String product_state, String product_maincategory);

	ProductVO increaseViews(ProductVO product);

	void modifyProduct(ProductVO pVo);

	boolean isWriter(String product_code, HttpServletRequest r);

	void choiceProduct(ProductVO cPVo,Integer cnt,String member_id,Integer product_total);

	ArrayList<BasketVO> getBasketList();

}
