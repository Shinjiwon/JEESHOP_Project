$(document).ready(function(){

    var form = $("#insertForm");

    $("#btn_submit").click(function(){

        var result = confirm("상품을 등록하시겠습니까?");

        if(result){
            var mainCategory = $("#mainCategory option:selected");
            var subCategory = $("#subCategory option:selected");
            var pro_name = $("#pro_name");
            var pro_dev = $("#pro_dev");
            var pro_price = $("#pro_price");
            var pro_discount = $("#pro_discount");
            var ckeditor = CKEDITOR.instances['pro_detail'];
            var file1 = $("#file1");
            var pro_amount = $("#pro_amount");
            var pro_buy = $("#pro_buy");
            var fileSize = file1.size;
            var fileFormat = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
            var maxSize = 5 * 1024 * 1024; // 5MB

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
                alert("할인률을 입력하세요.");
                pro_discount.focus();
                return;

            } else if(ckeditor.getData()=="" || ckeditor.getData()==null){
                alert("상품상세 내용을 입력하세요.");
                ckeditor.focus();
                return;

            } else if(file1.val()=="" || file1.val()==null){
                alert("이미지 파일을 추가하세요.");
                file1.focus();
                return;

            } else if(!file1.val().match(fileFormat)){
                alert("이미지형식의 파일만 업로드 가능합니다.");
                file1.focus();
                return;

            } else if(fileSize > maxSize){
                alert("파일 사이즈는 5MB까지 업로드 가능합니다.");
                file1.focus();
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