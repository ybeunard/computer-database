$(function() {
$("#discontinued").change(function() {
   var startDate = document.getElementById("introduced").value;
   var endDate = document.getElementById("discontinued").value;

   if ((Date.parse(endDate) <= Date.parse(startDate))) {
     alert("La date d interruption doit etre plus grande que la date d introduction");
     document.getElementById("discontinued").value = "";
   }
});
$("#introduced").change(function() {
   var startDate = document.getElementById("introduced").value;
   var endDate = document.getElementById("discontinued").value;

   if ((Date.parse(endDate) <= Date.parse(startDate))) {
     alert("La date d introduction doit etre plus petite que la date d interruption");
     document.getElementById("discontinued").value = "";
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