var pageIndex = 1;//当前页
var pageSize = 10;//每页行数
var totalPageCount = 0;//总页数

$(function(){
    //解析并显示数据(查询数据)
    queryForPages();

    $("#goPage").click(goPage);
    //分页操作
    build_page_info();
});
//跳转
function goPage() {
    pageIndex = $("#index").val();
    if (pageIndex <= totalPageCount) {
        queryForPages();
    }
}

function queryForPages() {
    // var skip = pageIndex*pageSize-pageSize;

    var params = {
        pageNumber: pageIndex,
        pageSize: pageSize,
        cusNo: $('#cusNo').val(),
    }
    $.ajax({
        url:'/jee_web/test/orderServlet/list',
        type:'post',
        data: JSON.stringify(params),
        // contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
        dataType: "json",
        async: false,
        success: function (data){

            fillTable(data.objList)

            //加载总页数
            var totalCount = data.totalCount;
            totalPageCount = Math.floor((totalCount % pageSize === 0) ? (totalCount / pageSize) : (totalCount / pageSize + 1));
            $("#pageCount").text("总共"+totalPageCount+"页 "+"记录总数:"+totalCount+"条");

        },
        error: function (error) {

        }

    })

};

//填充数据
function fillTable(obj) {
    $("#dataList").empty();

    for (var i in obj) {
        var indx = parseInt(i)+1;
        var id = obj[i].id;
        var cusNo = !obj[i].customerNumber?"":obj[i].customerNumber;
        var age = !obj[i].age?"":obj[i].age;
        var amount = obj[i].amount;
        var orderTime = !obj[i].orderTime?"":dataToStr(obj[i].orderTime);

        var tr="<tr id="+id+">"
            +"<td class=\"text-center\">"+indx+"</td>"
            +"<td class=\"text-center\">"+cusNo+"</td>"
            +"<td class=\"text-center\">"+age+"</td>"
            +"<td class=\"text-center\">"+amount+"</td>"
            +"<td class=\"text-center\">"+orderTime+"</td>"
            +"<td class=\"text-center\"><button type=\"button\" class=\"btn btn-sm btn-info\" onclick='modiy(this)' data-bs-toggle=\"modal\" data-bs-target=\"#updateModal\">Modify</button></td>"
            +"</tr>";
        var $opt = $(tr);//将字符串转换成jQuery对象
        $("#dataList").append($opt);
    }
}

//显示分页
function build_page_info(){
    //首页
    $("#firstPage").click(function () {
        pageIndex = 1;
        $("#index").val(pageIndex);
        queryForPages();
    });

    //上一页
    $("#previous").click(function () {
        if (pageIndex > 1) {
            pageIndex--;
        }
        $("#index").val(pageIndex);
        queryForPages();
    });

    //下一页
    $("#next").click(function () {
        if (pageIndex < totalPageCount) {
            pageIndex++;
        }else if (pageIndex === totalPageCount){
            pageIndex = totalPageCount;
        }
        $("#index").val(pageIndex);
        queryForPages();
    });

    //尾页
    $("#last").click(function () {
        $("#index").val(totalPageCount);
        pageIndex = totalPageCount;
        queryForPages();
    });
};