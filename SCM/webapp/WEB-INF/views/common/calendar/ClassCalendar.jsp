<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<link href='${cPath}/resources/fullcalendar-5.10.1/lib/main.css' rel='stylesheet' />
<script src='${cPath}/resources/fullcalendar-5.10.1/lib/main.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<style>
body{
	width : 80%;
}
</style>
	
<script>
 	document.addEventListener('DOMContentLoaded', function() {

	$(function(){
	var request = $.ajax({
	url: "${cPath}/classcalendar.do",
	method: "GET",
	dataType: "json"
	}); 
 
	
	
	request.done(function( data ) {
	console.log("데이터 : ", data);
	
	var calendarEl = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(calendarEl, {
		headerToolbar: {
		left: 'prev,next today',
		center: 'title',
		right: 'timeGridWeek,timeGridDay'
		},
		initialView: 'timeGridWeek',
		locale: "ko",
		events: data, 
		navLinks: true, // can click day/week names to navigate views 
		selectable: true, 
 		dayMaxEvents: true,
		selectMirror: true, // ì´ë²¤í¸ëª : function(){} : ê° ë ì§ì ëí ì´ë²¤í¸ë¥¼ íµí´ ì²ë¦¬í  ë´ì©..
		select: function(arg) {
			var title = prompt('Event Title:'); // title ê°ì´ ììë, íë©´ì calendar.addEvent() jsoníìì¼ë¡ ì¼ì ì ì¶ê° 
			if (title) { 
				calendar.addEvent({ 
					title: title, 
					start: arg.start, 
					end: arg.end
				}) 
			} 
			calendar.unselect() 
			} 
		
		});
		calendar.render();								
		});
		request.fail(function( jqXHR, textStatus ) {
			alert( "Request failed: " + textStatus );
		});
	});
}); 

</script>
    
  <div id='calendar'></div>