	
//加载table数据(分页+自定义参数)
function loadListDataPaginator(paginatorInfo,parameter){
	var url = paginatorInfo["url"];
	var page = paginatorInfo["page"];
	var pageSize = paginatorInfo["page_size"];
	var id = paginatorInfo["id"];
	var para = "";
	for(var index in parameter){
		para += "&" + index + "=" + parameter[index];
	} 
	url += "?page=" + page + "&length=" + pageSize + "&" + para;
	$("#" + id).load(url);
}


//加载table数据(分页+自定义参数)POST方式
function loadListDataPaginatorPost (paginatorInfo,parameter){
	var url = paginatorInfo["url"];
	var page = paginatorInfo["page"];
	var pageSize = paginatorInfo["page_size"];
	var id = paginatorInfo["id"];
	parameter["page"] = page;
	parameter["length"] = pageSize;
	
	$.ajax({
		url : paginatorInfo["url"],
		method : 'POST',
		data : parameter,
		dataType : 'text',
		success : function (data){
			$("#" + id).html(data);
		},
		error : function(data){
			$("#" + id).html("数据载入错误");
		}
	});
		
}

function loadListDataPaginatorPostWithCallback (paginatorInfo,parameter,callback){
	var url = paginatorInfo["url"];
	var page = paginatorInfo["page"];
	var pageSize = paginatorInfo["page_size"];
	var id = paginatorInfo["id"];
	parameter["page"] = page;
	parameter["length"] = pageSize;
	
	$.ajax({
		url : paginatorInfo["url"],
		method : 'POST',
		data : parameter,
		dataType : 'text',
		success : function (data){
			$("#" + id).html(data);
			callback();
		},
		error : function(data){
			$("#" + id).html("数据载入错误");
		}
	});
	
	
}