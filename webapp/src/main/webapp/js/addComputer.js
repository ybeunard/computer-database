$(function() {
 $("form[name='newComputer']").validate({
   
   rules: {
     
     computerName: "required"
     
   },
   
   messages: {
    computerName: "Veuillez entrer le nom de l'ordinateur",
   },
   
   submitHandler: function(form) {
     form.submit();
   }
 });
});