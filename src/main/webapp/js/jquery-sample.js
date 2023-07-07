// jQuery模板代码：

(function($){
  $(function(){
    // 这里写你的代码
  })
})(jQuery);

(function(){
  // 这里写你的代码
})();

$.ajax({
   url: 'your_url', // 请求的URL
   method: 'GET', // 请求的HTTP方法，例如GET、POST、PUT等
   data: { param1: 'value1', param2: 'value2' }, // 发送到服务器的数据，可以是对象或字符串
   dataType: 'json', // 期望的响应数据类型，可以是'json'、'xml'、'text'等
   success: function(response) { // 请求成功时的回调函数
       // 在这里处理服务器返回的数据
   },
   error: function(xhr, status, error) { // 请求失败时的回调函数
       // 在这里处理错误
   }
});


xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
  if (xhr.readyState==4 && xhr.status==200) {
    var result = xhr.responseText;
  }
}
xhr.open("GET",url,true);
xhr.send(null);
//if post
xhr.open("POST",url,true);
xhr.send("user="+username+"&password="+password);
