var replyPage = 1;

$(document).ready(function(){

    /* 장바구니 버튼 클릭 시 */
    $("#btn_cart").click(function(){

        var pro_num = $("#pro_num").val();
        var pro_amount = $("#ord_amount").val();

        $.ajax({

            url: "/cart/addMany",
            type: "post",
            dataType: "text",
            data: {
                pro_num: pro_num,
                pro_amount: pro_amount
            },
            success: function(data){
                var result = confirm("장바구니에 상품을 추가되었습니다.\n장바구니로 이동하시겠습니까?");

                if(result){
                    location.href = "/cart/list";
                } else{}
            }
        });
    });

    /* 리뷰영역 별점 클릭 시 색상변경*/
    $("#star_grade a").click(function(){
        $(this).parent().children("a").removeClass("on"); // 별점의 on클래스 전부제거
        $(this).addClass("on").prevAll("a").addClass("on"); // 클릭한 별과, 그 앞 별까지 on클래스 추가
        return false;
    });

    /* 상품후기 쓰기 버튼 클릭 */
    $("#btn_write_review").click(function(){

        var rew_score = 0;
        var rew_content = $("#reviewContent").val();
        var pro_num = $("#pro_num").val();

        // 별점 가져오기
        $("#star_grade a").each(function(i, e){
            if($(this).attr('class')=='on'){
                rew_score +=1;
            }
        });

        // 유효성 검사
        if(rew_score==0){
            alert("별점을 선택하세요.");
            return;

        } else if(rew_content=="" || rew_content==null){
            alert("내용을 입력하세요.");
            return;

        }

        $.ajax({
            url: '/review/write',
            type: 'post',
            dataType: 'text',
            data: {
                rew_score: rew_score,
                rew_content: rew_content,
                pro_num: pro_num
            },
            success: function(data){
                alert("상품후기가 등록되었습니다.");
                // 후기 등록 후 선택되었던 별점 제거
                $("#star_grade a").parent().children("a").removeClass("on");
                // 후기 등록 후 내용 제거
                $("#reviewContent").val("");
                replyPage = 1;
                getPage("/review/" + pro_num + "/1");
            }
        })
    });

    /*  Modal 창에서 별점 클릭 시, 색상 변경 */
	 $('#star_grade_modal a').click(function(){
        $(this).parent().children("a").removeClass("on"); 
        $(this).addClass("on").prevAll("a").addClass("on"); 
            return false;
    });	

    /* modal창에 후기 뿌리기 */
    $("#reviewList tbody").on("click", ".replyLi", function(){

        $("#modifyModal").show();

        var btn = $(this);
        var rew_num = btn.parent().prev().prev().prev().prev().prev().text();
        var score = btn.parent().prev().prev().find("input[name=rew_score]").val();
        
       // 평점 가져오기
       $("#star_grade_modal a").each(function(index, item){
         
        
        if(index<score){
                $(item).addClass('on');

            } else {
                $(item).removeClass('on');
            }
       });

       // 후기 내용 수정창에 가져오기
       
       $("#replytext").val(btn.parent().prev().text());

       // 후기 번호 가져오기
       $(".modal-body").attr("data-rew_num", rew_num);
    });

    /* 수정창 수정 버튼 클릭 */
    $("#btn_modal_modify").click(function(){

        var rew_num = $(".modal-body").attr("data-rew_num");
        var rew_content = $("#replytext").val();
        var pro_num = $("#pro_num").val();

        // 선택된 별점 개수 가져오기
        var rew_score = 0;
        $("#star_grade_modal a").each(function(i, e){

            if($(this).attr('class')=='on'){
                rew_score += 1;
            }
        });

        // DB작업
        $.ajax({

            url: "/review/modify/",
            type: "post",
            dataType: "text",
            data: {
                "rew_num": rew_num,
                "rew_content": rew_content,
                "rew_score": rew_score
            },
            success: function(result){
                alert("리뷰가 수정되었습니다.");
                getPage("/review/" + pro_num + "/" + replyPage);
            }
        });
    });

    /* 상품후기 리스트 템플릿 적용 */
    /* 상품후기보기 버튼 클릭 */
    $("#btn_see_review").click(function(){
        
        var pro_num = $("#pro_num").val();
        //$("#reviewListPage").show();
       //$("#noReview").show();
       if(reviewListCount!=0){
        $("#reviewListPage").toggle();
        $("#noReview").hide();

    } else{
        $("#noReview").toggle();
        $("#reviewListPage").hide();

    }
        // 리뷰테이블 열려있으면
        /*
        if($(".table-responsive table").length > 1){
            $("#reviewList").remove();
            $("#noReview").hide();
            return;
        }
        */

        // 열려 있지 않으면, 리뷰테이블 보여주기
        getPage("/review/" + pro_num + "/1");
    });

    /* 상품 후기 하단 페이지 부분 클릭 시 - 페이지 이동 */
	$(".pagination").on("click", "li a", function(event) {

		var pro_num = $("#pro_num").val();
		
		event.preventDefault();
		
		replyPage = $(this).attr("href");
		getPage("/review/" + pro_num + "/" + replyPage);

	});

});

var reviewListCount = 0;

 /* 후기 리스트 핸들바 템플릿에 적용 */
 function getPage(pageInfo){
    
    $.getJSON(pageInfo, function(data){

        if(data.list.length > 0){ // 상품후기 존재
            // 후기 리스트 핸들바 템플릿 적용
            reviewListCount = data.list.length;
            
            printData(data.list, $("#reviewList tbody"), $("#template"));
            printPaging(data.pageMaker,$(".pagination"));

			$("#replycntSmall").html("[ " + data.pageMaker.totalCount + " ]");

        } else{ // 상품후기 존재하지 않음
            printNoData();
            $("#replycntSmall").html("[0]");
        }
    });
}

/* 상품후기 존재하지 않음 → printNoData() */
var printNoData = function(){
    $("#reviewList").remove();
    $("#noReview").show();
}

/* 상품후기 존재 → printData() 템플릿에 적용 */
var printData = function(replyArr, target, templateObject){
    var template = Handlebars.compile(templateObject.html());

    var html = template(replyArr);
    //$("#reviewList").empty();
    $("#noReview").hide();
    $("#reviewListPage").show();

    $("#reviewList tbody").empty();
    target.append(html);
}

/* printPaging(): 상품후기 페이징 */
var printPaging = function(pageMaker, target){
    
    var str = "";

    // 이전 표시
    if(pageMaker.prev){
        str +="<li class=page-item><a class=page-link href='" + (pageMaker.startPage - 1)
        + "'></a></li>";
    }

    // 페이지 표시
    for(var i=pageMaker.startPage, len=pageMaker.endPage; i<=len; i++){
        var strClass = pageMaker.cri.page == i ? 'class=active':'';
        str += "<li class=page-item "+strClass+"><a class=page-link href='"+i+"'>" + i + "</a></li>";
    }

    // 다음 표시
    if (pageMaker.next) {
		str += "<li  class=page-item><a class=page-link href='" + (pageMaker.endPage + 1)
				+ "'> >> </a></li>";
    }
    
    target.html(str);
}

/* 상품후기 삭제 */

var deleteReview = function(rew_num){

    var result = confirm("현재 리뷰를 삭제하시겠습니까?");

    if(result){
        $.ajax({
            url: "/review/" + rew_num,
            type: "delete",
            dataType: "text",
            data: {'rew_num': rew_num},
            success: function(data){
                alert("상품후기가 삭제되었습니다.");
                var pro_num = $("#pro_num").val();

                getPage("/review/" + pro_num + "/" + replyPage);
            }
        });
    } else{}
}