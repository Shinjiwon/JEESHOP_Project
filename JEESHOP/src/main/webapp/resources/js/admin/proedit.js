$(document).ready(function(){

    var form = $("#editForm");

    /* 상품 수정 버튼 클릭 */
    $("#btn_submit").click(function(){

       var result = confirm("상품을 수정하시겠습니까?");

       if(result){

            var mainCategory = $("#mainCategory option:selected");
            var subCategory = $("#subCategory option:selected");
            var pro_name = $("#pro_name");
            var pro_dev = $("#pro_dev");
            var pro_price = $("#pro_price");
            var pro_discount = $("#pro_discount");
            var ckeditor = CKEDITOR.instances['pro_detail'];
            var pro_detail = $("#pro_detail");
            var pro_amount = $("#pro_amount");
            var pro_buy = $("#pro_buy");

            if(mainCategory.val()=="" || mainCategory.val()==null){
                alert("1차 카테고리를 선택하세요.");
                mainCategory.focus();
                return;

            } else if(subCategory.val()=="" || subCategory.val()==null){
                alert("2차 카테고리를 선택하세요.");
                subCategory.focus();
                return;

            } else if(pro_name.val()=="" || pro_name.val()==null){
                alert("상품명을 입력하세요.");
                pro_name.focus();
                return;

            } else if(pro_dev.val()=="" || pro_dev.val()==null){
                alert("제조사를 입력하세요.");
                pro_dev.focus();
                return;

            } else if(pro_price.val()=="" || pro_price.val()==null){
                alert("상품가격을 입력하세요.");
                pro_price.focus();
                return;

            } else if(pro_discount.val()=="" || pro_discount.val()==null){
                alert("상품할인율을 입력하세요.");
                pro_discount.focus();
                return;

            } else if(ckeditor.getData()=="" || ckeditor.getData()==null){
                alert("상품상세내용을 입력하세요.");
                ckeditor.focus();
                return;

            } else if(pro_amount.val()=="" || pro_amount.val()==null){
                alert("상품수량을 입력하세요.");
                pro_amount.focus();
                return;

            } else{
                form.submit();
                
            }
       } else{}
    });
});