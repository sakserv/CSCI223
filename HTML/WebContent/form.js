function submitForm(form){
	
	var error = document.getElementById("error");
	
	var name = document.comment_form.name.value;
	var email = document.comment_form.email.value;
	var comments = document.comment_form.comments.value;
	
	if (name == '') {
		error.innerHTML = "Name is a required field.";
		error.style.display = "block";
	} else if (email == '') {
		error.innerHTML = "Email is a required field.";
		error.style.display = "block";
	} else if (comments == '') {
		error.innerHTML = "Comments is a required field.";
		error.style.display = "block";
	} else {	
	    formdiv = document.getElementById("formwrap");
	    formdiv.style.textAlign = "center";
	    document.getElementById("submit").style.display = "none";
	    formdiv.innerHTML = "Thanks for the feedback " + name + "!!";
	}
}