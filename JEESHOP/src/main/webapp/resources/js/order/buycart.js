$(document).ready(function(){

    updatePrice();

    /* 전체 선택 체크박스 클릭 시 */
	$("#checkAll").on("click", function() {
		// 전체선택 클릭 여부로 다른 체크박스 체크
		$(".check").prop('checked', this.checked);
        updatePrice();
    });

	
	/* 체크박스 중 선택안된 체크박스 존재 시 전체선택 해제 */
	$(".check").on("click", function() {
		$("#checkAll").prop('checked', false);
        updatePrice();
    });
	
	
	/* 선택 상품 삭제 버튼 클릭 시 */
	$("#btn_delete_check").on("click", function() {
		// 체크여부 유효성 검사
		if ($("input[name='check']:checked").length == 0) {
			alert("삭제할 상품을 선택하세요.");
			return;
		}
		// 체크 된 상품이 존재할 경우 진행
		var result = confirm("선택한 상품을 삭제하시겠습니까?");
		if (result) {
			// 체크 된 상품의 value(cart_code)를 가져옴
			$("input[name='check']:checked").each(function(i) {
				var pdt_num = $(this).val();
				$("#productVO_"+pdt_num).remove();
				updatePrice();
			});
			
			if($("#ordertbl >tbody tr").length==0){
				$("#thead").append("<tr><td colspan='7' style='padding:20px 0px;'><span>구매할 상품이 존재하지 않습니다.</span></td></tr>");
			}
		} else {}
	});
	
	/* 결제하기 버튼 클릭 시 */
	$("#btn_submit").on("click", function(){
		
		if($("#ordertbl >tbody tr").length==0){
			// 결제할 상품이 없는 경우
			alert("결제할 상품이 없습니다.\n메인화면으로 돌아갑니다.");
			location.href="/";
			return;
			
		} else {
			// 결제할 상품이 존재하는 경우
			$("#orderForm").submit();
		}
		
	});
});

var updatePrice = function(){

    var totalPrice = 0;
    var totalCount = 0;

    $(".productRow").each(function(i, e){
        var price = parseInt($(this).find("input[name='price']").val());
        var discount = parseInt($(this).find("input[name='discount']").val());
        var amount = parseInt($(this).find("input[name='amount']").val());

        totalPrice += price * amount;
        totalCount += discount * amount;
    });

    
    $("#totalPrice").html(numberFormat(totalPrice) + " ￦");
    $("#totalCount").html(numberFormat(totalCount) + " ￦");
}

/* 숫자 콤마 설정 */
function numberFormat(inputNumber) {
	return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}