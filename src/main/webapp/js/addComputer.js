$(function() {
$("#discontinued").change(function() {
   var startDate = $("#introduced").val();
   var endDate = $("#discontinued").val();

   if ((Date.parse(endDate) <= Date.parse(startDate))) {
     alert("La date d interruption doit etre plus grande que la date d introduction");
     $("#discontinued").val("");
   }
});
$("#introduced").change(function() {
   var startDate = $("#introduced").val();
   var endDate = $("#discontinued").val();

   if ((Date.parse(endDate) <= Date.parse(startDate))) {
     alert("La date d introduction doit etre plus petite que la date d interruption");
     $("#introduced").val("");
   }
});
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