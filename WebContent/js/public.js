


var search = document.getElementById('search');
search.onfocus = function searchFocus() {
	document.getElementById('search').style.width = "200px";
	document.getElementById('search').placeholder = "请输入查询条件";
}
search.onblur = function searchBlur() {
	document.getElementById('search').style.width = "150px";
	document.getElementById('search').placeholder = "Search...";
}