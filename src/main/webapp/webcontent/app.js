setInterval(function(){
	$(".items").animate({ scrollLeft: $(this).width()},20000,'linear').animate({scrollLeft: 0}, 20000, 'linear');  
		
}, 2000);
