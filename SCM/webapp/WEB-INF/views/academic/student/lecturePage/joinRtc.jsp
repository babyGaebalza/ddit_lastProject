<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="image/png" href="https://i.pinimg.com/originals/22/e4/e0/22e4e079c332b3f3589f4a8b73545076.png" rel="icon" style="color: tomato;"> 
<title>실시간 비대면 강의</title>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<style>
body{
        height: 100%;        
    }
@media screen and (max-width: 900px) {
    #form{
        width: 100%;
    }
  }
  
  /* Responsive layout - makes the two columns stack on top of each other instead of next to each other */
  @media screen and (max-width: 600px) {
    #form{
        width: 100%;
    }
  }
#form{
    height: 130px;
    width: 400px;
    overflow: hidden;
    margin: auto;
    align-content: center;
    text-align: center;
    padding: 20px 20px;
    text-align: center;
    
}
input:nth-child(odd){
    padding: 20px 20px;
}
input:nth-child(even){
    padding: 10px 20px;
    border: none;
}
input{
    position: relative;
    width: 100%;
    outline: none;
    margin-top: 10px;
    left: 0;
    right: 0;
    display: block;
}
</style>

<body>
<div id="header">
<h1  style="text-align: center;">실시간 비대면강의</h1>
</div>
<div id="form" style="background-color: cornsilk;height: auto;">
		<input type="hidden" value="${memId }" id="memId"  />
		<input type="hidden" value="${classNo }" id="classNo"/>
		<input type="hidden" value="${memAtt }" id="memAtt"/>
        <input type="hidden" name="roomId" id="value" readonly="readonly" value="${classNo }">
        <input type="button" value="화상강의 참여" id="submit" style="color: darkorange;background-color: aliceblue;font-size: 30px;">
       <!-- <input type="button" value="Create Room" id="newRoom"> -->
</div>

<script >
    
    var classNo = document.getElementById("classNo").value;
    var memId = document.getElementById("memId").value;
    var memAtt = document.getElementById("memAtt").value;
    
    var form = document.getElementById("submit")
    form.addEventListener('click', ()=>{
        const x = document.getElementById("value");
        if(classNo == ""){
            //alert("Room id is empty!!!")
            swal("Oops.. ","Room id is empty !!")
        }else{
        	
        	$.ajax({
        		url : $.getContextPath() + "/student/webRtc/attend.do",
        		data : {
        			"classNo" : classNo,
        			"memId" : memId,
        			"memAtt" : memAtt
        		},
        		dataType : "text",
        		success : function(res) {
        			console.log(res);
                	window.open("https://192.168.0.153:3000/"+classNo+"?memId="+memId);
        		},
        		error : function(xhr, errorResp, errorMessage) {
        			console.log(xhr);
        			console.log(errorResp);
        			console.log(errorMessage);
        		}

        	});
            //location.replace("https://videocallappwebrtc.herokuapp.com/"+x.value);
            
            x.value = "";
        }
    });

</script>
<em style="font-family: Georgia, 'Times New Roman', Times, serif;
 text-align: center; bottom: 0;
 position: absolute;
  margin: auto; right:0;left: 0;
   color: #333; font-size: x-small;">Developed by Alan R S</em>
</body>
</html>