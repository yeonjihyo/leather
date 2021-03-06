<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/common.css">
<title>제품 등록</title>
<style>
	h3{
	padding-top: 50px; 
	padding-bottom:20px;
	text-align: center;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#mainCategory').change(function(){
			$('#register-form input[name=product_maincategory]').val($(this).val());
		});
		$('#subCategory').change(function(){
			$('#register-form input[name=product_subcategory]').val($(this).val());
		});
	});
</script>
</head>
<script type="text/javascript">
</script>
<body><!-- 괄호안은 속성명임 -->
	<h3 class="col-8 div-center border-bottom">|제품 등록</h3>
	<form  action="<%=request.getContextPath()%>/product/register" method="post" enctype="multipart/form-data" style="padding-top: 20px;" id="register-form">
		<div class="container-fluid">
		<input type="hidden" name="product_maincategory" >
		<input type="hidden" name="product_subcategory" >
			<div class="form-group col-8 div-center">
			  <label>작성자</label>
			  <!-- 로그인인터셉터에 유저를 세션에 저장했기 때문에 다 가져다 쓸 수 있다   -->
			  <input type="text" class="form-control" name="product_writer" value="${user.member_id}" readonly>
			</div>
			<div class="form-group col-8 div-center">
			  <label>제품코드</label>
			  <input type="text" class="form-control" name="product_code" value="" placeholder="제품코드">
			</div>
			<div class="form-group col-8 div-center">
			  <label>제품명</label>
			 <input type="text" class="form-control" name="product_title" value="" placeholder="제품명">
			</div>
			<div class="form-group col-8 div-center">
			  <label>메인카테고리</label>
			  <select class="form-control mainCategory" id="mainCategory">
			     <option value="000">선택</option>
			 	 <option value="001">001</option>
			   	 <option value="002">002</option>
			     <option value="003">003</option>
			     <option value="004">004</option>
			     <option value="005">005</option>
			   	 <option value="006">006</option>
			     <option value="007">007</option>
			     <option value="008">008</option>
			  </select>
			</div>
			<div class="form-group col-8 div-center subCategory">
			  <label>서브카테고리</label>
			  <select class="form-control" id="subCategory">
			  	 <option value="000">선택</option>
			 	 <option value="001">001</option>
			   	 <option value="002">002</option>
			     <option value="003">003</option>
			     <option value="004">004</option>
			     <option value="005">005</option>
			   	 <option value="006">006</option>
			     <option value="007">007</option>
			     <option value="008">008</option>
			     <option value="009">009</option>
			     <option value="010">010</option>
			  </select>
			</div>
			<div class="form-group col-8 div-center">
			  <label>원산지</label>
			 <input type="text" class="form-control" name="product_origin" value="" placeholder="원산지">
			</div>
			<div class="form-group col-8 div-center">
			  <label>제조사</label>
			 <input type="text" class="form-control" name="product_made" value="" placeholder="제조사">
			</div>
			<div class="form-group col-8 div-center">
			  <label>가격</label>
			 <input type="text" class="form-control" name="product_price" value="" placeholder="가격">
			</div>
			<div class="form-group col-8 div-center">
			  <label>재고수량</label>
			 <input type="text" class="form-control" name="product_stock" value="" placeholder="재고수량">
			</div>
			<div class="form-group col-8 div-center">
			  <label>제품내용</label>
			  <textarea rows="10" class="form-control" name="product_contents" placeholder="제품내용"></textarea>
			</div>
			
			<div class="form-group col-8 div-center">
			  <label>대표첨부파일</label>
			  <input type="file" class="form-control" name="file2" value="">
			</div>
			<div class="form-group col-8 div-center">
			  <label>상품첨부파일</label>
			  <input type="file" class="form-control" name="file3" value="">
			</div>
			<div class="div-center border-bottom col-8" style="padding : 20px 0;" >
				<a href="<%=request.getContextPath()%>/product/list">
					<button type="button" class="btn btn-navy">목록</button>
				</a>
				<button class="btn btn-navy">등록하기</button>
				<a href="<%=request.getContextPath()%>/product/list">
					<button type="button" class="btn btn-navy">취소</button>
				</a>
			</div>
		</div>
	</form>
</body>
</html> 