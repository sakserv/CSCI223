function submitForm(form){
	
	var error = document.getElementById("error");
	
	var name = document.comment_form.name.value;
	var email = document.comment_form.email.value;
	var comments = document.comment_form.comments.value;
	var age = document.comment_form.age.value;
	var ageDiv = document.getElementById("age");
	
	if (name == '') {
		error.innerHTML = "Name is a required field.";
		error.style.display = "block";
	} else if (email == '') {
		error.innerHTML = "Email is a required field.";
		error.style.display = "block";
	} else if (comments == '') {
		error.innerHTML = "Comments is a required field.";
		error.style.display = "block";
	} else if (age == '' && ageDiv.style.display == "block"){
		error.innerHTML = "You indicated you are over 21. Age is required";
		error.style.display = "block";
	} else {
		document.comment_form.action = 'results.html';
		document.comment_form.submit();
	}
}

function showAgeField(){
	var ageField = document.getElementById("age");
	ageField.style.display = "block";
}

function displayResults() {
	
	queryString = window.location.search;
	document.getElementById("formResp").innerHTML = "Thanks for your submission";
	document.getElementById("formName").innerHTML = "Name: " + queryString.substring(1).split("&")[0].split("=")[1];
	document.getElementById("formEmail").innerHTML = "Email: " + queryString.substring(1).split("&")[1].split("=")[1];
	document.getElementById("formComments").innerHTML = "Comments: " + queryString.substring(1).split("&")[2].split("=")[1];
	
	var ageVal = queryString.substring(1).split("&")[4].split("=")[1];
	if (ageVal != '') {
		document.getElementById("formAge").innerHTML = "Age: " + ageVal;
	}
}