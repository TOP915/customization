var $cusConfirm = $cusConfirm  || (function(){
    var cusConfirm = {};
    cusConfirm.alert = function (cotent){
        $.alert({
            title:'error',
            content: cotent,
            boxWidth: '15%',
            useBootstrap: false,
        });
    }
    return cusConfirm;
})();

var _page = _page || (function () {
    var _page = {};
    _page.pageQuery = function (pageNum) {
        if(parseInt(pageNum) == 0 ) return false;
        $("#_pageNum").val(pageNum);
        $("#_pageForm").submit();
    }
    return _page;
})();

var _user_opt = _user_opt || (function () {
    var _user_opt = {};
    _user_opt.add = function (formId) {
        $("#"+formId).submit();
    }
    _user_opt.checkAccount = function (url,account,_accountInfo) {
        $.ajax({
            url:url,
            type:"POST",
            data:{
                "account":account
            },
            success:function (backData) {
                $("#_accountInfo").text("");
                if(parseInt(backData.OPT_CODE) == 0)
                {
                    $("#_accountInfo").text("账户已存在！");
                    $("#_accountInfo ").prev().val("");
                }
            },
            error:function () {
                $cusConfirm.alert("请求错误！");
            }
        });
    }
    return _user_opt;
})();

var changeTab = function (liId) {
    var leftOb = window.parent.frames["leftFrame"];
    leftOb.$("#"+liId).click();
}