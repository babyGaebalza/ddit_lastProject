/**
 * 
 */
$("#deleteBtn").on("click", function(event){
	event.preventDefault();
	
	$.confirm({
		title : '정말 삭제하시겠습니까?',
		content : '정말 삭제하시겠습니까?',
		buttons : {
			확인 : {
				btnClass : 'btn-blue',
				action : function(){
					$.alert({
						title : '삭제 처리됩니다.',
						content : '삭제 처리됩니다.'
					});
					$("#deleteBtnForm").submit();
				}
				
				
			},
			취소 : function(){
				$.alert({
					title : '취소되었습니다.',
					content : '취소되었습니다.'
				});
			}
		}
	});
	

		

	
});

$("#modifyBtn").on("click", function(event){
	event.preventDefault();
	
	$.confirm({
		title : '정말 수정하시겠습니까?',
		content : '정말 수정하시겠습니까?',
		buttons : {
			확인 : {
				btnClass : 'btn-blue',
				action : function(){
					$("#modifyBtnForm").submit();
				}
			},
			취소 : function(){
				$.alert({
					title : '취소되었습니다.',
					content : '취소되었습니다.'
				});
			}
		}
	});

})
