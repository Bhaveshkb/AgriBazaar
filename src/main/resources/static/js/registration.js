/**
 * 
 */

 function validation() {
							var First = document.getElementById('firstName').value;
							var Last = document.getElementById('lastname').value;
							var Mobile = document.getElementById('mobileno').value;
							var email = document.getElementById('username').value;
							var pass = document.getElementById('password').value;
	
							if(document.getElementById('farm').checked){
								
							}else if(document.getElementById('buy').checked){
								
							}else{
								document.getElementById("disp").innerHTML
                   				 = "Check any one CheckBox";
							}
							if (First == "") {
								document.getElementById('firstt').innerHTML = "Enter First Name";
								return false;
							}
							if (First.length < 2) {
								document.getElementById('firstt').innerHTML = "First Name Should contain atleast 2 Characters";
								return false;
							}
							if (!isNaN(First)) {
								document.getElementById('firstt').innerHTML = "Only Alphabets are allowed";
								return false;
							} else {
								document.getElementById('firstt').innerHTML = ""
							}

							if (Last == "") {
								document.getElementById('lastt').innerHTML = "Enter Last Name";
								return false;

							}
							if (Last.length < 4) {
								document.getElementById('lastt').innerHTML = "Last Name Should contain atleast 3 Characters";
								return false;
							}
							if (!isNaN(Last)) {
								document.getElementById('lastt').innerHTML = "Only Alphabets are allowed";
								return false;
							} else {
								document.getElementById('lastt').innerHTML = ""
							}

							if (Mobile == "") {
								document.getElementById('mobilee').innerHTML = "Please Enter Mobile No";
							}

							if (isNaN(Mobile)) {
								document.getElementById('mobilee').innerHTML = "Only Numbers are allowed";
								return false;
							}

							if (Mobile.length<10 || Mobile.length>10) {
								document.getElementById('mobilee').innerHTML = "Enter valid 10 digit number";
								return false;
							} else {
								document.getElementById('mobilee').innerHTML = ""
							}

							if (email == "") {
								document.getElementById('emaill').innerHTML = "Enter Email Address";
								return false;
							}
							if (email.indexOf('@') <= 0) {
								document.getElementById('emaill').innerHTML = "Invalid position of @";
								return false;
							}
							if (email.charAt(email.length - 4) != '.'
									&& email.charAt(email.length - 3) != '.') {
								document.getElementById('emaill').innerHTML = "Invalid position of (.)";
								return false;
							}

							else {
								document.getElementById('emaill').innerHTML = ""
							}

							if (pass == "") {
								document.getElementById('passs').innerHTML = "Enter Password";
							}
							if (pass.length < 8) {
								document.getElementById('passs').innerHTML = "Atleast 8 characters required";
								return false;
							}
							if (pass.search(/[0-9]/) == -1) {
								document.getElementById('passs').innerHTML = "Atleast 1 numeric character required";
								return false;
							}
							if (pass.search(/[a-b]/) == -1) {
								document.getElementById('passs').innerHTML = "Atleast 1 lowercase character required";
								return false;
							}
							if (pass.search(/[A-Z]/) == -1) {
								document.getElementById('passs').innerHTML = "Atleast 1 Uppercase character required";
								return false;
							}
							if (pass.search(/[!\@\#\$\%\&]/) == -1) {
								document.getElementById('passs').innerHTML = "Atleast 1 special  character required";
								return false;
							} else {
								document.getElementById('passs').innerHTML = ""
							}

						}