document.getElementById('user-name-container').addEventListener('click', toggleClass);
document.getElementById('nav-form').addEventListener('click', setHiddenFieldValue);
document.getElementsByTagName('body')[0].addEventListener('click', removeClass);

function toggleClass() {
	document.getElementById('logout-container').classList.toggle('display-none');
	document.getElementById('logout-container').classList.toggle('display-block');
}

function removeClass() {
	if((event.srcElement.id != 'user-name-container') && (event.srcElement.className != 'user-name')) {
		document.getElementById('logout-container').classList.remove('display-block');
		document.getElementById('logout-container').classList.remove('display-none');
		document.getElementById('logout-container').classList.add('display-none');
	}
}

function setHiddenFieldValue(event) {
	var target = event.srcElement;
	document.getElementById('user-request').value = target.id;
}