

$(document).ready(function(){
$('#registration-row').attr( "style", "display: none !important;" );
$('#register').on('click', function(){
    $('#log-in-row').attr( "style", "display: none !important;" );
    $('#registration-row').attr( "style", "display: flex !important;" );
});
$('#btn-back').click(function(){
    $('#log-in-row').attr( "style", "display: flex !important;" );
    $('#registration-row').attr( "style", "display: none !important;" );
})



})