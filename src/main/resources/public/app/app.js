$( document ).ready(function() {



    function update() {
        document.getElementById('img1').src = "/rest/image.png?random="+new Date().getTime();
    }


    window.setInterval(update, 1000);

});
