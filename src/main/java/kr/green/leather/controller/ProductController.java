package kr.green.leather.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.green.leather.pagination.Criteria;
import kr.green.leather.pagination.PageMaker;
import kr.green.leather.service.PageMakerService;
import kr.green.leather.service.ProductService;
import kr.green.leather.utils.UploadFileUtils;
import kr.green.leather.vo.BasketVO;
import kr.green.leather.vo.DeliverVO;
import kr.green.leather.vo.MemberVO;
import kr.green.leather.vo.OrderVO;
import kr.green.leather.vo.ProductVO;


@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	@Autowired
	PageMakerService pageMakerService;
	@Resource
	private String uploadPath;
	
	
	//제품리스트
	@RequestMapping(value= "/product/list",method=RequestMethod.GET)
	public ModelAndView productListGet(ModelAndView mv, Criteria cri, String product_maincategory, String product_subcategory){
		String product_state ="I";
		cri.setPerPageNum(4);
		int displayPageNum=2;
		//System.out.println(cri);
		
		ArrayList<ProductVO> list;
		int totalCount=0;
		//System.out.println(product_maincategory +"," + product_subcategory);
		if((product_maincategory == null || product_maincategory.length() ==0) && (product_subcategory == null || product_subcategory.length() ==0)) {
			list = productService.getProductList(cri,product_state);
			totalCount = productService.getTotalCount(cri,product_state);
			
		}
		else if(product_subcategory != null && product_subcategory.length() !=0) {
			list = productService.getProductList(cri,product_state,product_maincategory,product_subcategory);
			totalCount = productService.getTotalCount(cri,product_state,product_maincategory,product_subcategory);
			
		}else{
			list = productService.getProductList(cri,product_state,product_maincategory);
			totalCount = productService.getTotalCount(cri,product_state,product_maincategory);
		
		}
	
		
		PageMaker pm = pageMakerService.getPageMaker(displayPageNum,cri,totalCount); 
		
		
	    mv.setViewName("/product/list");
	    mv.addObject("list",list);
	    mv.addObject("pageMaker",pm);
	    mv.addObject("product_maincategory",product_maincategory);
	    mv.addObject("product_subcategory",product_subcategory);
	    return mv;
	}
	
	
	//제품 등록
		@RequestMapping(value= "/product/register",method=RequestMethod.GET)
		public ModelAndView productRegisterGet(ModelAndView mv) throws Exception{

		    mv.setViewName("/product/register");
		    return mv;
		}
		@RequestMapping(value= "/product/register",method=RequestMethod.POST)
		public ModelAndView productRegisterPost(ModelAndView mv,ProductVO pVo,MultipartFile file2,MultipartFile file3) throws Exception{
			//System.out.println("productRegisterPost pVo : " + pVo);
			if(file2.getOriginalFilename().length() != 0) {
				String file = UploadFileUtils.uploadFile(uploadPath, file2.getOriginalFilename(),file2.getBytes());
				pVo.setFile(file);
			}
			if(file3.getOriginalFilename().length() != 0) {
				String contentsfile = UploadFileUtils.uploadFile(uploadPath, file3.getOriginalFilename(),file3.getBytes());
				pVo.setContentsfile(contentsfile);
			}
			productService.registerProduct(pVo);
			 mv.setViewName("redirect:/product/list");
		    return mv;
		}
		
		
		//파일업로드
		//@RequestMapping없음,내부적으로호출하기위한메소드  
		private String uploadFile(String name, byte[] data)
			throws Exception{
		    /* 고유한 파일명을 위해 UUID를 이용 */
			UUID uid = UUID.randomUUID();
			String savaName = uid.toString() + "_" + name;
			File target = new File(uploadPath, savaName);
			FileCopyUtils.copy(data, target);
			return savaName;
		}
		
		//제품 상세
		@RequestMapping(value= "/product/display",method=RequestMethod.GET)
		public ModelAndView productDisplayGet(ModelAndView mv,String product_code, Criteria cri){
		
			ProductVO product=productService.getProduct(product_code);
			product =productService.increaseViews(product);
		    mv.setViewName("/product/display");
		    mv.addObject("product",product);
		    mv.addObject("cri",cri);
		    
		   
		    return mv;
		}
		@RequestMapping(value= "/product/display",method=RequestMethod.POST)
		public ModelAndView productBasketPost(ModelAndView mv,ProductVO cPVo,Integer cnt, String member_id, Integer product_total){
			
			productService.choiceProduct(cPVo,cnt,member_id,product_total);
//			
			
			mv.setViewName("redirect:/product/basket");
		    
			
		    return mv;
		}
		
		
		//제품 수정
		@RequestMapping(value= "/product/modify",method=RequestMethod.GET)
		public ModelAndView productModifyGet(ModelAndView mv,String product_code, Criteria cri,HttpServletRequest r){
			boolean isWriter = productService.isWriter(product_code,r);
			//System.out.println(isWriter);
			ProductVO product=null;
			if(isWriter) {
				product=productService.getProduct(product_code);
				mv.setViewName("/product/modify");
			}else {
				mv.setViewName("redirect:/product/list");
			}
			
		    mv.addObject("product",product);
		    mv.addObject("cri",cri);
		    return mv;
		}
		@RequestMapping(value= "/product/modify",method=RequestMethod.POST)
		public String productModifyPost(ProductVO pVo,MultipartFile file2,MultipartFile file3) throws IOException, Exception{
		//System.out.println(pVo);
		//대표이미지첨부파일
		 if(file2.getOriginalFilename().length() !=0) { 
			 String file =UploadFileUtils.uploadFile(uploadPath,file2.getOriginalFilename(),file2.getBytes()); 
			 pVo.setFile(file); 
		 }
		 //상품첨부파일
		 if(file3.getOriginalFilename().length() !=0) { 
			 String contentsfile = UploadFileUtils.uploadFile(uploadPath,file3.getOriginalFilename(),file3.getBytes()); 
			 pVo.setContentsfile(contentsfile);
		 }
		 //수정된제품페이지
		 productService.modifyProduct(pVo);
		 
		    return "redirect:/product/modify";
		}
		
		//제품 삭제
		@RequestMapping(value= "/product/delete",method=RequestMethod.GET)
		public ModelAndView boardDeleteGet(ModelAndView mv,String product_code,HttpServletRequest r){
			if(productService.isWriter(product_code, r)) {
				productService.deleteProduct(product_code);
			}
		    mv.setViewName("redirect:/product/list");
		    return mv;
		}
		
		//장바구니
		@RequestMapping(value= "/product/basket",method=RequestMethod.GET)
		public ModelAndView productBasketGet(ModelAndView mv, HttpServletRequest r){
			MemberVO user = (MemberVO) r.getSession().getAttribute("user");
			ArrayList<BasketVO> list = null;
			if(user != null) {
				String member_id=user.getMember_id();
				//System.out.println("productBasketGet member_id : " + member_id);
				list = productService.getBasketList(member_id);
				mv.setViewName("/product/basket");
			}
			//System.out.println("productBasketGet list : " + list);
		    mv.addObject("list",list);
		    return mv;
		}

		
		//장바구니 삭제
		@RequestMapping("/product/dup")
		@ResponseBody
		public Map<Object, Object> deleteBasket(@RequestBody String basket_no){
		   
		    Map<Object, Object> map = new HashMap<Object, Object>();
		   // System.out.println(basket_no);
		    productService.deleteBasket(basket_no);
		    map.put(basket_no, basket_no);
		   
		    
		    return map;
		}
		
		//주문
		@RequestMapping(value= "/product/order",method=RequestMethod.GET)
		public ModelAndView productOrderGet(Integer[] cnt,Integer[] product_total,ModelAndView mv, Integer[] basket_check,int total,int deliverycost,int orderTotal){
			//수량이랑 수량적용된금액 잘 넘어오는지 확인
			for(Integer tmp: cnt) {
				//System.out.System.out.println(tmp);
			}
			for(Integer tmp: product_total) {
				//System.out.println(tmp);
			}
			
			
			ArrayList<BasketVO> list = productService.getBasketList(basket_check,cnt,product_total);
			
			//System.out.println(list);
			mv.addObject("list",list);
			mv.addObject("total",total);
			mv.addObject("deliverycost",deliverycost);
			mv.addObject("orderTotal",orderTotal);
			mv.setViewName("/product/order");
			return mv;
		}
		//주문2
		@RequestMapping(value= "/product/order2",method=RequestMethod.GET)
		public ModelAndView productOrder2Get(ModelAndView mv,String member_id){
				
			
			
			mv.setViewName("/product/order2");
			return mv;
		}
		@RequestMapping(value= "/product/order2",method=RequestMethod.POST)
		public ModelAndView productOrder2Post(ModelAndView mv,ProductVO pVo,Integer cnt){
			
//			System.out.println("jsp에서받음 "+pVo);
//			System.out.println(cnt);
			
			
			
			productService.orderProduct(pVo);
			//System.out.println(pVo);
			mv.addObject("pVo",pVo);
			mv.addObject("cnt",cnt);
			mv.setViewName("/product/order2");
			return mv;
		}
		//주문완료
		@RequestMapping(value= "/product/finish",method=RequestMethod.GET)
		public ModelAndView productFinishGet(ModelAndView mv, Integer []basket_check,DeliverVO dVo){
			
			
			mv.setViewName("/product/finish");
			return mv;
		}
		@RequestMapping(value= "/product/finish",method=RequestMethod.POST)
		public ModelAndView productFinishPost(String basket_state,ModelAndView mv, Integer []basket_check,DeliverVO dVo,String basket_member_id,ProductVO pVo,Integer cnt, String member_id, Integer orderTotal){
			ArrayList<Integer> orderNumList = new ArrayList<Integer>();
			
			for(Integer tmp : basket_check) {//체크된 값을 하나씩 끄집어내서  tmp에 저장 
				//체크한항목을 넘겨받아 주문 db 저장
				orderNumList.add(productService.checkBasket(tmp,orderTotal));
				//카트비우기 
				productService.emptyBasket(tmp,basket_state);
			} 
//			System.out.println("리스트");
//			System.out.println(orderNumList);
			
			
			//배송지정보추가 
			productService.deliverInfo(dVo, orderNumList);
			
			
			
			mv.setViewName("redirect:/product/finish");
			return mv;
		}
		//주문완료22
		@RequestMapping(value= "/product/finish2",method=RequestMethod.GET)
		public ModelAndView productFinish2Get(ModelAndView mv,DeliverVO dVo,String basket_member_id,ProductVO pVo,Integer cnt, String member_id, Integer orderTotal){
			System.out.println(member_id);
			Integer order_num;
			//오더2 추가
			order_num=productService.orderInsert2(pVo,cnt,member_id,orderTotal);
			
			System.out.println(order_num);
			//배송지정보추가 
			productService.deliverInfo(dVo,order_num);
			System.out.println(dVo);
			
			
			mv.setViewName("/product/finish2");
			return mv;
		}
				
		
		//주문조회
		@RequestMapping(value= "/product/orderList",method=RequestMethod.GET)
		public ModelAndView productOrderListGet(ModelAndView mv,HttpServletRequest r){
			MemberVO user = (MemberVO) r.getSession().getAttribute("user");
			ArrayList<BasketVO> list = null;
			if(user != null) {
			String member_id=user.getMember_id();
			list=productService.getOrder(member_id);
//			System.out.println(list);
			}
			mv.addObject("list",list);
			mv.setViewName("/product/orderList");
			return mv;
		}
}
