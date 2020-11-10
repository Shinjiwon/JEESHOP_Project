$(document).ready(function(){

    // 처음 가격 업데이트
    updatePrice();

    $("#checkAll").click(function(){

        // 전체 선택 클릭 시 체크박스 모두 체크
        $(".check").prop('checked', this.checked);
    });

    // 체크 박스 중 체크 안된 박스 존재시 전체선택 해제
    $(".check").click(function(){

        $("#checkAll").prop('checked', false);
    });

    /* 선택상품 삭제 버튼 클릭 시 */
    $("#btn_delete_check").click(function(){

        // 체크여부 유효성 검사
        if($("input[name='check']:checked").length == 0){
            alert("삭제할 상품을 선택하세요.");

        }

        // 체크된 상품이 존재 할 경우
        var result = confirm("선택한 상품을 삭제하시겠습니까?");
        if(result){
            $("input[name='check']:checked").each(function(i){
                var pro_num = $(this).val();
                $("#productVO_"+pro_num).remove();
                updatePrice();
            });

            if($("#ordertbl > tbody tr").length==0){
				$("#thead").append("<tr><td colspan='6' style='padding:20px 0px;'><p style='padding:50px 0px; text-align: center;'>구매할 상품이 존재하지 않습니다.</p></td></tr>");
			}
        } else{}
    });

    /* 결제하기 버튼 클릭 */
    $("#btn_submit").click(function(){

        // 주문할 상품이 없는 경우
        if($("#ordertbl > tbody tr").length==0){
            alert("결제할 상품이 없습니다.\n메인페이지로 이동합니다.");
            location.href = "/";
            return;

        } else{ // 결제할 상품이 존재하는 경우
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

        totalPrice = price * amount;
        totalCount = discount * amount;
    });

    
    $("#totalPrice").html(numberFormat(totalPrice) + " ￦");
    $("#totalCount").html(numberFormat(totalCount) + " ￦");
}

/* 숫자 콤마 설정 */
function numberFormat(inputNumber) {
	return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}