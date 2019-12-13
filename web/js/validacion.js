function validacion(){
  if (document.form.password.value=='gimenez' && document.form.login.value=='angel'){ 
        document.form.submit(); 
    } 
    else{ 
         alert("Los campos no pueden estar vacios."); 
    }
}
