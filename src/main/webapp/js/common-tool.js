function verifyDate(date){
	return /^\d{4}-\d{2}-\d{2}$/.test(date);
}

function dataToStr(d){
	var ts = Date.parse(d);
	var date = new Date(ts);
	return date.toLocaleString();
}

function getMyDate(str){
	var oDate = new Date(str),
		oYear = oDate.getFullYear(),
		oMonth = oDate.getMonth()+1,
		oDay = oDate.getDate(),
		// oHour = oDate.getHours(),
		// oMin = oDate.getMinutes(),
		// oSen = oDate.getSeconds(),
		// +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen)
		oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);//最后拼接时间
	return oTime;
};
//补0操作
function getzf(num){
	if(parseInt(num) < 10){
		num = '0'+num;
	}
	return num;
};