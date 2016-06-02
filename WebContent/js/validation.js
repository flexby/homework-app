function validateRecForm(){
	bool1 = textValidation('id_name','id_name_label');
	bool2 = textValidation('id_surname','id_surname_label');
	bool3 = textValidation('id_middle_name','id_middle_name_label');
	bool4 = textValidation('id_project_name','id_project_name_label');
	bool5 = courseRecValidation('id_rating','id_rating_label');
	bool6 = textValidation('id_teatcher_name','id_teatcher_name_label');
	bool7 = textValidation('id_teatcher_surname','id_teatcher_surname_label');
	bool8 = textValidation('id_teatcher_middle_name','id_teatcher_middle_name_label');

	if (bool1 && bool2 && bool3 && bool4 && bool5 && bool6 && bool7 && bool8){
		return true;
	}
	else return false;
}

function validateForm(){
	bool1 = textValidation('id_name','id_name_label');
	bool2 = textValidation('id_surname','id_surname_label');
	bool3 = textValidation('id_passphrase','id_passphrase_label');
	bool4 = ageValidation('id_age','id_age_label');
	bool5 = courseValidation('id_course','id_course_label');
	bool6 = ratingValidation('id_rating','id_rating_label');
	bool7 = teacherValidation('id_teacher','id_teacher_label');
	bool8 = findCourseValidation('id-find-course','id-find-course-label');
	
	
		if (bool1 && bool2 && bool3 && bool4 && bool5 && bool6 && bool7 && bool8){
			return true;
	}
	else return false;
}

function textValidation(fieldId,labelId) {
	if (document.getElementById(fieldId).value == "") {
		document.getElementById(labelId).className = "js-label-vis";
		return false;
	}
	else{
		var str = document.getElementById(fieldId).value;
		str = str.replace(/\s+/g, '');
		if (str == "") {
			document.getElementById(labelId).className = "js-label-vis";
			return false;
		}
		document.getElementById(labelId).className = "js-label";
	}
	return true;
	
}
function ageValidation(fieldId,labelId){
	if (document.getElementById(fieldId).value == "") {
		if (document.getElementById(fieldId).value < 1 || document.getElementById(fieldId).value > 120) {
			document.getElementById(labelId).className = "js-label-vis";
			return false;
		}
	}
	else{
		document.getElementById(labelId).className = "js-label";
	}
	return true;
}
function courseValidation(fieldId,labelId){
	var chk = 0;
	for (var i=0; i < document.getElementById(fieldId).length;i++){
		if (document.getElementById(fieldId).options[i].selected){
			chk++;
		}
	}
	if (chk==0){
		document.getElementById(labelId).innerHTML = 'Select one of the list';
		document.getElementById(labelId).className = "js-label-vis";
		return false;
	}
	else{
		document.getElementById(labelId).innerHTML = 'Course';
		document.getElementById(labelId).removeAttribute("class");
		return true;
	}	

}
function teacherValidation(fieldId,labelId){
	var chk = 0;
	for (var i=0; i < document.getElementById(fieldId).length;i++){
		if (document.getElementById(fieldId).options[i].selected){
			chk++;
		}
	}
	
	if (chk==0){
		document.getElementById(labelId).innerHTML = 'Select one of the list';
		document.getElementById(labelId).className = "js-label-vis";
		return false;
	}
	else{
		document.getElementById(labelId).innerHTML = 'Teacher';
		document.getElementById(labelId).removeAttribute("class");
		return true;
	}	
}
function ratingValidation(fieldId,labelId){
	var chk = 0;
	for (var i=0; i < document.getElementById(fieldId).length;i++){
		if (document.getElementById(fieldId).options[i].selected){
			chk++;
		}
	}
	
	if (chk==0){
		document.getElementById(labelId).innerHTML = 'Select one of the list';
		document.getElementById(labelId).className = "js-label-vis";
		return false;
	}
	else{
		document.getElementById(labelId).innerHTML = 'Rating';
		document.getElementById(labelId).removeAttribute("class");
		return true;
	}	

}
function checkValidation() {
	if (document.getElementById('id_off_validation').checked) {
		document.getElementById('id_taskOneForm').removeAttribute("onsubmit");
	} else {
		document.getElementById('id_taskOneForm').onsubmit = validateForm();
	}
}
function anotherCourses() {
	document.getElementById('id_another_course').innerHTML = "";
	var selIndex = document.getElementById("id_course").selectedIndex;
	for (var i = 0; i < document.getElementById("id_course").length; i++) {
		if (i != selIndex) {
			var opt = document.createElement('option');
			opt.text = document.getElementById("id_course")[i].text;
			document.getElementById("id_another_course").appendChild(opt);
		}
	}
}
function otherTextOn() {
	if (document.getElementById('id-find-6').checked) {
		document.getElementById('id-other-text').disabled = false;
	} else
		document.getElementById('id-other-text').disabled = true;

}
function findCourseValidation(fieldId,labelId){
	var chk = 0;
	var inputEements = document.getElementById(fieldId).getElementsByTagName('input');
	for (var i=0; i < inputEements.length;i++){
		if (inputEements[i].checked){
			chk++;
		}
	}
	if (chk==0){
		document.getElementById(labelId).innerHTML = 'Select one of the list';
		document.getElementById(labelId).className = "js-label-vis";
		return false;
	}
	else{
		document.getElementById(labelId).innerHTML = 'How did you find this course?';
		document.getElementById(labelId).removeAttribute("class");
		return true;
	}	
}
function courseRecValidation(fieldId,labelId){
	var chk = 0;
	for (var i=0; i < document.getElementById(fieldId).length;i++){
		if (document.getElementById(fieldId).options[i].selected){
			chk++;
		}
	}
	if (chk==0){
		document.getElementById(labelId).innerHTML = 'Выберите оценку';
		document.getElementById(labelId).className = "js-label-vis";
		return false;
	}
	else{
		document.getElementById(labelId).innerHTML = 'Оценка курса';
		document.getElementById(labelId).removeAttribute("class");
		return true;
	}

}
function checkArhivation() {
	if (document.getElementById('id_arhivation').checked) {
		document.getElementById('id-archive-jar').disabled = false;
		document.getElementById('id-archive-zip').disabled = false;
	} else {
		document.getElementById('id-archive-jar').disabled = true;
		document.getElementById('id-archive-zip').disabled = true;
	}
}
