<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

	<script src="https://code.jquery.com/jquery-3.3.1.js" type="text/javascript"></script>
	<script src="/jsscripts/jquery.autocomplete.min.js"></script>

</head>
<body>
	<h2>Spring MVC + jQuery + Autocomplete example</h2>

	<div>
		<input type="text"  id="w-input-search" value="">
		<span>
			<button id="w-button-search" type="button">Search</button>
		</span>
	</div>
	
	<script>
	$(document).ready(function() {

		$('#w-input-search').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getTags',
			paramName: "tagName",
			delimiter: ",",
		    transformResult: function(response) {
		    	
		        return {
		        	
		            suggestions: $.map($.parseJSON(response), function(item) {
		            	
		                return { value: item.username, data: item.userId };
		            })
		            
		        };
		        
		    }
		    
		});
		
		
	});
	</script>
	
</body>
</html>