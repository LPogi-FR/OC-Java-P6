var  xmlhttp  = new XMLHttpRequest();
xmlhttp.open("GET","/users");
xmlhttp.responseType="json";
xmlhttp.send()

xmlhttp.unload = function (){
if(xmlhttp.status==200){
JSON.stringify(xmlhttp.response)
}
}
