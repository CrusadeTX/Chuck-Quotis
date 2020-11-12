/// <reference path="../typings/tsd.d.ts" /
var hasLName = false;
var hasFName = false;
$(document).ready(function(){
$("#random-quote-container").hide();
$("#personalised-quote-container").hide();

    function doApiRequest(type){       
        $.ajax({
           method: "GET",
           url: QueryBuilder(type),
           dataType: "json"
        }).done(function(response){
            //console.log(response);
            switch(type){
                case "random": getRandomQuote(response); break;
                case "": getAllQuotes(response); break;
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
    template.find('span').text(data.value[i].joke)
    var img =template.find('img');
    generateImage(img);
    $('#quote-container').append(template);
    template.show();
    }
}

function clearAllQuotes(){
 $("#quote-container").empty();
}
function getPersonalisedQuote(data){
    $("#result-personalised-quote").text(data.value.joke)
    $("#personalised-quote-container").show();
    $("#first-name").val("");
    $("#last-name").val("");
    $("#submit-personalised-quote").attr("disabled", true)


}

function getRandomQuote(data){
    $("#first-name").val("");
    $("#last-name").val("");
    $("#result-random-quote").text(data.value.joke)
    $("#random-quote-container").show();
    $("#submit-personalised-quote").attr("disabled", true)
}

function changeView(){
    var quotes = $("#quote-container>div");
    if(quotes.length>0){
        var images = $("#quote-container").find("img");
        debugger;
        if(quotes.hasClass("card")){
        quotes.removeClass("card");
        quotes.removeClass("m-2");
        quotes.removeClass("shadow");
        quotes.removeClass("card-quote");
        quotes.addClass("d-flex");
        quotes.addClass("border-top");
        quotes.addClass("w-100");
        images.addClass("thumbnail");
        images.removeClass("img-full-size");
        }
        else{
        quotes.addClass("card");
        quotes.addClass("m-2");
        quotes.addClass("shadow");
        quotes.addClass("card-quote");
        quotes.removeClass("d-flex");
        quotes.removeClass("border-top");
        quotes.removeClass("w-100");
        images.removeClass("thumbnail");
        images.addClass("img-full-size");
        }
       debugger;

        /*for(i=0;i<quotes.length;i++){
            if(quotes[i].classList.contains("card")){
            quotes[i].classList.remove("card");
            quotes[i].classList.remove("m-2");
            quotes[i].classList.remove("shadow");
            quotes[i].classList.remove("card-quote");
            quotes[i].classList.add("d-flex");
            quotes[i].classList.add("border-top");
            debugger;
            }
            else{
            quotes[i].classList.add("card");
            quotes[i].classList.add("m-2");
            quotes[i].classList.add("shadow");
            quotes[i].classList.add("card-quote");
            quotes[i].classList.remove("d-flex");
            quotes[i].classList.remove("border-top");
            }
            
        }
        for(i=0;i<images.length;i++){
            if(images[i].classList.contains("thumbnail")){
                images[i].classList.remove("thumbnail");
            }
            else
            {
                images[i].classList.add("thumbnail"); 
            }
        } */
    }
    
    else{
       var quote = $("#card-template");
       var img = $("#card-template>img");
       quote.removeClass("card");
       quote.removeClass("m-2");
       quote.removeClass("shadow");
       quote.removeClass("card-quote");
       quote.addClass("d-flex");
       quote.addClass("border-top");
       quote.addClass("w-100");
       img.addClass("thumbnail");
       img.removeClass("img-full-size");
       debugger;
    }
    debugger;
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
    hasFName=false;
    hasLName=false;
})
$("#first-name").on("keydown", function(){
    console.log(1);
    if($("#first-name").val().length>0){
        hasFName = true;
    }
    else{
        hasFName = false;
    }
    if(hasFName && hasLName){
        $("#submit-personalised-quote").removeAttr("disabled")
    }
    else{
        $("#submit-personalised-quote").attr("disabled", true)
    }
})
$("#last-name").on("keydown", function(){
    if($("#last-name").val().length>0){
        hasLName = true;
    }
    else{
        hasLName =false;
    }
    if(hasFName && hasLName){
        $("#submit-personalised-quote").removeAttr("disabled")
    }
    else{
        $("#submit-personalised-quote").attr("disabled", true)
    }



})
$("#first-name").on("keyup", function(){
    console.log(1);
    if($("#first-name").val().length>0){
        hasFName = true;
    }
    else{
        hasFName = false;
    }
    if(hasFName && hasLName){
        $("#submit-personalised-quote").removeAttr("disabled")
    }
    else{
        $("#submit-personalised-quote").attr("disabled", true)
    }
})
$("#last-name").on("keyup", function(){
    if($("#last-name").val().length>0){
        hasLName = true;
    }
    else{
        hasLName =false;
    }
    if(hasFName && hasLName){
        $("#submit-personalised-quote").removeAttr("disabled")
    }
    else{
        $("#submit-personalised-quote").attr("disabled", true)
    }

})
$("#change-view").click(function(){
    changeView();
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
            creds = "?firstName=" + fname + "&lastName=" + lname;
        }; break;
        default: apiLimit =""; break;
    }
    if(type === "random"){
        creds ="";
    }

    var query = "https://api.icndb.com/jokes/"+apiType+apiLimit+creds;
    debugger;
    return query;




}


})