	var $ = jQuery;

	function deleteCompanies() {
		var filter = $("#companySearch").val().toUpperCase();
	    var withFilterObjects = $("#companiesForEach a");
	    
	    $("#companiesForEach a:not(:first)").hide();
        Array.from(withFilterObjects).forEach(function(element){
            
	    	if(element.text.toUpperCase().indexOf(filter) != -1){
	    		element.style.display= 'block';
	    	}
	    });
	}

	function setSpring(element){
	
        var id = $(element).attr("id");
        var name = $(element).attr("name");
		
        if($("#companyId").has("option")){
			$("#companyId").empty();	
		}
		
		$("#companyId").append("<option id='"+ id + "' value='"+ id + "'>${company.name}</option>");
		$("#companySearch").val(name);
		$("#dropdown").attr("class", "dropdown close");
	    
	}
	
	function onFocusDropDown(){
		$("#dropdown").attr("class", "dropdown open");
		$("#companiesForEach a").show();
	}