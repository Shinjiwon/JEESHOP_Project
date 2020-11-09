$(document).ready(function(){

    // 처음 가격 업데이트
    updatePrice();
    
    /* 전체 체크박스 클릭 */
    $("#checkAll").click(function(){

        $(".check").prop('checked', this.checked);
        updatePrice();
    });

    /* 체크박스 중 체크안된 박스 존재 시 전체선택 해제 */
    $(".check").click(function(){

        $("#checkAll").prop('checked', false);
        updatePrice();
    });

    /* 선택 상품 삭제 버튼 클릭 */
    $("#btn_delete_check").click(function(){

        // 체크여부 검사
        if($("input[name='check']:checked").length == 0){
            alert("삭제할 상품을 삭제하세요");
            return;

        }

        // 체크 된 상품이 존재할 경우
        var result = confirm("선택한 상품을 삭제하시겠습니까?");

        if(result){
            var checkArr = [];

            // 체크 된 상품의 value 가져오기
            $("input[name='check']:checked").each(function(i){

                var cat_code = $(this).val(); // 선택된 장바구니 코드
                checkArr.push(cat_code); // 삭제하고자 하는 장바구니 코드를 배열 형태로 저장
            });

            $.ajax({

                url: "/cart/deleteCheck",
                type: "post",
                dataType: "text",
                data: {checkArr: checkArr},
                success: function(data){
                    location.href = "/cart/list";
                }
            });
        } else{}
    });

    /* 상품리스트 안 수량 변경버튼 클릭 */
    $("button[name='btn_modify']").click(function(){

        var cat_code = $(this).val();
        // 변경된 수량
        var cat_amount = $("input[name='cat_amount_" + cat_code + "']").val();

        $.ajax({
            url: "/cart/update",
            type:"post",
            dataType: "text",
            data:{
                cat_code: cat_code,
                cat_amount: cat_amount
            },
            success: function(data){
                alert("수량이 변경되었습니다.");
                location.href = "/cart/list";
            }
        });
    });
});

/* 총 판매가, 할인가, 결제금액 */
var updatePrice = function(){
    var totalPrice = 0;
    var totalDiscount = 0;
    var totalCount = 0;

    $("input[name='check']:checked").each(function(i){
        var cat_code = $(this).val();
        var amount = parseInt($("input[name='cat_amount_"+ cat_code +"']").val());

        totalPrice += parseInt($("input[name='price_" + cat_code +"']").val()) * amount;
        totalCount += parseInt($("input[name='discount_" + cat_code +"']").val()) * amount;
    });

    $("#totalPrice").html(numberFormat(totalPrice) + " ￦");
    $("#totalCount").html(numberFormat(totalCount) + " ￦");
}

/* 가격 콤마 설정 */
function numberFormat(inputNumber){
    return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}