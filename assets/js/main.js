$(document).ready(function(){
    var limit = $('input[name="limit"]:checked').val();
    var apiResponse ="";


    function doApiRequest(type, fname, lname){
        var limit = $('input[name="limit"]:checked').val();
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
        }

    $.ajax({
        method: "GET",
       // url: "https://api.icndb.com/jokes/" + apiType + apiLimit + apiName,
       url: "https://api.icndb.com/jokes/",
       dataType: "json"
    }).done(function(response){
        console.log(response);
        getAllQuotes(response);
    }).fail(function(){
        console.log("Something went wrong")
    })
}

function getAllQuotes(data){

    for (i=0;i<data.value.length;i++){
 
    var template = $('#card-template').clone();
    template.attr('id', '');
    console.log(template.find('span').text(data.value[i].joke))
    var img =template.find('img');
    generateImage(img);
    $('#quote-container').append(template);
    template.show();
    }
}
function getPersonalisedQuote(data){

}

function getRandomQuote(data){

}

function convertToList(){

}
function convertToCard(){

}
function generateImage(img){
    var random = getRandomInt(4);
    var imgPath = "assets/images/"+random.toString()+".jpg";
    img.attr("src",imgPath);
    img.show();

}
function getRandomInt(max) {
    return 1+Math.floor(Math.random() * Math.floor(max));
  }

doApiRequest('random','Angel','Terziev')
console.log(apiResponse);
})