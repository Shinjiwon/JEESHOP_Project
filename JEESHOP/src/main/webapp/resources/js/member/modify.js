$(document).ready(function(){

    var form = $("#modifyForm");

    // 이메일 인증 확인여부 변수
    var chkEmail = "true";


    /* 이메일 변경 시 이메일 인증 활성화 */
	$("#mb_email").change(function(){
		$("#mbEmailChk").show();
		chkEmail = "false";
	});
	

    // 이메일 인증 버튼 클릭
    $("#mbEmailChk").click(function(){
        
        var receiveEmail = $("#mb_email").val();

        if($("#mb_email").val()=="" || $("#mb_email").val()==null){
            $("#authcode_status").html("이메일을 입력하세요.");
            return;
        }

        $("#authcode_status").css("color", "grey");
        $("#authcode_status").html("인증코드 전송 중...");

        $.ajax({
            url: '/email/sendEmail',
            type: 'post',
            dataType: 'text',
            data: {receiveEmail: receiveEmail},
            success: function(data){
                $("#email_authcode").show();
                $("#authcode_status").css("color", "grey");
                $("#authcode_status").html("메일이 전송되었습니다. 이메일에서 인증코드 확인 후 입력해주세요. ");
            }
        });
    });

    /* 인증번호 확인 버튼 클릭 */
    $("#btn_checkAuthCode").click(function(){

        var code = $("#mbAuthcode").val();

        $.ajax({

            url: '/member/emailAuthcodeChk',
            type: 'post',
            dataType: 'text',
            data: {code: code},
            success: function(data){
                
                if(data == 'SUCCESS'){
                    $("#email_authcode").hide();
                    $("#authcode_status").css("color", "blue");
                    $("#authcode_status").html("인증되었습니다.");
                    $("#mb_email").attr("readonly", true);
                    $("#mbEmailChk").attr("disabled", true);
                    chkEmail = "true";
                    return;
                    
                } else {
					$("#email_authcode").hide();
					$("#authcode_status").css("color", "red");
					$("#authcode_status").html('인증 실패. 다시 시도해주세요.');
					return;
				}
            }
        });
    });

    /* 수정 버튼 클릭 */
    $("#btn_submit").click(function(){
        
        var mb_id = $("#mb_id");
        var mb_name = $("#mb_name");
        var mb_nick = $("#mb_nick");
        var mb_email = $("#mb_email");
        var mbAuthcode = $("#mbAuthcode");
        var mb_phone = $("#mb_phone");
        var mb_postcode = $("input[name='mb_postcode']");
        var mb_addr = $("input[name='mb_addr']");
        var mb_deaddr = $("input[name='mb_deaddr']");
        
        // 유효성 검사
        if(mb_name.val()=="" || mb_name.val()==null){
            alert("이름을 입력하세요.");
            mb_name.focus();
            return;

        } else if(mb_nick.val()=="" || mb_nick.val()==null){
            alert("닉네임을 입력하세요.");
            mb_nick.focus();
            return;

        } else if(mb_email.val()=="" || mb_email.val()==null){
            alert("이메일을 입력하세요.");
            mb_email.focus();
            return;

        } else if(chkEmail=="false" &&
        			(mbAuthcode.val()=="" || mbAuthcode.val()==null)){
            alert("이메일 인증코드를 입력하세요.");
            mbAuthcode.focus();
            return;

        } else if(chkEmail=="false"){
            alert("이메일 인증을 진행하세요.");
            $("#mbEmailChk").focus();
            return;

        } else if(mb_phone.val()=="" || mb_phone.val()==null){
            alert("휴대폰번호를 입력하세요.");
            mb_phone.focus();
            return;

        } else if(mb_postcode.val()=="" || mb_postcode.val()==null){
            alert("우편번호를 입력하세요.");
            mb_postcode.focus();
            return;

        } else if(mb_addr.val()=="" || mb_addr.val()==null){
            alert("주소를 입력하세요.");
            mb_addr.focus();
            return;

        } else if(mb_deaddr.val()=="" || mb_deaddr.val()==null){
            alert("상세주소를 입력하세요.");
            mb_deaddr.focus();
            return;
        } else{
            form.submit();
        }
    });

    /* 취소 버튼 클릭 */
    $("#btn_cancle").click(function(){
        var result = confirm("회원정보 수정을 취소하시겠습니까?");

        if(result){
            location.href="/";

        } else{}
    });

    /* 회원탈퇴 버튼 클릭 */
    /* session작업 없고 String mb_id로 값을 받을 때 → location.href="/member/delete?mb_id={값}"; 
       session 작업으로 아이디값을 가져왔을 때 → location.href="/member/delete"; 
    */
       
    $("#btn_delete").click(function(){

        var result_delete = confirm("회원탈퇴 하시겠습니까?");

        if(result_delete){
            location.href="/member/delete";

        } else{}
    });
});