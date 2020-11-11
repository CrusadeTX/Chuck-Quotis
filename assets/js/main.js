/// <reference path="../typings/tsd.d.ts" /
$(document).ready(function(){
    //var templateList =[];
$("#random-quote-container").hide();
$("#personalised-quote-container").hide();

    function doApiRequest(type){       
        $.ajax({
            method: "GET",
           // url: "https://api.icndb.com/jokes/" + apiType + apiLimit + apiName,
           url: QueryBuilder(type),
           dataType: "json"
        }).done(function(response){
            debugger;
            console.log(response);
            debugger;
            switch(type){
                case "random": getRandomQuote(response); break;
                case "":  getAllQuotes(response); break;
                case "personalised": getPersonalisedQuote(response); break;
            }
        }).fail(function(){
            console.log("Something went wrong")
        })
       /* var limit = $('input[name="limit"]:checked').val();
        var apiType="";
        var apiName ="";
        var apiLimit="";
        switch(type){
            case "random" : apiType = "random"; break;
            case "multiple": apiType = "";break;
            default : apiType="random";break;
        }
        if (type === "random"){
            if(limit !== null && limit!==""){
                switch(limit){
                    case "nerdy": apiLimit ="?limitTo=[nerdy]"; break;
                    case "explicit": apiLimit ="?limitTo=[explicit]"; break;
                    case "all": apiLimit =""; break;
                    default: apiLimit =""; break;
                }
            }
            else{
                apiLimit="";
            }
        }
        else{
            if (fname !== null && lname !== null){
                if(limit ==="all"){
                    apiName = "?firstName=" + fname + "&lastName=" + lname;    
                }
                else{
                    apiName = "firstName=" + fname + "&lastName=" + lname;   
                }
            }
            else{
                apiName="";
            }
        }*/
}
function getAllQuotes(data){
    clearAllQuotes()
    for (i=0;i<data.value.length;i++){
    var template = $('#card-template').clone();
    template.attr('id', '');
    console.log(template.find('span').text(data.value[i].joke))
    var img =template.find('img');
    generateImage(img);
    $('#quote-container').append(template);
    template.show();
    //templateList.push(template);
    }
}

function clearAllQuotes(){
 $("#quote-container").empty();
 debugger

}
function getPersonalisedQuote(data){
    $("#result-personalised-quote").text(data.value.joke)
    debugger;
    $("#personalised-quote-container").show();
    debugger;


}

function getRandomQuote(data){
    $("#result-random-quote").text(data.value.joke)
    debugger
    $("#random-quote-container").show();
    debugger
}

function convertToList(){

}
function convertToCard(){

}
function generateImage(img){
    var random = getRandomInt(7);
    var imgPath = "assets/images/"+random.toString()+".jpg";
    img.attr("src",imgPath);
    img.show();

}
function getRandomInt(max) {
    return 1+Math.floor(Math.random() * Math.floor(max));
  }

$('input[name="limit"]').click(function(){
  doApiRequest("")
})
$("#submit-random-quote").click(function(){
    doApiRequest("random")
})
$("#submit-personalised-quote").click(function(){
    doApiRequest("personalised")
})

function QueryBuilder(type){
    var fname="";
    var lname="";
    var limit="";
    var creds="";
    var apiLimit="";
    var apiType="";

    if(type === "random" || type ==="personalised"){
    apiType ="random";
    fname = $("#first-name").val();
    lname = $("#last-name").val();
    if(fname !=="" && lname !==""){
        creds = "firstName=" + fname + "&lastName=" + lname;
    }
    }
    else{
    apiType="";
    creds="";;
    }
   var limit = $('input[name="limit"]:checked').val();
   if(limit === undefined){
       limit="all";
   }
    switch(limit){
        case "nerdy": apiLimit ="?limitTo=[nerdy]";
        if(fname !=="" && lname !=="" && (type==="random" || type==="personalised")){
            creds = "&firstName=" + fname + "&lastName=" + lname};
         break;
        case "explicit": apiLimit ="?limitTo=[explicit]";
        if(fname !=="" && lname !=="" && (type==="random" || type==="personalised")){
            creds = "&firstName=" + fname + "&lastName=" + lname}; 
        break;
        case "all": apiLimit ="";
        if(fname !=="" && lname !=="" && (type==="random" || type==="personalised")){
            console.log("i am here man")
            creds = "?firstName=" + fname + "&lastName=" + lname;
        }; break;
        default: apiLimit =""; break;
    }

    var query = "https://api.icndb.com/jokes/"+apiType+apiLimit+creds;
    debugger;
    return query;




}
})