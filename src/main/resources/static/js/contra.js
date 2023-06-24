
const password = document.getElementById('password');
const icon = document.getElementById('icon');
var eye = document.getElementById('Eye');
eye.addEventListener("click", function showHide(){
    if(password.type === 'password'){
        password.setAttribute('type','text');
        icon.classList.add('hide')
         eye.style.opacity = 0.8;
    }
    else{
        password.setAttribute('type', 'password');
        icon.classList.remove('hide')
         eye.style.opacity = 0.2;
    }
})