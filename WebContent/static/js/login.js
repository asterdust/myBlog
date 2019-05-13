var timer = document.getElementById("timer"),
    play = document.getElementById("play"),
    time = 10,
    pause = false;

function slideshow() {
    var lock = false;
    var slideshow = document.getElementById("background"),
        imgs = slideshow.getElementsByTagName("img"),
        current = 0;

    function slideOff() {
        imgs[current].className="";
    }

    function slideOn() {
        imgs[current].className="active";
    }

    function changeSlide() {
        if (lock) return;
        lock = !lock;
        slideOff();
        current++;
        if(current>=imgs.length) current = 0;
        slideOn();
        setTimeout(function(){
            imgs[current + 1 >= imgs.length ? 0 : current + 1]
                .setAttribute("src", "/random_images?salt=" + Math.random());
        },1000);
        lock = !lock;
    }

    function timecheck () {
        if (pause) return;
        if (!lock) time -= 1;
        if (time <= -1) {
            time = 10;
            changeSlide();
        }
        timer.innerText = time;
    }
    imgs[1].setAttribute("src", "/random_images?salt=" + Math.random());
    setInterval(timecheck, 1000);
}

slideshow();

function refresh() {
    time = -1;
}

function swap () {
    if (pause) {
        play.setAttribute("src", "/static/img/pause.png");
    }
    else {
        play.setAttribute("src", "/static/img/play.png");
    }
    pause = !pause;
}

function checkInput() {
    var password = document.getElementById('password');

    password.value = sha256(password.value);
    console.log(password.value);
    return true;
}