<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link href='${cPath}/resources/fullcalendar-5.10.1/lib/main.css' rel='stylesheet' />
<script src='${cPath}/resources/fullcalendar-5.10.1/lib/main.js'></script>
<script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
document.addEventListener('DOMContentLoaded', function() {
$(function(){
	var request = $.ajax({
	url: "${cPath}/calendar.do",
	method: "GET",
	dataType: "json"
});
 
request.done(function( data ) {
	console.log(data);
	
	var calendarEl = document.getElementById('calendar');

	var calendar = new FullCalendar.Calendar(calendarEl, {
		initialView: 'dayGridMonth',
		headerToolbar: {
		left: 'prev,next today',
		center: 'title',
		right: 'dayGridMonth,timeGridWeek,timeGridDay'
	},
	events: data,
	locale: "ko",
	navLinks: true, // can click day/week names to navigate views 
	selectable: true, 
	dayMaxEvents: true,
	selectMirror: true, // ì´ë²¤í¸ëª : function(){} : ê° ë ì§ì ëí ì´ë²¤í¸ë¥¼ íµí´ ì²ë¦¬í  ë´ì©..
	select: function(arg) {
			var title = prompt('ìë ¥í  ì¼ì :'); // title ê°ì´ ììë, íë©´ì calendar.addEvent() jsoníìì¼ë¡ ì¼ì ì ì¶ê° 
			if (title) { 
				calendar.addEvent({ 
					title: title, 
					start: arg.start, 
					end: arg.end, 
				}) 
			} 
			calendar.unselect() 
			}, 
// 			eventClick: function(arg) { // ìë ì¼ì  í´ë¦­ì,
// 				if (confirm('ë±ë¡ë ì¼ì ì ì­ì íìê² ìµëê¹?')) {
// 					arg.event.remove() 
// 				} 
// 			}
		});
		calendar.render();								
		});
		request.fail(function( jqXHR, textStatus ) {
			alert( "Request failed: " + textStatus );
		});
	});
});
</script>
<title>학사 일정</title>
  <div id='calendar'></div>