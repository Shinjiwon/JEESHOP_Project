$(document).ready(function(){

    /* 리뷰영역 별점 클릭 시 */
    $("#star_grade a").click(function(){
        $(this).parent().children("a").removeClass("on"); // 별점의 on클래스 전부제거
        $(this).addClass("on").prevAll("a").addClass("on"); // 클릭한 별과, 그 앞 별까지 on클래스 추가
        return false;
    });
});